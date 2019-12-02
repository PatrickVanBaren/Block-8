package com.example.ormlitetext;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String NAME = "database.db";
    private static final int VERSION = 1;
    private static DatabaseHelper sInstance;

    public static void createInstance(Context context) {
        sInstance = new DatabaseHelper(context);
    }

    public static DatabaseHelper getInstance() {
        return sInstance;
    }

    public DatabaseHelper(@Nullable Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE '" + ContractUser.TABLE_NAME + "' ('" +
                ContractUser.COLUMN_NAME_ID + "' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '" +
                ContractUser.COLUMN_NAME_FIRSTNAME + "' TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
