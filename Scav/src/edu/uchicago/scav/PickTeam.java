package edu.uchicago.scav;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class PickTeam extends Activity
{
	static String myTeam;
	static String myCNet;
	static String myPassword;
	static List<String> myTeams;
	

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pick_team);
		Bundle extras = getIntent().getExtras();
		myCNet = extras.getString("cnet");
		myPassword = extras.getString("password");
		
		// Show the Up button in the action bar.
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(false);
		// fill the view with a list of teams
		try {
			createTeamListView();
		} catch (InterruptedException e) {
			Log.e("error", e.toString());
		} catch (ExecutionException e) {
			Log.e("error", e.toString());
		}
	}
	
	// creates a list of teams
	public void createTeamListView() throws InterruptedException, ExecutionException
	{
		ListView teamView = (ListView) findViewById(R.id.team_list);
		final List<String> teams = new PickTeam.getTeams().execute((Void) null).get();
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
				android.R.id.text1, teams);
		
		teamView.setAdapter(adapter);
		teamView.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				myTeam = teams.get(position);
				next();
			}
		});
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
	
	private class getTeams extends AsyncTask<Void, Void, List<String>>
	{
			@Override
			protected List<String> doInBackground(Void...params)
			{
				ScavRest myRest = new ScavRest(Scav.serverURL, Scav.accessKey);
				Log.d("status", "I get here");
				List<Team> allTeams = myRest.getTeams();
				List<String> teamNames = new ArrayList<String>();
				
				for (int i = 0; i < allTeams.size(); i++)
				{
					teamNames.add(allTeams.get(i).aTeamName);
					Log.d("team", allTeams.get(i).aTeamName);
				}
				
				return teamNames;
			}
			
	}

	
	public void next()
	{
//		SharedPreferences scavPrefs = getSharedPreferences(Scav.PREFS_NAME, 0);
//		scavPrefs.edit().putString("team", myTeam).commit();
		 
		Intent finishLogin = new Intent(PickTeam.this, FinishLogin.class);
		finishLogin.putExtra("cnetid", myCNet);
		finishLogin.putExtra("password", myPassword);
		finishLogin.putExtra("team", myTeam);
		startActivity(finishLogin);
		finish();
		
		
//		HashMap<String, String> userData = new HashMap<String, String>();
//		
//        userData.put("cnetid", myCNet);
//        userData.put("password", myPassword);
//        userData.put("team", myTeam);
//        
//        Log.d("cnet", myCNet);
//        
//       
	}
	
	public void createNewTeam(View v)
	{
		Toast notYet = Toast.makeText(getApplication(), "doesn't work yet", Toast.LENGTH_LONG);
		notYet.show();
	}

}