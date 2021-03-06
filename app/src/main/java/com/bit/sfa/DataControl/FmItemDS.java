package com.bit.sfa.DataControl;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.bit.sfa.model.FmItem;
import com.bit.sfa.model.FmItems;
import com.bit.sfa.model.PreProduct;
import com.bit.sfa.Settings.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by Rashmi on 12/20/2018.
 */

public class FmItemDS {

    private SQLiteDatabase dB;
    private DatabaseHelper dbHelper;
    Context context;
    private String TAG = "FmItemDS";

    public static SharedPreferences localSP;

    public FmItemDS(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        dB = dbHelper.getWritableDatabase();
    }

    public long InsertFmItems(ArrayList<FmItems> fmItems) {

        int count = 0;

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        Cursor cursor = null;
        try {
            dB.beginTransaction();
            String sql = "Insert or Replace into " + dbHelper.TABLE_FMITEMS + " (" + dbHelper.GITEM_CODE + ", "
                    + dbHelper.GITEM_NAME + ", "
                    + dbHelper.UOM + ", "
                    + dbHelper.GITYPE + ", "
                    + dbHelper.GITYPES + ", "
                    + dbHelper.GITEM_NAMED + ", "
                    + dbHelper.REMARKS + ", "
                    + dbHelper.ADD_USER + ", "
                    + dbHelper.ADD_DATE + ", "
                    + dbHelper.ADD_MACH + ", "
                    + dbHelper.RECORD_ID + ") values(?,?,?,?,?,?,?,?,?,?,? )";

            SQLiteStatement insert = dB.compileStatement(sql);

            for (FmItems fitem : fmItems) {

                insert.bindString(1, fitem.getGITEM_CODE());
                insert.bindString(2, fitem.getGITEM_NAME());
                insert.bindString(3, fitem.getUOM());
                insert.bindString(4, fitem.getGITYPE());
                insert.bindString(5, fitem.getGITYPES());
                insert.bindString(6, fitem.getGITEM_NAMED());
                insert.bindString(7, fitem.getREMARKS());
                insert.bindString(8, fitem.getADD_USER());
                insert.bindString(9, fitem.getADD_DATE());
                insert.bindString(10, fitem.getADD_MACH());
                insert.bindString(11, fitem.getRECORD_ID());
                insert.execute();

                count = 1;
            }

            dB.setTransactionSuccessful();
            Log.w(TAG, "Done");
        } catch (Exception e) {

            Log.v(TAG + " FmItemDS", e.toString());

        } finally {
            dB.endTransaction();

            dB.close();
        }
        return count;


    }
    /*-*-**-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-**-*-**-*/

    public String getItemNameByCode(String code) {

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        String selectQuery = "SELECT * FROM " + DatabaseHelper.TABLE_FMITEMS + " WHERE " + DatabaseHelper.GITEM_CODE + "='" + code + "'";

        Cursor cursor = dB.rawQuery(selectQuery, null);
        try {
            while (cursor.moveToNext()) {

                return cursor.getString(cursor.getColumnIndex(DatabaseHelper.GITEM_NAME));

            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (cursor != null) {
                cursor.close();
            }
            dB.close();
        }
        return "";
    }

    /*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-**-*-*-*/
//---------------------------------------------------get Items for promo order----rashmi 2018-08-20------------------------------------------------

    public ArrayList<PreProduct> getAllItemForPreSales(String newText,String refno) {
        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        ArrayList<PreProduct> list = new ArrayList<PreProduct>();
        String selectQuery;
       // selectQuery = "SELECT itm.GItemName, itm.GItemCode, itm.UOM, itm.GIType, itm.GITypeS, itm.GItemNameD, itm.MarkUp, itm.MarkUpPer, itm.PriceChange, itm.lGrnPrice, loc.QOH FROM fitem itm, fitemLoc loc WHERE itm.ItemCode || itm.ItemName LIKE '%" + newText + "%' AND loc.ItemCode=itm.ItemCode AND  itm.ItemCode not in (SELECT DISTINCT ItemCode FROM FTranSODet WHERE " + type + " And RefNo ='" + refno + "') ORDER BY CAST(loc.QOH AS FLOAT) DESC";
        selectQuery = "SELECT itm.GItemName, itm.GItemCode, itm.UOM, itm.GIType, itm.GITypeS, itm.GItemNameD FROM fmItems itm WHERE itm.GItemName || itm.GItemCode LIKE '%" + newText + "%' AND  itm.GItemCode not in (SELECT DISTINCT GItemCode FROM FOrddet WHERE RefNo ='" + refno + "') ";
        Cursor cursor = dB.rawQuery(selectQuery, null);
        try {
            while (cursor.moveToNext()) {


                PreProduct preProduct=new PreProduct();
                preProduct.setPREPRODUCT_ITEMCODE(cursor.getString(cursor.getColumnIndex(DatabaseHelper.GITEM_CODE)));
                preProduct.setPREPRODUCT_ITEMNAME(cursor.getString(cursor.getColumnIndex(DatabaseHelper.GITEM_NAME)));
                preProduct.setPREPRODUCT_UOM(cursor.getString(cursor.getColumnIndex(DatabaseHelper.UOM)));
                preProduct.setPREPRODUCT_GITYPE(cursor.getString(cursor.getColumnIndex(DatabaseHelper.GITYPE)));
                preProduct.setPREPRODUCT_GITYPES(cursor.getString(cursor.getColumnIndex(DatabaseHelper.GITYPES)));
                preProduct.setPREPRODUCT_GITEMNAMED(cursor.getString(cursor.getColumnIndex(DatabaseHelper.GITEM_NAMED)));
                preProduct.setPREPRODUCT_QTY("0");

                list.add(preProduct);
                // }
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (cursor != null) {
                cursor.close();
            }
            dB.close();
        }
        return list;
    }
    public ArrayList<FmItem> getAllItems() {

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        Cursor cursor = null;
        ArrayList<FmItem> list = new ArrayList<FmItem>();
        try {


            String searchsql = "";
            searchsql = "SELECT * FROM " + DatabaseHelper.TABLE_FMITEMS;
            cursor = dB.rawQuery(searchsql, null);


            while (cursor.moveToNext()) {

                FmItem item = new FmItem();
                item.setGITEM_CODE(cursor.getString(cursor.getColumnIndex(DatabaseHelper.GITEM_CODE)));
                item.setGITEM_NAME(cursor.getString(cursor.getColumnIndex(DatabaseHelper.GITEM_NAME)));
                list.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
            dB.close();
        }

        return list;
    }
    public ArrayList<FmItem> findAllItems(String key) {

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        Cursor cursor = null;
        ArrayList<FmItem> list = new ArrayList<FmItem>();
        try {


            String searchsql = "";
            searchsql = "SELECT * FROM " + DatabaseHelper.TABLE_FMITEMS + " WHERE GItemName LIKE '" + key + "%'";
            cursor = dB.rawQuery(searchsql, null);


            while (cursor.moveToNext()) {

                FmItem item = new FmItem();
                item.setGITEM_CODE(cursor.getString(cursor.getColumnIndex(DatabaseHelper.GITEM_CODE)));
                item.setGITEM_NAME(cursor.getString(cursor.getColumnIndex(DatabaseHelper.GITEM_NAME)));
                list.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
            dB.close();
        }

        return list;
    }
}
