package com.palsoft.cyclingfront.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.palsoft.cyclingfront.R;
import com.palsoft.cyclingfront.activities.CFMapsActivity;
import com.palsoft.cyclingfront.models.RideData;
import com.palsoft.cyclingfront.presenters.RidePresenter;
import com.palsoft.cyclingfront.views.RideTimer;

public class RideFragment extends BaseFragment implements RidePresenter.IRide {

	private Button mNewRideButton;
	private SurfaceView mMapLayout;
	private ImageView mMapCover;
	private RideTimer mRideTimer;

    private Fragment mSummaryFragment;

    public RideFragment()
	{
		
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mPresenter = new RidePresenter(this);
		mPresenter.onCreate(savedInstanceState);
	}

	@Override
	public void setArguments(Bundle args) {
		// TODO Auto-generated method stub
		super.setArguments(args);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
        mRootView = (RelativeLayout) inflater.inflate(R.layout.fragment_ride, null);
		initFragmentView(mRootView);
		
		return mRootView;
	}
	
	@Override
	public void onPause() {
		super.onPause();
	}
	
	@Override
	public void onResume() {
		super.onResume();
	}
	
	public void initFragmentView(View mRoot)
	{
		mNewRideButton = (Button) mRoot.findViewById(R.id.ride_new_ride_button);
		mNewRideButton.setOnClickListener(new View.OnClickListener()
        {
			
			@Override
			public void onClick(View v) {
                ((RidePresenter) mPresenter).startNewRide();
				
			}
		});

		mMapCover = (ImageView) mRoot.findViewById(R.id.ride_gmap_cover);
		mMapLayout = (SurfaceView) mRoot.findViewById(R.id.ride_gmap_view);
		mMapLayout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
                ((RidePresenter) mPresenter).mapClicked();
			}
		});
		
		mRideTimer = (RideTimer) mRoot.findViewById(R.id.ride_rideTimer);		
	}	
	
	public void enableMap() {
		mMapLayout.setVisibility(View.VISIBLE);
		mMapCover.setVisibility(View.GONE);

        // start google maps library
        startActivity(new Intent(getActivity(), CFMapsActivity.class));
	}
	
	public void disableMap()
	{
		mMapLayout.setVisibility(View.GONE);
		mMapCover.setVisibility(View.VISIBLE);	

	}
	
	public void showInactiveScreen()
	{

	}

	public void showActiveScreen()
	{

	}
	
	public void onSavedTimerInfo(int seconds, double miles)
	{
		mRideTimer.setState(seconds, miles);
	}

    @Override
    public void showRideSummary(RideData mRide)
    {
        FragmentManager fragManager = getFragmentManager();
        mSummaryFragment = fragManager.findFragmentByTag(RideSummaryDialogFragment.TAG);

        if(mSummaryFragment == null)
        {
            mSummaryFragment = new RideSummaryDialogFragment();
        }

        fragManager.beginTransaction().add(mSummaryFragment, RideSummaryDialogFragment.TAG);

    }

    @Override
    public void startNewRide() {
        enableMap();
    }
}
