package edu.uchicago.scav;

import android.app.Application;

/*
 * The point of this class is to enable us to get the application context from anywhere
 * It's particularly useful for when you need to get something which is non-static from within a static context
 * such as Shared
 */

public class Scav extends Application
{
	static Application app;
	static String PREFS_NAME = "ScavPrefsFile";
	
	public void onCreate()
	{
		app = this;
	}
	
	public static Application getApp()
	{
		return app;
	}
	
	public static String getPrefsName()
	{
		return PREFS_NAME;
	}

}
