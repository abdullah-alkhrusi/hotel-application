package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class hoteldatabase extends SQLiteOpenHelper
{
    public static final String databasename="details.DataBase";
    public static final String tablename="hotel";
    public static final String Column_1="price";
    public static final String Column_2="day";
    public static final String Column_3="name";
    public static final String Column_4="number";
    public static final String Column_5="email";

    public hoteldatabase(Context context)
    {
        super(context, databasename,null,1);
    }

    public void onCreate(SQLiteDatabase DataBase)
    {
        DataBase.execSQL(" create table " + tablename + "(price INTEGER PRIMARY KEY AUTOINCREMENT, day INTEGER, " +
                "name TEXT, number INTEGER, email TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DataBase, int oldVersion, int newVersion)
    {
        DataBase.execSQL("DROP TABLE IF EXISTS " + tablename);
        onCreate(DataBase);
    }
    public boolean insertData(String price, String day, String name, String number, String email)
    {
        SQLiteDatabase DataBase = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Column_1,price);
        contentValues.put(Column_2,day);
        contentValues.put(Column_3,name);
        contentValues.put(Column_4,number);
        contentValues.put(Column_5,email);

        long result = DataBase.insert(tablename, null, contentValues);
        return result != -1;
    }
    public boolean updateData(String price, String day, String name, String number, String email)
    {
        SQLiteDatabase DataBase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Column_1,price);
        contentValues.put(Column_2,day);
        contentValues.put(Column_3,name);
        contentValues.put(Column_4,number);
        contentValues.put(Column_5,email);

        DataBase.update(tablename,contentValues,"price=?", new String[]{price});
        return true;
    }

    public Integer deleteData(String price)
    {
        SQLiteDatabase DataBase = this.getWritableDatabase();
        return DataBase.delete(tablename, "room=?", new String[]{price});
    }

    public Cursor getAllData()
    {
        SQLiteDatabase DataBase = this.getWritableDatabase();
        return DataBase.rawQuery("select * from " + tablename, null);
    }

}
