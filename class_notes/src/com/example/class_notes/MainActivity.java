package com.example.class_notes;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

/**
 * Start a service to run independently of application
 * @author josh
 *
 */
public class MainActivity extends Activity {
	//Same as in MainService
	private static final int SERVICE_CONNECTION_ACK = 1;

	private static final int MESSAGE_RECEIVED = 2;
	//messenger for this activity
	private Messenger messenger = new Messenger(new Handler(){
		@Override
		public void handleMessage(Message msg) {
			Log.i("MainActivity","got message");
			if(msg.what == MESSAGE_RECEIVED){
				//we've received the message from the service
				//now display it
				Bundle data = msg.getData();
				String message = data.getString("message");
				Log.i("MainActivity","received message "+message);
				TextView tv = (TextView) MainActivity.this.findViewById(R.id.message);
				tv.setText(message);
			}
		}
	});
	//this is used as a callback by the service in order to tell the activity when the
	// connection has been established, or disconnected
	private ServiceConnection serviceConn = new ServiceConnection(){

		@Override
		public void onServiceConnected(ComponentName className, IBinder service) {
			Log.i("MainActivity", "bound to service");
			Messenger serviceMessenger = new Messenger(service);
			//send an acknowledgement message back to service
			//message contains this activities messenger so that service
			//can send more messages to activity
			Message message = Message.obtain();
			message.what = SERVICE_CONNECTION_ACK;
			message.replyTo = messenger;
			try {
				serviceMessenger.send(message);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onServiceDisconnected(ComponentName className) {
			
		}
		
	};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = new Intent(this,MainService.class);
        startService(i);
        //bind to the service, passing in the established service connection
        bindService(i,serviceConn,0);
    }

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unbindService(serviceConn);
	}

}






