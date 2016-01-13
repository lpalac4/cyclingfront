package com.palsoft.cyclingfront.interfaces;

import android.content.Context;

public interface IView {
	
	public abstract void onUnhandledError();
	public abstract void onNetworkError();
	public abstract void onGenericAppError();
	public abstract Context getContext();
	public abstract void showLoadingAnimation();
	public abstract void stopLoadingAnimation();
	
}
