package com.palsoft.cyclingfront.client;

import com.palsoft.cyclingfront.interfaces.IView;
import com.palsoft.cyclingfront.messages.CFRequest;
import com.palsoft.cyclingfront.messages.CFResponse;
import android.content.Context;

public class PresenterJsonDelegate<S extends CFResponse> {

	private IView mView;
	
	public PresenterJsonDelegate(IView view)
	{
		mView = view;
	}

	public void onSuccessfulResponse(S result)
	{

	}
	
	public void onFailResponse(S result)
	{
		
	}


}
