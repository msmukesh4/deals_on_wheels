package com.dealsonwheels.app;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.dealsonwheels.app.homepage_fragments.HomePagerAdapter;

import java.io.IOException;
import java.util.List;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "Home";
    private HomePagerAdapter mHomePagerAdapter;
    private ViewPager mViewPager;
    private Location mlocation;
    private  LocationManager locationManager;
    private PermissionManager pm;
    private Dialog locationAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // set the home pager
        mHomePagerAdapter = new HomePagerAdapter(getSupportFragmentManager(),getApplicationContext());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mHomePagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        // Iterate over all tabs and set the custom view
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(mHomePagerAdapter.getTabView(i));
        }



//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /* check if permission for accessing location is granted
        *   if permission not granted ask for permission and then fetch location when permission is granted
        * */
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            pm = new PermissionManager(Home.this);
            pm.checkLocationPermission();
        }

        fetchLocation();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
        if (id == R.id.location_icon){
            Log.d(TAG, "clicked on location item");
//            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                pm = new PermissionManager(Home.this);
//                pm.checkLocationPermission();
//            }else {
//                fetchLocation();
//            }
            showLocationDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PermissionManager.LOCATION_PERMISSION_CODE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission is now granted
                    // we can extract the location
                    fetchLocation();

                } else {
//
//                    // permission denied, boo! Disable the
//                    // functionality that depends on this permission.
                    Log.e(TAG, "onRequestPermissionsResult: location permission denied" );
                }
                return;
        }
    }

    private void showLocationDialog(){

        locationAlertDialog = new Dialog(Home.this);
        locationAlertDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        locationAlertDialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        locationAlertDialog.setContentView(R.layout.dialog_location_picker);

        AutoCompleteTextView autoCompleteLocation = (AutoCompleteTextView) locationAlertDialog.findViewById(R.id.auto_complete_location);
        Button btnConfirmCity = (Button) locationAlertDialog.findViewById(R.id.btn_confirm_city);
        RelativeLayout layoutClose = (RelativeLayout) locationAlertDialog.findViewById(R.id.layout_close);

        locationAlertDialog.setCancelable(false);
        locationAlertDialog.setCanceledOnTouchOutside(false);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Home.this,
                android.R.layout.simple_spinner_dropdown_item, Constants.LOCATION);
        autoCompleteLocation.setAdapter(adapter);

        locationAlertDialog.show();

        btnConfirmCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationAlertDialog.dismiss();
            }
        });

        layoutClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
                locationAlertDialog.dismiss();
            }
        });



    }

    private void fetchLocation() {
        Log.d(TAG, "fetchLocation: ");
        final LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d("Location Changes", location.toString());
                mlocation = location;
                locationManager.removeUpdates(this);
                Geocoder geocoder = new Geocoder(getApplicationContext());
                try {
                    List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    System.out.println(addresses.get(0).toString());
                    Constants.currentUser.setAddress(addresses.get(0));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                Log.d("Status Changed", String.valueOf(status));
            }

            @Override
            public void onProviderEnabled(String provider) {
                Log.d("Provider Enabled", provider);
            }

            @Override
            public void onProviderDisabled(String provider) {
                Log.d("Provider Disabled", provider);
            }
        };

        // Now first make a criteria with your requirements
        // this is done to save the battery life of the device
        // there are various other other criteria you can search for..
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setSpeedRequired(false);
        criteria.setCostAllowed(true);
        criteria.setHorizontalAccuracy(Criteria.ACCURACY_HIGH);
        criteria.setVerticalAccuracy(Criteria.ACCURACY_HIGH);

        // Now create a location manager
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

////         This is the Best And IMPORTANT part
//        Looper looper = null;


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "fetchLocation: extraction location");
            if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
                Log.d(TAG, "fetchLocation: network provider enabled");
            if (locationManager.isProviderEnabled(LocationManager.PASSIVE_PROVIDER))
                Log.d(TAG, "fetchLocation: passive provider enabled");
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
                Log.d(TAG, "fetchLocation: gps provider enabled");


//            locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, locationListener,looper);
//            locationManager.requestSingleUpdate(LocationManager.PASSIVE_PROVIDER, locationListener,looper);
//            locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, locationListener,looper);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
            locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 0, 0, locationListener);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }else
            Log.d(TAG, "fetchLocation: don't have enough permission to access gps");


    }
}
