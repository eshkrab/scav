package edu.uchicago.scav;

import java.util.HashMap;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class FinishLogin extends Activity
{
	private static String cnetid;
	private static String password;
	private static String team;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info);
		Bundle extras = getIntent().getExtras();
		cnetid = extras.getString("cnetid");
		password = extras.getString("password");
		team = extras.getString("team");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_info, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId())
		{
		case R.id.next_button:
			next();
			return true;
		default:
			return false;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public void next()
	{
		EditText phoneNumberView = (EditText) findViewById(R.id.phone);
		String phoneNumber = phoneNumberView.getText().toString();
		EditText aboutView = (EditText) findViewById(R.id.about_text);
		String aboutText = aboutView.getText().toString();
		
		HashMap<String, String> userInfo = new HashMap<String, String>();
		
		userInfo.put("cnetid", cnetid);
		userInfo.put("password", password);
		userInfo.put("team", team);
		userInfo.put("phone_number", phoneNumber);
		userInfo.put("about", aboutText);
		
		try {
			JSONObject result = new createUser().execute(userInfo).get();
			String status = result.getString("status");
			if (status.equals("success"))
			{
				SharedPreferences scavPrefs = getSharedPreferences(Scav.PREFS_NAME, 0);
				scavPrefs.edit().putBoolean("first_launch", false).commit();
				scavPrefs.edit().putString("cnetid", cnetid).commit();
				scavPrefs.edit().putString("password", password).commit();
				scavPrefs.edit().putString("team", team).commit();
				
				Intent tabs = new Intent(FinishLogin.this, Tabs.class);
				startActivity(tabs);
				finish();
			} else
			{
//				String errorName = result.getString("name");
//				String errorText;
//				if (errorName.equals("duplicate_user_error"))
//				{
//					errorText = "Our records show that you've already registered.";
//				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e("error creating user", e.toString());
		}
	}

	private class createUser extends AsyncTask<HashMap<String, String>, Void, JSONObject>
	{
		@Override
		protected JSONObject doInBackground(HashMap<String, String>...hashMaps)
		{
			ScavRest myRest = new ScavRest(Scav.serverURL, Scav.accessKey);
			HashMap<String, String> user = hashMaps[0];
			String cnet = user.get("cnetid");
			String password = user.get("password");
			String team = user.get("team");
			String phone = user.get("phone_number");
			String about = user.get("about");
			JSONObject result = myRest.createUser(cnet, password, team, about, phone);
			return result;
			
		}
	}
}
