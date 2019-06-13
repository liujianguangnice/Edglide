package com.ed.edglide.common;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtil {
    public static final String SP_XML = "agency_info";
    private static SpUtil util;
    private static SharedPreferences sp;

    private SpUtil(Context context) {
        sp = context.getSharedPreferences(SP_XML, Context.MODE_PRIVATE);
    }
    public static SharedPreferences getSp(){
        return  sp;
    }

    /**
     * 初始化SharedPreferencesUtil,只需要初始化一次，建议在Application中初始化
     *
     * @param context 上下文对象
     */
    public static void getInstance(Context context) {
        if (util == null) {
            util = new SpUtil(context);
        }
    }


    public static String getString(String key) {
        return sp.getString(key, "");
    }

    public static void setString(String key, String value) {
        sp.edit().putString(key, value).apply();
    }

    public static boolean getBoolean(String key) {
        return sp.getBoolean(key, false);
    }

    public static void setBoolean(String key, boolean value) {
        sp.edit().putBoolean(key, value).apply();
    }

    public static int getInt(String key) {
        return sp.getInt(key, -1);
    }

    public static int getInt(String key, int value) {
        return sp.getInt(key, value);
    }

    public static void setInt(String key, int value) {
        sp.edit().putInt(key, value).apply();
    }

    public static long getLong(String key) {
        return sp.getLong(key, -1);
    }

    public static void setLong(String key, long value) {
        sp.edit().putLong(key, value).apply();
    }

    public static void remove(String key) {
        sp.edit().remove(key).apply();
    }

    public static void removeAll() {
        sp.edit().clear().apply();
    }

}
