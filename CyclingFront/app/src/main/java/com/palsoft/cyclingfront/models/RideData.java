package com.palsoft.cyclingfront.models;

import android.location.Location;

import java.util.Calendar;
import java.util.Date;

public class RideData {

    public static final String KEY_DATE = "com.palsoft.cyclingfront.DATE";
    public static final String KEY_MILES = "com.palsoft.cyclingfront.MILES";
    public static final String KEY_HOURS = "com.palsoft.cyclingfront.HOURS";
    public static final String KEY_MINUTES = "com.palsoft.cyclingfront.MINUTES";
    public static final String KEY_SECONDS = "com.palsoft.cyclingfront.SECONDS";
	
	public Date mDate;
	public long mMiles;
	public long mHours;
	public long mMinutes;
	public long mSeconds;

    private Location mStartLocation;
    private boolean mCorrupt;
    private Location mLastLocation;

    public RideData(){
        mStartLocation = null;
        mDate = Calendar.getInstance().getTime();
    }

    public void updateRideData(Location location) {

        if(mStartLocation == null) mStartLocation = location;

        mLastLocation = location;
        calculateDeltaDistance(location);

    }

    public void updateRideData(Date date) {
        calculateDeltaTime(date);
    }
    private void calculateDeltaDistance(Location location) {
        float meterDistance = mLastLocation.distanceTo(location);

        Integer mileDistance;
        try {
            mileDistance = Math.round(meterDistance / 1609.36f);
        } catch (Exception e) {
            mCorrupt = true;
            return;
        }

        mMiles += mileDistance;
    }

    private void calculateDeltaTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(mDate);

        long deltaTimeMS = date.getTime() - mDate.getTime();
        long deltaTimeH = deltaTimeMS / (1000 * 60 * 60);

        long msRemaining = deltaTimeMS - (deltaTimeH * 1000 * 60 * 60);

        long deltaTimeM = msRemaining / (1000 * 60);

        msRemaining -= deltaTimeM * 1000 * 60;

        long deltaTimeS = msRemaining / 1000;

        mHours = deltaTimeH;
        mMinutes = deltaTimeM;
        mSeconds = deltaTimeS;
    }
}
