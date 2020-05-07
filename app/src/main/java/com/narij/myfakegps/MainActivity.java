package com.narij.myfakegps;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    MockLocationProvider mock;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        if ((getApplication().getApplicationInfo().flags &
//                ApplicationInfo.FLAG_DEBUGGABLE) != 0) {
//            mock = new MockLocationProvider(
//                    LocationManager.NETWORK_PROVIDER, this);
//            //Set test location
//            mock.pushLocation(-12.34, 23.45);
//        }

        mock = new MockLocationProvider(LocationManager.NETWORK_PROVIDER, this);

        //Set test location
        mock.pushLocation(-12.34, 23.45);

        LocationManager locMgr = (LocationManager)
                getSystemService(LOCATION_SERVICE);
        LocationListener lis = new LocationListener() {
            public void onLocationChanged(Location location) {
                //You will get the mock location
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
            //...
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        locMgr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, lis);
    }

    protected void onDestroy() {
        mock.shutdown();
        super.onDestroy();
    }
}