package com.palsoft.cyclingfront.client;

import android.os.AsyncTask;

import com.palsoft.cyclingfront.messages.CFRequest;
import com.palsoft.cyclingfront.messages.CFResponse;
import com.palsoft.cyclingfront.models.ApplicationSettings;

public class CFJsonClient <R extends CFRequest, S extends CFResponse>
{

	private static ApplicationSettings mSettings;
	private static CFJsonClient mClient;
	private PresenterJsonDelegate<S> mDelegate;
	private R mRequest;
	
	public static CFJsonClient getInstance()
	{
		if(mClient == null)
		{
			mClient = new CFJsonClient();
		}

		return mClient;
	}
	
	public static enum ResponseStatus
	{
		invalid(0),
		valid(1);
		
		public int mValue;
		
		private ResponseStatus(int value)
		{
			mValue = value;
		}
		
		public int getValue()
		{
			return mValue;
		}
	}

	@SuppressWarnings("unchecked")
	public void createSession(R request, PresenterJsonDelegate<? extends S> delegate)
	{
		mDelegate = (PresenterJsonDelegate<S>) delegate;
		new JsonCallTask().execute(request);
		
	}

	
	public class JsonCallTask extends AsyncTask<R, Exception, S>
	{

		@Override
		protected S doInBackground(R... request)
		{
			S response = null;

			try
			{
				response = sendCall(request);

			}
			catch (Exception e)
			{
				publishProgress(e);
				cancel(false);
			}

			return response;
		}
		
		@Override
		protected void onPostExecute(S result)
		{
			super.onPostExecute(result);
			
			if(result == null)
			{	
				//testing
				mDelegate.onSuccessfulResponse(result);
				return;
			}
			
			if(result.status == ResponseStatus.valid.getValue())
			{
				mDelegate.onSuccessfulResponse(result);
			}
			else
			{
				mDelegate.onFailResponse(result);
			}
		}

		@Override
		protected void onProgressUpdate(Exception... values)
		{
			CFResponse response = new CFResponse();
			response.fault = true;
			response.exception = values;

			mDelegate.onFailResponse((S) response);
		}

	}

	private S sendCall(R[] request)
	{
		return null;
	}
}
