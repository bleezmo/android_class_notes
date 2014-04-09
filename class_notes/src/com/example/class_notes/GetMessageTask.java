package com.example.class_notes;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import android.os.AsyncTask;
import android.util.Log;

public class GetMessageTask extends AsyncTask<Void,Void,String>{
	private GetMessageListener listener;
	public GetMessageTask(GetMessageListener listener){
		this.listener = listener;
	}
	/**
	 * do a bunch of stuff to get the message fromt the server
	 * this may take several minutes to complete
	 */
	@Override
	protected String doInBackground(Void... arg0) {
		HttpURLConnection urlConnection = null;
		String id;
		try {
			URL url = new URL("http://android-test-server.herokuapp.com/coolmessage/process");
			urlConnection = (HttpURLConnection) url.openConnection();
			id = getResponseContents(urlConnection);
			urlConnection = null;
		}catch(MalformedURLException e){
			e.printStackTrace();
			return null;
		}catch (IOException e) {
			e.printStackTrace();
			return null;
		}finally{
			if(urlConnection != null) urlConnection.disconnect();
		}
		if(id != null){
			try {
				URL url = new URL("http://android-test-server.herokuapp.com/coolmessage/get/"+id);
				urlConnection = (HttpURLConnection) url.openConnection();
				while(urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK){
					urlConnection.disconnect();
					urlConnection = null;
					Thread.sleep(10000);
					urlConnection = (HttpURLConnection) url.openConnection();
				}
				Log.i("GetMessageTask","got a message!");
				String msg = getResponseContents(urlConnection);
				urlConnection = null;
				if(msg != null) return msg;
				else return null;
			}catch(MalformedURLException e){
				e.printStackTrace();
				return null;
			}catch (IOException e) {
				e.printStackTrace();
				return null;
			} catch (InterruptedException e) {
				e.printStackTrace();
				return null;
			}finally{
				if(urlConnection != null) urlConnection.disconnect();
			}
		}else{
			return null;
		}
	}
	/**
	 * gets the contents of the response sent by the server in a string form
	 * @param urlConnection
	 * @return
	 */
	private static final String getResponseContents(HttpURLConnection urlConnection){
		try {
			BufferedInputStream bis = new BufferedInputStream(urlConnection.getInputStream());
			BufferedReader in = new BufferedReader(
		                  new InputStreamReader(bis));
			StringBuffer sb = new StringBuffer();
			String line = in.readLine();
			while(line != null){
				sb.append(line);
				line = in.readLine();
			}
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}finally {
			urlConnection.disconnect();
		}
	}
	@Override
	protected void onPostExecute(String result) {
		listener.onMessageReceived(result);
	}

}
