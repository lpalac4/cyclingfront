package com.palsoft.cyclingfront.presenters;

import android.os.Bundle;

import com.palsoft.cyclingfront.activities.LoginActivity;
import com.palsoft.cyclingfront.interfaces.IView;

/**
 * Created by luispalacios on 10/4/15.
 */
public class LoginPresenter extends BasePresenter
{
    public ILogin mView;

    public interface ILogin extends IView
    {

    }

    public LoginPresenter(ILogin view) {
        mView = view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void updateUI() {

    }
}
