package edu.uchicago.scav;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class PickTeam extends  Activity
{
	String pickedTeam;
	final String PREFS_NAME = "ScavPrefsFile";

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pick_team);
		// Show the Up button in the action bar.
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(false);
		// fill the view with a list of teams
		createTeamList();
	}
	
	// creates a list of teams, currently dummies
	public void createTeamList()
	{
		ListView teamView = (ListView) findViewById(R.id.team_list);
		
		// dummy team list, later to be replaced with real data fetched from the server
		final String[] dummyTeams = new String[] {"Snitchcock", "BJ", "The Outsiders", "Placeholder Studios"};
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
				android.R.id.text1, dummyTeams);
		teamView.setAdapter(adapter);
		teamView.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				setPickedTeam(dummyTeams[position]);
				Toast.makeText(getApplicationContext(), "You picked " + getPickedTeam(), Toast.LENGTH_LONG).show();
			}
		});
	}
	
	public String getPickedTeam()
	{
		return pickedTeam;
	}
	
	public void setPickedTeam(String team)
	{
		pickedTeam = team;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_pick_team, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId()) 
		{
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void next(View v)
	{
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		settings.edit().putString("user_team", getPickedTeam()).commit();
		Intent tabs = new Intent(PickTeam.this, Tabs.class);
		startActivity(tabs);
		finish();
	}

}
