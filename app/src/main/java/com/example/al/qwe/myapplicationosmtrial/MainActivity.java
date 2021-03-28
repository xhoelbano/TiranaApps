package com.example.al.qwe.myapplicationosmtrial;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
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
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.compass.CompassOverlay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final int REQUEST_PERMISSIONS_REQUEST_CODE = 1;
    private MapView map = null;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    final List<Marker> markerList = new ArrayList<>();
    private ActionBarDrawerToggle mToggle;
    final List<GeoPoint> points = new ArrayList<>();



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
 /*       drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        mToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
       // navigationView.setNavigationItemSelectedListener(this);






        //Test BACK4App database connection
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

        map.setMinZoomLevel(12.0);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);
        IMapController mapController = map.getController();
        mapController.setZoom(18.0);
        //GeoPoint startPoint = new GeoPoint(41.3274122,19.8171285);
        //mapController.setCenter(startPoint);





        final Drawable drawable = getResources().getDrawable(R.drawable.marker_default);

        final String[] markerTitle = new String[2];

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
                        startMarker.setIcon(getResources().getDrawable(R.drawable.logo_marker));
                        startMarker.setTitle("Start point");
                        startMarker.setTitle(objects.get(i).getString("name"));
                        String location = (objects.get(i).getString("location"));
                        String markerContact = ( "Website: " + objects.get(i).getString("website") + "\nCelular: " + objects.get(i).getString("phone") + "\nE-mail: " + objects.get(i).getString("email"));
                       //String snippet = ("Location: " + objects.get(i).getString("location") + "\n " + markerContact + "\nKlikoni serisht markerin per me teper:");
                        String snippet = ( objects.get(i).getString("location"));
                        startMarker.setSnippet(snippet);




                        String longDescription = objects.get(i).getString("long_description");
                        markerList.add(startMarker);


                        startMarker.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
                            @Override
                            public boolean onMarkerClick(Marker marker, MapView mapView) {
                                map.getController().animateTo(gp); //animate to selected marker
                                marker.showInfoWindow(); // show marker info

                                markerTitle[0] = marker.getTitle();
                                startMarker.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
                                    @Override
                                    public boolean onMarkerClick(Marker marker, MapView mapView) {
                                        markerTitle[1] = marker.getTitle();

                                        if (markerTitle[0].equals(markerTitle[1]) ) {
                                            map.getController().animateTo(gp); //animate to selected marker
                                            Toast.makeText(MainActivity.this, marker.getTitle() + " u selektua", Toast.LENGTH_LONG).show(); //show a toast when a marker is selected

                                            String markerTitle = marker.getTitle();
                                            Intent i = new Intent(MainActivity.this, DetailsActivity.class);
                                            i.putExtra("title", markerTitle);

                                            String markerLocation = location;
                                            i.putExtra("loc", markerLocation);

                                            String markerDscp = longDescription;
                                            i.putExtra("long_dscp", markerDscp);

                                           i.putExtra("contact", markerContact);

                                            startActivity(i);
                                            return true;
                                        }
                                        return false;
                                    }
                                });

                                return true;
                            }


                        });

                        //startMarker.setInfoWindow(new MarkerInfoWindow());
                        map.getOverlays().add(startMarker);
                    }
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //there are problems with item selection, I have defined methods to get item selection but they does not work when i run the code
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        //actual code to animate to marker in map , when an item is selected from navigation bar
       for(int i=0; i< markerList.size(); i++ ) {
            if (item.getTitle().equals(markerList.get(i).getTitle())) {
                map.getController().animateTo(points.get(i));
                markerList.get(i).showInfoWindow();
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }



}