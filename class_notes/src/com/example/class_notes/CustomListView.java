package com.example.class_notes;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class CustomListView extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ListView lv = (ListView) findViewById(R.layout.custom_list_view);
		ItemModel[] items = new ItemModel[]{
				new ItemModel("Thrall","Son of Durotan. Leader of the orcs"),
				new ItemModel("Jaina Proudmore"," the founder and former ruler of Theramore Isle, the Alliance's major port in southern Kalimdor"),
				new ItemModel("Arthas Menethil","Crown Prince of Lordaeron and Knight of the Silver Hand"),
				new ItemModel("Doktor Professor Ironpants","dwarven Archaeology trainer located in the Hall of Explorers in the City of Ironforge. He may be a reference to the TV show SpongeBob SquarePants, which has an episode in which Patrick addresses himself as Mister Doctor Professor Patrick, and Ironpants has a similar last name to SpongeBob.")
		};
		
	}

}
