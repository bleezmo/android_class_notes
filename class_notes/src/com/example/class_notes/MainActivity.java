package com.example.class_notes;

import utils.Either;
import utils.Failure;
import utils.Success;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnFinishedDownloadListener{
	private static class MyRunnable implements Runnable{
		private Activity ctx;
		public MyRunnable(Activity ctx){
			this.ctx = ctx;
		}
		@Override
		public void run() {
			try {
				Thread.sleep(10000);
				TextView tv = (TextView) ctx.findViewById(R.id.lbl);
				tv.setText("Look at me. My text has changed");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //We know this code fails because it stalls the UI thread
        //Thread.sleep(10000);
        
        //We knowthis code fails because it attempts to edit the ui threa
        //(new Thread(new MyRunnable(this))).start();
        
        //AsyncTask version. this will run just fine
        //new MyAsyncTask(this).execute();
        Handler h = new Handler();
        new Thread(new MyLongRunningThread(h,this)).start();
    }
	@Override
	public void onFinished() {
		Toast.makeText(MainActivity.this, "Toasty toast is toasting", Toast.LENGTH_SHORT).show();
		TextView tv = (TextView) findViewById(R.id.lbl);
		tv.setText("The text is different. Look at meeee!!!!");
	}
	@Override
	public void onFinishedDownload(String text) {
		TextView tv = (TextView) findViewById(R.id.lbl);
		tv.setText(text);
	}

}
