package com.bit.sfa.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.util.Log;

import com.bit.sfa.model.User;

import java.util.List;

/**
 * Created by TaZ on 12/11/14.
 * Functions to access shared preferences.
 */
public class SharedPref {

    private static final String LOG_TAG = SharedPref.class.getSimpleName();

//    private Context context;
    private SharedPreferences sharedPref;

    private static SharedPref pref;

    public SharedPref() {
    }

    public static SharedPref getInstance(Context context) {
        if(pref == null) {
            pref = new SharedPref(context);
        }

        return pref;
    }

    private SharedPref(Context context) {
//        this.context = context;
        sharedPref = context.getSharedPreferences("app_data", Context.MODE_PRIVATE);
    }

    public void setLoginStatus(boolean status) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("login_status", status).apply();
    }

    public boolean isLoggedIn() {
        return sharedPref.getBoolean("login_status", false);
    }

    public void storeLoginUser(User user) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("user_id", user.getCode());
        editor.putString("user_name", user.getName());
        editor.putString("user_username", user.getUserName());
        editor.putString("user_password", user.getPassword());
        editor.putString("user_route", user.getRoute());
        editor.putString("user_status", user.getStatus());
        editor.putString("user_mobile", user.getMobile());
        editor.putString("user_address", user.getAddress());

        editor.apply();
    }

    public User getLoginUser() {

        User user = new User();
        user.setCode(sharedPref.getString("user_id", ""));
        user.setName(sharedPref.getString("user_name", ""));
        user.setUserName(sharedPref.getString("user_username", ""));
        user.setPassword(sharedPref.getString("user_password", ""));
        user.setRoute(sharedPref.getString("user_route", ""));
        user.setStatus(sharedPref.getString("user_status", ""));
        user.setMobile(sharedPref.getString("user_mobile", ""));
        user.setAddress(sharedPref.getString("user_address", ""));


        if (user.getCode().equals("")) {
            return null;
        } else {
            return user;
        }
    }


    public long generateOrderId() {
        long time = System.currentTimeMillis();
        Log.wtf("ID", String.valueOf(sharedPref.getInt("user_location_id", 0)) + String.valueOf(time));
        long order_id = Long.parseLong(String.valueOf(sharedPref.getInt("user_location_id", 0)) + String.valueOf(time));
        return (order_id < 0 ? -order_id : order_id);
    }

//    public void storeSelectedRoute(Route route) {
//        if (route != null) {
//            SharedPreferences.Editor editor = sharedPref.edit();
//            editor.putInt("selected_route_id", route.getRouteId());
//            editor.putString("selected_route_code", route.getRouteCode());
//            editor.putString("selected_route_name", route.getRouteName());
//            editor.putFloat("selected_route_fixed_target", (float) route.getFixedTarget());
//            editor.putFloat("selected_route_selected_target", (float) route.getSelectedTarget());
//            editor.apply();
//        }
//    }
//
//    public Route getSelectedRoute() {
//
//        Route route = new Route(sharedPref.getInt("selected_route_id", 0),
//                sharedPref.getString("selected_route_code", null),
//                sharedPref.getString("selected_route_name", null),
//                sharedPref.getFloat("selected_route_fixed_target", 0),
//                sharedPref.getFloat("selected_route_selected_target", 0));
//        if (route.getRouteId() != 0) {
//            return route;
//        }
//
//        return null;
//    }
//
//    public void storePreviousRoute(Route route) {
//        if (route != null) {
//            SharedPreferences.Editor editor = sharedPref.edit();
//            editor.putInt("previous_route_id", route.getRouteId());
//            editor.putString("previous_route_name", route.getRouteName());
//            editor.putFloat("previous_route_fixed_target", (float) route.getFixedTarget());
//            editor.putFloat("previous_route_selected_target", (float) route.getSelectedTarget());
//            editor.apply();
//        }
//    }

//    public Route getPreviousRoute() {
//
//        Route route = new Route(sharedPref.getInt("previous_route_id", 0), sharedPref.getString("previous_route_name", null),
//                sharedPref.getFloat("previous_route_fixed_target", 0), sharedPref.getFloat("previous_route_selected_target", 0));
//        if (route.getRouteId() != 0) {
//            return route;
//        }
//
//        return null;
//    }

    public void clearPref() {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("login_status", false);
      //  storePreviousRoute(getSelectedRoute());
        editor.putInt("selected_route_id", 0);
        editor.putString("selected_route_name", "");
        editor.putFloat("selected_route_fixed_target", 0);
        editor.putFloat("selected_route_selected_target", 0);
        editor.apply();
    }

    public int getSelectedOutletId() {
        return sharedPref.getInt("selected_out_id", 0);
    }

    public void setSelectedOutletId(int outletId) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("selected_out_id", outletId);
        editor.apply();
    }

//    public int startDay() {
//
//        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.putBoolean("day_status", true);
//
//        int session = sharedPref.getInt("local_session", 0) + 1;
//        editor.putInt("local_session", session);
//
//        long timeOut = TimeUtils.getDayEndTime(System.currentTimeMillis());
//
//        Log.d(LOG_TAG, "Setting timeout time at " + TimeUtils.formatDateTime(timeOut));
//
//        editor.putLong("login_timeout", timeOut);
//
//        editor.apply();
//
//        return session;
//    }

    public long getLoginTimeout() {
        return sharedPref.getLong("login_timeout", 0);
    }

    public void endDay() {
        Log.d(LOG_TAG, "Ending Day");
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("day_status", false);
     //   storePreviousRoute(getSelectedRoute());
        editor.putInt("selected_route_id", 0);
        editor.putString("selected_route_name", null);
        editor.putFloat("selected_route_fixed_target", 0);
        editor.putFloat("selected_route_selected_target", 0);
        editor.apply();
    }

    public int getLocalSessionId() {
        return sharedPref.getInt("local_session", 0);
    }

//    public void setDayStatus(boolean isDayStarted) {
//        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.putBoolean("day_status", isDayStarted);
//        editor.apply();
//    }

    public boolean isDayStarted() {
        return sharedPref.getBoolean("day_status", false);
    }

    public void setTransferToDealerList(boolean flag) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("transfer_to_dlist",flag);
        editor.apply();
    }

    public boolean getTransferToDealerList(boolean inverse) {

        boolean result = sharedPref.getBoolean("transfer_to_dlist", false);

        if(inverse){
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean("transfer_to_dlist", false);
            editor.apply();
        }

        return result;
    }

    public boolean validForLogin(int outletId) {
        String key = "outlet_changed_".concat(String.valueOf(outletId));
        int updatedCount = sharedPref.getInt(key, 0);

        return updatedCount <= 2;
    }

    public void notifyOutletHasChanged(int outletId) {
        String key = "outlet_changed_".concat(String.valueOf(outletId));
        int updatedCount = sharedPref.getInt(key, 0);
        updatedCount++;
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key, updatedCount);
        editor.apply();
    }

    //<editor-fold desc="Time Management">
    public void createInitialTimeVariables(long correctTime) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong("app_start_time", correctTime);
        editor.putLong("app_start_elapsed_time", SystemClock.elapsedRealtime());
        editor.putLong("time_differential", 0);
        editor.apply();
    }

    public void calculateTimeDifferential(long changedTime, long nowElapsedTime) {
        long initialTime = sharedPref.getLong("app_start_time", 0);
        long initialElapsedTime = sharedPref.getLong("app_start_elapsed_time", 0);
//        long initialDifferential = sharedPref.getLong("time_differential", 0);

        long currentCorrectTime = initialTime + (nowElapsedTime - initialElapsedTime);

        // The difference between the correct time and the changed time
        long differential = changedTime - currentCorrectTime;

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong("time_differential", differential);

        // Don't apply, commit. We need immediate change in the file.
        editor.commit();

    }

    public void compensateForDeviceReboot() {

        long newCorrectTime = System.currentTimeMillis() + sharedPref.getLong("time_differential", 0);
        long newElapsedTime = SystemClock.elapsedRealtime();

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong("app_start_time", newCorrectTime);
        editor.putLong("app_start_elapsed_time", newElapsedTime);

        // Don't apply, commit. We need immediate change in the file.
        editor.commit();
    }

    public long getRealTimeInMillis() {
        return System.currentTimeMillis() + sharedPref.getLong("time_differential", 0);
    }
    //</editor-fold>

    public void setPointingLocationIndex(int index) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("pointing_location", index);
        editor.apply();
    }

    public int getPointingLocationIndex() {
        return sharedPref.getInt("pointing_location", 0);
    }

    public void setBaseURL(String baseURL) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("baseURL", baseURL);
        editor.apply();
    }

    public String getBaseURL() {
        return sharedPref.getString("baseURL", "http://localhost");

    }

    public void setCurrentMillage(double millage){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putFloat("millage", (float) millage);
        editor.apply();
    }

    public double getPrevoiusMillage(){
        return sharedPref.getFloat("millage", 0);
    }



    public void setVersionName(String versionName) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("app_version_name", versionName).commit();
    }

    public String getVersionName(){
        return sharedPref.getString("app_version_name", "0.0.0");
    }
}
