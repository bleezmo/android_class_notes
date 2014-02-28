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
        //the list view to be populated with items
        ListView lv = (ListView) findViewById(R.id.staticlv);
        //our array of items
        String[] planets = getResources().getStringArray(R.array.planets_array);
        //adapter to be added to the list view which contains array of items
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
        		this,
        		android.R.layout.simple_list_item_1,
        		planets
        );
        //set the adapter
        lv.setAdapter(adapter);
    }
    
    public void toListEvents(View view){
    	Intent intent = new Intent(this,ListOfEvents.class);
    	startActivity(intent);
    }

}
