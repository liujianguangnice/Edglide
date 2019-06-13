package com.ed.edglide.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class PackageUtils {

    /**
     * 获取package信息
     */
    public static PackageInfo getPackageInfo(Context context) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getApplicationContext().getPackageManager()
                    .getPackageInfo(context.getApplicationContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return packageInfo;
    }


    /**
     * 获取APP版本versionName
     */
    public static String getVersionName(Context context) {
        String versionName ="";
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getApplicationContext().getPackageManager()
                    .getPackageInfo(context.getApplicationContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        //获取APP版本versionName
        versionName = packageInfo.versionName;
        return  versionName;
    }


    /**
     * 获取APP版本versionCode
     */
    public static int getVersionCode(Context context) {
        int versionCode = 1;
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getApplicationContext().getPackageManager()
                    .getPackageInfo(context.getApplicationContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        versionCode = packageInfo.versionCode;
        return  versionCode;
    }
}
