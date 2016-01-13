package com.palsoft.cyclingfront.client;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.palsoft.cyclingfront.contentproviders.RideProvider;
import com.palsoft.cyclingfront.models.RideData;

import java.util.List;

/**
 * Created by luispalacios on 10/1/15.
 */
public class CFSqlClient {

    private static final String LOG_TAG = CFSqlClient.class.getSimpleName();

    // lazy loaded singleton
    private static CFSqlClient instance;

    // class variables
    private static String username;

    // instance member variables
    private Context mContext;

    public static void initializeDBUser(String user)
    {
       username = user;
    }

    public static CFSqlClient getInstance(Context ctx)
    {
        if(instance == null)
        {
            instance = new CFSqlClient(ctx);
        }

        return instance;
    }

    private CFSqlClient(Context ctx)
    {
        mContext = ctx;
    }


    public Cursor getRideData()
    {
        String[] selectionArgs = new String[]
                {
                        username
                };

        Cursor cur = mContext.getContentResolver().query(RideProvider.CONTENT_URI,
                RideProvider.EVENT_PROJECTION,
                RideProvider.RIDER_SELECTION,
                selectionArgs,
                null);

        return cur;
    }

    public int persistRideData(List<RideData> rideDataBatch)
    {
        if(username == null)
        {
            Log.e(LOG_TAG, "Attempting to persist user data, but the user is anonymous");
        }

        ContentValues[] ops = new ContentValues[rideDataBatch.size()];

        for(int i = 0; i <= ops.length; i++)
        {
            RideData ride = rideDataBatch.get(i);

            ops[i] = createContentValues(ride);
        }

        return mContext.getContentResolver().bulkInsert(RideProvider.CONTENT_URI, ops);
    }

    private ContentValues createContentValues(RideData ride)
    {
        ContentValues val = new ContentValues();
        val.put(RideProvider.USERNAME, username);
        val.put(RideProvider.DATE, ride.mDate.toString());
        val.put(RideProvider.RIDE_MILES, ride.mMiles);
        val.put(RideProvider.RIDE_HOURS, ride.mHours);
        val.put(RideProvider.RIDE_MINUTES, ride.mMinutes);
        val.put(RideProvider.RIDE_SECONDS, ride.mSeconds);

        return val;
    }

    public void persistRideData(RideData ride)
    {
        mContext.getContentResolver().insert(RideProvider.CONTENT_URI, createContentValues(ride));

    }
}
