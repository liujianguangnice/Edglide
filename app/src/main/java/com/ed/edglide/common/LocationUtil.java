package com.ed.edglide.common;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

public class LocationUtil {
    /**
     * 获取当前Location
     */
    public static void getLocation(Activity context) {
        //获取地理位置管理器
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        LocationListener mLocationListener01 = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                updateToNewLocation(location,context);
            }


            @Override
            public void onProviderDisabled(String provider) {
                updateToNewLocation(null,context);
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }
        };

        if (!gpsEnabled(locationManager) && !netWorkEnabled(locationManager)) {
            Toast.makeText(context, "请打开手机定位～", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            context.startActivityForResult(intent, 0);
        }

      /*  if (gpsEnabled(locationManager) && getGPSLocation(locationManager,context) != null) {
            return getGPSLocation(locationManager,context);

        } else if (netWorkEnabled(locationManager) && getNetWorkLocation(locationManager,context) != null) {
            return getNetWorkLocation(locationManager,context);

        } else {
            return null;
        }*/
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 0, mLocationListener01);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 3000, 0, mLocationListener01);

    }
    private static Location updateToNewLocation(Location location, Context context) {
        System.out.println("--------zhixing--2--------");
        String latLongString;
        double lat = 0;
        double lng=0;

        if (location != null) {
            lat = location.getLatitude();
            lng = location.getLongitude();
            latLongString = "纬度:" + lat + "\n经度:" + lng;
            System.out.println("经度："+lng+"纬度："+lat);
        } else {
            latLongString = "无法获取地理信息，请稍后...";
        }
        if(lat!=0){
            System.out.println("--------反馈信息----------"+ String.valueOf(lat));
        }

        Toast.makeText(context, latLongString, Toast.LENGTH_SHORT).show();

        return location;

    }

    private static boolean gpsEnabled(LocationManager locationManager) {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    private static boolean netWorkEnabled(LocationManager locationManager) {
        return locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    private static Location getGPSLocation(LocationManager locationManager, Context context) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        return locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    }

    private static Location getNetWorkLocation(LocationManager locationManager, Context context) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        return locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
    }

}
