/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Models.HeartAttack;
import View.HeartAttackView;
import View.HomeView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Hugo Luna
 */
public class HeartAttackController {

    private HeartAttackView heartAttackView;
    private HeartAttack heartAttackModel;

    public HeartAttackController(HeartAttackView heartAttackView) {
        this.heartAttackView = heartAttackView;
        heartAttackView.setVisible(true);
        events();
    }

    private void events() {
        heartAttackView.btnCalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                actionCalculateProbability();
            }
        });

        heartAttackView.btnGoToBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                heartAttackView.setVisible(false);
                new HomeController(new HomeView());
            }
        });

    }

    private void actionCalculateProbability() {
        heartAttackModel = new HeartAttack();

        heartAttackModel.setStatusSport(heartAttackView.statusSport.isSelected());

        heartAttackModel.setStatusBalancedFeeding(heartAttackView.statusNutrition.isSelected());

        heartAttackModel.setStatusNormalBloodPressure(heartAttackView.statusBloodPressure.isSelected());

        heartAttackModel.setStatusSmokerActivated(heartAttackView.statusSmoker.isSelected());

        heartAttackModel.showMessageResponse(heartAttackView);
    }

}
