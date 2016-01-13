package com.palsoft.cyclingfront.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.palsoft.cyclingfront.CFApplication;
import com.palsoft.cyclingfront.R;
import com.palsoft.cyclingfront.adapters.SideMenuAdapter;
import com.palsoft.cyclingfront.interfaces.IMenu;
import com.palsoft.cyclingfront.models.SideMenuItem;
import com.palsoft.cyclingfront.presenters.DrawerPresenter;

import java.util.ArrayList;
import java.util.List;


public class MainDrawerActivity extends BaseActivity implements IMenu, DrawerPresenter.IDrawer {

	private final String LOG_TAG = MainDrawerActivity.class.getSimpleName();

	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private ListView mSideMenu;

	private static ArrayList<SideMenuItem> mSideMenuItems;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        mPresenter = new DrawerPresenter(this);

		setContentView(R.layout.activity_main_drawer);
		
		mTitle = mDrawerTitle = getTitle();
		
		mSideMenu = (ListView) findViewById(R.id.left_drawer);

		mSideMenu.setAdapter(new SideMenuAdapter(this, R.layout.adapter_sidemenu_item, getMenuItemList()));
		mSideMenu.setOnItemClickListener(new DrawerItemClickListener());
		
		// Set drawer listeners
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.string.drawer_open, R.string.drawer_closed)
		{
			public void onDrawerClosed(View view)
			{
				super.onDrawerClosed(view);
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu();
			}
			
			public void onDrawerOpened(View drawerView)
			{
				super.onDrawerOpened(drawerView);
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu();
			}
		};
		
		mDrawerLayout.setDrawerListener(mDrawerToggle);

        if(getActionBar() != null)
        {
            getActionBar().setDisplayHomeAsUpEnabled(true);
            getActionBar().setHomeButtonEnabled(true);
        }
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mSideMenu);
		// hide any action buttons that show when the drawer is not visible.
		return super.onPrepareOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(mDrawerToggle.onOptionsItemSelected(item))
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private List<SideMenuItem> getMenuItemList() {
		if(mSideMenuItems == null)
		{
			mSideMenuItems = new ArrayList<SideMenuItem>();
			mSideMenuItems.add(new SideMenuItem(CFApplication.getAppSettings().getProfileName(), this, SideMenuItem.MenuType.CFPROFILE));
			mSideMenuItems.add(new SideMenuItem(getResources().getString(R.string.main_menu_new_ride), this, SideMenuItem.MenuType.CFRIDE));
			mSideMenuItems.add(new SideMenuItem(getResources().getString(R.string.main_menu_settings), this, SideMenuItem.MenuType.CFSETTINGS));
			mSideMenuItems.add(new SideMenuItem(getResources().getString(R.string.main_menu_exit), this, SideMenuItem.MenuType.CFEXIT));
		}
		
		return mSideMenuItems;		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_drawer, menu);
		return true;
	}
	
	/**
	 * Menu item selected coordinate this with the list of menu items to open the proper fragment/activity
	 * @param position
	 */
	private void selectItem(int position)
	{
        if(mDrawerLayout.isDrawerOpen(Gravity.LEFT))
        {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        }

		switch (position) {
		case 0:
			openProfile();
			break;
		case 1:
			openNewBikeRide();
			break;
		case 2:
			openSettings();
			break;
		case 3:
			exitApplication();
			break;

		default:
			break;
		}
	}

    @Override
    public void openSettings()
    {

    }

    @Override
    public void openProfile()
    {

    }

    @Override
    public void openNewBikeRide()
    {

    }

    @Override
    public void exitApplication()
    {
        DialogInterface.OnClickListener closeListener = new DialogInterface.OnClickListener()
        {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d(LOG_TAG, "Closing application");
                // TODO: Save data before quitting
                closeApplication();

            }
        };

        createDialogMessage(getResources().getString(R.string.exit_confirmation_message),
                getResources().getString(R.string.ok),
                closeListener);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener
	{
		private Integer mSelectedPosition = null;

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			if(mSelectedPosition == null || mSelectedPosition != position)
			{
				mSelectedPosition = position;
				selectItem(position);
			}
		}
		
	}
	
	protected void removeFragmentFromResource(Fragment fragment)
	{
		FragmentTransaction fTrans = getFragmentManager().beginTransaction();
		fTrans.remove(fragment);
		fTrans.commit();
	}
	
	protected void switchFragments(int resourceId, Fragment newFragment, boolean addToBackStack)
	{
		FragmentTransaction fTrans = getFragmentManager().beginTransaction();
		fTrans.replace(resourceId, newFragment);
		if(addToBackStack)
			fTrans.addToBackStack(null);
		fTrans.commit();
	}

	
}
