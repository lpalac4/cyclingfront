package com.palsoft.cyclingfront.presenters;

import android.os.Bundle;

import com.palsoft.cyclingfront.interfaces.IView;
import com.palsoft.cyclingfront.models.RideData;
import com.palsoft.cyclingfront.util.CFEnum;

import java.util.List;

public class SettingsPresenter extends BasePresenter {

	public ISettings mView;
	
	public interface ISettings extends IView
	{
		public void logout();
	}
	
	public SettingsPresenter(ISettings view)
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
		mView.logout();
	}

    @Override
    public void updateUI() {

    }
}
