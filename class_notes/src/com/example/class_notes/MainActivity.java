package com.example.class_notes;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * start a camera and display the image taken on the main activity
 * @author josh
 *
 */
public class MainActivity extends Activity {
	public static final int CAMERA_REQUEST_CODE = 1;
	//contains path to image
	private Uri uri;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == CAMERA_REQUEST_CODE){
			if(resultCode == RESULT_OK){
				ImageView iv = (ImageView) findViewById(R.id.camera_img);
				//display the picture taken in the image view
				iv.setImageBitmap(BitmapFactory.decodeFile(uri.getPath()));
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	public void takePicture(View v){
		//start the camera via intent
    	Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    	uri = getOutputMediaFileUri();
    	//pass in the path to where the picture should be saved
    	intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
    	//we pass in request code to retrieve on return
    	startActivityForResult(intent,CAMERA_REQUEST_CODE);
    }
	/**
	 * generate the file path in which we want to save the image
	 * @return
	 */
    private Uri getOutputMediaFileUri(){
    	String storageState = Environment.getExternalStorageState();
    	if(storageState.equals(Environment.MEDIA_MOUNTED)){
    		File mediaStorageDir = new File(
    			Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
    			"MyCameraApp");
    		if(!mediaStorageDir.exists()){
    			if(!mediaStorageDir.mkdirs()){
    				Toast.makeText(this, "Failed to create picture directory", Toast.LENGTH_SHORT);
    				return null;
    			}
    		}
    		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    		File mediaFile = new File(mediaStorageDir.getPath()+File.separator+"IMG_"+timeStamp+".jpg");
    		return Uri.fromFile(mediaFile);
    	}else{
    		Toast.makeText(this, "No SDCard found!", Toast.LENGTH_SHORT).show();
    		return null;
    	}
    }
}






