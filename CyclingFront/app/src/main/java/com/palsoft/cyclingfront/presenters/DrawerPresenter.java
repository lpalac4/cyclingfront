package com.palsoft.cyclingfront.presenters;

import android.os.Bundle;

import com.palsoft.cyclingfront.activities.MainDrawerActivity;
import com.palsoft.cyclingfront.interfaces.IView;

/**
 * Created by luispalacios on 10/4/15.
 */
public class DrawerPresenter extends BasePresenter
{
    public IDrawer mView;

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

    public interface IDrawer extends IView
    {

    }

    public DrawerPresenter(IDrawer view)
    {
        super();
        mView = view;
    }
}
