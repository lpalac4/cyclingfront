package com.palsoft.cyclingfront.services;

import android.app.IntentService;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.palsoft.cyclingfront.client.CFSqlClient;
import com.palsoft.cyclingfront.models.ApplicationSettings;
import com.palsoft.cyclingfront.models.RideData;

import java.util.Date;

/**
 * Created by luispalacios on 11/22/15.
 */
public class CFLocationService extends IntentService implements LocationListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    public static final String LOCATION_STATUS_ACTION = "com.palsoft.cyclingfront.LOCATION_STATUS_CHANGE";
    public static final String LOCATION_STATUS_DATA = "com.palsoft.cyclingfront.LOCATION_STATUS_DATA";
    public static final String RIDE_START = "com.palsoft.cyclingfront.RIDE_START";
    public static final String RIDE_END = "com.palsoft.cyclingfront.RIDE_END";

    private Location mLastLocation;
    private LocationRequest mLocationRequest;
    private GoogleApiClient mGoogleApiClient;
    private RideData mActiveRide;
    private String mUsername;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public CFLocationService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        if(intent.getAction() == RIDE_START) {
            startRide();

        } else if(intent.getAction() == RIDE_END) {
            endRide(new Date(), true);

        } else if(intent.getAction() == LOCATION_STATUS_ACTION) {
            buildActiveRide(intent);
            createLocationRequest();
            buildGoogleApiClient();
            startLocationUpdates();
        }
    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    protected void startLocationUpdates() {
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);
    }

    /**
     * Called by the GoogleApiClient through the LocationListener interface
     * @param location
     */
    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        sendLocationChangeBroadcast();
        updateRide(location);
    }

    private void buildActiveRide(Intent intent) {
        mUsername = intent.getStringExtra(ApplicationSettings.USERNAMEKEY);
    }

    private void startRide() {
        mActiveRide = new RideData();
    }

    private void updateRide(Location location) {
        if(mActiveRide == null) startRide();

        mActiveRide.updateRideData(location);
    }

    public void endRide(Date date, boolean save) {
        if(save) {
            mActiveRide.updateRideData(date);

        } else {
            mActiveRide = null;
            stopSelf();
        }
    }

    private void sendLocationChangeBroadcast() {
        Intent locationChangeIntent = new Intent(LOCATION_STATUS_ACTION);
        locationChangeIntent.putExtra(LOCATION_STATUS_DATA, mLastLocation);

        LocalBroadcastManager.getInstance(this).sendBroadcast(locationChangeIntent);
    }

    /**
     * Called when the GoogleAPIClient is ready
     * @param bundle
     */
    @Override
    public void onConnected(Bundle bundle) {
        startLocationUpdates();
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
    }

    /**
     * Called when GoogleApiClient is suspended
     * @param i
     */
    @Override
    public void onConnectionSuspended(int i) {

    }

    /**
     * Called when GoogleApiClient fails to connect
     * @param connectionResult
     */
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
