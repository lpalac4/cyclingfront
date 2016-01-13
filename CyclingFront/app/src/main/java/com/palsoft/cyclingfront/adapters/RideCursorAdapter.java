package com.palsoft.cyclingfront.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.palsoft.cyclingfront.R;
import com.palsoft.cyclingfront.contentproviders.RideProvider;

/**
 * Created by luispalacios on 9/26/15.
 */
public class RideCursorAdapter extends CursorAdapter {

    public RideCursorAdapter(Context context, Cursor cursor, int flags)
    {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup)
    {
        return LayoutInflater.from(context).inflate(R.layout.adapter_ride_history_item, null);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor)
    {
        TextView date = (TextView) view.findViewById(R.id.date);
        TextView distance = (TextView) view.findViewById(R.id.distance);
        TextView duration = (TextView) view.findViewById(R.id.duration);

        String hours = cursor.getString(cursor.getColumnIndexOrThrow(RideProvider.RIDE_HOURS));
        String minutes = cursor.getString(cursor.getColumnIndexOrThrow(RideProvider.RIDE_MINUTES));
        String seconds = cursor.getString(cursor.getColumnIndexOrThrow(RideProvider.RIDE_SECONDS));

        String durationString = hours + " hours " + minutes + " minutes " + seconds + " seconds";

        date.setText(cursor.getString(cursor.getColumnIndexOrThrow(RideProvider.DATE)));
        distance.setText(cursor.getString(cursor.getColumnIndexOrThrow(RideProvider.RIDE_MILES)));
        duration.setText(durationString);
    }
}
