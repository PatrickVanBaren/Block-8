package com.example.ormlite84;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = Users.TABLE_NAME)
public class Users {
    public static final String TABLE_NAME = "Article";
    public static final String COLUMN_NAME_ID = "_id";
    public static final String COLUMN_NAME_FIRSTNAME = "firstName";

    @DatabaseField(generatedId = true, columnName = COLUMN_NAME_ID)
    private long mId;
    @DatabaseField(columnName = COLUMN_NAME_FIRSTNAME)
    private String mFirsName;

    public void setmId(long mId) {
        this.mId = mId;
    }

    public void setFirsName(String mFirsName) {
        this.mFirsName = mFirsName;
    }

    public long getmId() {
        return mId;
    }

    public String getFirsName() {
        return mFirsName;
    }
}
