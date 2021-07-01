package com.example.sqliteexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.CollapsibleActionView;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    private static final String TABLE_NAME = "moisture_readings";
    private static final String COL1 = "ID";
    private static final String COL2 = "moisture_value";

    public DatabaseHelper(Context context)
    {
        super(context,TABLE_NAME,null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
      String createTable = "CREATE TABLE " + TABLE_NAME + " ( ID INTEGER PRIMARY KEY AUTOINCREMENT, " +  COL2 +" TEXT)";
      db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean addData(String item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item);
        Log.d(TAG, "addData: Adding" + item + "to" + TABLE_NAME);
        long result = db.insert(TABLE_NAME, null, contentValues);

//        if(COL2.isEmpty()) {
//
//            Log.d(TAG, "addData: Adding" + item + "to" + TABLE_NAME);
//            long result = db.insert(TABLE_NAME, null, contentValues);
//        }
//        else{
//            Log.d(TAG, "addData: Updating" + item + "to" + TABLE_NAME);
//            long result = db.execSQL(" UPDATE "+ TABLE_NAME +"SET moisture_value = "+" '"+ COL2+"'"+"WHERE ID =1");
//        }

        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

}
