package edu.uchicago.scav.rest;

import org.json.JSONObject;

import java.util.List;

public class JavaActivity {
    private static final ScavRest myRest = new ScavRest("http://raspi.ostensible.me:5000");


    public static void main(String[] args) {

        for(int i = 0; i < 10; i++) {
            myRest.CreateItem("Item" + i, "Description" + i);
            myRest.CreateTeam("Team" + i);
            myRest.CreateUser("user" + i + "@uchicago.edu", "Password" + i,  "Team" + i);
        }

        JSONObject user = myRest.getUser("user1@uchicago.edu", "Password1");
        JSONObject team = myRest.getTeam("Team1");
        List<Item> items = myRest.GetItems();
        List<Team> teams = myRest.GetTeams();
        List<User> users = myRest.GetUsers();
    }
}
