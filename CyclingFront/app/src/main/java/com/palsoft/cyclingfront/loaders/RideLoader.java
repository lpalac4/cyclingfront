package com.palsoft.cyclingfront.loaders;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.CursorAdapter;
import android.widget.ListView;

import com.palsoft.cyclingfront.adapters.RecyclerCursorAdapter;
import com.palsoft.cyclingfront.contentproviders.RideProvider;

/**
 * Created by luispalacios on 9/30/15.
 */
public class RideLoader implements LoaderManager.LoaderCallbacks<Cursor> {

    private final Context mContext;
    private final RecyclerView mListView;
    private RecyclerCursorAdapter mRideAdapter;

    public RideLoader(Context ctx, RecyclerCursorAdapter adapter, RecyclerView list)
    {
        mContext = ctx;
        mRideAdapter = adapter;
        mListView = list;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = { RideProvider._ID, RideProvider.DATE, RideProvider.RIDE_MILES, RideProvider.RIDE_HOURS,
                RideProvider.RIDE_MINUTES, RideProvider.RIDE_SECONDS};
        CursorLoader cursorLoader = new CursorLoader(mContext,
                RideProvider.CONTENT_URI, projection, null, null, null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mRideAdapter.swapCursor(data);
        mListView.setLayoutManager(new LinearLayoutManager(mContext));

        mListView.setAdapter(mRideAdapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // data is not available anymore, delete reference
        mRideAdapter.swapCursor(null);
    }
}