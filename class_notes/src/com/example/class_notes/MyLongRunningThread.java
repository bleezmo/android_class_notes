package com.example.class_notes;

import utils.Downloader;
import utils.Either;
import android.os.Handler;
import android.util.Log;

public class MyLongRunningThread implements Runnable{

	private OnFinishedDownloadListener listener;
	private Handler handler;
	
	public MyLongRunningThread(Handler handler, OnFinishedDownloadListener listener){
		this.listener = listener;
		this.handler = handler;
	}
	@Override
	public void run() {
		Either<StringBuffer> optsb = Downloader.downloadText("http://cis228.herokuapp.com/assets/objectives.txt");
		if(optsb.isSuccess()){
			final StringBuffer sb = optsb.getObject();
			handler.post(new Runnable(){
				@Override
				public void run() {
					listener.onFinishedDownload(sb.toString());
				}
				
			});
		}else{
			try {
				throw optsb.getError();
			} catch (Throwable e) {
				e.printStackTrace();
			}
			//Log.e("MyLongRunningThread", optsb.getError().getMessage());
		}
	}

}
