package edu.uchicago.scav;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class ItemActivity extends Activity
{
	private static final ScavRest myRest = new ScavRest(Scav.serverURL, Scav.accessKey);
	static String itemNumber;
	//static Item curItem;

	static List<String> myTeams;
	

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item);
		Bundle extras = getIntent().getExtras();
		itemNumber = extras.getString("itemNumber");
		
		// Show the Up button in the action bar.
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(false);
		// fill the view with a list of teams
	/*	try {
			createTeamListView();
		} catch (InterruptedException e) {
			Log.e("error", e.toString());
		} catch (ExecutionException e) {
			Log.e("error", e.toString());
		}*/
	}
	
	// creates an Item?
	public void createItemView() throws InterruptedException, ExecutionException
	{
		ListView itemView = (ListView) findViewById(R.id.item_name);
		Void[] nothing = null;
		final Item item = new ItemActivity.getItem().execute(nothing).get();
		/*
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
				android.R.id.text1, item);
		teamView.setAdapter(adapter);
		teamView.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				myTeam = teams.get(position);
				next();
			}
		});*/
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
	
	private class getItem extends AsyncTask<Void, Void, Item>
	{
			@Override
			protected Item doInBackground(Void...params)
			{
				ScavRest myRest = new ScavRest(Scav.serverURL, Scav.accessKey);
				Log.d("status", "I get here");
				Item item = myRest.getItem(Integer.valueOf(itemNumber));
			
				return item;
			}
			
	}
	
	
	public void next()
	{
		SharedPreferences scavPrefs = getSharedPreferences(Scav.PREFS_NAME, 0);
	//	scavPrefs.edit().putString("team", myTeam).commit();
		
        
        scavPrefs.edit().putBoolean("first_launch", false).commit();
		
		Intent tabs = new Intent(ItemActivity.this, Tabs.class);
		startActivity(tabs);
		finish();
	}
	
	public void createNewTeam(View v)
	{
		Toast notYet = Toast.makeText(getApplication(), "doesn't work yet", Toast.LENGTH_LONG);
		notYet.show();
	}

}