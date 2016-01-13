package com.palsoft.cyclingfront.activities;

import com.palsoft.cyclingfront.R;
import com.palsoft.cyclingfront.interfaces.IView;
import com.palsoft.cyclingfront.presenters.BasePresenter;

import android.app.AlertDialog;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

public class BaseActivity extends Activity implements IView {

	private AnimationDrawable mLoadingImage;
	private Dialog mDialog;
	protected BasePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
	protected void onDestroy()
	{
		super.onDestroy();

		// notify any presenters listening to this activities lifecycle that this activity is no longer active
		mPresenter.onDestroy();
	}

	@Override
	public void onUnhandledError()
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(getContext()).setMessage(this.getResources().getString(R.string.generic_error_message));
		builder.setPositiveButton(R.string.ok, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				
			}
		});
		
		mDialog = builder.create();
		mDialog.show();
		
	}

	@Override
	public void onNetworkError() {
		AlertDialog.Builder builder = new AlertDialog.Builder(getContext()).setMessage(this.getResources().getString(R.string.generic_error_message));
		builder.setPositiveButton(R.string.ok, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				
			}
		});
		
		mDialog = builder.create();
		mDialog.show();
		
	}

	@Override
	public Context getContext() {
		return this;
	}
	
	protected void createDialogMessage(String message, String positive, final DialogInterface.OnClickListener delegate)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(getContext()).setMessage(message);
		builder.setPositiveButton(positive, delegate);

		mDialog = builder.create();
		mDialog.show();
	}
	
	protected void createDialogProcessing(String message, int animationRes)
	{
		ImageView loadingImageContainer = (ImageView) findViewById(animationRes);
        mLoadingImage = (AnimationDrawable) loadingImageContainer.getBackground();
	}
	
	@Override
	public void showLoadingAnimation()
	{
		if(mLoadingImage != null)
		{
			mLoadingImage.start();	
		}		
	}
	@Override
	public void stopLoadingAnimation()
	{
		if(mLoadingImage != null)
		{
			mLoadingImage.stop();
		}
	}
	@Override
	public void onGenericAppError()
	{
		createDialogMessage(getResources().getString(R.string.generic_error_message),
				getResources().getString(R.string.ok), new OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });
		
	}

    @Override
    protected void onResume() {
        super.onResume();

        mPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        mPresenter.onPause();
    }

    protected void closeApplication()
    {
        finishAffinity();
    }

    public void setViewVisibility(int viewResId, int visibility)
	{
		findViewById(viewResId).setVisibility(visibility);
	}
	

}
