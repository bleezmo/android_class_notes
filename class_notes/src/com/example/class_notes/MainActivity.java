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
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Start a service to run independently of application
 * @author josh
 *
 */
public class MainActivity extends Activity {
	
	//this is used as a callback by the service in order to tell the activity when the
	// connection has been established, or disconnected
	private ServiceConnection serviceConn = new ServiceConnection(){

		@Override
		public void onServiceConnected(ComponentName className, IBinder service) {
			Log.i("MainActivity", "bound to service");
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






