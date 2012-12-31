package com.jsanc623.shabo.shell;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataProvider {
    public static final String  KEY_ROWID = "id";
    public static final String  KEY_IMAGE = "image";
    public static final String  KEY_PASSWORD = "password";
    public static final String  KEY_REQUIRE_PASSWORD = "require_password";
    public static final String  KEY_SOUND = "sound";
    private static final String TAG = "DataProvider";
    
    private static final String DATABASE_NAME = "ShaboShellDB";
    private static final String DATABASE_TABLE = "notes";
    private static final int    DATABASE_VERSION = 2;

    private static final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE + " (" + 
    		                                      KEY_ROWID            + " integer primary key autoincrement, " + 
    		                                      KEY_IMAGE            + " VARCHAR not null, " + 
    		                                      KEY_PASSWORD         + " VARCHAR, " + 
    		                                      KEY_REQUIRE_PASSWORD + " VARCHAR, " + 
    		                                      KEY_SOUND            + " VARCHAR );";
        
    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DataProvider(Context ctx){
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
        
    private static class DatabaseHelper extends SQLiteOpenHelper{
        DatabaseHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
        	try {
        		db.execSQL(DATABASE_CREATE);	
        	} catch (SQLException e) {
        		e.printStackTrace();
        	}
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS contacts");
            onCreate(db);
        }
    }    

    //---opens the database---
    public DataProvider open() throws SQLException{
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---    
    public void close(){
        DBHelper.close();
    }
    
    //---insert a record into the database---
    public long insertRecord(String image, String password, String require_password, String sound){
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_IMAGE, image);
        initialValues.put(KEY_PASSWORD, password);
        initialValues.put(KEY_REQUIRE_PASSWORD, require_password);
        initialValues.put(KEY_SOUND, sound);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    //---deletes a particular record---
    public boolean deleteContact(long rowId){
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    //---retrieves all the records---
    public Cursor getAllRecords(){
        return db.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_IMAGE,
                KEY_PASSWORD, KEY_REQUIRE_PASSWORD, KEY_SOUND}, null, null, null, null, null);
    }

    //---retrieves a particular record---
    public Cursor getRecord(long rowId) throws SQLException{
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
                KEY_IMAGE, KEY_PASSWORD, KEY_REQUIRE_PASSWORD, KEY_SOUND}, 
                KEY_ROWID + "=" + rowId, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //---updates a record---
    public boolean updateRecord(long rowId, String image, String password, String require_password, String sound){
        ContentValues args = new ContentValues();
        if(!("").equals(image)){ args.put(KEY_IMAGE, image); } 
        if(!("").equals(password)){ args.put(KEY_PASSWORD, password); } 
        if(!("").equals(require_password)){ args.put(KEY_REQUIRE_PASSWORD, require_password); } 
        if(!("").equals(sound)){ args.put(KEY_SOUND, sound); } 
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
}
