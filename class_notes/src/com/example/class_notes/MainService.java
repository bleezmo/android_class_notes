package com.example.class_notes;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

public class MainService extends Service{
	//this is th message we'll send back to MainActivity
	private String msg = "This message is from the service";
	//this is the flag to set to tell us the message is ready to be sent
	private boolean msgAvailable = false;
	//amount of time to wait before sending back the message
	private static final long TIMER_DELAY = 60000;
	@Override
	public void onCreate() {
		//message is initially unavailable
		msgAvailable = false;
		Timer tim = new Timer();
		//after a TIMER_DELAY amount of time, set message to available
		tim.schedule(new TimerTask(){
			@Override
			public void run() {
				msgAvailable = true;
				Log.i("MainService","The message is available");
				stopSelf();
			}
		}, TIMER_DELAY);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return Service.START_NOT_STICKY;
	}
	
	private Messenger messenger = new Messenger(new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
		}
	});
	@Override
	public IBinder onBind(Intent arg0) {		
		return messenger.getBinder();
	}

}
