/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.AlarmView;
import View.HeartAttackView;
import View.HomeView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Hugo Luna
 */
public class HomeController {
    
    private HomeView homeView;
    
    public HomeController(HomeView homeView){
        this.homeView = homeView;
        homeView.setVisible(true);
        events();
    }
    
    
    public void events(){
        
        homeView.btnActionShowAlarm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                homeView.setVisible(false);
                new AlarmController(new AlarmView());
            }
        });
        
         homeView.btnActionShowHeartAttack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                homeView.setVisible(false);
                new HeartAttackController(new HeartAttackView());
            }
        });
        
    }
    
}
