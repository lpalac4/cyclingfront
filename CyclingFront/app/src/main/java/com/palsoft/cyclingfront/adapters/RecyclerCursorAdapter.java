package com.palsoft.cyclingfront.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.palsoft.cyclingfront.R;

/**
 * Created by luispalacios on 10/4/15.
 */
public class RecyclerCursorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context mContext;
    private final int mFlags;
    private RideCursorAdapter mCursor;

    public RecyclerCursorAdapter(Context context, Cursor cursor, int flags)
    {
        mContext = context;
        mFlags = flags;
        mCursor = new RideCursorAdapter(context, cursor, flags);

    }

    @Override
    public CFCursorViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View v = mCursor.newView(mContext, mCursor.getCursor(), viewGroup);
        return new CFCursorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i)
    {
        mCursor.getCursor().moveToPosition(i);
        mCursor.bindView(viewHolder.itemView, mContext, mCursor.getCursor());
    }

    @Override
    public int getItemCount() {
       return mCursor.getCount();
    }

    public void swapCursor(Cursor data) {
        if(mCursor != null && mCursor.getCursor() == data) return;

        mCursor.swapCursor(data);
        notifyDataSetChanged();
    }

    public static class CFCursorViewHolder extends RecyclerView.ViewHolder{

        View view;

        public CFCursorViewHolder(View itemView) {
            super(itemView);

            view = itemView.findViewById(R.id.ride_history);
        }
    }

}
