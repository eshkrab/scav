package edu.uchicago.scav;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class ScavRest
{
    private final String aHostName;
    private final String aAccessKey;
    private static final String theCreateUserAddress = "/createUser";
    private static final String theCreateTeamAddress = "/createTeam";
    private static final String theCreateItemAddress = "/createItem";
    private static final String theAmendItemAddress = "/amendItem";
    private static final String theGetUserAddress = "/getUser";
    private static final String theGetTeamAddress = "/getTeam";
    private static final String theGetAllUsersAddress = "/getAllUsers";
    private static final String theGetAllTeamsAddress = "/getTeams";
    private static final String theGetItemsAddress = "/getItems";
    DefaultHttpClient httpClient = new DefaultHttpClient();

    public ScavRest(String aHostName, String aAccessKey)
    {
        this.aHostName = aHostName;
        this.aAccessKey = aAccessKey;
    }

    public JSONObject createUser(String aCnetID, String aPassword, String aTeam, String about, String aPhoneNumber) 
    {
        try {
            JSONObject myObject = new JSONObject().put("access_key", aAccessKey)
                                                  .put("cnetid", aCnetID)
                                                  .put("password", aPassword)
                                                  .put("team", aTeam)
            									  .put("about", about)
            									  .put("phone_number", aPhoneNumber);
            return MakePostRequest(aHostName, theCreateUserAddress, myObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new JSONObject();
    }

    public JSONObject createTeam(String aTeam, String aCaptain)
    {
        try {
            JSONObject myObject = new JSONObject().put("access_key", aAccessKey)
                                                  .put("team", aTeam)
            									  .put("captain", aCaptain);
            									  
            return MakePostRequest(aHostName, theCreateTeamAddress, myObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new JSONObject();
    }


//	public JSONObject createItem(int number, String aDescription, int aPoints, String aDueDate)
//	{
//        try {
//            JSONObject myObject = new JSONObject().put("access_key", aAccessKey)
//                                                  .put("number", number)
//                                                  .put("description", aDescription)
//                                                  .put("points", aPoints)
//                                                  .put("due_date", aDueDate);
//            return MakePostRequest(aHostName, theCreateItemAddress, myObject.toString());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        return new JSONObject();
//    }

    public User getUser(String aCnetID, String aPassword)
    {
        try {
            JSONObject myObject = new JSONObject().put("access_key", aAccessKey)
                                                  .put("cnetid", aCnetID)
                                                  .put("password", aPassword);
            JSONObject result =  MakePostRequest(aHostName, theGetUserAddress, myObject.toString());
            Log.d("User JSON data", result.toString());
 
            User myUser = new User(aCnetID, result.getString("pass_hash"), result.getString("team"), result.getString("phone_number"), result.getString("about"));
            return myUser;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    @Deprecated
    public Boolean userExists(String aCnetID, String aPassword)
    {
    	JSONObject result = null;
    	try {
    		JSONObject myObject = new JSONObject().put("access_key", aAccessKey)
                                                  .put("cnetid", aCnetID)
                                                  .put("password", aPassword);
    		result = MakePostRequest(aHostName, theGetUserAddress, myObject.toString());
    	} catch (JSONException e) {
            return false;
        }
    	
		try {
				result.get("email");
				return true;
		} catch (JSONException e) {
			return false;
		}
    }

    public JSONObject getTeam(String aTeam)
    {
        try {
            JSONObject myObject = new JSONObject().put("access_key", aAccessKey)
                                                  .put("team", aTeam);
            return MakePostRequest(aHostName, theGetTeamAddress, myObject.toString());
        } catch (JSONException e) {
            Log.e("JSON error", e.toString());
        }

        return new JSONObject();
    }
    
    public JSONObject amendItem(int number, String newOwner, String newStatus)
    {
    	try {
    		JSONObject amendedItem = new JSONObject().put("access_key", aAccessKey)
    												 .put("number", number)
    												 .put("new_owner", newOwner)
    												 .put("new_status", newStatus);
    		return MakePostRequest(aHostName, theAmendItemAddress, amendedItem.toString());
    	} catch (JSONException e) {
    		Log.e("JSON error", e.toString());
    		return null;
    	}
    }

//    public List<User> getUsers() {
//        JSONObject myObject = MakeGetRequest(aHostName, theGetAllUsersAddress);
//        Iterator<?> myKeys = myObject.keys();
//        List<User> myUsers = new ArrayList<User>();
//        try {
//            while(myKeys.hasNext()) {
//                String myUserName = (String)myKeys.next();
//                JSONObject myUserInfo = myObject.getJSONObject(myUserName);
//                String myUserEmail = (String) myUserInfo.get("cnetid");
//                String myUserTeam = (String) myUserInfo.get("team");
//
//                myUsers.add(new User(myUserName, myUserEmail, myUserTeam));
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        return myUsers;
//    }

    public List<Team> getTeams()
    {
        JSONObject myObject = MakeGetRequest(aHostName, theGetAllTeamsAddress);
        Iterator<?> myKeys = myObject.keys();
        List<Team> myTeams = new ArrayList<Team>();

        try {
            while(myKeys.hasNext()) {
                String myName = (String) myKeys.next();
                
                JSONObject myTeam = myObject.getJSONObject(myName);
                String myCaptain = myTeam.getString("captain");
                JSONArray myMembers = myTeam.getJSONArray("members");

                List<String> myUsernames = new ArrayList<String>();
                
                for (int i = 0; i < myMembers.length(); i++)
                {
                    myUsernames.add((String) myMembers.get(i));
                }

                myTeams.add(new Team(myName, myCaptain, myUsernames));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return myTeams;
    }

    public List<Item> getItems(String team)
    {
        JSONObject allItems = MakeGetRequest(aHostName, theGetItemsAddress);
        List<Item> myItems = new ArrayList<Item>();

            for (int i = 1; i < (allItems.length() + 1); i++)
            {
            	try {
                JSONObject itemContent = (JSONObject) allItems.getJSONObject(String.valueOf(i));
                String teamStatus = itemContent.getJSONObject("status").getString(team);
                // we need to replace JSON's unescaped newlines characters ("\\n") for correct newline formatting
                myItems.add(new Item(i, itemContent.getString("description").replace("\\n", "\n"),
                		(String) teamStatus, (String) itemContent.getString("points") , (String) itemContent.getString("due_date"), null));
            	} catch (JSONException e)
            	{
            		Log.e("error processing returned items", e.toString());
            		continue;
            	}
            }

        return myItems;
    }
    
    public Item getItem(int number, String team)
    {
        JSONObject allItems = MakeGetRequest(aHostName, theGetItemsAddress);
        Item nullItem = new Item(0, "null", aAccessKey, "1000", "in progress", "nobody");
        
        try {
               JSONObject itemContent = (JSONObject) allItems.getJSONObject(String.valueOf(number));
               String teamItemStatus = itemContent.getJSONObject("status").getString(team);
               String teamOwner;
               try {
            	   teamOwner = itemContent.getJSONObject("owner").getString(team);
               } catch (JSONException e)
               {
            	   teamOwner = "not claimed by anyone";
               }
            // we need to replace JSON's unescaped newlines characters ("\\n") for correct newline formatting
			Item item = new Item(number, itemContent.getString("description").replace("\\n", "\n"),
                		teamItemStatus, (String)itemContent.getString("points"), (String) itemContent.getString("due_date"), teamOwner);
               return item;
        } catch (JSONException e) {
            e.printStackTrace();
            return nullItem;
        }
    }
    
    

    private JSONObject MakePostRequest(String aURL, String aPostFix, String aJSONObject) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(aURL + aPostFix);
            StringEntity myEntity = new StringEntity(aJSONObject);
            myEntity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            postRequest.setEntity(myEntity);

            HttpResponse response = client.execute(postRequest);
            BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
            String output = br.readLine();
            
            return new JSONObject(output);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
			e.printStackTrace();
		}

        return new JSONObject();
    }

    private JSONObject MakeGetRequest(String aURL, String aPostFix) {
        try {
            URIBuilder myBuilder = new URIBuilder(aURL + aPostFix);
            HttpGet getRequest = new HttpGet(aURL + aPostFix);
            getRequest.addHeader("accept", "application/json");
            HttpResponse response = httpClient.execute(getRequest);
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

            String output = br.readLine();
            return new JSONObject(output);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return new JSONObject();
    }
}
