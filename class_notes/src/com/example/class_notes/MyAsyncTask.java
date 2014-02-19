package com.example.class_notes;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class MyAsyncTask extends AsyncTask<Void,Void,Void>{

	public interface OnFinishedListener{
		public void onFinished();
	}
	private OnFinishedListener listener;

	public MyAsyncTask(OnFinishedListener listener){
		this.listener = listener;
	}
	@Override
	protected Void doInBackground(Void... params) {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		listener.onFinished();
	}

}
