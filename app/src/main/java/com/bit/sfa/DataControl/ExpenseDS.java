package com.bit.sfa.DataControl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bit.sfa.model.Expense;
import com.bit.sfa.Settings.DatabaseHelper;

import java.util.ArrayList;

public class ExpenseDS {
	private SQLiteDatabase dB;
	private DatabaseHelper dbHelper;
	Context context;

	private String TAG = "swadeshi";

	public ExpenseDS(Context context) {
		this.context = context;
		dbHelper = new DatabaseHelper(context);
	}

	public void open() throws SQLException {
		dB = dbHelper.getWritableDatabase();
	}

	/*
	 * insert code
	 */
	@SuppressWarnings("static-access")
	public int createOrUpdateFExpense(ArrayList<Expense> list) {

		int count = 0;

		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}
		Cursor cursor = null;
		Cursor cursor_ini = null;

		try {

			cursor_ini = dB.rawQuery("SELECT * FROM " + dbHelper.TABLE_FEXPENSE, null);

			for (Expense expense : list) {
				ContentValues values = new ContentValues();

				values.put(dbHelper.EXPCODE, expense.getFEXPENSE_CODE());
				values.put(dbHelper.EXP_GRP_RECORDID, expense.getFEXPENSE_GRP_CODE());
				values.put(dbHelper.EXPNAME, expense.getFEXPENSE_NAME());
				values.put(dbHelper.EXP_RECORDID, expense.getFEXPENSE_RECORDID());
				values.put(dbHelper.EXP_ADD_MACH, expense.getFEXPENSE_ADD_MACH());
				values.put(dbHelper.EXP_STATUS, expense.getFEXPENSE_STATUS());
				values.put(dbHelper.EXP_ADDUSER, expense.getFEXPENSE_ADD_USER());
				values.put(dbHelper.EXP_ADD_DATE, expense.getFEXPENSE_ADD_DATE());

				if (cursor_ini.moveToFirst()) {
					String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FEXPENSE + " WHERE " + dbHelper.EXPCODE + "='" + expense.getFEXPENSE_CODE() + "'";
					cursor = dB.rawQuery(selectQuery, null);

					if (cursor.moveToFirst()) {
						count = (int) dB.update(dbHelper.TABLE_FEXPENSE, values, dbHelper.EXPCODE + "='" + expense.getFEXPENSE_CODE() + "'", null);
					} else {
						count = (int) dB.insert(dbHelper.TABLE_FEXPENSE, null, values);
					}

				} else {
					count = (int) dB.insert(dbHelper.TABLE_FEXPENSE, null, values);
				}

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

	/*
	 * delete code
	 */
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

			cursor = dB.rawQuery("SELECT * FROM " + dbHelper.TABLE_FEXPENSE, null);
			count = cursor.getCount();
			if (count > 0) {
				int success = dB.delete(dbHelper.TABLE_FEXPENSE, null, null);
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

	// Load the Expense for GRP_CODE
	public ArrayList<Expense> getAllExpense(String excode) {
		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}

		ArrayList<Expense> list = new ArrayList<Expense>();

		String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FEXPENSE + " WHERE " + dbHelper.EXP_GRP_CODE + "='" + excode + "'";

		Cursor cursor = dB.rawQuery(selectQuery, null);
		while (cursor.moveToNext()) {

			Expense expense = new Expense();

			expense.setFEXPENSE_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.EXPCODE)));
			expense.setFEXPENSE_NAME(cursor.getString(cursor.getColumnIndex(dbHelper.EXPNAME)));

			list.add(expense);

		}

		return list;
	}

	public ArrayList<Expense> getExpDetails(String searchword) {
		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}

		ArrayList<Expense> Itemname = new ArrayList<Expense>();

		String selectQuery = "select * from fExpense where expgrpcode='GCR001' AND ReaCode LIKE '%" + searchword + "%' OR ExpName LIKE '%" + searchword + "%' ";

		Cursor cursor = null;
		cursor = dB.rawQuery(selectQuery, null);

		while (cursor.moveToNext()) {
			Expense expense = new Expense();

			expense.setFEXPENSE_NAME(cursor.getString(cursor.getColumnIndex(dbHelper.EXPNAME)));
			expense.setFEXPENSE_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.EXPCODE)));
			Itemname.add(expense);
		}

		return Itemname;
	}

}
