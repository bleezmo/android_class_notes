package com.example.class_notes;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class MainService extends Service implements GetMessageListener{
	//Same as in MainActivity
	private static final int SERVICE_CONNECTION_ACK = 1;
	private static final int MESSAGE_RECEIVED = 2;
	//the MainActivity messenger to send messages to
	private Messenger clientMessenger = null;
	private String msg = null;
	@Override
	public void onCreate() {
		//start the process of getting the message
		new GetMessageTask(this).execute();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return Service.START_NOT_STICKY;
	}
	
	private Messenger messenger = new Messenger(new Handler(){
		@Override
		public void handleMessage(Message msg) {
			if(msg.what == SERVICE_CONNECTION_ACK){
				//set the client messenger to the MainActivity messenger
				//once we receive the ack from MainActivity
				clientMessenger = msg.replyTo;
				sendMessageToClient();
			}else{
				super.handleMessage(msg);
			}
		}
	});
	@Override
	public IBinder onBind(Intent arg0) {		
		return messenger.getBinder();
	}

	@Override
	public boolean onUnbind(Intent intent) {
		//we want to remove the reference from clientMessenger so that
		//MainActivity can be correctly garbage collected
		clientMessenger = null;
		return false;
	}
	/**
	 * received the message from GetMessageTask
	 */
	@Override
	public void onMessageReceived(String msg) {
		this.msg = msg;
		sendMessageToClient();
	}
	/**
	 * send the message to the client (MainActivity), if the client exists
	 */
	private void sendMessageToClient(){
		if(clientMessenger != null && this.msg != null){
			Message msg = Message.obtain();
			msg.what = MESSAGE_RECEIVED;
			Bundle data = new Bundle();
			data.putString("message", this.msg);
			msg.setData(data);
			try {
				clientMessenger.send(msg);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

}
