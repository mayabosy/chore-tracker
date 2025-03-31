/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kf5012assessment;

import java.util.ArrayList;

/**
 *
 * @author w21023500
 */
public class ChoreCompletionPoints {
    private int points;
    private ChoresDatabase choreDB;
    private ArrayList<Chore> choreList = new ArrayList();
    
    // (EstimateTime - ActualTime) * choreDifficulty(Based on time, < 15 min is easy = 1, < 30 min is med =  1.5, > 30 is hard  = 2)

    public int pointCalculation(Chore chore, int activeUser){
        float estimate = 0;
        float actualTime = 0;
        float time = 0;
        float difficulty = 1;
        if(activeUser == 1){
            try{
                choreList = choreDB.selectEstimateTimeUserOne(); // Get the list of estimates for user 1
            }catch (Exception e){
                System.out.println("DB ERROR: " + e);
            }
            
            for(Chore c : choreList){
                if(c.getChoreID() == chore.getChoreID()){
                    estimate = c.getEstimateTimeUserOne(); // Set estimate to number found in DB
                }
            }
            
            actualTime = chore.getCompletionTime(); // Gets the completion time of the chore inputted by the user
            if (estimate >= 60){
                difficulty = 2;
            }
            if (estimate < 60 && estimate >= 30){
                difficulty = 1.5f;
            }
            if (estimate < 30 && estimate >= 0){
                difficulty = 1;
            } else {
                System.out.println("ERROR: Invalid estimate");
            }
            System.out.println("EST TIME: " + estimate);
            if(actualTime < estimate){
                time = (estimate - actualTime) * difficulty; // SCENARIO (50 - 30) * 1.5 =  30
                System.out.println("FAST: "+time);
            } else{
                time = ((actualTime - estimate * -1) * difficulty) * 0.5f; // SCENARIO ((30 - 50 * -1) * 1.5) * 0.5 = 15
                System.out.println("SLOW: "+time);
            }
            points = Math.round(time);
            
        }
        if(activeUser == 2){
            try{
                choreList = choreDB.selectEstimateTimeUserTwo(); // Get the list of estimates for user 2
            }catch (Exception e){
                System.out.println("DB ERROR: " + e);
            }
            
            for(Chore c : choreList){
                if(c.getChoreID() == chore.getChoreID()){
                    estimate = c.getEstimateTimeUserTwo(); // Set estimate to number found in DB
                }
            }
            
            actualTime = chore.getCompletionTime(); // Gets the completion time of the chore inputted by the user
            if (estimate >= 60){
                difficulty = 2;
            }
            if (estimate < 60 && estimate >= 30){
                difficulty = 1.5f;
            }
            if (estimate < 30 && estimate >= 0){
                difficulty = 1;
            } else {
                System.out.println("ERROR: Invalid estimate");
            }
            System.out.println("EST TIME: " + estimate);
            if(actualTime < estimate){
                time = (estimate - actualTime) * difficulty; // SCENARIO (50 - 30) * 1.5 =  30
                System.out.println("FAST: "+time);
            } else{
                time = ((actualTime - estimate * -1) * difficulty) * 0.5f; // SCENARIO ((30 - 50 * -1) * 1.5) * 0.5 = 15
                System.out.println("SLOW: "+time);
            }
            points = Math.round(time); // Round points to whole number
        }
        
        return points;
    }
    
    // Set the users total at end of week
    public void totalCalculation(User user){
       int total = user.getUserTotalScore();
       int weekScore = user.getUserWeekScore();
       
       total = total + weekScore;
       
       user.setUserTotalScore(total);
    }
}

