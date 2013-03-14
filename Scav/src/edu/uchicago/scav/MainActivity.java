package edu.uchicago.scav;

import java.io.IOException;
import java.util.List;

import org.json.JSONObject;

/*import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.jackson.JacksonFactory;
*/

//import edu.uchicago.scav.*;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;



public class MainActivity extends Activity
{
			
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        SharedPreferences settings = getSharedPreferences(Scav.PREFS_NAME, 0);
        
        
        Intent tabs = new Intent(MainActivity.this, Tabs.class);
        
        
    	if (settings.getBoolean("first_launch", true)) 
    	{
    		Intent login = new Intent(MainActivity.this, LoginActivity.class);
    		startActivity(login);
    	}
    	else
    	{
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
    
    
   
}

