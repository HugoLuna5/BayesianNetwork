/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bayesiannetwork;

import Controller.HomeController;
import View.HomeView;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import mdlaf.MaterialLookAndFeel;

/**
 *
 * @author Hugo Luna
 */
public class BayesianNetwork {

     public BayesianNetwork() {
        initMaterial();
        launchScreen();
    }
    
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        new BayesianNetwork();

    }
     
    public void initMaterial() {
        try {
            UIManager.setLookAndFeel(new MaterialLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
    
    
    public void launchScreen() {
       
        new HomeController(new HomeView());

      
    }
    
    
}
