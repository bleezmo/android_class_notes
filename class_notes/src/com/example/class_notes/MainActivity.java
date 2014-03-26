package com.example.class_notes;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import utils.Downloader;
import utils.MyCursorAdapter;
import utils.SportsManager;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * list view displaying parsed json stored in an sqlite database
 * @author josh
 *
 */
public class MainActivity extends Activity implements Downloader.JSONDownloaderListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Downloader.JSONAsyncDownloader(this).execute("http://data.nba.com/json/cms/noseason/scoreboard/20101229/games.json");
    }

	@Override
	public void onJSONRetrieved(JSONObject json) {
		try {
			//get new database object
			final SportsManager dbmanager = new SportsManager(this);
			//insert the sports json into the database
			dbmanager.insertSports(json);
			//display the database in a list using cursor adapter
			final ListView lv = (ListView) findViewById(R.id.arenalist);
			lv.setAdapter(MyCursorAdapter.defaultImplementation(this,dbmanager));
			//when clicking an item, remove it from the list
			lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					SQLiteDatabase db = dbmanager.getWritableDatabase();
					//delete the record from the database based on the id
					db.delete(SportsManager.TABLE_NAME, "_id=?", new String[]{Long.toString(id)});
					//renew the adapter for the listview
					lv.setAdapter(MyCursorAdapter.defaultImplementation(MainActivity.this, dbmanager));
				}
			});
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
	}

}
