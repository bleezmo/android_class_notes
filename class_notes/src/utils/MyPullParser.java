package utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;

public class MyPullParser {
	public static void parse(String url) throws XmlPullParserException, IOException{
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();
        
        xpp.setInput(new URL(url).openStream(),null);
        int eventType = xpp.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
         if(eventType == XmlPullParser.START_DOCUMENT) {
             Log.i("MyPullParser","Start document");
         } else if(eventType == XmlPullParser.START_TAG) {
        	 Log.i("MyPullParser","Start tag "+xpp.getName());
         } else if(eventType == XmlPullParser.END_TAG) {
        	 Log.i("MyPullParser","End tag "+xpp.getName());
         } else if(eventType == XmlPullParser.TEXT) {
        	 Log.i("MyPullParser","Text "+xpp.getText());
         }
         eventType = xpp.next();
        }
        Log.i("MyPullParser","End document");
    }
}
