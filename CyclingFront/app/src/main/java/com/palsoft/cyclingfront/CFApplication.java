package com.palsoft.cyclingfront;

import com.palsoft.cyclingfront.contentproviders.RideProvider;
import com.palsoft.cyclingfront.db.RideOpenHelper;
import com.palsoft.cyclingfront.models.ApplicationSettings;

import android.app.Activity;
import android.app.Application;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;

public class CFApplication extends Application
{

	private static ApplicationSettings mAppSettings;
	private static Context mContext;
	private static RideProvider mDbHelper;
	
	public static ApplicationSettings getAppSettings()
	{
		if(mAppSettings == null)
		{
			mAppSettings = new ApplicationSettings(mContext);
		}
		
		return mAppSettings;
	}	
	
	@Override
	public void onCreate()
	{
		super.onCreate();
		mContext = getApplicationContext();
	}
	
	@Override
	public void onLowMemory()
	{
		super.onLowMemory();
	}
	
	@Override
	public void onTerminate()
	{
		super.onTerminate();
	}

	public static RideProvider getDbProvider()
	{
		return mDbHelper;
	}
}
