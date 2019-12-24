package com.bit.sfa.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

//import com.afollestad.materialdialogs.MaterialDialog;
import com.bit.sfa.DataControl.SalRepDS;
import com.bit.sfa.DataControl.ServerDatabaseDS;
import com.bit.sfa.R;
import com.bit.sfa.helpers.NetworkFunctions;
import com.bit.sfa.helpers.SharedPref;
import com.bit.sfa.model.ServerDatabase;

import com.bit.sfa.Settings.ConnectionDetector;
import com.bit.sfa.Settings.DatabaseHelper;
import com.bit.sfa.Settings.SettingsActivity;
import com.bit.sfa.Settings.SharedPreferencesClass;
import com.bit.sfa.dialog.CustomProgressDialog;
import com.bit.sfa.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class ActivitySplash extends AppCompatActivity{

    private ImageView logo;
    private static int SPLASH_TIME_OUT = 2000;
    private Context context = this;
    DatabaseHelper db;
    private SharedPref pref;
    private NetworkFunctions networkFunctions;
    private String TAG = "ActivitySplash";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        db=new DatabaseHelper(getApplicationContext());
        SQLiteDatabase SFA;
        SFA = db.getWritableDatabase();
        pref = SharedPref.getInstance(context);
        db.onUpgrade(SFA, 1, 2);

        logo = (ImageView)findViewById(R.id.logo);
        networkFunctions = new NetworkFunctions(context);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.logo_up);
        logo.startAnimation(animation1);

        boolean connectionStatus = new ConnectionDetector(ActivitySplash.this).isConnectingToInternet();

//user data aragenanm  no need to check,
        // login wela nam kelinma customer list ekata
        //slim eke authenticate function eka liyanna-study that

        if(pref.getLoginUser()==null) {

            if (connectionStatus) {

                new Handler().postDelayed(new Runnable() {

                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void run() {
                        // This method will be executed once the timer is over
                        // Start your app main activity
                        // close this activity
                        new Validate("84:55:A5:F1:E6:0C").execute();

//                        if (!ds.getCurrentRepCode().equals("") && localSP.getString("Sync_Status", "").toString().equals("Success")) {
//
//                            Intent intent = new Intent(context, ActivityHome.class);
//                            startActivity(intent);
//                            finish();
//
//                            overridePendingTransition(R.anim.in, R.anim.exit);
//
//                        } else {
//
//                       //     TODO :: Setting activity should be in home for setting icon
////                            Intent mainActivity = new Intent(ActivitySplash.this, SettingsActivity.class);
////                            startActivity(mainActivity);
////                            finish();
////                            overridePendingTransition(R.anim.in, R.anim.exit);
//
//                        }
                        // close this activity
                        finish();
                    }
                }, SPLASH_TIME_OUT);

            } else {
                Toast.makeText(this, "No internet connection..", Toast.LENGTH_LONG).show();
            }
        }else{

        }

}

//
    public void reCallActivity(){
        Intent mainActivity = new Intent(ActivitySplash.this, ActivitySplash.class);
        startActivity(mainActivity);
    }
//already checked macId
    public void goToLogin(){
        Intent mainActivity = new Intent(ActivitySplash.this, ActivityLogin.class);
        startActivity(mainActivity);
        finish();
    }
    // write get mac address code

    private class Validate extends AsyncTask<String, Integer, Boolean> {
        int totalRecords=0;
        private CustomProgressDialog pDialog;
        private String macId;

        public Validate(String macId){
            this.macId = macId;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog  = new CustomProgressDialog(ActivitySplash.this);
            pDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            pDialog.setMessage("Validating...");
            pDialog.show();

        }

        @Override
        protected Boolean doInBackground(String... arg0) {

            try {
                int recordCount = 0;
                int totalBytes  = 0;
                String validateResponse = null;
                JSONObject validateJSON;
                try {
                    validateResponse = networkFunctions.validate(macId);
                    Log.d("validateResponse",validateResponse);
                    validateJSON = new JSONObject(validateResponse);
                } catch (IOException e) {
                    Log.e("networkFunctions ->","IOException -> "+e.toString());
                    throw e;
                } catch (JSONException e) {
                    Log.e("networkFunctions ->","JSONException -> "+e.toString());
                    throw e;
                }

                if (validateJSON != null && validateJSON.getBoolean("result")) {
                    //dbHandler.clearTables();
                    // Login successful. Proceed to download other items
                    User user = User.parseUser(validateJSON);
                    networkFunctions.setUser(user);
                    pref.storeLoginUser(user);
                }


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pDialog.setMessage("Authenticated...");
                        }
                    });

            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            pDialog.setMessage("Prefetching data..." + progress[0] + "/" + totalRecords);

        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            pDialog.dismiss();
            if(result){
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                //set user details to shared prefferences
                //Intent mainActivity = new Intent(ActivitySplash.this, SettingsActivity.class);
                Intent loginActivity = new Intent(ActivitySplash.this, ActivityLogin.class);
                startActivity(loginActivity);
                finish();
            }else{
                Toast.makeText(getApplicationContext(), "Invalid Mac Id", Toast.LENGTH_SHORT).show();


               // create a dialog with AlertDialog builder
//                AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AppThemeWithTitle);
//              //  builder.setTitle(getString(R.string.label_exit_game));
//                builder.setMessage("Invalid Response");
//
//                String positiveText = getString(android.R.string.ok);
//                builder.setPositiveButton(positiveText,
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                // dismiss alert dialog, update preferences with game score and restart play fragment
//                              reCallActivity();
//                                Log.d("myTag", "positive button clicked");
//                                dialog.dismiss();
//                            }
//                        });
//
//                String negativeText = getString(android.R.string.cancel);
//                builder.setNegativeButton(negativeText,
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                // dismiss dialog, start counter again
//                                dialog.dismiss();
//                                Log.d("myTag", "negative button clicked");
//                            }
//                        });
//
//                AlertDialog dialog = builder.create();
//// display dialog
//                dialog.show();



            }
        }

    }
}
