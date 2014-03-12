package utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;
/**
 * This code is loosely based on the pull parser code from:
 * http://developer.android.com/reference/org/xmlpull/v1/XmlPullParser.html
 * @author josh
 *
 */
public class MyPullParser {
	private ArrayList<RssItem> items = new ArrayList<RssItem>();
	private RssItem currentItem = null;
	public void parse(String url) throws XmlPullParserException, IOException{
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		factory.setNamespaceAware(true);
		XmlPullParser xpp = factory.newPullParser();

		xpp.setInput(new URL(url).openStream(),null);
		int eventType = xpp.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			if(eventType == XmlPullParser.START_DOCUMENT) {
				Log.i("MyPullParser","Start document");
			} else if(eventType == XmlPullParser.START_TAG) {
				if(xpp.getName().equals("item")){
					//check for item start tag.
					//if so, set the currentItem so that we can manipulate title and description
					//in the child elements
					currentItem = new RssItem();
				}else if(xpp.getName().equals("title") && currentItem != null){
					//set title for the current item
					currentItem.title = xpp.nextText();
				}else if(xpp.getName().equals("description") && currentItem != null){
					//set description for the current item
					currentItem.description = xpp.nextText();
				}
			} else if(eventType == XmlPullParser.END_TAG) {
				if(xpp.getName().equals("item")){
					//add the current item to items so that we can instantiate 
					//the next item in current item
					items.add(currentItem);
				}
			}
			eventType = xpp.next();
		}
		Log.i("MyPullParser","End document");
		Log.i("MyPullParser","We received: "+items.size());
	}
	@Override
	public String toString(){
		String prettyPrint = "";
		for(int i = 0; i < items.size(); i++){
			prettyPrint = prettyPrint+
					"\n"+
					"---title: "+items.get(i).title+"\n"+
					"---description: "+items.get(i).description;
		}
		return prettyPrint;
	}
	public ArrayList<RssItem> getItems(){
		return items;
	}
}
