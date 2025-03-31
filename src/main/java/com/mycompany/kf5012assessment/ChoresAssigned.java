/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kf5012assessment;

/**
 *
 * @author James
 */
public class ChoresAssigned {
    private int choresAssignedID;
    private String choreName;
    private int userID;
    private int daysOfWeekID;
    private boolean choreComplete = false;
    private float choreCompletionTime;

    public ChoresAssigned(int choresAssignedID, String choreName, int userID, int daysOfWeekID, boolean choreComplete, float choreCompletionTime) {
        this.choresAssignedID = choresAssignedID;
        this.choreName = choreName;
        this.userID = userID;
        this.daysOfWeekID = daysOfWeekID;
        this.choreComplete = choreComplete;
        this.choreCompletionTime = choreCompletionTime;
    }

    public int getChoresAssignedID() {
        return choresAssignedID;
    }

    public void setChoresAssignedID(int choresAssignedID) {
        this.choresAssignedID = choresAssignedID;
    }

    public String getChoreName() {
        return choreName;
    }

    public void setChoreID(String choreName) {
        this.choreName = choreName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getDaysOfWeekID() {
        return daysOfWeekID;
    }

    public void setDaysOfWeekID(int daysOfWeekID) {
        this.daysOfWeekID = daysOfWeekID;
    }

    public boolean getChoreComplete() {
        return choreComplete;
    }

    public void setChoreComplete(boolean choreComplete) {
        this.choreComplete = choreComplete;
    }

    public float getChoreCompletionTime() {
        return choreCompletionTime;
    }

    public void setChoreCompletionTime(float choreCompletionTime) {
        this.choreCompletionTime = choreCompletionTime;
    }

}
