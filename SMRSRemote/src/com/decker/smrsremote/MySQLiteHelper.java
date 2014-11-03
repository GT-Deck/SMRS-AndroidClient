package com.decker.smrsremote;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "ServerList";
	private static final String TABLENAME = "SavedServers";

	public MySQLiteHelper(Context context) {
		// specify our database name and version
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		/* Create the table for saving the users server IP's and Name */
		db.execSQL("CREATE TABLE " + TABLENAME + " (IP TEXT, NAME TEXT)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// No Upgrades planned, do nothing
	}

	public String getTableName() {
		return TABLENAME;
	}

}
