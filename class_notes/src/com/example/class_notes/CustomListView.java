package com.example.class_notes;

import utils.CustomListAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

/**
 * Display list view with custom adapter which displays custom list items
 * as specified in an xml layout
 * @author josh
 *
 */
public class CustomListView extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.custom_list_view);
		ListView lv = (ListView) findViewById(R.id.custom_lv);
		//item POJO's representing title and description objects
		ItemModel[] items = new ItemModel[]{
				new ItemModel("Thrall","Son of Durotan. Leader of the orcs"),
				new ItemModel("Jaina Proudmore"," the founder and former ruler of Theramore Isle, the Alliance's major port in southern Kalimdor"),
				new ItemModel("Arthas Menethil","Crown Prince of Lordaeron and Knight of the Silver Hand"),
				new ItemModel("Doktor Professor Ironpants","dwarven Archaeology trainer located in the Hall of Explorers in the City of Ironforge. He may be a reference to the TV show SpongeBob SquarePants, which has an episode in which Patrick addresses himself as Mister Doctor Professor Patrick, and Ironpants has a similar last name to SpongeBob.")
		};
		lv.setAdapter(new CustomListAdapter(this,items));
	}

}
