package edu.uchicago.scav;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;


public class MainActivity extends Activity
{
	
	final String PREFS_NAME = "MyPrefsFile";
			
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
    	
    	if (settings.getBoolean("first_launch", true)) 
    	{
    		Intent login = new Intent(MainActivity.this, LoginActivity.class);
    		startActivity(login);
    		settings.edit().putBoolean("first_launch", false).commit();
    	}
    	else
    	{
    		Intent tabs = new Intent(MainActivity.this, Tabs.class);
    		startActivity(tabs);
    		
    	}
    	setContentView(R.layout.activity_main);
    }
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
    
    public void firstLaunchClick(View view)
    {
    	SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
    	settings.edit().putBoolean("first_launch", true).commit();
    	android.os.Process.killProcess(android.os.Process.myPid());
    }
    
}
