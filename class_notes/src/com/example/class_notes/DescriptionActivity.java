package com.example.class_notes;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

/**
 * display the title and description passed in from the intent extras
 * @author josh
 *
 */
public class DescriptionActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.description_layout);
		String title = getIntent().getStringExtra("title");
		String description = getIntent().getStringExtra("description");
		TextView tv = (TextView) findViewById(R.id.title);
		tv.setText(title);
		//we use a webview because the description contains html
		WebView wv = (WebView) findViewById(R.id.description);
		wv.loadData(description, "text/html", null);
	}

}
