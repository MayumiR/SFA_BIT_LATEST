package com.bit.sfa.DataControl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bit.sfa.model.FmSalRep;
import com.bit.sfa.Settings.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by Sathiyaraja on 6/19/2018.
 */

public class SalRepDS {
    private SQLiteDatabase dB;
    private DatabaseHelper dbHelper;
    Context context;
    private String TAG = "kfd MEDI";


    public SalRepDS(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        dB = dbHelper.getWritableDatabase();
    }

    @SuppressWarnings("static-access")
    public int createOrUpdateSalRep(ArrayList<FmSalRep> list) {

        int count = 0;

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        Cursor cursor = null;

        try {

            cursor = dB.rawQuery("SELECT * FROM " + dbHelper.TABLE_FMSALREP, null);
            if (cursor.getCount() > 0) {
                int success = dB.delete(dbHelper.TABLE_FMSALREP, null, null);
                Log.v("Success", success + "");
            }


            for (FmSalRep rep : list) {

                ContentValues values = new ContentValues();

                values.put(dbHelper.FMSALREP_ID, rep.getRepCodem());
                values.put(dbHelper.FMSALREP_NAME, rep.getRepNamem());
                values.put(dbHelper.FMSALREP_IDNO, rep.getRecordId());
                values.put(dbHelper.FMSALREP_ADD1, rep.getRepAdd1());
                values.put(dbHelper.FMSALREP_ADD2, rep.getRepAdd2());
                values.put(dbHelper.FMSALREP_ADD3, rep.getRepAdd3());
                values.put(dbHelper.FMSALREP_TELE, rep.getRepTele());
                values.put(dbHelper.FMSALREP_MOBILE, rep.getRepMobil());
                values.put(dbHelper.FMSALREP_EMAIL, rep.getRepEMail());
                values.put(dbHelper.FMSALREP_ROUTE_CODE, rep.getRouteCode());
                values.put(dbHelper.FMSALREP_LOCCODE, rep.getLocCode());
                values.put(dbHelper.FMSALREP_AREASCODE, rep.getAreascode());
                values.put(dbHelper.FMSALREP_ADD_USER, rep.getAddUser());
                values.put(dbHelper.FMSALREP_ADD_MACH, rep.getAddMach());
                values.put(dbHelper.FMSALREP_ADD_DATE, rep.getAddDate());


                count = (int) dB.insert(dbHelper.TABLE_FMSALREP, null, values);

            }
        } catch (Exception e) {

            Log.v(TAG + " Exception", e.toString());

        } finally {
            if (cursor != null) {
                cursor.close();
            }
            dB.close();
        }
        return count;

    }

    @SuppressWarnings("static-access")
    public String getCurrentRepCode() {

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FMSALREP;

        Cursor cursor = dB.rawQuery(selectQuery, null);

        while (cursor.moveToNext()) {

            return cursor.getString(cursor.getColumnIndex(dbHelper.FMSALREP_ID));

        }

        return "";
    }


    public ArrayList<FmSalRep> getSaleRepDetails() {

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        ArrayList<FmSalRep> rep_list = new ArrayList<FmSalRep>();

        String selectRep = "SELECT * FROM " + dbHelper.TABLE_FMSALREP;

        Cursor curRep = null;
        curRep = dB.rawQuery(selectRep, null);

        while (curRep.moveToNext()) {

            FmSalRep newRep = new FmSalRep();
            newRep.setRepCodem(curRep.getString(0));
            newRep.setRepNamem(curRep.getString(1));
            newRep.setRepIdNo(curRep.getString(2));
            newRep.setRepAdd1(curRep.getString(3));
            newRep.setRepAdd2(curRep.getString(4));
            newRep.setRepAdd3(curRep.getString(5));
            newRep.setRepTele(curRep.getString(6));
            newRep.setRepMobil(curRep.getString(7));
            newRep.setRepEMail(curRep.getString(8));
            newRep.setRouteCode(curRep.getString(9));
            newRep.setLocCode(curRep.getString(10));
            newRep.setAreascode(curRep.getString(11));
            newRep.setAddUser(curRep.getString(12));
            newRep.setAddMach(curRep.getString(12));
            newRep.setAddDate(curRep.getString(12));
            newRep.setRecordId(curRep.getString(12));
            rep_list.add(newRep);
        }

        return rep_list;
    }

    @SuppressWarnings("static-access")
    public String getDealCode() {

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FMSALREP;

        Cursor cursor = dB.rawQuery(selectQuery, null);

        while (cursor.moveToNext()) {

//            return cursor.getString(cursor.getColumnIndex(dbHelper.FMSALREP_DEAL_CODE));

        }

        return "";
    }


    public String getAreaCode() {

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FMSALREP;

        Cursor cursor = dB.rawQuery(selectQuery, null);

        while (cursor.moveToNext()) {

            return cursor.getString(cursor.getColumnIndex(dbHelper.FMSALREP_AREASCODE));

        }

        return "";
    }


    @SuppressWarnings("static-access")
    public String getCurrentRepLocCode() {
        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FMSALREP;

        Cursor cursor = null;
        cursor = dB.rawQuery(selectQuery, null);

        while (cursor.moveToNext()) {

            return cursor.getString(cursor.getColumnIndex(dbHelper.FMSALREP_LOCCODE));

        }

        return "";
    }

    @SuppressWarnings("static-access")
    public String getCurrentCostCode() {
        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FMSALREP;

        Cursor cursor = null;
        cursor = dB.rawQuery(selectQuery, null);

        while (cursor.moveToNext()) {
//
//            return cursor.getString(cursor.getColumnIndex(dbHelper.FSALREP_COSTCODE));

        }

        return "";
    }

    public FmSalRep getSaleRep(String Repcode) {

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        String selectRep = "SELECT * FROM fmSalRep WHERE RepCodem='" + Repcode + "'";

        Cursor curRep = null;
        curRep = dB.rawQuery(selectRep, null);
        FmSalRep newRep = new FmSalRep();

        while (curRep.moveToNext()) {
            newRep.setPrefix(curRep.getString(curRep.getColumnIndex(DatabaseHelper.FMSALREP_REP_PREFIX)));
            newRep.setRepCodem(curRep.getString(curRep.getColumnIndex(DatabaseHelper.FMSALREP_ID)));
            newRep.setRepEMail(curRep.getString(curRep.getColumnIndex(DatabaseHelper.FMSALREP_EMAIL)));
            newRep.setRepNamem(curRep.getString(curRep.getColumnIndex(DatabaseHelper.FMSALREP_NAME)));
            newRep.setRepMobil(curRep.getString(curRep.getColumnIndex(DatabaseHelper.FMSALREP_MOBILE)));
        }
        return newRep;
    }
}
