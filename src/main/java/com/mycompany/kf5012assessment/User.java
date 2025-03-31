/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kf5012assessment;


/**
 *
 * @author w21023500
 */

// This is the actual user
public class User {
    private int userID; // PK for DB 
    private int userActive; // is this the current active user
    private int userTotalScore; // Grand total score
    private int userWeekScore;  // Score for this week
    private int estimateTimeID;

    public int getEstimateTimeID() {
        return estimateTimeID;
    }
   
    
    public int getUserID() {
        return userID;
    }

    public void setUserID(int id) {
        this.userID = id;
    }

    public int isUserActive() {
        return userActive;
    }

    public void setUserActive(int activeUser) {
        this.userActive = activeUser;
    }


    public int getUserWeekScore() {
        return userWeekScore;
    }

    public void setUserWeekScore(int weekScore) {
        this.userWeekScore = weekScore;
    }

    public int getUserTotalScore() {
        return userTotalScore;
    }

    public void setUserTotalScore(int totalScore) {
        this.userTotalScore = totalScore;
    }
}
