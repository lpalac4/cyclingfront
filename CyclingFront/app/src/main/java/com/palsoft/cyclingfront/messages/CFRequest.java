package com.palsoft.cyclingfront.messages;

import com.palsoft.cyclingfront.client.PresenterJsonDelegate;
import com.palsoft.cyclingfront.messages.CreateSession.Response;

public class CFRequest {

	private PresenterJsonDelegate<? extends CFResponse> mDelegate;

	private static Integer mDeviceId;
	private static String mAuthSessionId;

	public void setDelegate(PresenterJsonDelegate<? extends CFResponse> delegate) {
		mDelegate = delegate;
	}

	public PresenterJsonDelegate<? extends CFResponse> getDelegate() {
		return mDelegate;
	}
	

}
