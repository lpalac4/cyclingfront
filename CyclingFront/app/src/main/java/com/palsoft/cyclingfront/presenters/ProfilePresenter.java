package com.palsoft.cyclingfront.presenters;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.content.Loader;

import com.palsoft.cyclingfront.interfaces.IView;
import com.palsoft.cyclingfront.models.RideData;
import com.palsoft.cyclingfront.util.CFEnum;

import java.util.List;

public class ProfilePresenter extends BasePresenter {
	
	public IProfile mView;

	public interface IProfile extends IView
	{

		void populateRideHistory();
	}
	
	public ProfilePresenter(IProfile view)
	{
		mView = view;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onResume() {
		updateUI();
		
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		
	}

	public void updateUI() {
		mView.populateRideHistory();
		
	}

}
