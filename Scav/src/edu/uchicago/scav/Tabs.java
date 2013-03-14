package edu.uchicago.scav;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.drawable;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Checkable;
import android.widget.ListView;
import android.widget.TextView;

@SuppressLint("DefaultLocale")
public class Tabs extends FragmentActivity implements ActionBar.TabListener {
	
	final String PREFS_NAME = "ScavPrefsFile";
	static Boolean sortItemsByStatus = true;

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// hack
		// fetch items from server 
		
		setContentView(R.layout.activity_tabs);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		// Show the Up button in the action bar.
		actionBar.setDisplayHomeAsUpEnabled(false);
		actionBar.setTitle(R.string.app_name);
		
		

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++)
		{
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_tabs, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
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
		case R.id.first_launch:
			firstLaunchClick();
			return true;
		case R.id.menu_settings:
			Intent settings = new Intent(Tabs.this, SettingsActivity.class);
			startActivity(settings);
			return true;
		case R.id.sort_by_status:
			Checkable sortByStatusButton = (Checkable) findViewById(R.id.sort_by_status);
			if (sortByStatusButton.isChecked())
			{
				sortItemsByStatus = true;
			} else {
				sortItemsByStatus = false;
			}
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = new TabsFragment();
			Bundle args = new Bundle();
			args.putInt(TabsFragment.ARG_SECTION_NUMBER, position);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}
		
		@Override
		public CharSequence getPageTitle(int position) {
			switch (position)
			{
			case 0:
				return getString(R.string.items_tab).toUpperCase(Locale.getDefault());
			case 1:
				return getString(R.string.team_tab).toUpperCase(Locale.getDefault());
			case 2:
				return getString(R.string.me_tab).toUpperCase(Locale.getDefault());
			}
			return null;
		}
	}

	
	public static class TabsFragment extends Fragment
	{
		public final static String ARG_SECTION_NUMBER = "section_number";
		
		public TabsFragment()
		{
			// here be dragons
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState)
		{
			int sectionNumber = getArguments().getInt(
					ARG_SECTION_NUMBER);
			String text = null;
			View view = null;
		
			switch(sectionNumber)
			{
			case 0:
				try{
					ListView itemView = getItemList();
					return itemView;
				} catch (Exception e) {
					Log.e("itemView error", e.toString());
					return view;
				}
				
			case 1:
				text = getTeam();
				try {
					ListView teamMemberList = getTeamMemberList();
					return teamMemberList;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					Log.e("error while drawing team member list", e.toString());
				}
			case 2:
				text = getMe();
				TextView textView = new TextView(getActivity());
				textView.setText(text);
				return textView;
			default:
				// this is needed syntactically, not logically
				// because the method needs to return something
				return view;
			}
			
		}
		
		
		public ListView getItemList() throws Exception
		{
			final Set<String> headers = new TreeSet<String>();
			headers.add(Scav.getApp().getString(R.string.available_header));
			headers.add(Scav.getApp().getString(R.string.in_progress_header));
			headers.add(Scav.getApp().getString(R.string.done_header));
			
			// this is needed because Java is silly
			Void[] nothing = null;
			final List<Item> items = new getItems().execute(nothing).get();
			List<Item> availableItems = new ArrayList<Item>();
			List<Item> inProgressItems = new ArrayList<Item>();
			List<Item> doneItems = new ArrayList<Item>();
			int numItems = items.size();
			int numAv = availableItems.size();
			int numIP = inProgressItems.size();
			int numDone = doneItems.size();
			Log.d("number of items", String.valueOf(numItems));
			ListView itemsView = new ListView(getActivity());
			itemsView.setId(R.id.item_list);
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(Scav.getApp(), android.R.layout.simple_list_item_1)
			{
				// the following is to make the text in the list black
				// because by default it comes out as white
		 		@SuppressLint("NewApi")
				@Override
		 		public View getView(int position, View convertView,
	                ViewGroup parent)
		 		{
		 			View view = super.getView(position, convertView, parent);
		 			TextView textView = (TextView) view.findViewById(android.R.id.text1);
		 			
		 			String itemText = getItem(position);
		 			
		 			if (itemText == Scav.getApp().getString(R.string.available_header) ||
		 					itemText == Scav.getApp().getString(R.string.in_progress_header) ||
		 					itemText == Scav.getApp().getString(R.string.done_header))
		 			{
		 				textView.setClickable(false);
		 				textView.setGravity(Gravity.CENTER);
		 				textView.setPadding(5, 5, 5, 5);
		 				if (itemText == Scav.getApp().getString(R.string.available_header))
		 				{
		 					textView.setBackgroundColor(Color.rgb(195, 33, 72));
		 				} else if (itemText == Scav.getApp().getString(R.string.in_progress_header))
		 				{
		 					textView.setBackgroundColor(Color.LTGRAY);
		 				} else {
		 					textView.setBackgroundColor(Color.DKGRAY);
		 				}
		 			}
		 			
		 			textView.setTextColor(Color.BLACK);
		 		
		 			return view;
		 		}
			};
			
				for (int i= 0; i < numItems; i++)
				{
					
					try {
						Item curItem = items.get(i);
						String name = curItem.aDescription;
						int number = curItem.aNumber;
						String status = curItem.aStatus;
						if (status.equals("available")){
							availableItems.add(curItem);
							numAv++;
							
						}
						else if (status.equals("in progress")){
							inProgressItems.add(curItem);
							numIP++;
						}
						else if (status.equals("done")){
							doneItems.add(curItem);
							numDone++;
						}
						if (!sortItemsByStatus){
							String itemString = String.valueOf(number) +". "+ name;
							adapter.add(itemString);
						}
					}
						
					catch(Exception e){
						Log.d("error", e.toString());
					}
				}
				try{
					if (sortItemsByStatus){
						
							adapter.add(getActivity().getString(R.string.available_header));
						
							for (int j=0; j<numAv; j++){
								Item curItem = availableItems.get(j);
								String name = curItem.aDescription;
								int number = curItem.aNumber;
								int pts = curItem.aPoints;
								String itemString = String.valueOf(number) +". "+ name+"    "+pts+" points.";
								adapter.add(itemString);
								}
							adapter.add(getActivity().getString(R.string.in_progress_header));
							for (int k=0; k<numIP; k++){
								Item curItem1 = inProgressItems.get(k);
								String name = curItem1.aDescription;
								int number = curItem1.aNumber;
								int pts = curItem1.aPoints;
								String itemString = String.valueOf(number) +". "+ name+"    "+pts+" points.";
								adapter.add(itemString);
							}

							adapter.add(getActivity().getString(R.string.done_header));
							for (int j=0; j<numDone; j++){
								Item curItem2 = doneItems.get(j);
								String name = curItem2.aDescription;
								int number = curItem2.aNumber;
								int pts = curItem2.aPoints;
								String itemString = String.valueOf(number) +". "+ name+"    "+pts+" points.";
								adapter.add(itemString);
							}
					}
				}
				catch(Exception e){
					Log.d("error", e.toString());
				}
			
			itemsView.setAdapter(adapter);
			
			itemsView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener()
			{
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id)
				{
					try{
						Log.d("message", "so I get here and then...");
						Intent itemActivity = new Intent(Scav.getApp(), ItemActivity.class);
						itemActivity.putExtra("itemNumber", items.get(position).aNumber);
						//startActivity(itemActivity);
					} catch (Exception e)
					{
						Log.e("error in login", e.toString());
					}
				}
			});
			return itemsView;
		}
		
		public ListView getTeamMemberList() throws Exception
		{
			String myTeam = getTeam();
			Log.d("my team", myTeam);
			final List<String> teamMembers = new getTeamMembers().execute(getTeam()).get();
			int numMembers = teamMembers.size();
			Log.d("number of items", String.valueOf(numMembers));
			ListView teamMemberList = new ListView(getActivity());
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(Scav.getApp(), android.R.layout.simple_list_item_1)
			{
				// the following is to make the text in the list black
				// because by default it comes out as white
				@Override
		 		public View getView(int position, View convertView,
	                ViewGroup parent)
		 		{
		 			View view = super.getView(position, convertView, parent);
		 			TextView textView = (TextView) view.findViewById(android.R.id.text1);
		 			
		 			textView.setTextColor(Color.BLACK);
		 		
		 			return view;
		 		}
			};
			
			for (int i = 0; i < teamMembers.size(); i++)
			{
				Log.d("adding member", teamMembers.get(i));
				adapter.add(teamMembers.get(i));
			}
			
			teamMemberList.setAdapter(adapter);
			return teamMemberList;
			
		}
	}
	
	// TESTING
	// this method is for testing purposes only and should be removed from the final version
	// as should be its button from the action bar in layout/activity_pick_team.xml
	public void firstLaunchClick()
	{
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
	    settings.edit().putBoolean("first_launch", true).commit();
	    Intent login = new Intent(Tabs.this, LoginActivity.class);
	    startActivity(login);
	    // android.os.Process.killProcess(android.os.Process.myPid());
	    finish();
	}
	
	private static class getItems extends AsyncTask<Void, Void, List<Item>>
	{
		@Override
		protected List<Item> doInBackground(Void...params)
		{
			ScavRest myRest = new ScavRest(Scav.serverURL, Scav.accessKey);
			return myRest.getItems();
		}
	}
		
	private static class getTeamMembers extends AsyncTask<String, Void, List<String>>
	{
		@Override
		protected List<String> doInBackground(String...params)
		{
			String theTeam = params[0];
			Log.d("the team that I'm looking up", theTeam);
			ScavRest myRest = new ScavRest(Scav.serverURL, Scav.accessKey);
			List<String> myTeamMemberNames = new ArrayList<String>();
			Log.d("result of getting team", String.valueOf(myRest.getTeam(theTeam)));
			JSONObject myTeam = myRest.getTeam(theTeam);
			try {
				JSONArray myTeamMembers = myTeam.getJSONArray("members");
				for (int i = 0; i < myTeamMembers.length(); i++)
				{
					String myTeamMember = (String) myTeamMembers.get(i);
					myTeamMemberNames.add(myTeamMember);
				}
				return myTeamMemberNames;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Log.e("error parsing team member JSON", e.toString());
				return null;
			}
			
		}
	}
		
	
	public static String getTeam()
	{
		SharedPreferences settings = Scav.getApp().getSharedPreferences(Scav.PREFS_NAME, 0);
		String team = settings.getString("team", "none");
		return team;
	}
	
	public static String getMe()
	{
		SharedPreferences settings = Scav.getApp().getSharedPreferences(Scav.PREFS_NAME, 0);
		String myCNet = settings.getString("cnetid", "cnet goes here");
		return myCNet;
	}

}
