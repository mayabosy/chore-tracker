/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.kf5012assessment;

import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author w21023500
 */
// For Task 7 Group work
public class AssigningChoresAlgo {
    private ChoresDatabase choresDB = new ChoresDatabase();
    private ArrayList<Chore> choresArrayListUserOne;
    private ArrayList<Chore> choresArrayListUserTwo;
    private ArrayList<Chore> fullList;
    ArrayList<Chore> toSend;
    User newUser;


    //private choresArrayList = dummyList;
    public AssigningChoresAlgo() throws SQLException {

        ChoresDatabase choresDB = new ChoresDatabase();

        try {
            fullList = choresDB.selectedThisWeekChores();
            choresArrayListUserOne = choresDB.selectEstimateTimeUserOne();
            choresArrayListUserTwo = choresDB.selectEstimateTimeUserTwo();

        } catch (Exception e) {
            System.out.println("Error occured in extracting data");
        }
        toSend = calculation(fullList);
        for (Chore c : toSend) {
            //choresDB.addAssignedChore(c.getChoreID(), newUser, c.getChoreFrequencyID(), );
            choresDB.addAssignedChore(c, newUser, c.getChoreFrequencyID(), c.getChoreAssignTo());
        }
        System.out.println(fullList);

        //  calculation();
    }

    public boolean unassignedChore(ArrayList<Chore> choreList) {
        for (Chore c : choreList) {
            if (c.getChoreAssignTo() == 0) {
                return true;
            }
        }
        return false;
    }

    // private float userTwoTotal = 0;
    public  ArrayList<Chore> calculation(ArrayList<Chore> fullChoreList) {

        ArrayList<Chore> assignedList = new ArrayList<Chore>();

        //      AssignedChoresList user1ChoresAssigned = new AssignedChoresList();
        //     AssignedChoresList user2ChoresAssigned = new AssignedChoresList();
        float userOneTotal = 0;
        float userTwoTotal = 0;

        for (Chore c : fullChoreList) {
            userOneTotal = userOneTotal + c.getEstimateTimeUserOne();
            userTwoTotal = userTwoTotal + c.getEstimateTimeUserTwo();

        }

        for (Chore c : fullChoreList) {
            c.setEstimateTimeUserOne(c.getEstimateTimeUserOne() / userOneTotal);
            c.setEstimateTimeUserTwo(c.getEstimateTimeUserTwo() / userTwoTotal);

        }

        float User1Load = 0;     // variables for this weeks chore 
        float User2Load = 0; //// variables for this weeks chore 
        float User1LoadCarriedOver = 0; //// variables for last weeks imbalance 
        float User2LoadCarriedOver = 0;//variables for last weeks imbalance
        int numUnassignChores = 0;

        int unassignedChores = fullChoreList.size();

        while (unassignedChores > 0) {
            if (User1Load < User2Load) {
                // Sorting the list and coparing the estimate times
                Collections.sort(fullChoreList, new Comparator<Chore>() {
                    @Override
                    public int compare(Chore c1, Chore c2) {
                        return Double.compare(c2.getEstimateTimeUserOne(), c1.getEstimateTimeUserOne());
                    }
                });

                Chore chosenChore = null;

                for (Chore c : fullChoreList) {
                    if (c.getEstimateTimeUserOne() < c.getEstimateTimeUserTwo()) {
                        chosenChore = c;
                        break;
                    }
                }

                if (chosenChore == null) {
                    for (Chore c : fullChoreList) {
                        if (c.getChoreAssignTo() == 0) {
                            chosenChore = c;
                            break;
                        }
                    }
                }

                //  user1ChoresAssigned.addToChoreList(chosenChore);
                User1Load = User1Load + chosenChore.getEstimateTimeUserOne();
                chosenChore.assignTo(1);
                assignedList.add(chosenChore);
                unassignedChores--;
                try {
                    choresDB.addAssignedChore(chosenChore, newUser, chosenChore.getChoreFrequencyID(), chosenChore.getChoreAssignTo());
                } catch (Exception e){
                    System.out.println("ASSIGN ERROR: " + e);
                }
                

            } else {
                // Sorting the list and coparing the estimate times
                Collections.sort(fullChoreList, new Comparator<Chore>() {
                    @Override
                    public int compare(Chore c1, Chore c2) {
                        return Double.compare(c2.getEstimateTimeUserTwo(), c1.getEstimateTimeUserTwo());
                    }
                });

                Chore chosenChore = null;

                for (Chore c : fullChoreList) {
                    if (c.getEstimateTimeUserTwo() < c.getEstimateTimeUserOne()) {
                        chosenChore = c;
                        break;
                    }
                }

                if (chosenChore == null) {
                    for (Chore c : fullChoreList) {
                        if (c.getChoreAssignTo() == 0) {
                            chosenChore = c;
                            break;
                        }
                    }
                }

                //  user1ChoresAssigned.addToChoreList(chosenChore);
                User2Load = User1Load + chosenChore.getEstimateTimeUserTwo();
                chosenChore.assignTo(2);
                assignedList.add(chosenChore);
                unassignedChores--;
                try {
                    choresDB.addAssignedChore(chosenChore, newUser, chosenChore.getChoreFrequencyID(), chosenChore.getChoreAssignTo());
                } catch (Exception e){
                    System.out.println("ASSIGN ERROR: " + e);
                }
            }
            if(User1Load > User2Load){
                User2LoadCarriedOver = User2Load- User1Load;
                User1LoadCarriedOver=0;
            }else{
                User1LoadCarriedOver= User1Load-User2Load;
                User2LoadCarriedOver=0;
            }
                

        }
        return assignedList;

//Testing the assigning chores
/*public void main(String[] args) {

        //System.out.println(choresArrayList.size());
        //System.out.println(choresArrayListTwo.size());

        /*for (int i = 0; i < choresArrayList.size(); i++) {
            //user1Total += user1Total + choresArrayList.get(i);
            //user2Total += user2Total + choresArrayList.get(i);
            System.out.println(choresArrayList.get(i));
        }*/
        // for(Chore li : ChoreList.getChoreList){
        //}
        /*ChoresDatabase db = new ChoresDatabase();
        try {
            db.selectChores();
        } catch (SQLException ex) {
            Logger.getLogger(AssigningChoresAlgo.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        //NormaliseTotalTime();
    }

    
    



    /*  public void NormaliseTotalTime() {
        ArrayList<Chore> total = new ArrayList();
        user1.getAssignedChoresList();
        for(int i = 0; i < user1.getAssignedChoresList().size(); i++){
            System.out.println("Loop" + i);
        }

    }  */
}
