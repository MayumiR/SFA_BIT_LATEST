package com.bit.sfa.DataControl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bit.sfa.model.Control;
import com.bit.sfa.Settings.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by Sathiyaraja on 6/20/2018.
 */

public class ControlDS {
    private SQLiteDatabase dB;
    private DatabaseHelper dbHelper;
    Context context;

    public ControlDS (Context context){
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }


    public void open() throws SQLException {
        dB = dbHelper.getWritableDatabase();
    }


    //work pending

    @SuppressWarnings("static-access")
    public int createOrUpdateFControl(ArrayList<Control> coList) {
        int serverdbID =0;
        if(dB == null){
            open();
        }else if(!dB.isOpen()){
            open();
        }
        Cursor cursor = null;

        try{


            cursor = dB.rawQuery("SELECT * FROM " + dbHelper.TABLE_FCONTROL, null);
            if(cursor.getCount()>0){
                int success = dB.delete(dbHelper.TABLE_FCONTROL, null, null);
                Log.v("Success", success+"");
            }

            for (Control control : coList) {

                ContentValues values = new ContentValues();

                values.put(dbHelper.FCONTROL_COM_NAME, control.getFCONTROL_COM_NAME());
                values.put(dbHelper.FCONTROL_COM_ADD1, control.getFCONTROL_COM_ADD1());
                values.put(dbHelper.FCONTROL_COM_ADD2, control.getFCONTROL_COM_ADD2());
                values.put(dbHelper.FCONTROL_COM_ADD3, control.getFCONTROL_COM_ADD3());
                values.put(dbHelper.FCONTROL_COM_TEL1, control.getFCONTROL_COM_TEL1());
                values.put(dbHelper.FCONTROL_COM_TEL2, control.getFCONTROL_COM_TEL2());
                values.put(dbHelper.FCONTROL_COM_FAX, control.getFCONTROL_COM_FAX());
                values.put(dbHelper.FCONTROL_COM_EMAIL, control.getFCONTROL_COM_EMAIL());
                values.put(dbHelper.FCONTROL_COM_WEB, control.getFCONTROL_COM_WEB());
                values.put(dbHelper.FCONTROL_FYEAR, control.getFCONTROL_FYEAR());
                values.put(dbHelper.FCONTROL_TYEAR, control.getFCONTROL_TYEAR());
                values.put(dbHelper.FCONTROL_COM_REGNO, control.getFCONTROL_COM_REGNO());
                values.put(dbHelper.FCONTROL_FTXN, control.getFCONTROL_FTXN());
                values.put(dbHelper.FCONTROL_TTXN, control.getFCONTROL_TTXN());
                values.put(dbHelper.FCONTROL_CRYSTALPATH, control.getFCONTROL_CRYSTALPATH());
                values.put(dbHelper.FCONTROL_VATCMTAXNO, control.getFCONTROL_VATCMTAXNO());
                values.put(dbHelper.FCONTROL_NBTCMTAXNO, control.getFCONTROL_NBTCMTAXNO());
                values.put(dbHelper.FCONTROL_SYSTYPE, control.getFCONTROL_SYSTYPE());
                values.put(dbHelper.FCONTROL_DEALCODE, control.getFCONTROL_DEALCODE());
                values.put(dbHelper.FCONTROL_BASECUR, control.getFCONTROL_BASECUR());
                values.put(dbHelper.FCONTROL_BALGCRLM, control.getFCONTROL_BALGCRLM());
                values.put(dbHelper.FCONTROL_CONAGE1, control.getFCONTROL_CONAGE1());
                values.put(dbHelper.FCONTROL_CONAGE2, control.getFCONTROL_CONAGE2());
                values.put(dbHelper.FCONTROL_CONAGE3, control.getFCONTROL_CONAGE3());
                values.put(dbHelper.FCONTROL_CONAGE4, control.getFCONTROL_CONAGE4());
                values.put(dbHelper.FCONTROL_CONAGE5, control.getFCONTROL_CONAGE5());
                values.put(dbHelper.FCONTROL_CONAGE6, control.getFCONTROL_CONAGE6());
                values.put(dbHelper.FCONTROL_CONAGE7, control.getFCONTROL_CONAGE7());
                values.put(dbHelper.FCONTROL_CONAGE8, control.getFCONTROL_CONAGE8());
                values.put(dbHelper.FCONTROL_CONAGE9, control.getFCONTROL_CONAGE9());
                values.put(dbHelper.FCONTROL_CONAGE10, control.getFCONTROL_CONAGE10());
                values.put(dbHelper.FCONTROL_CONAGE11, control.getFCONTROL_CONAGE11());
                values.put(dbHelper.FCONTROL_CONAGE12, control.getFCONTROL_CONAGE12());
                values.put(dbHelper.FCONTROL_CONAGE13, control.getFCONTROL_CONAGE13());
                values.put(dbHelper.FCONTROL_CONAGE14, control.getFCONTROL_CONAGE14());
                values.put(dbHelper.FCONTROL_SALESACC, control.getFCONTROL_SALESACC());

                serverdbID = (int) dB.insert(dbHelper.TABLE_FCONTROL, null, values);

            }
        }finally {
            if (cursor !=null) {
                cursor.close();
            }
            dB.close();
        }
        return serverdbID;

    }

    public ArrayList<Control> getAllControl() {
        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        ArrayList<Control> list = new ArrayList<Control>();

        String selectQuery = "select * from "+dbHelper.TABLE_FCONTROL;

        Cursor cursor = dB.rawQuery(selectQuery, null);
        while(cursor.moveToNext()){

            Control aControl=new Control();

            aControl.setFCONTROL_COM_NAME(cursor.getString(cursor.getColumnIndex(dbHelper.FCONTROL_COM_NAME)));
            aControl.setFCONTROL_COM_ADD1(cursor.getString(cursor.getColumnIndex(dbHelper.FCONTROL_COM_ADD1)));
            aControl.setFCONTROL_COM_ADD2(cursor.getString(cursor.getColumnIndex(dbHelper.FCONTROL_COM_ADD2)));
            aControl.setFCONTROL_COM_ADD3(cursor.getString(cursor.getColumnIndex(dbHelper.FCONTROL_COM_ADD3)));
            aControl.setFCONTROL_COM_TEL1(cursor.getString(cursor.getColumnIndex(dbHelper.FCONTROL_COM_TEL1)));
            aControl.setFCONTROL_COM_TEL2(cursor.getString(cursor.getColumnIndex(dbHelper.FCONTROL_COM_TEL2)));
            aControl.setFCONTROL_COM_FAX(cursor.getString(cursor.getColumnIndex(dbHelper.FCONTROL_COM_FAX)));
            aControl.setFCONTROL_COM_EMAIL(cursor.getString(cursor.getColumnIndex(dbHelper.FCONTROL_COM_EMAIL)));
            aControl.setFCONTROL_COM_WEB(cursor.getString(cursor.getColumnIndex(dbHelper.FCONTROL_COM_WEB)));
            aControl.setFCONTROL_DEALCODE(cursor.getString(cursor.getColumnIndex(dbHelper.FCONTROL_DEALCODE)));

            list.add(aControl);

        }

        return list;
    }


    public int getSysType(){

        if(dB == null){
            open();
        }else if(!dB.isOpen()){
            open();
        }

        String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FCONTROL ;

        Cursor cursor = null;
        cursor = dB.rawQuery(selectQuery, null);

        while(cursor.moveToNext()){

            return cursor.getInt(cursor.getColumnIndex(dbHelper.FCONTROL_SYSTYPE));

        }
        return 0;

    }
}
