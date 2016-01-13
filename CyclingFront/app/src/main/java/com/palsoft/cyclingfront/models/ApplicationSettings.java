package com.palsoft.cyclingfront.models;

import com.palsoft.cyclingfront.CFApplication;
import com.palsoft.cyclingfront.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class ApplicationSettings {

	public static final String LOG_TAG =  ApplicationSettings.class.getSimpleName();
	public static final String USERNAMEKEY = "cycling_front." + LOG_TAG + ".username";
	public static final String PASSWORDKEY = "cycling_front." + LOG_TAG + ".password";
	
	private Context mContext;
	private String mUserName;
	private String mUserPassword;
	
	public ApplicationSettings(Context context)
	{
		mContext = context;
		SharedPreferences sharedPrefs = context.getSharedPreferences(LOG_TAG, 0);
		mUserName = sharedPrefs.getString(USERNAMEKEY, null);
		mUserPassword = sharedPrefs.getString(PASSWORDKEY, null);
	}
	
	public boolean deviceIsTablet() {
		return mContext.getResources().getBoolean(R.bool.isTablet);
	}

	public String getProfileName() {

		if(mUserName == null)
		{
			mUserName = mContext.getSharedPreferences(LOG_TAG, 0).getString(USERNAMEKEY, null);

		}

		if(mUserName != null)
			return mUserName;

		return mContext.getString(R.string.main_menu_profile_anonymous);
	}


	private boolean isAnonymous() {
		if(mUserName == null)
		{
			return true;
		}
		
		return false;
	}
	
	public void saveLoginCredentials(String name, String password)
	{
		SharedPreferences sharedPrefs = mContext.getSharedPreferences(LOG_TAG, 0);
		Editor sharedPrefsEditor = sharedPrefs.edit();
				
		sharedPrefsEditor.putString(USERNAMEKEY, name);
		sharedPrefsEditor.putString(PASSWORDKEY, password);
		sharedPrefsEditor.commit();		
		
	}

}
