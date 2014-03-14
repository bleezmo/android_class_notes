package com.example.class_notes;

import java.io.IOException;

import utils.Downloader;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
        final Handler mainHandler = new Handler();
        new Thread(new Runnable(){
			@Override
			public void run() {
				//download the image specified by the url
				final byte[] bytes = Downloader.downloadBytes("http://theoutlawlife.files.wordpress.com/2013/01/oh-the-huge-manatee.jpg");
				//make ui changes on ui thread
				mainHandler.post(new Runnable(){
					@Override
					public void run() {
						//get the image view
						ImageView iv = (ImageView) findViewById(R.id.myfirstimage);
						//set the image view to display the downloaded image
						iv.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
					}
				});
			}
        }).start();
    }

}
