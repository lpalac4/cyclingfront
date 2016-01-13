package com.palsoft.cyclingfront.presenters;

import android.os.Bundle;

import com.palsoft.cyclingfront.client.CFJsonClient;
import com.palsoft.cyclingfront.client.PresenterJsonDelegate;
import com.palsoft.cyclingfront.interfaces.IView;
import com.palsoft.cyclingfront.messages.CreateSession;

public class LoaderPresenter extends BasePresenter {

    public ILoader mView;

	public interface ILoader extends IView
	{

		void loadHomeActivity();
		void loadLoginActivity();
		
	}
	
	public LoaderPresenter(ILoader view)
	{
		mView = view;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{

	}
	
	private void loadAppData() 
	{
		mView.showLoadingAnimation();
		
		initializeSession();
		
		mView.stopLoadingAnimation();
	}

	private void initializeSession() {
		CFJsonClient client = CFJsonClient.getInstance();
		CreateSession.Request request = CreateSession.createRequest();
		
		PresenterJsonDelegate<CreateSession.Response> delegate = new PresenterJsonDelegate<CreateSession.Response>(mView)
		{
			@Override
			public void onSuccessfulResponse(CreateSession.Response response)
			{
				loadLoginActivity();
			}
			
			@Override
			public void onFailResponse(CreateSession.Response response) 
			{
				mView.onNetworkError();
			}
		};

		client.createSession(request, delegate);		
		
	}

	protected void loadHomeActivity() {
        mView.loadHomeActivity();
		
	}
	
	protected void loadLoginActivity()
	{
		mView.loadLoginActivity();
	}

	@Override
	public void onResume() 
	{
        loadAppData();

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


}
