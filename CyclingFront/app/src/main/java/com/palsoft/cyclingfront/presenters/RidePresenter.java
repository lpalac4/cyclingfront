package com.palsoft.cyclingfront.presenters;

import android.os.Bundle;

import com.palsoft.cyclingfront.client.CFSqlClient;
import com.palsoft.cyclingfront.interfaces.IView;
import com.palsoft.cyclingfront.models.RideData;
import com.palsoft.cyclingfront.util.CFEnum;

import java.util.List;

public class RidePresenter extends BasePresenter {
	
	public IRide mView;
    public RideData mRide;

	public interface IRide extends IView
	{
        void showRideSummary(RideData mRide);
        void startNewRide();
    }
	
	public RidePresenter(IRide view)
	{
		mView = view;
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onResume()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPause()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDestroy()
	{
		// TODO Auto-generated method stub
		
	}

    @Override
    public void updateUI() {

    }

    public void startNewRide()
	{
		mRide = new RideData();

        mView.startNewRide();
	}

	public void mapClicked()
	{
		// TODO Auto-generated method stub
		
	}

    public void completeRide()
    {
        persistCurrentRide();
        mView.showRideSummary(mRide);
    }

    public void persistCurrentRide()
    {
        CFSqlClient.getInstance(mView.getContext()).persistRideData(mRide);
    }

	public void timeButtonClicked()
	{
		// TODO Auto-generated method stub
		
	}

}
