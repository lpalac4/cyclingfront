package com.palsoft.cyclingfront.models;

import com.palsoft.cyclingfront.R;
import com.palsoft.cyclingfront.interfaces.IMenu;

public class SideMenuItem {

	private String mTitle;
	private IMenu mTouchDelegate;
	private MenuType mType;
	
	public enum MenuType
	{
		CFPROFILE,
		CFSETTINGS,
		CFRIDE,
		CFEXIT
	}
	
	public SideMenuItem(String title, IMenu menuDelegate, MenuType type)
	{
		mTitle =  title;
		mTouchDelegate = menuDelegate;
		mType = type;
	}
	
	public void selected()
	{
		switch(mType)
		{
		case CFEXIT:
			mTouchDelegate.exitApplication();
			break;
		case CFPROFILE:
			mTouchDelegate.openProfile();
			break;
		case CFRIDE:
			mTouchDelegate.openNewBikeRide();
			break;
		case CFSETTINGS:
			mTouchDelegate.openSettings();
			break;
		default:
			break;
		
		}
	}
	
	public String getTitle()
	{
		return mTitle;
	}
	
	public int getImageId()
	{
		switch (mType)
		{
			case CFEXIT:
				return R.drawable.ic_menu_close_clear_cancel;
			case CFPROFILE:
				return R.drawable.ic_contact_picture;
			case CFRIDE:
				return R.drawable.ic_ride;
			case CFSETTINGS:
				return R.drawable.ic_sysbar_quicksettings;
		}

		return 0;
	}
}
