package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

public class Downloader {
	/**
	 * listener for used for JSONAsyncDownloader. When download and parsing
	 * is finished, the async task calls onJSONRetrieved in the UI Thread
	 * @author josh
	 *
	 */
	public interface JSONDownloaderListener{
		public void onJSONRetrieved(JSONObject json);
	}
	/**
	 * download the text from the website (we assume the url is a link to JSON text)
	 * parse the json into JSONObject
	 * @author josh
	 *
	 */
	public static class JSONAsyncDownloader extends AsyncTask<Void,Void,JSONObject>{	
		private JSONDownloaderListener listener;

		public JSONAsyncDownloader(JSONDownloaderListener listener){
			this.listener = listener;
		}
		@Override
		protected JSONObject doInBackground(Void... params) {
			String jsontext = Downloader
					.downloadText("http://data.nba.com/json/cms/noseason/scoreboard/20101229/games.json")
					.toString();
			try {
				JSONObject json = new JSONObject(jsontext);
				return json;
			} catch (JSONException e) {
				e.printStackTrace();
				return null;
			}
		}

		@Override
		protected void onPostExecute(JSONObject result) {
			listener.onJSONRetrieved(result);
		}
		
	}
	public static final StringBuffer downloadText(String strUrl){
		try {
			URL url = new URL(strUrl);
			BufferedReader in = new BufferedReader(
					new InputStreamReader(url.openStream()));
			StringBuffer sb = new StringBuffer();
			String line = in.readLine();
			while(line != null){
				sb.append(line);
				line = in.readLine();
			}
			in.close();
			return new StringBuffer(sb);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
