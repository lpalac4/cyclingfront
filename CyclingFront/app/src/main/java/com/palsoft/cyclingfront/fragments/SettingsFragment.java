package com.palsoft.cyclingfront.fragments;

import android.os.Bundle;

import com.palsoft.cyclingfront.presenters.SettingsPresenter;

public class SettingsFragment extends BaseFragment implements SettingsPresenter.ISettings {

	public SettingsFragment()
    {

	}

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        mPresenter = new SettingsPresenter(this);
        mPresenter.onCreate(savedInstanceState);
    }

    @Override
	public void setArguments(Bundle args)
    {
		super.setArguments(args);
	}

	@Override
	public void logout()
    {

	}
	
	
}
