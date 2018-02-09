package com.manticorn.emilyrunk.sqlitesearchviewsample;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

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
//        dbHelper.close();
        database = dbHelper.getReadableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public ArrayList<String> search(String input) {
        ArrayList<String> results = new ArrayList<String>();
        try{
            String query = "SELECT word FROM dict WHERE word LIKE '" + input +"%'";
            //"word" is the name of the column, "dict" is the name of the table
            Cursor cursor = database.rawQuery(query,null);
            if (cursor.moveToFirst()) {
                do {
                    String value = cursor.getString(0);
                    results.add(value);
//                    Log.d("db", value);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (SQLException e){
            //handle
            Log.e("Error", "trying to use DB"+ e);
        }
        return results;
    }

    public void test() {
        try{
            String query = "SELECT word FROM dict";
            //"word" is the name of the column, "dict" is the name of the table
            Cursor cursor = database.rawQuery(query,null);
            if (cursor.moveToFirst()) {
                do {
                    String value = cursor.getString(0);
                    Log.d("db", value);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (SQLException e){
            //handle
        }
    }
}
