package com.example.androidproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDBHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "details.db";
    private static final int DATABASE_VERSION = 1;
 
    public static final String TABLE_NAME = "data";
    public static final String COLUMN_ID =  "userid";
    public static final String COLUMN_ROLE =  "role";
    public static final String COLUMN_FULLNAME =  "fullname";
    public static final String COLUMN_MOBILE =  "mobile";
    public static final String COLUMN_EMAIL =  "email";
    public static final String COLUMN_PASSWORD =  "password";
    public static final String COLUMN_WORK_PLACE =  "working_place";
    public static final String COLUMN_EDUCATION =  "highest_education_level";
    public static final String COLUMN_SKILL =  "skill";
 
    private static final String CREATE_TABLE_QUERY =
            "CREATE TABLE " + TABLE_NAME + " (" +
             COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
             COLUMN_ROLE + " TEXT, "+
             COLUMN_FULLNAME + " TEXT, "+
             COLUMN_MOBILE + " TEXT, " +
             COLUMN_EMAIL + " TEXT, " +
             COLUMN_PASSWORD + " TEXT, " +
             COLUMN_WORK_PLACE + " TEXT, " + 
             COLUMN_EDUCATION + " TEXT, " +
             COLUMN_SKILL + " TEXT "+")";
 
    //modified constructor
    public SQLiteDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		// TODO Auto-generated method stub

		sqLiteDatabase.execSQL(CREATE_TABLE_QUERY);
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

}

