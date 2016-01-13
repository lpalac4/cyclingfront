package com.palsoft.cyclingfront.presenters;

import android.os.Bundle;

import com.palsoft.cyclingfront.interfaces.IView;

public abstract class BasePresenter
{
	public abstract void onCreate(Bundle savedInstanceState);
	public abstract void onResume();
	public abstract void onPause();
	public abstract void onDestroy();
    public abstract void updateUI();
}
