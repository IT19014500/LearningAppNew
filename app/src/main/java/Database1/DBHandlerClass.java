package Database1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class DBHandlerClass extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Database1.db";

    public DBHandlerClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + User.userTable.TABLE_NAME + " (" +
                    User.userTable._ID + " INTEGER PRIMARY KEY," +
                    User.userTable.COLUMN_1 + " TEXT," +
                    User.userTable.COLUMN_2 + " TEXT," +
                    User.userTable.COLUMN_3 + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + User.userTable.TABLE_NAME;
    public long addData(String usrName,String pass,String dob){
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(User.userTable.COLUMN_1, usrName);
        values.put(User.userTable.COLUMN_2, pass);
        values.put(User.userTable.COLUMN_3, dob);

// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(User.userTable.TABLE_NAME, null, values);
        return newRowId;
    }
    public Boolean loginPart(String uName,String pwd){
        SQLiteDatabase db = getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                User.userTable.COLUMN_1,
                User.userTable.COLUMN_2
        };

// Filter results WHERE "title" = 'My Title'
        String selection = User.userTable.COLUMN_1 + " = ? AND "+User.userTable.COLUMN_2 + " = ?";
        String[] selectionArgs = {uName,pwd};

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                User.userTable.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                User.userTable.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        List validUser = new ArrayList();
        while(cursor.moveToNext()) {
            String j = cursor.getString(cursor.getColumnIndexOrThrow(User.userTable.COLUMN_1));
            validUser.add(j);
        }
        cursor.close();
        if (validUser.isEmpty()){
            return false;
        }
        else {
            return true;
        }
    }

    public List readInfo(){
        String userNa = "jaye";
        SQLiteDatabase db = getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                User.userTable.COLUMN_1,
                User.userTable.COLUMN_2,
                User.userTable.COLUMN_3
        };

// Filter results WHERE "title" = 'My Title'
        String selection = User.userTable.COLUMN_1 + " = ?";
        String[] selectionArgs = { userNa };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                User.userTable.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                User.userTable.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        List searchL = new ArrayList<>();
        while(cursor.moveToNext()) {
            String ser = cursor.getString(
                    cursor.getColumnIndexOrThrow(User.userTable.COLUMN_1));
            searchL.add(ser);
        }
        cursor.close();
        return searchL;

    }
    public List readInfo(String userName){

        SQLiteDatabase db = getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                User.userTable.COLUMN_1,
                User.userTable.COLUMN_2,
                User.userTable.COLUMN_3
        };

// Filter results WHERE "title" = 'My Title'
        String selection = User.userTable.COLUMN_1 + " = ?";
        String[] selectionArgs = { userName };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                User.userTable.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                User.userTable.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        List searchUn = new ArrayList<>();
        while(cursor.moveToNext()) {
            String uname = cursor.getString(cursor.getColumnIndexOrThrow(User.userTable.COLUMN_1));
            String pass = cursor.getString(cursor.getColumnIndexOrThrow(User.userTable.COLUMN_2));
            String dob = cursor.getString(cursor.getColumnIndexOrThrow(User.userTable.COLUMN_3));
            searchUn.add(uname);//0
            searchUn.add(pass);//1
            searchUn.add(dob);//2
        }
        cursor.close();
        return searchUn;
    }
    public Boolean updatePart(String userName,String password,String dob){
        SQLiteDatabase db = getWritableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(User.userTable.COLUMN_1, userName);
        values.put(User.userTable.COLUMN_2, password);
        values.put(User.userTable.COLUMN_3, dob);

// Which row to update, based on the title
        String selection = User.userTable.COLUMN_1 + " LIKE ?";
        String[] selectionArgs = { userName };

        int count = db.update(
                User.userTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        if (count>=1){
            return true;
        }
        else {
            return false;
        }
    }
    public void deleteData(String userN){

        SQLiteDatabase db = getWritableDatabase();
        // Define 'where' part of query.
        String selection = User.userTable.COLUMN_1 + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { userN };
        // Issue SQL statement.
        int deletedRows = db.delete(User.userTable.TABLE_NAME, selection, selectionArgs);
    }

}