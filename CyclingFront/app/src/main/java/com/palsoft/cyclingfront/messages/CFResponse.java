package com.palsoft.cyclingfront.messages;

import com.palsoft.cyclingfront.client.PresenterJsonDelegate;

public class CFResponse {
	
	public PresenterJsonDelegate<? extends CFResponse> responseDelegate;
	public Integer status;
	public boolean fault;
	public Exception[] exception;

	public static CFResponse createEmptyResponse() {
		// TODO Auto-generated method stub
		return null;
	}

}
