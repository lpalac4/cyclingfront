package com.palsoft.cyclingfront.activities;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.palsoft.cyclingfront.CFApplication;
import com.palsoft.cyclingfront.broadcastreceivers.CFLocationBroadcastReceiver;
import com.palsoft.cyclingfront.models.ApplicationSettings;
import com.palsoft.cyclingfront.services.CFLocationService;
import com.palsoft.cyclingfront.R;


public class CFMapsActivity extends FragmentActivity implements OnMapReadyCallback, CFLocationBroadcastReceiver.ILocationConsumer, View.OnClickListener {

    private MapFragment mMapFragment;
    private GoogleMap mMap;
    private CFLocationBroadcastReceiver mLocationReceiver;
    private boolean mCompletingRide;
    private Button mEndRideButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cfmaps);
        mEndRideButton = (Button) findViewById(R.id.end_ride_button);
        mEndRideButton.setOnClickListener(this);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterLocationChangeReceiver();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMapFragment == null &&
                checkPermission("android.permission.access_fine_location", 0, 0) == PackageManager.PERMISSION_GRANTED) {
            // Try to obtain the map from the SupportMapFragment.
            mMapFragment = ((MapFragment) getFragmentManager().findFragmentById(R.id.map));
            // Check if we were successful in obtaining the map.
            if (mMapFragment != null) {
                mMapFragment.getMapAsync(this);
            }
        }
    }

    /**
     * Called by the GoogleMapFragment.getMapAsync() method when it is ready
     * @param googleMap
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        registerService();
        registerBroadcastReceiver();

        prepareService();
    }

    /**
     * Pass necessary values to the background service managing location data.
     */
    private void prepareService() {
        Intent serviceInitIntent = new Intent(CFLocationService.LOCATION_STATUS_ACTION);
        serviceInitIntent.putExtra(ApplicationSettings.USERNAMEKEY, CFApplication.getAppSettings().getProfileName());
        startService(serviceInitIntent);
    }

    private void endRideService() {
        Intent serviceEndIntent = new Intent(CFLocationService.RIDE_END);
        startService(serviceEndIntent);
    }

    private void unregisterLocationChangeReceiver() {
        if(mLocationReceiver != null) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(mLocationReceiver);
        }
    }

    private void registerBroadcastReceiver() {
        if(mLocationReceiver == null){
            mLocationReceiver = new CFLocationBroadcastReceiver(this);
        }

        IntentFilter locationIntentFilter = new IntentFilter(CFLocationService.LOCATION_STATUS_DATA);
        LocalBroadcastManager.getInstance(this).registerReceiver(mLocationReceiver, locationIntentFilter);
    }

    private void registerService() {
        Intent locationIntent = new Intent(this, CFLocationService.class);
        startService(locationIntent);
    }

    @Override
    public void locationChange(Location location) {

        /** Filter location changes that match the following criteria
         * 1 ) Delta with previous location < 5 meters
         * 2 ) Were received after the user has selected to end their ride
         */
        if(mCompletingRide) {
            return;
        }
        
        Location currentLoc = mMap.getMyLocation();
        
        if(currentLoc.distanceTo(location) < 5)
            return;

        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.end_ride_button) {
            mCompletingRide = true;
            endRideService();
            cleanUpBeforeEnding();
        }
    }

    private void cleanUpBeforeEnding() {
        unregisterLocationChangeReceiver();
        mLocationReceiver = null;
    }
}
