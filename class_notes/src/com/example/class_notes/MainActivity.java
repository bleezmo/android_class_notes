package com.example.class_notes;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import utils.Downloader;
import utils.SportsManager;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
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
			//just run this to test to make sure the database is being populated correctly
			new SportsManager(this).insertSports(json);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		//Log.i("MainActivity","got json: "+json.toString());
		ListView lv = (ListView) findViewById(R.id.arenalist);
		try {
			JSONArray games = json.
					getJSONObject("sports_content").
					getJSONObject("games").getJSONArray("game");
			String[] arenas = new String[games.length()];
			for(int i = 0; i < games.length(); i++){
				arenas[i] = games.getJSONObject(i).getString("arena");
			}
			lv.setAdapter(new ArrayAdapter<String>(
					this,android.R.layout.simple_list_item_1,arenas));
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
