package com.example.al.qwe.myapplicationosmtrial;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.compass.CompassOverlay;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    private final int REQUEST_PERMISSIONS_REQUEST_CODE = 1;
    private MapView map = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
       // View inflater = null;
       // View v = inflater.inflate(R.layout.recyclerview, null);
       // map = v.findViewById(R.id.map);

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
        mapController.setZoom(15.0);
        GeoPoint startPoint = new GeoPoint(41.3274812,19.8169882);
        mapController.setCenter(startPoint);

//your items
        ArrayList<OverlayItem> items = new ArrayList<OverlayItem>();
        items.add(new OverlayItem("Title", "Description", new GeoPoint(41.3274812,19.8169882))); // Lat/Lon decimal degrees
        final DisplayMetrics dm = ctx.getResources().getDisplayMetrics();
/*        final ImageView image = (ImageView) findViewById(R.id.image);
        IconOverlay iOverlay = new IconOverlay(new GeoPoint(0.0d,0.0d), image.getDrawable());*/

//the overlay
        ItemizedOverlayWithFocus<OverlayItem> mOverlay = new ItemizedOverlayWithFocus<OverlayItem>(items,
                new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
                    private CompassOverlay mCompassOverlay;

/*                    @Override
                    public boolean onItemSingleTapUp(final int index, final OverlayItem item) {
                        MinimapOverlay mMinimapOverlay = new MinimapOverlay(ctx, map.getTileRequestCompleteHandler());
                        mMinimapOverlay.setWidth(dm.widthPixels / 5);
                        mMinimapOverlay.setHeight(dm.heightPixels / 5);
//optionally, you can set the minimap to a different tile source
//mMinimapOverlay.setTileSource(....);
                        map.getOverlays().add(mMinimapOverlay);
                        //next add
                        this.mCompassOverlay = new CompassOverlay(ctx, new InternalCompassOrientationProvider(ctx), map);
                        this.mCompassOverlay.enableCompass();
                        map.getOverlays().add(this.mCompassOverlay);
                        return true;
                    }*/

                    @Override
                    public boolean onItemSingleTapUp(final int index, final OverlayItem item) {
                        setContentView(R.layout.recycler_main);
                        MyListData[] myListData = new MyListData[] {
                                new MyListData("Email", android.R.drawable.ic_dialog_email),
                                new MyListData("Info", android.R.drawable.ic_dialog_info),
                                new MyListData("Delete", android.R.drawable.ic_delete),
                                new MyListData("Dialer", android.R.drawable.ic_dialog_dialer),
                                new MyListData("Alert", android.R.drawable.ic_dialog_alert),
                                new MyListData("Map", android.R.drawable.ic_dialog_map),
                                new MyListData("Email", android.R.drawable.ic_dialog_email),
                                new MyListData("Info", android.R.drawable.ic_dialog_info),
                                new MyListData("Delete", android.R.drawable.ic_delete),
                                new MyListData("Dialer", android.R.drawable.ic_dialog_dialer),
                                new MyListData("Alert", android.R.drawable.ic_dialog_alert),
                                new MyListData("Map", android.R.drawable.ic_dialog_map),
                                new MyListData("Xhoel", android.R.drawable.star_big_on)
                        };

                        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                        MyListAdapter adapter = new MyListAdapter(myListData);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(ctx));
                        recyclerView.setAdapter(adapter);
                        return true;
                    }



                    @Override
                    public boolean onItemLongPress(final int index, final OverlayItem item) {
                        return false;
                    }
                }, ctx);
        mOverlay.setFocusItemsOnTap(true);

        map.getOverlays().add(mOverlay);

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