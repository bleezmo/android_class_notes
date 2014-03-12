package com.example.class_notes;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;
import utils.MyPullParser;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * This first activity just shows a static list view that does
 * nothing when the items are clicked
 * @author josh
 *
 */
public class MainActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        (new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					final MyPullParser parser = new MyPullParser();
					parser.parse("http://rss.cnn.com/rss/cnn_topstories.rss");
					MainActivity.this.runOnUiThread(new Runnable(){
						@Override
						public void run() {
							setUpUiAfterParse(parser);
						}
					});
				} catch (XmlPullParserException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
        })).start();
    }
    /**
     * set up the ui after we've downloaded and parsed the rss feed
     * @param parser
     */
    private void setUpUiAfterParse(final MyPullParser parser){
		TextView tv = (TextView) findViewById(R.id.mytxt);
		//generate a string representation of everything that we downloaded
		//and set the text view to it
		tv.setText(parser.toString());
		//this button, when clicked, will go to a new activity which
		//will display the description for the item in a web view
		Button btn = (Button) findViewById(R.id.to_descr);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,DescriptionActivity.class);
				intent.putExtra("title", parser.getItems().get(0).title);
				intent.putExtra("description", parser.getItems().get(0).description);
				startActivity(intent);
			}
		});
    }
}
