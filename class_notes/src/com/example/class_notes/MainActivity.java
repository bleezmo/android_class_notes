package com.example.class_notes;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

/**
 * start an action bar activity
 * @author josh
 *
 */
public class MainActivity extends FragmentActivity {
	GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpMapIfNeeded();
    }   
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                mMap.setMyLocationEnabled(true);
                mMap.addMarker(new MarkerOptions()
                		.position(new LatLng(40.758546225130104,-73.975471816957))
                		.title("Hello from marker"));
                mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
					@Override
					public void onMapClick(LatLng pos) {
						Log.i("MainActivity","("+pos.latitude+","+pos.longitude+")");
					}
				});
                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {				
					@Override
					public boolean onMarkerClick(Marker marker) {
						Toast.makeText(MainActivity.this, 
								"You clicked my marker!", 
								Toast.LENGTH_SHORT).show();
						return false;
					}
				});
            }
            
        }
    }
    
}






