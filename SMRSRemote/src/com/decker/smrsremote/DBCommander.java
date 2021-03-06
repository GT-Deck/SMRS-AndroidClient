package com.decker.smrsremote;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteStatement;
import android.widget.Toast;

public class DBCommander {
	MySQLiteHelper helper;
	private SQLiteDatabase db;
	Context appContext;

	public DBCommander(Context context) {
		appContext = context;
		helper = new MySQLiteHelper(context);
		try {
			db = helper.getWritableDatabase();

		} catch (SQLiteException ex) {
			showToast("Error: failed to open database, please restart application");
		}

	}

	public ArrayList<StarMadeServer> getList() {

		Cursor tempCursor = db.rawQuery(
				"SELECT * FROM " + helper.getTableName(), new String[] {});
		ArrayList<StarMadeServer> tempArrayList = cursorToArrayList(tempCursor);
		tempCursor.close();
		return tempArrayList;
	}

	public void closeDB() {
		db.close();
	}

	public void openDB() {
		try {
			db = helper.getWritableDatabase();
		} catch (SQLiteException ex) {
			showToast("Error:Failed to open data base");
		}
	}

	public boolean isDbOpen() {
		return db.isOpen();
	}

	public void addItemToList(StarMadeServer item) {
		String sql = "INSERT INTO " + helper.getTableName() + " VALUES(?,?)";
		SQLiteStatement statement = db.compileStatement(sql);
		db.beginTransaction();
		statement.clearBindings();
		statement.bindString(1, item.getIP());
		statement.bindString(2, item.getName());
		statement.execute();
		db.setTransactionSuccessful();
		db.endTransaction();
	}

	public void removeItemFromList(StarMadeServer item) {
		String[] args = new String[] { item.getName() };
		db.delete(helper.getTableName(), "Name = ?", args);
	}

	private ArrayList<StarMadeServer> cursorToArrayList(Cursor cursor) {
		ArrayList<StarMadeServer> temp = new ArrayList<StarMadeServer>();
		if (cursor != null) {
			cursor.moveToFirst();
			for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
					.moveToNext()) {
				if (!cursor.getString(0).equalsIgnoreCase("android_metadata")) {
					temp.add(new StarMadeServer(cursor.getString(1), cursor
							.getString(0)));
				}
			}
		}
		cursor.close();
		return temp;
	}

	private void showToast(String message) {
		Toast toast = Toast.makeText(appContext, message, Toast.LENGTH_LONG);
		toast.show();
	}
}
