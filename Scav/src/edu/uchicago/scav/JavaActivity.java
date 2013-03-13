package edu.uchicago.scav;

import org.json.JSONObject;

import java.util.List;

public class JavaActivity {
    private static final String myAccessKey = "8ee420fabd4c8c52763bed";
    private static final String myURL = "http://raspi.ostensible.me:5000";
    private static final ScavRest myRest = new ScavRest(myURL, myAccessKey);

    public static void main(String[] args) {

        for(int i = 1; i < 11; i++)
        {
            myRest.createItem(i, "Item" + i, "Description" + i);
            myRest.createTeam("Team" + i);
            myRest.createUser("user" + i + "@uchicago.edu", "Password" + i,  "Team" + i);
        }

        JSONObject user = myRest.getUser("user1@uchicago.edu", "Password1");
        JSONObject team = myRest.getTeam("Team1");
        List<Item> items = myRest.getItems();
        List<Team> teams = myRest.getTeams();
        List<User> users = myRest.getUsers();

        for(int i = 0; i < items.size(); i++)
        {
            Item currentItem = items.get(i);
            System.out.println(currentItem.aNumber + ". " + currentItem.aItemName + ": " + currentItem.aDescription);
        }
    }
}
