package com.palsoft.cyclingfront.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class RideOpenHelper extends SQLiteOpenHelper {
	
	private static final String LOG_TAG = RideOpenHelper.class.getSimpleName();
	private static final int DATABASE_VERSION = 1;
	public static final String TABLE_NAME = "rides";
	
	private static final String RIDES_TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + 
			" (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
			" username TEXT NOT NULL, " + 
			" date TEXT NOT NULL, " +
			" miles TEXT, " +
			" hours TEXT, " +
			" minutes TEXT, " +
			" seconds TEXT);";	
	
	private static final String INSERT_TEST_DATA1 = "INSERT INTO " + TABLE_NAME +
			" (username, date, miles, hours, minutes, seconds) VALUES('palatinum','05/08/2014','65','3','23','09')";
	private static final String INSERT_TEST_DATA2 = "INSERT INTO " + TABLE_NAME +
			" (username, date, miles, hours, minutes, seconds) VALUES('palatinum','05/16/2014','25','1','3','21')";
	private static final String INSERT_TEST_DATA3 = "INSERT INTO " + TABLE_NAME +
			" (username, date, miles, hours, minutes, seconds) VALUES('palatinum','06/02/2014','44','2','43','33')";
	private static final String INSERT_TEST_DATA4 = "INSERT INTO " + TABLE_NAME +
			" (username, date, miles, hours, minutes, seconds) VALUES('palatinum','06/21/2014','30','1','40','54')";
	private static final String INSERT_TEST_DATA5 = "INSERT INTO " + TABLE_NAME +
			" (username, date, miles, hours, minutes, seconds) VALUES('palatinum','07/04/2014','27','1','35','09')";
	private static final String INSERT_TEST_DATA6 = "INSERT INTO " + TABLE_NAME +
			" (username, date, miles, hours, minutes, seconds) VALUES('palatinum','07/31/2014','80','4','44','55')";
	
	public RideOpenHelper(Context context)
	{
		super(context, TABLE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL(RIDES_TABLE_CREATE);
		//TODO: remove test data
		db.execSQL(INSERT_TEST_DATA1);
		db.execSQL(INSERT_TEST_DATA2);
		db.execSQL(INSERT_TEST_DATA3);
		db.execSQL(INSERT_TEST_DATA4);
		db.execSQL(INSERT_TEST_DATA5);
		db.execSQL(INSERT_TEST_DATA6);

	}

	public ContentValues createContentValue(String sql)
	{
		return null;
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2)
	{
		Log.d(LOG_TAG, "Upgrading database version " + arg1 + " to " + arg2);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}

}
