package com.palsoft.cyclingfront.fragments;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.palsoft.cyclingfront.R;
import com.palsoft.cyclingfront.models.RideData;
import com.palsoft.cyclingfront.presenters.ProfilePresenter;

import java.util.List;

public class ProfileFragment extends BaseFragment implements ProfilePresenter.IProfile {

	private String mUsername;
	private List<RideData> mRides;
	
	private TextView mUsernameText;
	private TextView mUserLevel;
	private RecyclerView mRidesTaken;
	private RelativeLayout mRootView;
	private String mUserlevel;
	
	public ProfileFragment() 
	{
		
	}
	
	@Override
	public void setArguments(Bundle args)
	{
		super.setArguments(args);
		
		if(args != null)
		{
			String username = args.getString("username");
			String userlevel = args.getString("userlevel");
			
			if(username != null)
			{
				mUsername = username;
			}
			
			if(userlevel != null)
			{
				mUserlevel = userlevel;
			}
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) 
	{		
		mRootView = (RelativeLayout) inflater.inflate(R.layout.fragment_profile, null);

		initializeViews();
		
		return mRootView;		
	}

	protected void initializeViews()
	{
		mUsernameText = (TextView) mRootView.findViewById(R.id.username_text);
		if(mUsername != null)
			mUsernameText.setText(mUsername);
		
		mUserLevel = (TextView) mRootView.findViewById(R.id.user_level);
		if(mUserlevel != null)
			mUserLevel.setText(mUserlevel);
		
		mRidesTaken = (RecyclerView) mRootView.findViewById(R.id.list_rides);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		mPresenter = new ProfilePresenter(this);
		
	}

	@Override
	public void populateRideHistory()
	{
		initRideLoader(mRidesTaken);
	}
}
