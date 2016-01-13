package com.palsoft.cyclingfront.views;

import javax.security.auth.callback.Callback;

import com.palsoft.cyclingfront.R;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DigitalClock;
import android.widget.RelativeLayout;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class RideTimer extends View {
	
	private RelativeLayout mRoot;
	private DigitalClock mClock;
	private TextView mDistance;
	private long mStartTime = 0;
	private long mRunningTime;
	
	private Handler mTimerHandler = new Handler();
	private Runnable mTimerRunnable = new Runnable() {
		
		@Override
		public void run() {
			long millis = System.currentTimeMillis() - mStartTime;
            int seconds = (int) (millis / 1000);
            mRunningTime = seconds % 60;
            
            //figure out the distance using google map lib
            setState(mRunningTime, 0);

            mTimerHandler.postDelayed(this, 500);

		}
	};
	
	public RideTimer(Context context) {
		super(context);
		initializeViews();
	}
	
	public RideTimer(Context context, AttributeSet attrs) {
		super(context, attrs);
		initializeViews();
	}

	public RideTimer(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initializeViews();
	}

	private void initializeViews() {
		mRoot = (RelativeLayout)inflate(getContext(), R.layout.view_ridetimer, null);
		mClock = (DigitalClock) mRoot.findViewById(R.id.timer);
		mDistance = (TextView) mRoot.findViewById(R.id.distance);
		
	}

	public void setState(long seconds, double miles) {
		int hours = (int) (seconds/60/60);
		int minutes = (int) ((seconds/60) - (hours * 60));
		int sec = (int) ((seconds/60) - minutes + hours);
		
		mClock.setText(String.valueOf(hours) + ":" + String.valueOf(minutes) + ":" + String.valueOf(sec));
		
	}
	
	public void setDistance(int distance)
	{
		mDistance.setText(String.valueOf(distance));
	}
	
	public void onStartTimer()
	{
		mStartTime = System.currentTimeMillis();
        mTimerHandler.postDelayed(mTimerRunnable, 0);

	}
	
	public void onStopTime()
	{
		mTimerHandler.removeCallbacks(mTimerRunnable);
	}
	
	@Override
	protected void onDetachedFromWindow() {
		onStopTime();
		super.onDetachedFromWindow();
	}
	


	

}
