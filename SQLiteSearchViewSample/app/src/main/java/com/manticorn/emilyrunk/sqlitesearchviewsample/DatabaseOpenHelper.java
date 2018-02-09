package com.manticorn.emilyrunk.sqlitesearchviewsample;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by emilyrunk on 12/12/17.
 */

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "dict.db";
    private static final String DB_PATH = "/data/data/com.manticorn.emilyrunk.sqlitesearchviewsample/databases/";
    private static String APP_DATA_PATH = "";

    private final Context myContext;
    private SQLiteDatabase myDictBase;

    //Constructor takes and keeps reference of passed context to access application assets and resources

    public DatabaseOpenHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.myContext = context;
    }

    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if(dbExist) {
            //do nothing - database exists!
        } else {
            //Empty database is created into default system path of the application so overwrite
            //the empty database with our own database.
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    //Check if database exists to avoid re-copying the file each time the application is opened.
    // @return true if exists, false if not
    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath,null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLException e) {

        }
        if(checkDB != null) {
            checkDB.close();
        }
        return checkDB !=null ? true : false;
    }

    //Copies database from device assets folder to the newly created empty database in system folder so it can be
    // accessed and handled

    private void copyDataBase()  throws IOException {
        //Open local DB as input stream
        InputStream myInputStream = myContext.getAssets().open(DB_NAME);

        //Path to the newly created empty DB
        String outputFileName = DB_PATH + DB_NAME;

        //Open empty db as new output stream
        OutputStream myOutputStream = new FileOutputStream(outputFileName);

        //Transfer bytes from input file into output file
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInputStream.read(buffer))>0) {
            myOutputStream.write(buffer, 0, length);
        }

        //Close the streams
        myOutputStream.flush();
        myOutputStream.close();
        myInputStream.close();
    }

    public boolean openDataBase() throws SQLException{
        String mPath = DB_PATH + DB_NAME;
        myDictBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.OPEN_READONLY);
        return myDictBase != null;
    }

    @Override
    public synchronized void close() {
        if(myDictBase != null) {
            myDictBase.close();
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
