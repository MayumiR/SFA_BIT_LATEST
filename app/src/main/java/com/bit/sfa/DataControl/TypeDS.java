package com.bit.sfa.DataControl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bit.sfa.model.Type;
import com.bit.sfa.Settings.DatabaseHelper;

import java.util.ArrayList;

public class TypeDS {

	private SQLiteDatabase dB;
	private DatabaseHelper dbHelper;
	Context context;
	private String TAG = "swadeshi";

	public TypeDS(Context context) {
		this.context = context;
		dbHelper = new DatabaseHelper(context);
	}

	public void open() throws SQLException {
		dB = dbHelper.getWritableDatabase();
	}

	@SuppressWarnings("static-access")
	public int createOrUpdateType(ArrayList<Type> list) {

		int count = 0;

		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}
		Cursor cursor = null;
		Cursor cursor_ini = null;

		try {

			cursor_ini = dB.rawQuery("SELECT * FROM " + dbHelper.TABLE_FTYPE, null);

			for (Type type : list) {

				ContentValues values = new ContentValues();

				values.put(dbHelper.FTYPE_ADD_DATE, type.getFTYPE_ADD_DATE());
				values.put(dbHelper.FTYPE_ADD_MACH, type.getFTYPE_ADD_MACH());
				values.put(dbHelper.FTYPE_ADD_USER, type.getFTYPE_ADD_USER());
				values.put(dbHelper.FTYPE_RECORDID, type.getFTYPE_RECORDID());
				values.put(dbHelper.FTYPE_CODE, type.getFTYPE_CODE());
				values.put(dbHelper.FTYPE_NAME, type.getFTYPE_NAME());

				if (cursor_ini.moveToFirst()) {
					String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FTYPE + " WHERE " + dbHelper.FTYPE_CODE + "='" + type.getFTYPE_CODE() + "'";
					cursor = dB.rawQuery(selectQuery, null);

					if (cursor.moveToFirst()) {
						count = (int) dB.update(dbHelper.TABLE_FTYPE, values, dbHelper.FTYPE_CODE + "='" + type.getFTYPE_CODE() + "'", null);
					} else {
						count = (int) dB.insert(dbHelper.TABLE_FTYPE, null, values);
					}

				} else {
					count = (int) dB.insert(dbHelper.TABLE_FTYPE, null, values);
				}

			}
		} catch (Exception e) {

			Log.v("Exception", e.toString());

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

			cursor = dB.rawQuery("SELECT * FROM " + dbHelper.TABLE_FTYPE, null);
			count = cursor.getCount();
			if (count > 0) {
				int success = dB.delete(dbHelper.TABLE_FTYPE, null, null);
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
}
