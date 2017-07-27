package com.example.assistech.iitdnavi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by moon on 27/7/17.
 */

public class StoreImageAndLabelsHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;


    private static final String DATABASE_NAME = "image_database";
    private static final String TABLE_NAME="image_label_pair";
    private static final String KEY_ID="_id";
    private static final String  KEY_LABELS="labels";
    private static final String  KEY_IMAGE="image";
    private static final String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "( "+ KEY_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_LABELS + " TEXT," + KEY_IMAGE + " BLOB"
            + ");";


    public StoreImageAndLabelsHelper(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CONTACTS_TABLE);
        Log.v("Query----->",CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // create new table
        onCreate(db);
    }




    public void addEntry( String labels, byte[] image) throws SQLiteException {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_LABELS,    labels);
        cv.put(KEY_IMAGE,   image);
        database.insert( TABLE_NAME, null, cv );
    }

    public Cursor getLast10Records(){
        String columns[]= new String[2];
        columns[0]=KEY_LABELS;
        columns[1]=KEY_IMAGE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor= db.query(TABLE_NAME,null,null,null,null,null,KEY_ID +" DESC","10");

        return cursor;
    }


}
