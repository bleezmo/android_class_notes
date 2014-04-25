package com.example.class_notes;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class DetailsActivity extends FragmentActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//create a new details fragment and pass in the index to the arguments
		//the index is passed in from the intent from the calling activity
		DetailsFragment fragment = new DetailsFragment();
		fragment.setArguments(getIntent().getExtras());
		//add the fragment to container
		FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
		ft.add(android.R.id.content, fragment, "my_details").commit();
	}

	
	
}
