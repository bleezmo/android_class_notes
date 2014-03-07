package com.example.class_notes;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import utils.Downloader;
import utils.Either;
import utils.Failure;
import utils.MyPullParser;
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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This first activity just shows a static list view that does
 * nothing when the items are clicked
 * @author josh
 *
 */
public class MainActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        (new Thread(new Runnable(){
			@Override
			public void run() {
				final Either<StringBuffer> optbuf = Downloader.downloadText("http://cis228.herokuapp.com/assets/objectives.txt");
				if(optbuf.isSuccess()){
					MainActivity.this.runOnUiThread(new Runnable(){
						@Override
						public void run() {
							TextView tv = (TextView) findViewById(R.id.mytxt);
							tv.setText(optbuf.getObject().toString());
						}
					});
				}else{
					try {
						throw optbuf.getError();
					} catch (Throwable e) {
						e.printStackTrace();
					}
				}
			}
        })).start();
    }
    
}
