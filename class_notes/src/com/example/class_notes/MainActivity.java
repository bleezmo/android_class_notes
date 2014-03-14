package com.example.class_notes;

import java.io.IOException;

import utils.ByteImageAdapter;
import utils.DownloaderByteArray;
import utils.FileImageAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * This activity shows a large list of images
 * Illustrates different methods of downloading and storing images
 * @author josh
 *
 */
public class MainActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lv = (ListView) findViewById(R.id.imagelist);
		int imageCount = 100;
		//because imageCount is large, ByteImageAdapter crashes because it
		//is memory inefficient
		//lv.setAdapter(new ByteImageAdapter(this,imageCount))
		//we use file image adapter to manage the images in temporary files
		lv.setAdapter(new FileImageAdapter(this,imageCount));
    }

}
