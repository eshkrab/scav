package edu.uchicago.scav;

import android.app.Application;

/*
 * The point of this class is to enable us to get the application context from anywhere
 * It's particularly useful for when you need to get something which is non-static from within a static context
 * such as Shared
 */

public final class Scav extends Application
{	
	public final static String serverURL = "http://raspi.ostensible.me:5000";
	public final static String accessKey = "8ee420fabd4c8c52763bed";

	public static Application app;
	public static String PREFS_NAME = "ScavPrefsFile";
	
	
	public void onCreate()
	{
		app = this;
	}
	
	public static Application getApp()
	{
		return app;
	}
}