package com.example.class_notes;

import com.example.class_notes.Global.ListItem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MyListFragment extends ListFragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//set up adapter with pre-defined list items from Global
		ArrayAdapter<ListItem> adapter = new ArrayAdapter<ListItem>(getActivity(),android.R.layout.simple_list_item_1,Global.rsslist);
		//set the list adapter for this list fragment
		setListAdapter(adapter);
		//must return a inflated view of the list layout
		return inflater.inflate(R.layout.my_list, container, false);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		//when an item is clicked, 
		//start the DetailsActivity with the passed in index (position)
		Intent i = new Intent(this.getActivity(),DetailsActivity.class);
		i.putExtra("index", position);
		startActivity(i);
	}
	
}
