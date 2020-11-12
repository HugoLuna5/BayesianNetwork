/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Models.Alarm;
import View.AlarmView;
import View.HomeView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Hugo Luna
 */
public class AlarmController {

    private AlarmView alarmView;
    private Alarm alarmModel;

    public AlarmController(AlarmView alarmView) {
        this.alarmView = alarmView;
        alarmView.setVisible(true);
        events();
    }

    public void events() {

        alarmView.btnCalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                actionCalculateProbability();

            }
        });

        alarmView.btnGoToBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                alarmView.setVisible(false);
                new HomeController(new HomeView());
            }
        });

    }

    private void actionCalculateProbability() {
        alarmModel = new Alarm();
        alarmModel.setStatusJuan(alarmView.callMeJuan.isSelected());
        alarmModel.setStatusMaria(alarmView.callMeMaria.isSelected());
        alarmModel.showMessageResponse(alarmView);
    }
}
