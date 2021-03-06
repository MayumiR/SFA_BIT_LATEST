package com.bit.sfa.DataControl;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.bit.sfa.model.FmDebtor;
import com.bit.sfa.Settings.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by Rashmi on 7/23/2018.
 */

public class FmDebtorDS {
    private SQLiteDatabase dB;
    private DatabaseHelper dbHelper;
    Context context;
    private String TAG = "FmDebtorDS";

    public static SharedPreferences localSP;

    public FmDebtorDS(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        dB = dbHelper.getWritableDatabase();
    }

    public long InsertFmDebtor(ArrayList<FmDebtor> fmDebtors) {

        int count = 0;

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        Cursor cursor = null;
        try {
            dB.beginTransaction();
            String sql = "Insert or Replace into " + dbHelper.TABLE_FMDEBTOR + " (" + dbHelper.FDEBTOR_CODEM + ", "
                    + dbHelper.FDEBTOR_NAMEM + ","
                    + dbHelper.FDEBTOR_REPCODEM + ","
                    + dbHelper.FDEBTOR_ADD_USER + ","
                    + dbHelper.FDEBTOR_ADD_MACH + ","
                    + dbHelper.FDEBTOR_ADD_DATE + ","
                    + dbHelper.FDEBTOR_MROUTE_CODE + ","
                    + dbHelper.FDEBTOR_RECORD_ID + ") values(?,?,?,?,?,?,?,?)";

            SQLiteStatement insert = dB.compileStatement(sql);

            for (FmDebtor debtor : fmDebtors) {

                insert.bindString(1, debtor.getDebCodeM());
                insert.bindString(2, debtor.getDebNameM());
                insert.bindString(3, debtor.getRepCodem());
                insert.bindString(4, debtor.getAddUser());
                insert.bindString(5, debtor.getAddMach());
                insert.bindString(6, debtor.getAddDate());
                insert.bindString(7, debtor.getRouteCode());
                insert.bindString(8, debtor.getRecordid());
                insert.execute();

                count = 1;
            }

            dB.setTransactionSuccessful();
            Log.w(TAG, "Done");
        } catch (Exception e) {

            Log.v(TAG + " Exception", e.toString());

        } finally {
            dB.endTransaction();

            dB.close();
        }
        return count;
    }
    public ArrayList<FmDebtor> getRouteCustomers(String date,String RouteCode) {
        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        ArrayList<FmDebtor> list = new ArrayList<FmDebtor>();

        String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FITEDEBDET + " RD, " + dbHelper.TABLE_FMDEBTOR
                + " D WHERE RD." + dbHelper.FITEDEBDET_DEBCODEM + "=D." + dbHelper.FDEBTOR_CODEM + " AND RD."
                + dbHelper.FITEDEBDET_ROUTE_CODE + "='" + RouteCode + "' and RD."+dbHelper.FITEDEBDET_TXNDATE+" = '"+date+"'";

       // String selectQuery = "select * from fmDebtor ";


        Cursor cursor = dB.rawQuery(selectQuery, null);
        while (cursor.moveToNext()) {

            FmDebtor aDebtor = new FmDebtor();

            aDebtor.setDebCodeM(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CODEM)));
            aDebtor.setDebNameM(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_NAMEM)));
            aDebtor.setRepCodem(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_REPCODEM)));
            aDebtor.setAddUser(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD_USER)));
            aDebtor.setAddMach(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD_MACH)));
            aDebtor.setAddDate(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD_DATE)));
            aDebtor.setRouteCode(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_MROUTE_CODE)));
            aDebtor.setRecordid(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_RECORD_ID)));
            list.add(aDebtor);

        }

        return list;
        }
    public ArrayList<FmDebtor> getAllCustomers() {
        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        ArrayList<FmDebtor> list = new ArrayList<FmDebtor>();


         String selectQuery = "select * from fmDebtor ";

        Cursor cursor = dB.rawQuery(selectQuery, null);
        while (cursor.moveToNext()) {

            FmDebtor aDebtor = new FmDebtor();

            aDebtor.setDebCodeM(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CODEM)));
            aDebtor.setDebNameM(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_NAMEM)));
            aDebtor.setRepCodem(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_REPCODEM)));
            aDebtor.setAddUser(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD_USER)));
            aDebtor.setAddMach(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD_MACH)));
            aDebtor.setAddDate(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD_DATE)));
            aDebtor.setRouteCode(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_MROUTE_CODE)));
            aDebtor.setRecordid(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_RECORD_ID)));
            list.add(aDebtor);

        }

        return list;
    }
    public FmDebtor getSelectedCustomerByCode(String code) {
        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        // ArrayList<Debtor> list = new ArrayList<Debtor>();
        Cursor cursor = null;
        try {
            String selectQuery = "select * from " + dbHelper.TABLE_FMDEBTOR + " Where " + dbHelper.FMISS_DEBCODEM + "='"
                    + code + "'";

            cursor = dB.rawQuery(selectQuery, null);

            while (cursor.moveToNext()) {

                FmDebtor aDebtor = new FmDebtor();

                aDebtor.setDebCodeM(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CODEM)));
                aDebtor.setDebNameM(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_NAMEM)));
                aDebtor.setRepCodem(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_REPCODEM)));
                aDebtor.setAddUser(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD_USER)));
                aDebtor.setAddMach(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD_MACH)));
                aDebtor.setAddDate(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD_DATE)));
                aDebtor.setRouteCode(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_MROUTE_CODE)));
                aDebtor.setRecordid(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_RECORD_ID)));

                return aDebtor;

            }
        } catch (Exception e) {

            Log.v(TAG + " Exception", e.toString());

        } finally {
            if (cursor != null) {
                cursor.close();
            }
            dB.close();
        }

        return null;

    }
}
