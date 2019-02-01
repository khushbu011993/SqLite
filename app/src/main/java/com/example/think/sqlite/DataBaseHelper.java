package com.example.think.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="Database";
    public static final String TABLE_NAME="Registration";
    public static final String COL_0="Id";
    public static final String COL_1="Name";
    public static final String COL_2="Password";
    public static final String COL_3="Date";
    public static final String COL_4="Mobile_No";
    public static final String COL_5="Email_Address";

    public DataBaseHelper(Context context) {
        super(context,DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME + "(Id INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT," +
                "Password TEXT,Date DATE,Mobile_No INTEGER,Email_Address TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }

    public boolean OnInsert(String name,String password,String date,String mobile_no,String Email_address)
    {
        SQLiteDatabase database=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,password);
        contentValues.put(COL_3,date);
        contentValues.put(COL_4,mobile_no);
        contentValues.put(COL_5,Email_address);

         long result=  database.insert(TABLE_NAME,null,contentValues);

      if (result==-1)
      {
          return false;
      }
      else
      {
          return true;
      }
    }

    public Cursor getAllData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
}

