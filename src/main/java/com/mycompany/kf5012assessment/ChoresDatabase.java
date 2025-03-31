/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kf5012assessment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author majabosy
 */
public class ChoresDatabase {

    private static DBConnection database;
    public static Chore newchore = new Chore();

    static ArrayList<Chore> choresArrayList = new ArrayList<Chore>();

    //Chore newChore;
    public ChoresDatabase() {
        database = new DBConnection();

        //Database connnection
        database.Connect("C:\\Users\\nihal\\Documents\\UpdateFriday\\KF5012Assignment\\src\\main\\java\\com\\mycompany\\kf5012assessment\\kf5012db.db");
    }

    /**
     *
     * Functions
     *
     */
    //Select all chores from chores table
    public ArrayList<Chore> selectChores() throws SQLException {

        String sqlSelectChores = "SELECT choreID, choreName, choreFrequencyID, assignedTo, daysOfWeekID, isSelected FROM chores;";

        ResultSet choreList = database.RunSQLQuery(sqlSelectChores);
        ArrayList<Chore> chores = new ArrayList<Chore>();

        try {
            while (choreList.next()) {
                Chore newChore = new Chore();
                newChore.setChoreID(choreList.getInt(1));
                newChore.setChoreName(choreList.getString(2));
                newChore.setChoreFrequencyID(choreList.getInt(3));
                newChore.assignTo(choreList.getInt(4));
                newChore.setChoreDay(choreList.getInt(5));

                int sel = choreList.getInt(6);

                //Translates int into boolean
                if (sel == 0) {
                    newChore.setSelectedForThisWeek(false);
                } else {
                    newChore.setSelectedForThisWeek(true);

                }
                chores.add(newChore);

                //Testing the function
                //System.out.println("Chore ID: " + newChore.getChoreID() + " Chore name: " + newChore.getChoreName());
            }
        } catch (SQLException e) {
            System.out.println("Failed to process query in selectChores()");
            System.out.println("SQL attempted: " + sqlSelectChores);
            System.out.println("Error: " + e.getErrorCode());
            System.out.println("Message: " + e.getMessage());
            e.printStackTrace();
        }
        return chores;
    }

    //Select all assigned chores from choresAssigned table
    public ArrayList<Chore> selectChoresAssigned() throws SQLException {

        String sqlSelectChoresAssigned = "SELECT choreID, userID, daysOfWeekID, choreComplete, choreCompletionTime "
                + "FROM choresAssigned"
                + " INNER JOIN chores ON choreID.chores =  choreID.choresAssigned "
                + " INNER JOIN chores ON userID.choresAssigned = assignedTo.chores;";

        ResultSet choreList = database.RunSQLQuery(sqlSelectChoresAssigned);
        ArrayList<Chore> chores = new ArrayList<Chore>();

        ResultSet userList = database.RunSQLQuery(sqlSelectChoresAssigned);
        ArrayList<User> users = new ArrayList<User>();

        try {
            while (choreList.next()) {
                Chore newChore = new Chore();
                User newUser = new User();
                newChore.setChoreID(choreList.getInt(1));
                newUser.setUserID(userList.getInt(2));
                newChore.setChoreDay(choreList.getInt(3));
                int sel2 = choreList.getInt(4);

                //Translates int into boolean
                if (sel2 == 0) {
                    newChore.setChoreComplete(false);
                } else {
                    newChore.setChoreComplete(true);

                }
                chores.add(newChore);
                newChore.setCompletionTime(choreList.getInt(5));
            }
        } catch (SQLException e) {
            System.out.println("Failed to process query in selectChores()");
            System.out.println("SQL attempted: " + sqlSelectChoresAssigned);
            System.out.println("Error: " + e.getErrorCode());
            System.out.println("Message: " + e.getMessage());
            e.printStackTrace();
        }
        return chores;
    }

    //Select userID from choresAssigned table
    public ArrayList<User> selectUserIDChoresAssigned() throws SQLException {

        String sqlSelectUserIDChoresAssigned = "SELECT userID FROM choresAssigned;";

        ResultSet userList = database.RunSQLQuery(sqlSelectUserIDChoresAssigned);
        ArrayList<User> users = new ArrayList<User>();

        try {
            while (userList.next()) {
                User newUser = new User();
                newUser.setUserID(userList.getInt(1));
                users.add(newUser);
            }
        } catch (SQLException e) {
            System.out.println("Failed to process query in selectChores()");
            System.out.println("SQL attempted: " + sqlSelectUserIDChoresAssigned);
            System.out.println("Error: " + e.getErrorCode());
            System.out.println("Message: " + e.getMessage());
            e.printStackTrace();
        }
        return users;
    }

    //assignedTo = 1 it is assigned to User 1
    //assignedTo = 2 it is assigned to User 2
    //Assign a chore in chores table
    public void assignChore(String choreName, int assignedTo) throws SQLException {
        String sqlUpdateChore = "UPDATE chores SET assignedTo = " + assignedTo + " WHERE choreName = '" + choreName + "' ;";

        boolean success = database.RunSQL(sqlUpdateChore);

        if (success) {
            System.out.println(choreName + " was sucessfully updated");
        }
        if (!success) {
            System.out.println("Failed to process query" + sqlUpdateChore);
        }
    }

    //Update choresComplete in assignedChores table
    public void updateChoresComplete(int choreID, float completionTime) throws SQLException {
        ArrayList<Chore> chores = new ArrayList<Chore>();
        Chore newChore = new Chore();

        String sqlUpdateChoresComplete = "UPDATE choresAssigned SET choreComplete = " + newChore.isChoreComplete() + ", choreCompletionTime = " + completionTime + " WHERE choreID = " + newChore.getChoreID() + " ;";

        boolean success = database.RunSQL(sqlUpdateChoresComplete);

        if (success) {
            System.out.println(newChore.getChoreID() + " was sucessfully updated");
        }
        if (!success) {
            System.out.println("Failed to process query" + sqlUpdateChoresComplete);
        }
    }

    //Update userActive in users table 
    public void updateUserActive(int active, int userID) throws SQLException {
        ArrayList<User> users = new ArrayList<User>();

        String sqlUpdateUserActive = "UPDATE users SET userActive = " + active + " WHERE userID = " + userID + ";";

        boolean success = database.RunSQL(sqlUpdateUserActive);

        if (success) {
            System.out.println("Users active status was sucessfully updated to " + active);
        }
        if (!success) {
            System.out.println("Failed to process query" + sqlUpdateUserActive);
        }
    }

    //Update user week score in users table
    public void updateUserWeekScore(int userWeekScore, int userID) throws SQLException {
        ArrayList<User> users = new ArrayList<User>();

        String sqlUpdateUserWeekScore = "UPDATE users SET userWeekScore = " + userWeekScore + " WHERE userActive = " + userID + ";";

        boolean success = database.RunSQL(sqlUpdateUserWeekScore);

        if (success) {
            System.out.println("Week score was successfully updated to " + userWeekScore);
        }
        if (!success) {
            System.out.println("Failed to process query" + sqlUpdateUserWeekScore);
        }
    }

    //Update user total score in users table
    public void updateUserTotalScore(int userTotalScore, int userID) throws SQLException {
        ArrayList<User> users = new ArrayList<User>();

        String sqlUpdateUserTotalScore = "UPDATE users SET userTotalScore = " + userTotalScore + " WHERE userActive = " + userID + ";";

        boolean success = database.RunSQL(sqlUpdateUserTotalScore);

        if (success) {
            System.out.println("Total score was successfully updated to " + userTotalScore);
        }
        if (!success) {
            System.out.println("Failed to process query" + sqlUpdateUserTotalScore);
        }
    }

    //Delete a chore in chores table
    public void deleteChore(String choreName, int assignedTo) throws SQLException {
        String sqlDeleteChore = "DELETE FROM chores WHERE choreName = '" + choreName + "';";

        boolean success = database.RunSQL(sqlDeleteChore);

        if (success) {
            System.out.println(choreName + " was successfully deleted");
        }
        if (!success) {
            System.out.println("Failed to process query" + sqlDeleteChore);
        }
    }

    //Select estimate times for user 1 from estimateTime table
    public ArrayList<Chore> selectEstimateTimeUserOne() throws SQLException {

        String sqlEstimateTimeUserOne = "SELECT choreEstimateTime, choreID  FROM estimateTime WHERE userID = 1;";

        ResultSet choreList = database.RunSQLQuery(sqlEstimateTimeUserOne);
        ArrayList<Chore> chores = new ArrayList<Chore>();

        try {
            while (choreList.next()) {
                Chore newChore = new Chore();
                newChore.setEstimateTimeUserOne(choreList.getFloat(1));
                newChore.setChoreID(choreList.getInt(2));
                chores.add(newChore);

            }
        } catch (SQLException e) {
            System.out.println("Failed to process query in selectChores()");
            System.out.println("SQL attempted: " + sqlEstimateTimeUserOne);
            System.out.println("Error: " + e.getErrorCode());
            System.out.println("Message: " + e.getMessage());
            e.printStackTrace();
        }
        return chores;
    }

    //Select estimate times for user 2 from estimateTime table
    public ArrayList<Chore> selectEstimateTimeUserTwo() throws SQLException {

        String sqlEstimateTimeUserTwo = "SELECT choreEstimateTime, choreID  FROM estimateTime WHERE userID = 2;";

        ResultSet choreList = database.RunSQLQuery(sqlEstimateTimeUserTwo);
        ArrayList<Chore> chores = new ArrayList<Chore>();

        try {
            while (choreList.next()) {
                Chore newChore = new Chore();
                newChore.setEstimateTimeUserTwo(choreList.getFloat(1));
                newChore.setChoreID(choreList.getInt(2));
                chores.add(newChore);

            }
        } catch (SQLException e) {
            System.out.println("Failed to process query in selectChores()");
            System.out.println("SQL attempted: " + sqlEstimateTimeUserTwo);
            System.out.println("Error: " + e.getErrorCode());
            System.out.println("Message: " + e.getMessage());
            e.printStackTrace();
        }
        return chores;
    }

    //Update estimate time for user 1 in estimateTime table
    public void updateEstimateTimeUserOne() throws SQLException {
        String sqlUpdateEstimateTime1 = "UPDATE estimateTime SET choreEstimateTime = " + newchore.getEstimateTimeUserOne() + " WHERE userID = 1 ;";

        boolean success = database.RunSQL(sqlUpdateEstimateTime1);

        if (success) {
            System.out.println("Estimate time was successfully updated to " + newchore.getEstimateTimeUserOne());
        }
        if (!success) {
            System.out.println("Failed to process query" + sqlUpdateEstimateTime1);
        }
    }

    //Update estimate time for user 2 in estimateTime table
    public void updateEstimateTimeUserTwo() throws SQLException {
        String sqlUpdateEstimateTime2 = "UPDATE estimateTime SET choreEstimateTime = " + newchore.getEstimateTimeUserTwo() + " WHERE userID = 2 ;";

        boolean success = database.RunSQL(sqlUpdateEstimateTime2);

        if (success) {
            System.out.println("Estimate time was successfully updated to " + newchore.getEstimateTimeUserTwo());
        }
        if (!success) {
            System.out.println("Failed to process query" + sqlUpdateEstimateTime2);
        }
    }

    //Select weekly chores from chores table
    public ArrayList<Chore> selectChoresFrequencyWeekly() throws SQLException {

        String sqlSelectChoresWeekly = "SELECT choreID, choreName, choreFrequency "
                + "FROM chores "
                + "INNER JOIN choreFrequency ON choreFrequency.choreFrequencyID = chores.choreFrequencyID "
                + "WHERE choreFrequency = 'Weekly';";

        ResultSet choreList = database.RunSQLQuery(sqlSelectChoresWeekly);
        ArrayList<Chore> chores = new ArrayList<Chore>();

        try {
            while (choreList.next()) {
                Chore newChore = new Chore();
                newChore.setChoreID(choreList.getInt(1));
                newChore.setChoreName(choreList.getString(2));
                newChore.setChoreFrequency(choreList.getString(3));
                chores.add(newChore);

                //Testing the function   
                /*
                System.out.println("Chore ID: " + newChore.getChoreID() + " Frequency: "
                + newChore.getChoreFrequency() + " Chore name: " + newChore.getChoreName());
                 */
            }
        } catch (SQLException e) {
            System.out.println("Failed to process query in selectChores()");
            System.out.println("SQL attempted: " + sqlSelectChoresWeekly);
            System.out.println("Error: " + e.getErrorCode());
            System.out.println("Message: " + e.getMessage());
            e.printStackTrace();
        }
        return chores;
    }

    //Select one-off chores from chores table
    public ArrayList<Chore> selectChoresFrequencyOneOff() throws SQLException {

        String sqlSelectChoresOff = "SELECT choreID, choreName "
                + "FROM chores "
                + "INNER JOIN choreFrequency ON choreFrequency.choreFrequencyID = chores.choreFrequencyID "
                + "WHERE choreFrequency = 'One-off';";

        ResultSet choreList = database.RunSQLQuery(sqlSelectChoresOff);
        ArrayList<Chore> chores = new ArrayList<Chore>();

        try {
            while (choreList.next()) {
                Chore newChore = new Chore();
                newChore.setChoreID(choreList.getInt(1));
                newChore.setChoreName(choreList.getString(2));
                chores.add(newChore);

            }
        } catch (SQLException e) {
            System.out.println("Failed to process query in selectChores()");
            System.out.println("SQL attempted: " + sqlSelectChoresOff);
            System.out.println("Error: " + e.getErrorCode());
            System.out.println("Message: " + e.getMessage());
            e.printStackTrace();
        }
        return chores;
    }

    //isSelected = 1 is selected
    //isSelected = 0 is not selected
    //Select all selected chores from chores table
    public ArrayList<Chore> selectedThisWeekChores() throws SQLException {

        String sqlSelectedChores = "SELECT choreID, choreName FROM chores WHERE isSelected = 1;";

        ResultSet choreList = database.RunSQLQuery(sqlSelectedChores);
        ArrayList<Chore> chores = new ArrayList<Chore>();

        try {
            while (choreList.next()) {
                Chore newChore = new Chore();
                newChore.setChoreID(choreList.getInt(1));
                newChore.setChoreName(choreList.getString(2));
                chores.add(newChore);

            }
        } catch (SQLException e) {
            System.out.println("Failed to process query in selectChores()");
            System.out.println("SQL attempted: " + sqlSelectedChores);
            System.out.println("Error: " + e.getErrorCode());
            System.out.println("Message: " + e.getMessage());
            e.printStackTrace();
        }
        return chores;
    }

    //Update chore to be selected in chores table
    public void updateToSelected() throws SQLException {
        String sqlUpdateToSelected = "UPDATE chores SET isSelected = 1 "
                + "WHERE choreID = '" + newchore.getChoreID() + "' ;";

        boolean success = database.RunSQL(sqlUpdateToSelected);

        if (success) {
            System.out.println("Chore " + newchore.getChoreID() + " was successfully updated to selected");
        }
        if (!success) {
            System.out.println("Failed to process query" + sqlUpdateToSelected);
        }
    }

    //Update chore to be unselected in chores table
    public void updateToUnselected() throws SQLException {
        String sqlUpdateToUnselected = "UPDATE chores SET isSelected = 0 "
                + "WHERE choreID = '" + newchore.getChoreID() + "' ;";

        boolean success = database.RunSQL(sqlUpdateToUnselected);

        if (success) {
            System.out.println("Chore " + newchore.getChoreID() + " was successfully updated to unselected");
        }
        if (!success) {
            System.out.println("Failed to process query" + sqlUpdateToUnselected);
        }
    }

    //Select all users from users table
    public ArrayList<User> selectUsers() throws SQLException {

        String sqlSelectUsers = "SELECT userID, userActive, userWeekScore, userTotalScore FROM users;";

        ResultSet userList = database.RunSQLQuery(sqlSelectUsers);
        ArrayList<User> users = new ArrayList<User>();

        try {
            while (userList.next()) {
                User newUser = new User();
                newUser.setUserID(userList.getInt(1));
                newUser.setUserActive(userList.getInt(2));
                newUser.setUserWeekScore(userList.getInt(3));
                newUser.setUserTotalScore(userList.getInt(4));

                users.add(newUser);

            }
        } catch (SQLException e) {
            System.out.println("Failed to process query in selectUsers()");
            System.out.println("SQL attempted: " + sqlSelectUsers);
            System.out.println("Error: " + e.getErrorCode());
            System.out.println("Message: " + e.getMessage());
            e.printStackTrace();
        }
        return users;
    }

    //Add a new chore in chores table
    public void addChore(Chore newchore, int choreFrequencyID, int assignedTo, int select) throws SQLException {

        String rs = ("select max(choreID) from chores;");
        ResultSet maxID = database.RunSQLQuery(rs);

        String name = newchore.getChoreName();

        int convertedMax = 0;

        while (maxID.next()) {
            convertedMax = maxID.getInt(1) + 1;
        }

        String v = newchore.getChoreName();
        System.out.println(v);

        String sqlAddChore = "INSERT INTO chores (choreID, choreName, choreFrequencyID, daysOfWeekID, assignedTo, isSelected) "
                + "VALUES(" + convertedMax + ", '" + name + "', " + choreFrequencyID + ", "
                + newchore.getChoreDay() + ", " + assignedTo + ", " + select + "); ";

        boolean success;
        success = database.RunSQL(sqlAddChore);

        if (success) {
            System.out.println("Chore " + name + " was successfully added");
        }
        if (!success) {
            System.out.println("Failed to process query" + sqlAddChore);
        }

    }

    //Add an assigned chore in choresAssigned table
    public void addAssignedChore(Chore newChore, User newUser, int choreFrequencyID, int assignedTo) throws SQLException {

        String rs = ("select max(choresAssignedID) from choresAssigned;");
        ResultSet maxID = database.RunSQLQuery(rs);

        int convertedMax = 0;

        while (maxID.next()) {
            convertedMax = maxID.getInt(1) + 1;
        }

        System.out.println(convertedMax);
        //int max = Integer.parseInt(rs) + 1;

        String sqlAddAssignedChore = "INSERT INTO choresAssigned (choresAssignedID, choreID, userID, daysOfWeekID, choreComplete, choreCompletionTime) "
                + "VALUES(" + convertedMax + ", " + newChore.getChoreID() + ", " + newUser.getUserID() + ", " + newChore.getChoreDay() + ", " + newChore.isChoreComplete() + ", " + newChore.getCompletionTime() + "); ";

        boolean success;
        success = database.RunSQL(sqlAddAssignedChore);

        if (success) {
            System.out.println("User " + newUser.getUserID() + " was successfully assigned");
        }
        if (!success) {
            System.out.println("Failed to process query" + sqlAddAssignedChore);
        }

    }

    //Add a new user in users table
    public void addUser(int userActive, int weekScore, int totalScore) throws SQLException {

        String rs = ("select max(userID) from users");
        ResultSet maxID = database.RunSQLQuery(rs);

        int convertedMax = 0;

        while (maxID.next()) {
            convertedMax = maxID.getInt(1) + 1;
        }

        String sqlAddUser = "INSERT INTO users (userID, userActive, userWeekScore, userTotalScore) "
                + "VALUES(" + convertedMax + ", " + userActive + ", " + weekScore + ", " + totalScore + "); ";

        boolean success;
        success = database.RunSQL(sqlAddUser);

        if (success) {
            System.out.println("User " + convertedMax + " was successfully added");
        }

        if (!success) {
            System.out.println("Failed to process query" + sqlAddUser);
        }

    }

    //Delete an user in users table
    public void deleteUser(int userID) {
        String sqlDeleteUser = "DELETE FROM users WHERE userID = " + userID + ";";

        boolean success = database.RunSQL(sqlDeleteUser);

        if (success) {
            System.out.println("User " + userID + " was successfully deleted");
        }
        if (!success) {
            System.out.println("Failed to process query" + sqlDeleteUser);
        }

    }

    //Delete a chore in chores table
    public void deleteChore(String choreName) {
        String sqlDeleteChore = "DELETE FROM chores WHERE choreName = '" + choreName + "';";

        boolean success = database.RunSQL(sqlDeleteChore);

        if (success) {
            System.out.println("Chore " + choreName + " was successfully deleted");
        }
        if (!success) {
            System.out.println("Failed to process query" + sqlDeleteChore);
        }

    }

    //Delete chores table
    public void dropChoresTable() {
        String sqlDropChores = "DROP TABLE chores";

        boolean success = database.RunSQL(sqlDropChores);

        if (success) {
            System.out.println("Chores table was successfully deleted");
        }
        if (!success) {
            System.out.println("Failed to process query" + sqlDropChores);
        }

    }

    //Delete users table
    public void dropUsersTable() {
        String sqlDropUsers = "DROP TABLE users";

        boolean success = database.RunSQL(sqlDropUsers);

        if (success) {
            System.out.println("Users table was successfully deleted");
        }
        if (!success) {
            System.out.println("Failed to process query" + sqlDropUsers);
        }

    }
}
