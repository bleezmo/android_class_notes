package com.example.class_notes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;

public class SecondActivity extends ActionBarActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_activity);
		
		//enable the action bar so that it is displayed
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
	}
	/**
	 * need to override this in order to set up the 'up' (back) button in the action bar
	 */
	@Override
	public Intent getSupportParentActivityIntent() {
		Intent i = new Intent(this,MainActivity.class);
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
		return i;
	}

}
