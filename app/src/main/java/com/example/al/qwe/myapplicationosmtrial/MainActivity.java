package com.example.al.qwe.myapplicationosmtrial;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.BoundingBox;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.compass.CompassOverlay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private final int REQUEST_PERMISSIONS_REQUEST_CODE = 1;
    private MapView map = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Test BACK4App database connection
        setContentView(R.layout.activity_main);
        ParseObject firstObject = new  ParseObject("FirstClass");
        firstObject.put("message","Hey ! First message from android. Parse is now connected");
        firstObject.saveInBackground(e -> {
            if (e != null){
                Log.e("MainActivity", e.getLocalizedMessage());
            }else{
                Log.d("MainActivity","Object saved.");
            }
        });



        //handle permissions first, before map is created. not depicted here

        //load/initialize the osmdroid configuration, this can be done
        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        //setting this before the layout is inflated is a good idea
        //it 'should' ensure that the map has a writable location for the map cache, even without permissions
        //if no tiles are displayed, you can try overriding the cache path using Configuration.getInstance().setCachePath
        //see also StorageUtils
        //note, the load method also sets the HTTP User Agent to your application's package name, abusing osm's
        //tile servers will get you banned based on this string

        //inflate and create the map
        setContentView(R.layout.activity_main);

        map = (MapView) findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);



        requestPermissionsIfNecessary(new String[] {
                // if you need to show the current location, uncomment the line below
                Manifest.permission.ACCESS_FINE_LOCATION,
                // WRITE_EXTERNAL_STORAGE is required in order to show the map
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        });

        map.setMinZoomLevel(7.5);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);
        IMapController mapController = map.getController();
        mapController.setZoom(18.0);
        //GeoPoint startPoint = new GeoPoint(41.3274122,19.8171285);
        //mapController.setCenter(startPoint);

        final List<GeoPoint> points = new ArrayList<>();
        final Drawable drawable = getResources().getDrawable(R.drawable.marker_default);





        // Configure Query
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Drejtori");

        query.findInBackground(new FindCallback<ParseObject>() {
                                   @Override
                                   public void done(List<ParseObject> objects, final ParseException e) {
                                       if (e == null) {
                                           // Adding objects into the Array
                                           mapController.setCenter(new GeoPoint(objects.get(0).getParseGeoPoint("coordinates").getLatitude(), objects.get(0).getParseGeoPoint("coordinates").getLongitude()));
                                           for (int i = 0; i < objects.size(); i++) {
                                               GeoPoint gp = new GeoPoint(objects.get(i).getParseGeoPoint("coordinates").getLatitude(), objects.get(i).getParseGeoPoint("coordinates").getLongitude());
                                                points.add(gp);
                                               Marker startMarker = new Marker(map);
                                               startMarker.setPosition(gp);
                                               startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
                                               startMarker.setIcon(drawable);
                                               startMarker.setTitle(objects.get(i).getString("name"));
                                               startMarker.setSnippet(objects.get(i).getString("location"));
                                               startMarker.setSubDescription(objects.get(i).getString("long_description"));
                                               String markerContact = ("Website: " + objects.get(i).getString("website") + "\nCelular: " + objects.get(i).getString("phone") + "\nE-mail: " + objects.get(i).getString("email"));


                                               startMarker.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
                                                   @Override
                                                   public boolean onMarkerClick(Marker marker, MapView mapView) {
                                                       Toast.makeText(MainActivity.this, marker.getTitle() + " u selektua", Toast.LENGTH_LONG).show();
                                                       //marker.showInfoWindow();

                                                       String markerTitle = marker.getTitle();
                                                       Intent i = new Intent(MainActivity.this, DetailsActivity.class);
                                                       i.putExtra("title", markerTitle);

                                                       String markerLocation = marker.getSnippet();
                                                       i.putExtra("loc", markerLocation);

                                                       String markerDscp = marker.getSubDescription();
                                                       i.putExtra("long_dscp", markerDscp);

                                                       i.putExtra("contact", markerContact);

                                                       startActivity(i);
                                                       return true;
                                                   }


                                               });

                                               //startMarker.setInfoWindow(new MarkerInfoWindow());
                                               map.getOverlays().add(startMarker);
                                           }
                                       }
                                       else {

                                       }
                                   }
                               });


            //add compass
            CompassOverlay overlay = new CompassOverlay(MainActivity.this, map);
            overlay.setPointerMode(false);
            overlay.enableCompass();
            map.getOverlayManager().add(overlay);
            map.invalidate();



    }

    @Override
    public void onResume() {
        super.onResume();


        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
        map.onResume(); //needed for compass, my location overlays, v6.0.0 and up
    }

    @Override
    public void onPause() {
        super.onPause();

        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().save(this, prefs);
        map.onPause();  //needed for compass, my location overlays, v6.0.0 and up
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        ArrayList<String> permissionsToRequest = new ArrayList<>();
        for (int i = 0; i < grantResults.length; i++) {
            permissionsToRequest.add(permissions[i]);
        }
        if (permissionsToRequest.size() > 0) {
            ActivityCompat.requestPermissions(
                    this,
                    permissionsToRequest.toArray(new String[0]),
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }

    private void requestPermissionsIfNecessary(String[] permissions) {
        ArrayList<String> permissionsToRequest = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                // Permission is not granted
                permissionsToRequest.add(permission);
            }
        }
        if (permissionsToRequest.size() > 0) {
            ActivityCompat.requestPermissions(
                    this,
                    permissionsToRequest.toArray(new String[0]),
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }

}