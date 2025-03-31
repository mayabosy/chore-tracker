/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.kf5012assessment;

import java.util.ArrayList;

/**
 *
 * @author w21023500
 */
// Main Class for running the system
public class HouseholdChoreSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
   HomePageGUI HomePageGUI = new HomePageGUI();
        HomePageGUI.pack(); // pack the dialog
        HomePageGUI.setVisible(true);
        // hide the current GUI
      
        ArrayList<Chore> list = new ArrayList();



        for (Chore c : list) {
            System.out.println(c.getChoreName() + " assign to " + c.getChoreAssignTo() + " user one " + c.getEstimateTimeUserOne() + " user Two " + c.getEstimateTimeUserTwo());
        }
    }

}
