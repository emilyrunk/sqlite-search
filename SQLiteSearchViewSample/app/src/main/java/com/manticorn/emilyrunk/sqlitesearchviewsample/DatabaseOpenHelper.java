package com.manticorn.emilyrunk.sqlitesearchviewsample;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by emilyrunk on 12/12/17.
 */

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "dict.db";
    private static final String DB_SUB_PATH = "/databases/" + DB_NAME;
    private static String APP_DATA_PATH = "";

    private final Context context;
    private SQLiteDatabase dataBase;

    public DatabaseOpenHelper(Context context) {
        super(context, DB_NAME, null, 1);
        APP_DATA_PATH = context.getApplicationInfo().dataDir;
        this.context = context;
    }

    public boolean openDataBase() throws SQLException{
        String mPath = APP_DATA_PATH + DB_SUB_PATH;
        dataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.OPEN_READWRITE);
        return dataBase != null;
    }

    @Override
    public synchronized void close() {
        if(dataBase != null) {
            dataBase.close();
        }
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
