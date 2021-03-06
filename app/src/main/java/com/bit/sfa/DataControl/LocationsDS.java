package com.bit.sfa.DataControl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bit.sfa.model.Locations;
import com.bit.sfa.Settings.DatabaseHelper;

import java.util.ArrayList;

public class LocationsDS {
	private SQLiteDatabase dB;
	private DatabaseHelper dbHelper;
	Context context;
	private String TAG = "swadeshi";

	public LocationsDS(Context context) {
		this.context = context;
		dbHelper = new DatabaseHelper(context);
	}

	public void open() throws SQLException {
		dB = dbHelper.getWritableDatabase();
	}

	@SuppressWarnings("static-access")
	public int createOrUpdateFLocations(ArrayList<Locations> list) {
		int count = 0;
		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}
		Cursor cursor = null;
		Cursor cursor_ini = null;
		try {

			cursor_ini = dB.rawQuery("SELECT * FROM " + dbHelper.TABLE_FLOCATIONS, null);
			
			for (Locations locations : list) {

				ContentValues values = new ContentValues();
				values.put(dbHelper.FLOCATIONS_ADD_MACH, locations.getFLOCATIONS_ADD_MACH());
				values.put(dbHelper.FLOCATIONS_ADD_USER, locations.getFLOCATIONS_ADD_USER());
				values.put(dbHelper.FLOCATIONS_LOC_CODE, locations.getFLOCATIONS_LOC_CODE());
				values.put(dbHelper.FLOCATIONS_LOC_NAME, locations.getFLOCATIONS_LOC_NAME());
				values.put(dbHelper.FLOCATIONS_LOC_T_CODE, locations.getFLOCATIONS_LOC_T_CODE());
				values.put(dbHelper.FLOCATIONS_REP_CODE, locations.getFLOCATIONS_REP_CODE());
				values.put(dbHelper.FLOCATIONS_COST_CODE, locations.getFLOCATIONS_COST_CODE());

				if (cursor_ini.moveToFirst()) {
					String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FLOCATIONS + " WHERE " + dbHelper.FLOCATIONS_LOC_CODE + "='" + locations.getFLOCATIONS_LOC_CODE() + "'";
					cursor = dB.rawQuery(selectQuery, null);

					if (cursor.moveToFirst()) {
						count = (int) dB.update(dbHelper.TABLE_FLOCATIONS, values, dbHelper.FLOCATIONS_LOC_CODE + "='" + locations.getFLOCATIONS_LOC_CODE() + "'", null);
					} else {
						count = (int) dB.insert(dbHelper.TABLE_FLOCATIONS, null, values);
					}

				} else {
					count = (int) dB.insert(dbHelper.TABLE_FLOCATIONS, null, values);
				}

			}
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			
			if (cursor_ini != null) {
				cursor_ini.close();
			}
			dB.close();
		}
		return count;

	}

	// location search by name.
	public ArrayList<String> getlocDetails(String loctcode, String searchword) {
		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}

		ArrayList<String> Itemname = new ArrayList<String>();

		String selectQuery = "select LocName,LocCode from fLocations where LoctCode = '" + loctcode + "' AND ( LocCode LIKE '%" + searchword + "%' OR LocName LIKE '%" + searchword + "%')";

		Cursor cursor = null;
		cursor = dB.rawQuery(selectQuery, null);

		while (cursor.moveToNext()) {

			String Itemname1 = cursor.getString(0) + "-:-" + cursor.getString(1);
			Itemname.add(Itemname1);

		}

		return Itemname;
	}

	public ArrayList<String> getAlllocDetails(String searchword) {
		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}

		ArrayList<String> Itemname = new ArrayList<String>();

		String selectQuery = "select LocName,LocCode from fLocations where ( LocCode LIKE '%" + searchword + "%' OR LocName LIKE '%" + searchword + "%')";

		Cursor cursor = null;
		cursor = dB.rawQuery(selectQuery, null);

		while (cursor.moveToNext()) {

			String Itemname1 = cursor.getString(0) + "-:-" + cursor.getString(1);
			Itemname.add(Itemname1);

		}

		return Itemname;
	}

	// public ArrayList<Locations> getlocDetails(String loctcode)
	// {
	// if (dB == null)
	// {
	// open();
	// } else if (!dB.isOpen())
	// {
	// open();
	// }
	//
	// ArrayList<Locations> list = new ArrayList<Locations>();
	//
	// String selectQuery =
	// "select LocName,LocCode from fLocation where LoctCode = '"+loctcode+"'";
	//
	// Cursor cursor = dB.rawQuery(selectQuery, null);
	// while(cursor.moveToNext()){
	//
	// Locations Locationsdata=new Locations();
	//
	// Locationsdata.setFLOCATIONS_LOC_NAME(cursor.getString(cursor.getColumnIndex(dbHelper.FLOCATIONS_LOC_NAME)));
	// Locationsdata.setFLOCATIONS_LOC_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FLOCATIONS_LOC_CODE)));
	//
	//
	// list.addLocationsdatat);
	//
	// }
	//
	// return list;
	// }

	@SuppressWarnings("static-access")
	public int deleteAll() {

		int count = 0;

		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}
		Cursor cursor = null;
		try {

			cursor = dB.rawQuery("SELECT * FROM " + dbHelper.TABLE_FLOCATIONS, null);
			count = cursor.getCount();
			if (count > 0) {
				int success = dB.delete(dbHelper.TABLE_FLOCATIONS, null, null);
				Log.v("Success", success + "");
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
	public String getItemNameByCode(String code) {

		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}

		String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FLOCATIONS + " WHERE " + dbHelper.FLOCATIONS_LOC_CODE + "='" + code + "'";

		Cursor cursor = dB.rawQuery(selectQuery, null);

		while (cursor.moveToNext()) {

			return cursor.getString(cursor.getColumnIndex(dbHelper.FLOCATIONS_LOC_NAME));

		}

		return ""; 
	}
	
	@SuppressWarnings("static-access")
	public String getRepLocation(String CostCode) {

		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}

		String selectQuery = "SELECT "+dbHelper.FLOCATIONS_LOC_CODE+" FROM " + dbHelper.TABLE_FLOCATIONS + " WHERE " + dbHelper.FCOST_CODE + "='" + CostCode + "'" ;

		Cursor cursor = dB.rawQuery(selectQuery, null);

		while (cursor.moveToNext()) {

			return cursor.getString(cursor.getColumnIndex(dbHelper.FLOCATIONS_LOC_CODE));

		}

		return "";
	}

	public String getMRepLocation(String RepCode) {

		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}

		String selectQuery = "SELECT "+dbHelper.FMSALREP_LOCCODE+" FROM " + dbHelper.TABLE_FMSALREP + " WHERE " + dbHelper.FMSALREP_ID + "='" + RepCode + "'" ;

		Cursor cursor = dB.rawQuery(selectQuery, null);

		while (cursor.moveToNext()) {

			return cursor.getString(cursor.getColumnIndex(dbHelper.FMSALREP_LOCCODE));

		}

		return "";
	}

	public String getMRepCostCode(String LocCode) {

		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}

		String selectQuery = "SELECT "+dbHelper.FLOCATIONS_COST_CODE+" FROM " + dbHelper.TABLE_FLOCATIONS + " WHERE " + dbHelper.FLOCATIONS_LOC_CODE + "='" + LocCode + "'" ;

		Cursor cursor = dB.rawQuery(selectQuery, null);

		while (cursor.moveToNext()) {

			return cursor.getString(cursor.getColumnIndex(dbHelper.FMSALREP_LOCCODE));

		}

		return "";
	}
}
