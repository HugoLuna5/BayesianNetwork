/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import View.HeartAttackView;
import javabayes.Helpers.BayesNetHelper;
import javabayes.InferenceGraphs.InferenceGraph;
import javabayes.InferenceGraphs.InferenceGraphNode;
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;

/**
 *
 * @author Hugo Luna
 */
public class HeartAttack {

    private double belief;
    InferenceGraph inferenceRoot = new InferenceGraph();
    InferenceGraphNode Deporte = BayesNetHelper.createNode(inferenceRoot, "deporte", "si", "no");
    InferenceGraphNode Alimentacion = BayesNetHelper.createNode(inferenceRoot, "alimentacion", "equilibrada", "noequilibrada");
    InferenceGraphNode Presion = BayesNetHelper.createNode(inferenceRoot, "presion", "alta", "normal");
    InferenceGraphNode Fumador = BayesNetHelper.createNode(inferenceRoot, "fumador", "si", "no");
    InferenceGraphNode Infarto = BayesNetHelper.createNode(inferenceRoot, "infarto", "si", "no");

    public HeartAttack() {
        inferenceRoot.create_arc(Deporte, Presion);
        inferenceRoot.create_arc(Alimentacion, Presion);
        inferenceRoot.create_arc(Presion, Infarto);
        inferenceRoot.create_arc(Fumador, Infarto);

        BayesNetHelper.setProbabilityValues(Deporte, 0.1, 0.9);
        BayesNetHelper.setProbabilityValues(Alimentacion, 0.4, 0.6);
        BayesNetHelper.setProbabilityValues(Fumador, 0.4, 0.6);

        BayesNetHelper.setProbabilityValues(Presion, "equilibrada", "si", 0.01, 0.99);
        BayesNetHelper.setProbabilityValues(Presion, "noequilibrada", "si", 0.2, 0.8);
        BayesNetHelper.setProbabilityValues(Presion, "equilibrada", "no", 0.25, 0.75);
        BayesNetHelper.setProbabilityValues(Presion, "noequilibrada", "no", 0.7, 0.3);

        BayesNetHelper.setProbabilityValues(Infarto, "alta", "si", 0.8, 0.2);
        BayesNetHelper.setProbabilityValues(Infarto, "normal", "si", 0.6, 0.4);
        BayesNetHelper.setProbabilityValues(Infarto, "alta", "no", 0.7, 0.3);
        BayesNetHelper.setProbabilityValues(Infarto, "normal", "no", 0.3, 0.7);
    }

    public void setStatusSport(boolean status) {
        if (status) {
            Deporte.set_observation_value("si");
            belief = BayesNetHelper.getBelief(inferenceRoot, Infarto);
            System.out.println(" deporte: " + belief);
        } else {
            Deporte.set_observation_value("no");
            belief = BayesNetHelper.getBelief(inferenceRoot, Infarto);
            System.out.println("no deporte: " + belief);
        }
       

    }

    public void setStatusBalancedFeeding(boolean status) {
        if (status) {
            Alimentacion.set_observation_value("equilibrada");
            belief = BayesNetHelper.getBelief(inferenceRoot, Infarto);
            System.out.println("alimentacion equilibrada: " + belief);
        } else {
            Alimentacion.set_observation_value("noequilibrada");
            belief = BayesNetHelper.getBelief(inferenceRoot, Infarto);
            System.out.println("alimentacion no equilibrada: " + belief);
        }
        
    }

    public void setStatusNormalBloodPressure(boolean status) {
        if (status) {
            Presion.set_observation_value("normal");
            belief = BayesNetHelper.getBelief(inferenceRoot, Infarto);
            System.out.println("normal : " + belief);
        } else {
            Presion.set_observation_value("alta");
            belief = BayesNetHelper.getBelief(inferenceRoot, Infarto);
            System.out.println("alta : " + belief);
        }
        
    }

    public void setStatusSmokerActivated(boolean status) {
        if (status) {
            Fumador.set_observation_value("si");
            belief = BayesNetHelper.getBelief(inferenceRoot, Infarto);
            System.out.println("fume : " + belief);
        } else {
            Fumador.set_observation_value("no");
            belief = BayesNetHelper.getBelief(inferenceRoot, Infarto);
            System.out.println(" nofume : " + belief);
        }
        
    }

 
    public void showMessageResponse(HeartAttackView heartAttackView) {

        belief = BayesNetHelper.getBelief(inferenceRoot, Infarto);

        String message = (belief * 100) + "%";
        JOptionPane.showMessageDialog(heartAttackView, message, "Probabilidad de infarto", JOptionPane.INFORMATION_MESSAGE);
       
    }

}
