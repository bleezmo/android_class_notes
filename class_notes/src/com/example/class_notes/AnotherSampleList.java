package com.example.class_notes;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AnotherSampleList extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.another_sample_list);
		ListView lv = (ListView) findViewById(R.id.sample_lv);
		String[] objects = new String[]{
				"Java",
				"Python",
				"Perl",
				"C Sharp",
				"C++",
				"PHP",
				"Javascript",
				"C",
				"Cobol",
				"Ada",
				"Ruby",
				"Scala",
				"ActionScript",
				"Fortran",
				"Visual Basic",
				"Erlang",
				"Haskell",
				"Algol",
				"Go",
				"Dart"
		};
		lv.setAdapter(
				new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1,
						objects
						)
				);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.another_sample_list, menu);
		return true;
	}

}
