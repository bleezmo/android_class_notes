package com.example.class_notes;

import com.example.class_notes.Global.ListItem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MyListFragment extends ListFragment{

	private boolean dualPaneMode = false;

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
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		//check to see if both fragments are displayed in the activity
		//if they are, we know we are using a device that can support dual pane mode
		View details = getActivity().findViewById(R.id.details_fragment);
		dualPaneMode = details != null && details.getVisibility() == View.VISIBLE;
		if(dualPaneMode){
			showDetails(0);
		}
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		//when an item is clicked, 
		//start the DetailsActivity with the passed in index (position)
		showDetails(position);
	}
	
	private void showDetails(int position){
		if(dualPaneMode){
			DetailsFragment curDetails = (DetailsFragment) this.getFragmentManager().findFragmentById(R.id.details_fragment);
			//check to see if the current details fragment is the same as the one about to be loaded
			//efficiency purposes only
			if(curDetails == null || curDetails.currentListItemIndex() != position){
				//replace the current fragment in the details_fragment layout with the new fragment
				FragmentTransaction transaction = this.getFragmentManager().beginTransaction();
				DetailsFragment details = DetailsFragment.newInstance(position);
				transaction.replace(R.id.details_fragment, details);
				transaction.commit();
			}
		}else{
			//we are not in dual pane mode, launch a separate activity for the details
			Intent i = new Intent(this.getActivity(),DetailsActivity.class);
			i.putExtra("index", position);
			startActivity(i);
		}
	}
	
}
