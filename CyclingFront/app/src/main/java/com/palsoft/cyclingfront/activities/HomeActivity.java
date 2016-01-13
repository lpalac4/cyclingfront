package com.palsoft.cyclingfront.activities;

import com.palsoft.cyclingfront.CFApplication;
import com.palsoft.cyclingfront.R;
import com.palsoft.cyclingfront.fragments.BaseFragment;
import com.palsoft.cyclingfront.fragments.ProfileFragment;
import com.palsoft.cyclingfront.fragments.RideFragment;
import com.palsoft.cyclingfront.fragments.SettingsFragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.FrameLayout;

public class HomeActivity extends MainDrawerActivity {

    private static final String LOG_TAG = HomeActivity.class.getSimpleName();
	private FrameLayout mContentLayout;
	private FrameLayout mLeftDetails;
	private FrameLayout mRightDetails;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mContentLayout = (FrameLayout) findViewById(R.id.content_frame);
		mLeftDetails = (FrameLayout) findViewById(R.id.left_details);
		mRightDetails = (FrameLayout) findViewById(R.id.right_details);
		
		initializeLayout();
	}

	private void initializeLayout() {
		FragmentTransaction fTrans = getFragmentManager().beginTransaction();
		
		ProfileFragment profileFragment = new ProfileFragment();

		Intent profileIntent = new Intent();
		profileIntent.putExtra("username", CFApplication.getAppSettings().getProfileName());
		profileFragment.setArguments(profileIntent.getExtras());

		fTrans.replace(mLeftDetails.getId(), profileFragment);
		
		if(CFApplication.getAppSettings().deviceIsTablet())
		{
			RideFragment rightDetailsFragment = new RideFragment();			
			fTrans.replace(mRightDetails.getId(), rightDetailsFragment);
		}	
		
		fTrans.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}
	
	public void attachFragmentToRightDetails(Fragment fragment)
	{
		switchFragments(mRightDetails.getId(), fragment, false);
	}
	
	public void attachFragmentToLeftDetails(Fragment fragment)
	{
		switchFragments(mLeftDetails.getId(), fragment, false);
	}
	
	
	@Override
	public void openSettings()
	{
		Fragment rightFrag = getFragmentManager().findFragmentById(mRightDetails.getId());
		if(rightFrag != null)
			removeFragmentFromResource(rightFrag);
		
		attachFragmentToLeftDetails(new SettingsFragment());		
	}

	@Override
	public void openProfile()
	{
		attachFragmentToLeftDetails(new ProfileFragment());		
	}

	@Override
	public void openNewBikeRide()
	{
		attachFragmentToLeftDetails(new RideFragment());
	}

}
