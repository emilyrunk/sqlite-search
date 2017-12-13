package com.manticorn.emilyrunk.sqlitesearchviewsample;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by emilyrunk on 12/13/17.
 */

public class DictDatabase {

    private final Context context;
    private SQLiteDatabase database;
    private DatabaseOpenHelper dbHelper;

    public DictDatabase(Context context) {
        this.context = context;
        dbHelper = new DatabaseOpenHelper(context);
    }

    public DictDatabase open() throws SQLException {
        dbHelper.openDataBase();
        dbHelper.close();
        database = dbHelper.getReadableDatabase();
        return this;
    }
}
