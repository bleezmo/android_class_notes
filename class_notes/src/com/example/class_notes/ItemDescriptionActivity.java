package com.example.class_notes;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class ItemDescriptionActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_description);
		//get the description that was passed in from clicking the button
		//in the list item (See CustomListAdapter.getView)
		Bundle bundle = getIntent().getExtras();
		String descr = bundle.getString("description");
		TextView tv = (TextView) findViewById(R.id.item_descr);
		tv.setText(descr);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.item_description, menu);
		return true;
	}

}
