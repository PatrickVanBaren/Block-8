package com.example.ormlite84;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import androidx.annotation.Nullable;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
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
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Users.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {

    }
}
