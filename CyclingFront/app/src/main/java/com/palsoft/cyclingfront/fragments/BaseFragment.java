package com.palsoft.cyclingfront.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.palsoft.cyclingfront.R;
import com.palsoft.cyclingfront.adapters.RecyclerCursorAdapter;
import com.palsoft.cyclingfront.adapters.RideCursorAdapter;
import com.palsoft.cyclingfront.interfaces.IView;
import com.palsoft.cyclingfront.loaders.RideLoader;
import com.palsoft.cyclingfront.presenters.BasePresenter;

public class BaseFragment extends Fragment implements IView {

    private AnimationDrawable mLoadingImage;
    private Dialog mDialog;
    private CursorAdapter cursorRideAdapter;
    protected View mRootView;
    protected BasePresenter mPresenter;

    public BaseFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    public void setArguments(Bundle args) {

    }

    protected void exitApplication() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setPositiveButton(getResources().getString(R.string.ok), new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        })
                .setNegativeButton(getResources().getString(R.string.cancel), new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                })
                .setMessage(getResources().getString(R.string.exit_confirmation_message))
                .show();
    }

    public void createDialogMessage(String message, String buttonText) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setPositiveButton(buttonText, new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        })
                .setMessage(message)
                .show();
    }

    public void createDialogProcessing(String message, int animationRes) {
        ImageView loadingImageContainer = (ImageView) ((Activity) getContext()).findViewById(animationRes);
        mLoadingImage = (AnimationDrawable) loadingImageContainer.getBackground();
    }


    @Override
    public void onUnhandledError() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext()).setMessage(getContext().getString(R.string.generic_error_message));
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
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext()).setMessage(getContext().getResources().getString(R.string.generic_error_message));
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
    public void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public void showLoadingAnimation() {
        if (mLoadingImage != null) {
            mLoadingImage.start();
        }
    }

    @Override
    public void stopLoadingAnimation() {
        if (mLoadingImage != null) {
            mLoadingImage.stop();
        }
    }

    @Override
    public void onGenericAppError() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext()).setMessage(getActivity().getResources().getString(R.string.generic_error_message));
        builder.setPositiveButton(R.string.ok, new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });

        mDialog = builder.create();
        mDialog.show();

    }

    public void setViewVisibility(int viewResId, int visibility) {
        View view = mRootView.findViewById(viewResId);

        if(view != null) view.setVisibility(visibility);
    }
    
    public void initRideLoader(RecyclerView list)
    {
        RideLoader rideLoader = new RideLoader(getContext(), new RecyclerCursorAdapter(getContext(), null, 0), list);
        getLoaderManager().initLoader(0, null, rideLoader);
    }

}
