package com.palsoft.cyclingfront.contentproviders;

import java.util.List;

import com.palsoft.cyclingfront.db.RideOpenHelper;
import com.palsoft.cyclingfront.models.RideData;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class RideProvider extends ContentProvider {

	public static final String PROVIDER_NAME = "com.palsoft.cyclingfront.contentproviders.RideProvider";
	public static final String URL = "content://" + PROVIDER_NAME + "/rider";
	public static final Uri CONTENT_URI = Uri.parse(URL);
	public static final long RIDER_ID = 1;
	
	public static final String _ID = "_id";
	public static final String USERNAME = "username";
	public static final String RIDE_MILES = "miles";
	public static final String RIDE_HOURS = "hours";
	public static final String RIDE_MINUTES = "minutes";
	public static final String RIDE_SECONDS = "seconds";
	public static final String DATE = "date";

	public static final String[] EVENT_PROJECTION = new String[]
			{
					_ID,
					USERNAME,
					DATE,
					RIDE_MILES,
					RIDE_HOURS,
					RIDE_MINUTES,
					RIDE_SECONDS
			};

	public static final String RIDER_SELECTION = "(" + USERNAME + "= ?)";
	
	private RideOpenHelper mRideDbHelper;
	
	@Override
	public int delete(Uri arg0, String arg1, String[] arg2)
	{
		return 0;
	}

	@Override
	public String getType(Uri arg0)
	{
		return null;
	}

	@Override
	public Uri insert(Uri arg0, ContentValues arg1)
	{
		return null;
	}

	@Override
	public boolean onCreate()
	{
		Context context = getContext();
		mRideDbHelper = new RideOpenHelper(context);
		return true;

	}

	@Override
	public Cursor query(Uri arg0, String[] arg1, String arg2, String[] arg3,
			String arg4) {
		SQLiteDatabase db = mRideDbHelper.getReadableDatabase();
		return db.query(RideOpenHelper.TABLE_NAME, arg1, arg2, arg3, null, null, arg4);
	}

	@Override
	public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3)
	{
		return 0;
	}

}
