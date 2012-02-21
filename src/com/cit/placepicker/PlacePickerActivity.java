package com.cit.placepicker;

import java.util.ArrayList;

import org.apache.http.util.LangUtils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.LabeledIntent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.ExifInterface;
import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class PlacePickerActivity extends Activity {
    Context c;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        c=this;
       TextView tv = new TextView(this);
       tv.setText("Enter the place to search");
       final EditText text = new EditText(this);
       Button click = new Button(this);
       click.setText("Search");
       final LinearLayout ll  = new LinearLayout(this);
       ll.setOrientation(LinearLayout.VERTICAL);
       ll.addView(tv);
     
      click.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
		final Double longitude;
		final Double latitude;
		Toast.makeText(getApplicationContext(), "Fetching GPS location . .", Toast.LENGTH_LONG).show();
			final LocationListener locationListener = new LocationListener() {
			    public void onLocationChanged(Location location) {
			        Double longitude = location.getLongitude();
			        Double latitude = location.getLatitude();
			        Foursquare fs = new Foursquare();
			      ArrayList<Venue> venueList =  fs.getVenues(latitude+","+longitude, text.getText().toString());
			        int i =0;
			        for(i=0;i<venueList.size();i++)
			        {
			        	TextView tt = new TextView(getApplicationContext());
			        	Venue v = venueList.get(i);
			        	tt.setText(v.getName());
			        	ll.addView(tt);
			        }
			        
			        
			        
			        
			    }

				@Override
				public void onProviderDisabled(String arg0) {
					// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(), "GPS Fetch Failed  . .", Toast.LENGTH_LONG).show();
					
				}

				@Override
				public void onProviderEnabled(String arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
					// TODO Auto-generated method stub
					
				}
			};
			LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
			lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, locationListener);

			
			
			
			
		}
	});
      ll.addView(text);
      ll.addView(click);
        
        
       
       
        
        
        //R.layout.main
      ScrollView sv = new ScrollView(this);
      sv.addView(ll);
    //setContentView(R.layout.sample);
  /*try {
		Thread.sleep(7000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
        setContentView(sv);
       // Toast.makeText(getApplicationContext(),"welcome ...",Toast.LENGTH_SHORT);
       
    }
}