package com.example.class_notes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailsFragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		//inflate the layout for the fragment
		View v = inflater.inflate(R.layout.my_details, container, false);
		//get the text view to populate the description
		TextView tv = (TextView) v.findViewById(R.id.description);
		//get the last item that was clicked and display the corresponding description
		if(currentListItemIndex() == -1){
			tv.setText("");
		}else{
			tv.setText(Global.rsslist[currentListItemIndex()].description);
		}
		return v;
	}

	/*
	 * create a new DetailsFragment and initialize the index
	 *  as to get the correct description
	 */
	public static DetailsFragment newInstance(int listItemIndex){
		DetailsFragment fragment = new DetailsFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("index", listItemIndex);
		fragment.setArguments(bundle);
		return fragment;
	}
	/**
	 * @return the index of the last item clicked
	 */
	public int currentListItemIndex(){
		Bundle args = getArguments();
		if(args != null){
			return args.getInt("index");
		}else{
			return -1;
		}
	}
}
