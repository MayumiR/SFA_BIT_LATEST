package com.bit.sfa.DataControl;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.bit.sfa.model.ItemSearchList;
import com.bit.sfa.model.Items;
import com.bit.sfa.model.Product;
import com.bit.sfa.Settings.DatabaseHelper;
import com.bit.sfa.Settings.SharedPref;

import java.util.ArrayList;

public class ItemsDS {

	private SQLiteDatabase dB;
	private DatabaseHelper dbHelper;
	Context context;
	private String TAG = "swadeshi";
	SharedPref mSharedPref;

	public ItemsDS(Context context) {
		this.context = context;
		dbHelper = new DatabaseHelper(context);
		mSharedPref = new SharedPref(context);
	}

	public void open() throws SQLException {
		dB = dbHelper.getWritableDatabase();
	}

	@SuppressWarnings("static-access")
	public int createOrUpdateItems(ArrayList<Items> list) {

		int count = 0;

		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}
		Cursor cursor = null;
		Cursor cursor_ini = null;

		try {

			// cursor = dB.rawQuery("SELECT * FROM " + dbHelper.TABLE_FITEM,
			// null);
			// if(cursor.getCount()>0){
			// int success = dB.delete(dbHelper.TABLE_FITEM, null, null);
			// Log.v("Success", success+"");
			// }
			dB.beginTransaction();

			String sql = "Insert or Replace into " + dbHelper.TABLE_FITEM + " (" + dbHelper.FITEM_ITEM_CODE + ", " + dbHelper.FITEM_ITEM_NAME + ", " + dbHelper.FITEM_VEN_P_CODE + ", " + dbHelper.FITEM_GROUP_CODE + ", " + dbHelper.FITEM_TYPE_CODE + ", " + dbHelper.FITEM_TAX_COM_CODE + ", " + dbHelper.FITEM_UNIT_CODE + ", " + dbHelper.FITEM_ITEM_STATUS + ", " + dbHelper.FITEM_RE_ORDER_LVL + ", " + dbHelper.FITEM_RE_ORDER_QTY + ", " + dbHelper.FITEM_AVG_PRICE + ", " + dbHelper.FITEM_PRIL_CODE + ", " + dbHelper.FITEM_BRAND_CODE + ", " + dbHelper.FITEM_NOU_CASE + ", " + dbHelper.FITEM_ORD_SEQ + ", " + dbHelper.FITEM_ADD_USER + ", " + dbHelper.FITEM_ADD_MATCH + ", " + dbHelper.FITEM_MUST_SALE + ", " + dbHelper.FITEM_CAT_CODE + ", " + dbHelper.FITEM_PACK + ", " + dbHelper.FITEM_PACK_SIZE + ", " + dbHelper.FITEM_SUP_CODE + ", " + dbHelper.FITEM_MUST_FREE + ") values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			SQLiteStatement insert = dB.compileStatement(sql);

			for (Items item : list) {

				insert.bindString(1, item.getFITEM_ITEM_CODE());
				insert.bindString(2, item.getFITEM_ITEM_NAME());
				insert.bindString(3, item.getFITEM_VEN_P_CODE());
				insert.bindString(4, item.getFITEM_GROUP_CODE());
				insert.bindString(5, item.getFITEM_TYPE_CODE());
				insert.bindString(6, item.getFITEM_TAX_COM_CODE());
				insert.bindString(7, item.getFITEM_UNIT_CODE());
				insert.bindString(8, item.getFITEM_ITEM_STATUS());
				insert.bindString(9, item.getFITEM_RE_ORDER_LVL());
				insert.bindString(10, item.getFITEM_RE_ORDER_QTY());
				insert.bindString(11, item.getFITEM_AVG_PRICE());
				insert.bindString(12, item.getFITEM_PRIL_CODE());
				insert.bindString(13, item.getFITEM_BRAND_CODE());
				insert.bindString(14, item.getFITEM_NOU_CASE());
				insert.bindString(15, item.getFITEM_ORD_SEQ());
				insert.bindString(16, item.getFITEM_ADD_USER());
				insert.bindString(17, item.getFITEM_ADD_MATCH());
				insert.bindString(18, item.getFITEM_MUST_SALE());
				insert.bindString(19, item.getFITEM_CAT_CODE());
				insert.bindString(20, item.getFITEM_PACK());
				insert.bindString(21, item.getFITEM_PACK_SIZE());
				insert.bindString(22, item.getFITEM_SUP_CODE());
				insert.bindString(23, item.getFITEM_MUST_FREE());
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

	public int deleteAll() {

		int count = 0;

		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}
		Cursor cursor = null;
		try {

			cursor = dB.rawQuery("SELECT * FROM " + dbHelper.TABLE_FITEM, null);
			count = cursor.getCount();
			if (count > 0) {
				int success = dB.delete(dbHelper.TABLE_FITEM, null, null);
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
	public ArrayList<ItemSearchList> getAllItem(String newText, String type, String refno, String LocCode) {
		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}

		ArrayList<ItemSearchList> list = new ArrayList<ItemSearchList>();
		try {

			// String selectQuery = "SELECT * FROM "+dbHelper.TABLE_FITEM
			// +" WHERE "+dbHelper.FITEM_ITEM_CODE+" || "+dbHelper.FITEM_ITEM_NAME
			// +" LIKE '%"+newText+"%' AND "+dbHelper.FITEM_ITEM_CODE+" not in (SELECT DISTINCT itemcode FROM forddet WHERE "+type+" And refno ='"+refno+"')";   Types

			// AND  loc.LocCode IN (SELECT LocCode FROM FLocations )
			String selectQuery = "SELECT itm.ItemCode,itm.ItemName,itm.NOUCase,itm.Pack , Sum(loc.QOH) as QOH FROM fitem itm, fitemLoc loc WHERE itm.ItemName LIKE '" + newText + "%' AND loc.itemcode=itm.itemcode AND loc.LocCode='" + LocCode + "' AND itm.ItemCode not in (SELECT DISTINCT itemcode FROM forddet WHERE " + type + " And refno ='" + refno + "') Group By itm.ItemCode,itm.ItemName,itm.NOUCase,itm.PackSize order by CAST(loc.QOH AS FLOAT) DESC";
			// TESTING 14-07-2016 String selectQuery = "SELECT itm.ItemCode,itm.ItemName,itm.NOUCase,itm.PackSize FROM fitem itm WHERE itm.ItemName LIKE '" + newText + "%' AND itm.ItemCode not in (SELECT DISTINCT itemcode FROM forddet WHERE Types = 'SA' And refno ='" + refno + "' order by itemcode) order by itm.ItemCode DESC"; //Group By itm.ItemCode,itm.ItemName,itm.NOUCase,itm.PackSize

			// Original As At 13/String selectQuery =
			// "SELECT itm.ItemCode,itm.ItemName,itm.NOUCase,itm.PackSize , loc.QOH FROM fitem itm, fitemLoc loc WHERE itm.ItemCode || itm.ItemName LIKE '%"
			// + newText + "%' AND loc.itemcode=itm.itemcode AND  loc.LocCode='"
			// +
			// LocCode +
			// "' AND itm.ItemCode not in (SELECT DISTINCT itemcode FROM forddet WHERE "
			// + type + " And refno ='" + refno +
			// "') order by CAST(loc.QOH AS FLOAT) DESC";

			Cursor cursor = dB.rawQuery(selectQuery, null);
			while (cursor.moveToNext()) {

				ItemSearchList itemslist = new ItemSearchList("");

				// items.setFITEM_ID(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_ID)));
				// items.setFITEM_ADD_MATCH(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_ADD_MATCH)));
				// items.setFITEM_ADD_USER(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_ADD_USER)));
				// items.setFITEM_AVG_PRICE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_AVG_PRICE)));
				// items.setFITEM_BRAND_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_BRAND_CODE)));
				// items.setFITEM_GROUP_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_GROUP_CODE)));
				itemslist.setFITEM_ITEM_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_ITEM_CODE)));
				itemslist.setFITEM_ITEM_NAME(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_ITEM_NAME)));
				// items.setFITEM_ITEM_STATUS(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_ITEM_STATUS)));
				// items.setFITEM_MAP_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_MAP_CODE)));
				// items.setFITEM_MUST_SALE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_MUST_SALE)));
				itemslist.setFITEM_NOU_CASE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_NOU_CASE)));
				itemslist.setFITEM_PACK(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_PACK)));
				// items.setFITEM_ORD_SEQ(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_ORD_SEQ)));
				// items.setFITEM_PRIL_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_PRIL_CODE)));
				// items.setFITEM_RE_ORDER_LVL(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_RE_ORDER_LVL)));
				// items.setFITEM_RE_ORDER_QTY(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_RE_ORDER_QTY)));
				// items.setFITEM_S_BRAND_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_S_BRAND_CODE)));
				// items.setFITEM_SKU_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_SKU_CODE)));
				// items.setFITEM_SKU_SIZ_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_SKU_SIZ_CODE)));
				// items.setFITEM_TAX_COM_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_TAX_COM_CODE)));
				// items.setFITEM_TYPE_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_TYPE_CODE)));
				// items.setFITEM_UNIT_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_UNIT_CODE)));
				// items.setFITEM_VEN_P_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_VEN_P_CODE)));
				
				itemslist.setQOH(cursor.getString(cursor.getColumnIndex(dbHelper.FITEMLOC_QOH)));

				list.add(itemslist);

			}

			cursor.close();
		} catch (Exception e) {

			Log.v(TAG + " Exception", e.toString());

		} finally {
			dB.close();
		}

		return list;
	}

	@SuppressWarnings("static-access")
	public ArrayList<Items> getAllItemForVanSale(String newText, String type, String refno, String LocCode) {
		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}

		ArrayList<Items> list = new ArrayList<Items>();

		// String selectQuery = "SELECT * FROM "+dbHelper.TABLE_FITEM
		// +" WHERE "+dbHelper.FITEM_ITEM_CODE+" || "+dbHelper.FITEM_ITEM_NAME
		// +" LIKE '%"+newText+"%' AND "+dbHelper.FITEM_ITEM_CODE+" not in (SELECT DISTINCT itemcode FROM finvdet WHERE "+type+" And refno ='"+refno+"')";

		String selectQuery = "SELECT itm.* , loc.QOH FROM fitem itm, fitemLoc loc WHERE itm.ItemCode || itm.ItemName LIKE '%" + newText + "%' AND loc.itemcode=itm.itemcode AND  loc.LocCode='" + LocCode + "' AND itm.ItemCode not in (SELECT DISTINCT itemcode FROM finvdet WHERE " + type + " And refno ='" + refno + "') order by CAST(loc.QOH AS FLOAT) DESC";

		Cursor cursor = dB.rawQuery(selectQuery, null);
		while (cursor.moveToNext()) {

			Items items = new Items();

			items.setFITEM_ID(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_ID)));
			items.setFITEM_ADD_MATCH(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_ADD_MATCH)));
			items.setFITEM_ADD_USER(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_ADD_USER)));
			items.setFITEM_AVG_PRICE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_AVG_PRICE)));
			items.setFITEM_BRAND_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_BRAND_CODE)));
			items.setFITEM_GROUP_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_GROUP_CODE)));
			items.setFITEM_ITEM_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_ITEM_CODE)));
			items.setFITEM_ITEM_NAME(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_ITEM_NAME)));
			items.setFITEM_ITEM_STATUS(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_ITEM_STATUS)));
			items.setFITEM_MUST_SALE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_MUST_SALE)));
			items.setFITEM_NOU_CASE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_NOU_CASE)));
			items.setFITEM_ORD_SEQ(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_ORD_SEQ)));
			items.setFITEM_PRIL_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_PRIL_CODE)));
			items.setFITEM_RE_ORDER_LVL(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_RE_ORDER_LVL)));
			items.setFITEM_RE_ORDER_QTY(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_RE_ORDER_QTY)));
			items.setFITEM_TAX_COM_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_TAX_COM_CODE)));
			items.setFITEM_TYPE_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_TYPE_CODE)));
			items.setFITEM_UNIT_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_UNIT_CODE)));
			items.setFITEM_VEN_P_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_VEN_P_CODE)));
			items.setQOH(cursor.getString(cursor.getColumnIndex(dbHelper.FITEMLOC_QOH)));

			list.add(items);

		}

		return list;
	}

	@SuppressWarnings("static-access")
	public String getItemNameByCode(String code) {

		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}
		Cursor cursor = null;
		try {
			String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FITEM + " WHERE " + dbHelper.FITEM_ITEM_CODE + "='" + code + "'";

			cursor = dB.rawQuery(selectQuery, null);

			while (cursor.moveToNext()) {

				return cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_ITEM_NAME));

			}

		} catch (Exception e) {

			Log.v(TAG + " Exception", e.toString());

		} finally {
			if (cursor != null) {
				cursor.close();
			}
			dB.close();
		}

		return "";
	}

	// getFITEM_NOU_CASE

	@SuppressWarnings("static-access")
	public String getUnitByCode(String itemCode) {

		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}

		String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FITEM + " WHERE " + dbHelper.FITEM_ITEM_CODE + "='" + itemCode + "'";

		Cursor cursor = dB.rawQuery(selectQuery, null);

		while (cursor.moveToNext()) {

			return cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_NOU_CASE));

		}

		return "";
	}

	@SuppressWarnings("static-access")
	public String getPackSizeByCode(String itemCode) {

		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}
		Cursor cursor = null;
		try {
			String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FITEM + " WHERE " + dbHelper.FITEM_ITEM_CODE + "='" + itemCode + "'";

			cursor = dB.rawQuery(selectQuery, null);

			while (cursor.moveToNext()) {

				return cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_PACK));

			}
		} catch (Exception e) {

			Log.v(TAG + " Exception", e.toString());

		} finally {
			if (cursor != null) {
				cursor.close();
			}
			dB.close();
		}

		return "";
	}

	@SuppressWarnings("static-access")
	public String getItemTaxComCode(String itemCode) {

		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}
		Cursor cursor = null;
		try {
			String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FITEM + " WHERE " + dbHelper.FITEM_ITEM_CODE + "='" + itemCode + "'";

			cursor = dB.rawQuery(selectQuery, null);

			while (cursor.moveToNext()) {

				return cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_TAX_COM_CODE));

			}

		} catch (Exception e) {

			Log.v(TAG + " Exception", e.toString());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			dB.close();
		}

		return "";
	}

//	@SuppressWarnings("static-access")
//	public ArrayList<ItemFreeIssue> getAllFreeItemNameByRefno(String code) {
//		if (dB == null) {
//			open();
//		} else if (!dB.isOpen()) {
//			open();
//		}
//
//		ArrayList<ItemFreeIssue> list = new ArrayList<ItemFreeIssue>();
//
//		String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FITEM + " WHERE " + dbHelper.FITEM_ITEM_CODE + " in (select itemcode from ffreeItem where refno ='" + code + "')";
//
//		Cursor cursor = dB.rawQuery(selectQuery, null);
//		while (cursor.moveToNext()) {
//
//			ItemFreeIssue issue = new ItemFreeIssue();
//			Items items = new Items();
//
//			items.setFITEM_ID(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_ID)));
//			items.setFITEM_ADD_MATCH(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_ADD_MATCH)));
//			items.setFITEM_ADD_USER(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_ADD_USER)));
//			items.setFITEM_AVG_PRICE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_AVG_PRICE)));
//			items.setFITEM_BRAND_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_BRAND_CODE)));
//			items.setFITEM_GROUP_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_GROUP_CODE)));
//			items.setFITEM_ITEM_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_ITEM_CODE)));
//			items.setFITEM_ITEM_NAME(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_ITEM_NAME)));
//			items.setFITEM_ITEM_STATUS(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_ITEM_STATUS)));
//			items.setFITEM_MUST_SALE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_MUST_SALE)));
//			items.setFITEM_NOU_CASE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_NOU_CASE)));
//			items.setFITEM_ORD_SEQ(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_ORD_SEQ)));
//			items.setFITEM_PRIL_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_PRIL_CODE)));
//			items.setFITEM_RE_ORDER_LVL(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_RE_ORDER_LVL)));
//			items.setFITEM_RE_ORDER_QTY(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_RE_ORDER_QTY)));
//			items.setFITEM_TAX_COM_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_TAX_COM_CODE)));
//			items.setFITEM_TYPE_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_TYPE_CODE)));
//			items.setFITEM_UNIT_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_UNIT_CODE)));
//			items.setFITEM_VEN_P_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_VEN_P_CODE)));
//
//			issue.setItems(items);
//			issue.setAlloc("0");
//			list.add(issue);
//
//		}
//
//		return list;
//	}

	/*
	 * Sales Return ITem Adpater Class Use To view Details of item.
	 */
	@SuppressWarnings("static-access")
	public ArrayList<ItemSearchList> getAllItemReturn(String newText, String RefNo) {
		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}

		ArrayList<ItemSearchList> list = new ArrayList<ItemSearchList>();
		Cursor cursor = null;
		// String selectQuery = "SELECT * FROM "+dbHelper.TABLE_FITEM
		// +" WHERE "+dbHelper.FITEM_ITEM_CODE+" || "+dbHelper.FITEM_ITEM_NAME
		// +" LIKE '%"+newText+"%' AND "+dbHelper.FITEM_ITEM_CODE+" not in (SELECT DISTINCT itemcode FROM forddet WHERE "+type+" And refno ='"+refno+"')";
		try {
		String selectQuery = "SELECT itm.ItemCode,itm.ItemName,itm.NOUCase, itm.itemstatus,itm.Pack FROM fitem itm WHERE itm.ItemName LIKE '" + newText + "%' AND itm.ItemCode not in (SELECT DISTINCT itemcode FROM FInvRDet WHERE refno ='"+RefNo+"')";

		cursor = dB.rawQuery(selectQuery, null);
		while (cursor.moveToNext()) {

			ItemSearchList itemslist = new ItemSearchList("");

			// items.setFITEM_ID(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_ID)));
			// items.setFITEM_ADD_MATCH(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_ADD_MATCH)));
			// items.setFITEM_ADD_USER(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_ADD_USER)));
			// items.setFITEM_AVG_PRICE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_AVG_PRICE)));
			// items.setFITEM_BRAND_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_BRAND_CODE)));
			// items.setFITEM_GROUP_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_GROUP_CODE)));
			itemslist.setFITEM_ITEM_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_ITEM_CODE)));
			itemslist.setFITEM_ITEM_NAME(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_ITEM_NAME)));
			// items.setFITEM_ITEM_STATUS(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_ITEM_STATUS)));
			// items.setFITEM_MAP_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_MAP_CODE)));
			// items.setFITEM_MUST_SALE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_MUST_SALE)));
			itemslist.setFITEM_NOU_CASE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_NOU_CASE)));
			itemslist.setFITEM_PACK(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_PACK)));
			// items.setFITEM_ORD_SEQ(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_ORD_SEQ)));
			// items.setFITEM_PRIL_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_PRIL_CODE)));
			// items.setFITEM_RE_ORDER_LVL(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_RE_ORDER_LVL)));
			// items.setFITEM_RE_ORDER_QTY(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_RE_ORDER_QTY)));
			// items.setFITEM_S_BRAND_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_S_BRAND_CODE)));
			// items.setFITEM_SKU_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_SKU_CODE)));
			// items.setFITEM_SKU_SIZ_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_SKU_SIZ_CODE)));
			// items.setFITEM_TAX_COM_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_TAX_COM_CODE)));
			// items.setFITEM_TYPE_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_TYPE_CODE)));
			// items.setFITEM_UNIT_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_UNIT_CODE)));
			// items.setFITEM_VEN_P_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_VEN_P_CODE)));
			itemslist.setQOH(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_ITEM_STATUS)));

			list.add(itemslist);

		}

		} catch (Exception e) {

			Log.v(TAG + " Exception", e.toString());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			dB.close();
		}
		
		return list;
	}

	public boolean getCheckAllowSelect(String code) {

		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}
		
		Cursor cursor = null;
		try {
		String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FITEM + " WHERE " + dbHelper.FITEM_ITEM_CODE + "='" + code + "'";

		
		cursor = dB.rawQuery(selectQuery, null);

		while (cursor.moveToNext()) {
			int AllowChange = Integer.parseInt(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_MUST_FREE)));
			
			if(AllowChange==1){
				return true;
			}else{
				return false;
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
		return false;

	}
	//---------------------------------------------------------------------------------------------------------------------------------------------
	
	
	public ArrayList<Product> getAllItemFor(String type, String refno, String LocCode,String debCode) {
		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}
		Cursor cursor =null;
		ArrayList<Product> list = new ArrayList<Product>();
		double unitPrice;
		String freeSchema = "";
		String FreeRef = "";
		
		try {

//			String selectQuery = "SELECT itm.ItemCode,itm.ItemName,itm.NOUCase,itm.Pack , Sum(CAST(loc.QOH AS integer)) as QOH,itm.SupCode FROM fitem itm, fitemLoc loc WHERE loc.itemcode=itm.itemcode AND loc.LocCode='"
//					+ LocCode + "' AND itm.ItemCode not in (SELECT DISTINCT itemcode FROM forddet WHERE " + type
//					+ " And refno ='" + refno
//					+ "') Group By itm.ItemCode,itm.ItemName,itm.NOUCase,itm.PackSize order by CAST(loc.QOH AS integer) DESC";
			
//			String selectQuery = "SELECT itm.ItemCode,itm.ItemName,itm.NOUCase,itm.Pack , Sum(CAST(loc.QOH AS integer)) as QOH,itm.SupCode ,"
//					+ "( select refno from ffreehed where refno in (select refno from ffreedet where itemcode = itm.ItemCode)) as freeref FROM fitem itm, fitemLoc loc WHERE loc.itemcode=itm.itemcode AND loc.LocCode='"
//					+ LocCode + "' AND itm.ItemCode not in (SELECT DISTINCT itemcode FROM forddet WHERE " + type
//					+ " And refno ='" + refno
//					+ "') Group By itm.ItemCode,itm.ItemName,itm.NOUCase,itm.PackSize order by CAST(loc.QOH AS integer) DESC";

			String selectQuery = "SELECT itm.ItemCode,itm.ItemName,itm.NOUCase,itm.Pack , Sum(CAST(loc.QOH AS integer)) as QOH,itm.SupCode ,"
					+ "ifnull(( select refno from ffreehed where refno in (select refno from ffreedet where itemcode = itm.ItemCode)),'') as freeref, "
					+ " ifnull( (select price from fitempri where itemcode = itm.ItemCode and costcode = '"+mSharedPref.getGlobalVal("PrekeyCost")+"'),'0.00') as itpri"
					+ " FROM fitem itm, fitemLoc loc WHERE loc.itemcode=itm.itemcode AND loc.LocCode='"
					+ LocCode + "' AND itm.ItemCode not in (SELECT DISTINCT itemcode FROM forddet WHERE " + type
					+ " And refno ='" + refno
					+ "') Group By itm.ItemCode,itm.ItemName,itm.NOUCase,itm.PackSize order by CAST(loc.QOH AS integer) DESC";

			
			//Log.v(TAG + " ITEMCNT ", Integer.toString(cursor.getCount()));
			
		 cursor = dB.rawQuery(selectQuery, null);
			while (cursor.moveToNext()) {
				Product product = new Product();
				
				product.setFPRODUCT_ITEMCODE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_ITEM_CODE)));
				product.setFPRODUCT_ITEMNAME(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_ITEM_NAME)));
				product.setFPRODUCT_NOUCASE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_NOU_CASE)));
				product.setFPRODUCT_PACK(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_PACK)));
				product.setFPRODUCT_QOH(cursor.getString(cursor.getColumnIndex(dbHelper.FITEMLOC_QOH)));
				product.setSupCode(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_SUP_CODE)));
				product.setFPRODUCT_QTY("0");
				//unitPrice = Double.parseDouble(new ItemPriDS(context).getProductPriceByCode(product.getFPRODUCT_ITEMCODE(),mSharedPref.getGlobalVal("PrekeyCost"))) / Double.parseDouble(product.getFPRODUCT_NOUCASE());
				//product.setFPRODUCT_PRICE(String.format("%.2f", unitPrice)); 
				product.setFPRODUCT_PRICE(cursor.getString(cursor.getColumnIndex("itpri")));
				FreeRef = cursor.getString(cursor.getColumnIndex("freeref"));
				
				//freeSchema = new FreeMslabDS(context).getFreeDetailsnew(FreeRef,debCode);
				freeSchema = "";
				product.setFPRODUCT_DEBCODE(debCode);
				product.setFPRODUCT_FREESCREMA(freeSchema);
				list.add(product);
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
