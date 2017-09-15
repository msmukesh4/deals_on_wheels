package com.dealsonwheels.app;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by mukesh on 15/9/17.
 */

public class PermissionManager {

    public static final int LOCATION_PERMISSION_CODE = 1;
    private Context context;
    private Activity activity;

    public PermissionManager(Context context, Activity activity){
        this.activity = activity;
        this.context = context;

    }

    public PermissionManager(Context context){
        this.context = context;
    }

    public void checkLocationPermission(){
        fineLocation();
        courseLocation();
    }

    private boolean fineLocation(){
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if(currentAPIVersion>=android.os.Build.VERSION_CODES.M)
        {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_CODE);
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    private boolean courseLocation(){
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if(currentAPIVersion>=android.os.Build.VERSION_CODES.M)
        {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_PERMISSION_CODE);

                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

}
