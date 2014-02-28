package com.example.class_notes;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * ListView which contains items, that when clicked, will display
 * a dialog pop-up that contains the text of the item
 * 
 * @author josh
 *
 */
public class ListOfEvents extends Activity{
	/**
	 * build a dialog that displays the title parameter
	 * in the title section
	 * @param title
	 * @return
	 */
	private AlertDialog buildDialog(String title){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(title);
		return builder.create();
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_with_events);
		ListView lv = (ListView) findViewById(R.id.eventslist);
		final String[] planets = getResources().getStringArray(R.array.planets_array);
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				this,
				android.R.layout.simple_list_item_1,
				planets
				);
		lv.setAdapter(adapter);
		//the OnItemClickListener.onItemClick gets called whenever an item
		//in the list is clicked/tapped
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, 
					View view, int position,long id) {
				//get the planet that was just clicked
				String planet = planets[position];
				//show a dialog with the planet name
				buildDialog(planet).show();
			}
			
		});		
	}

}
