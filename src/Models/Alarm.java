/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import View.AlarmView;
import javabayes.Helpers.BayesNetHelper;
import javabayes.InferenceGraphs.InferenceGraph;
import javabayes.InferenceGraphs.InferenceGraphNode;
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;

/**
 *
 * @author Hugo Luna
 */
public class Alarm {

    private double belief;
    private double belief1;
    InferenceGraph inferenceRoot = new InferenceGraph();

    InferenceGraphNode Robo = BayesNetHelper.createNode(inferenceRoot, "robo", "robo", "norobo");
    InferenceGraphNode Terremoto = BayesNetHelper.createNode(inferenceRoot, "terremoto", "terremoto", "noterremoto");
    InferenceGraphNode Alarma = BayesNetHelper.createNode(inferenceRoot, "alarma", "alarma", "noalarma");
    InferenceGraphNode Juanllama = BayesNetHelper.createNode(inferenceRoot, "juanllama", "alarma", "noalarma");
    InferenceGraphNode Mariallama = BayesNetHelper.createNode(inferenceRoot, "mariallama", "alarma", "noalarma");

    public Alarm() {
        inferenceRoot.create_arc(Robo, Alarma);
        inferenceRoot.create_arc(Terremoto, Alarma);
        inferenceRoot.create_arc(Alarma, Juanllama);
        inferenceRoot.create_arc(Alarma, Mariallama);

        //asignar los valores  de las elecciones
        BayesNetHelper.setProbabilityValues(Robo, 0.001, 0.999);
        BayesNetHelper.setProbabilityValues(Terremoto, 0.002, 0.998);

        BayesNetHelper.setProbabilityValues(Alarma, "robo", "terremoto", 0.95, 0.05);
        BayesNetHelper.setProbabilityValues(Alarma, "robo", "noterremoto", 0.94, 0.06);
        BayesNetHelper.setProbabilityValues(Alarma, "norobo", "terremoto", 0.29, 0.71);
        BayesNetHelper.setProbabilityValues(Alarma, "norobo", "noterremoto", 0.001, 0.999);

        BayesNetHelper.setProbabilityValues(Juanllama, "alarma", 0.90, 0.1);
        BayesNetHelper.setProbabilityValues(Juanllama, "noalarma", 0.05, 0.95);

        BayesNetHelper.setProbabilityValues(Mariallama, "alarma", 0.70, 0.30);
        BayesNetHelper.setProbabilityValues(Mariallama, "noalarma", 0.01, 0.99);

        Alarma.set_observation_value("terremoto");
        Alarma.set_observation_value("robo");

        System.out.println("Nivel 1:");
        belief = BayesNetHelper.getBelief(inferenceRoot, Terremoto);
        System.out.println("La probabilidad de que suceda terremoto:" + belief);
        belief1 = BayesNetHelper.getBelief(inferenceRoot, Robo);
        System.out.println("La probabilidad de que suceda un robo:" + belief1);
        System.out.println("");
    }

    public void setStatusMaria(boolean status) {
        if (status) {
            Mariallama.set_observation_value("alarma");
        } else {
            Mariallama.set_observation_value("noalarma");
        }
    }

    public void setStatusJuan(boolean status) {
        if (status) {
            Juanllama.set_observation_value("alarma");
        } else {
            Juanllama.set_observation_value("noalarma");
        }
    }

    public void showMessageResponse(AlarmView alarmView) {
        belief = BayesNetHelper.getBelief(inferenceRoot, Terremoto);
        belief1 = BayesNetHelper.getBelief(inferenceRoot, Robo);

        String message = (belief * 100) + "% de terremoto\n" + (belief1 * 100) + "% de robo";
        JOptionPane.showMessageDialog(alarmView, message, "Probabilidad", JOptionPane.INFORMATION_MESSAGE);

    }

}
