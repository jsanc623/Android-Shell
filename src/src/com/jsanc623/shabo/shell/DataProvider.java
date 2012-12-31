package com.jsanc623.shabo.shell;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

/**
 * Provides access to a database of notes. Each note has a title, the note
 * itself, a creation date and a modified data.
 */
public class DataProvider extends ContentProvider {

    private static final String TAG = "NotePadProvider";
    private static final String DATABASE_NAME = "shabo_shell.db";
    private static final int DATABASE_VERSION = 2;
    private static final String SHABO_SHELL_TABLE_NAME = "notes";
    private static final int _ID = 0;
    private static final String TITLE = "";
    private static final String NOTE = "";
    private static final int CREATED_DATE = 0;
    private static final int MODIFIED_DATE = 0;

    /**
     * This class helps open, create, and upgrade the database file.
     */
    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + SHABO_SHELL_TABLE_NAME + " ("
                    + _ID + " INTEGER PRIMARY KEY,"
                    + TITLE + " TEXT,"
                    + NOTE + " TEXT,"
                    + CREATED_DATE + " INTEGER,"
                    + MODIFIED_DATE + " INTEGER"
                    + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS notes");
            onCreate(db);
        }
    }

    private DatabaseHelper mOpenHelper;

    @Override
    public boolean onCreate() {
        mOpenHelper = new DatabaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
            String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(SHABO_SHELL_TABLE_NAME);

        // If no sort order is specified use the default
        String orderBy = sortOrder;

        // Get the database and run the query
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, orderBy);

        // Tell the cursor what uri to watch, so it knows when its source data changes
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Override
    public String getType(Uri uri) {
        return uri.toString();
    }

    @Override
    public Uri insert(Uri uri, ContentValues initialValues) {
        ContentValues values;
        if (initialValues != null) {
            values = new ContentValues(initialValues);
        } else {
            values = new ContentValues();
        }
        
        @SuppressWarnings("unused")
		Long now = Long.valueOf(System.currentTimeMillis());

        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
       
        @SuppressWarnings("unused")
		long rowId = db.insert(SHABO_SHELL_TABLE_NAME, NOTE, values);

        throw new SQLException("Failed to insert row into " + uri);
    }

    @Override
    public int delete(Uri uri, String where, String[] whereArgs) {
        @SuppressWarnings("unused")
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int count = 0;
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String where, String[] whereArgs) {
        @SuppressWarnings("unused")
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int count = 0;
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
}
