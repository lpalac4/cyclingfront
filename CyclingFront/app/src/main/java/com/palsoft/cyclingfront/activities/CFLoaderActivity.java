package com.palsoft.cyclingfront.activities;

import com.palsoft.cyclingfront.R;
import com.palsoft.cyclingfront.presenters.LoaderPresenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

/**
 * Loader activity
 * @author Luis
 *
 */
public class CFLoaderActivity extends BaseActivity implements LoaderPresenter.ILoader 
{
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        if(getActionBar() != null)
        {
            getActionBar().hide();
        }

        mPresenter = new LoaderPresenter(this);
        mPresenter.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cfmain);        

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cfmain, menu);
        return true;
    }

	@Override
	public void loadHomeActivity() {
		Intent homeIntent = new Intent(this, HomeActivity.class);
		startActivity(homeIntent);
	}

	@Override
	public void loadLoginActivity() {
		Intent loginIntent = new Intent(this, LoginActivity.class);
		startActivity(loginIntent);
				
	}
        
    
}
