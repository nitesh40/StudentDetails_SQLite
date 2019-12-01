package com.nitesh.sqlite_learning;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public static final String Database_Name="student.db";
    public static final String Table_Name="student_table";
    public static final String Column_1="Id";
    public static final String Column_2="Name";
    public static final String Column_3="LastName";
    public static final String Column_4="Marks";

    public Database(@Nullable Context context) {
        super(context, Database_Name, null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + Table_Name +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,LASTNAME TEXT,MARKS INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        onCreate(db);
    }
    //to insert
    public boolean insertData(String name, String lastname, String marks){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Column_2,name);
        contentValues.put(Column_3,lastname);
        contentValues.put(Column_4,marks);

        long result = db.insert(Table_Name,null,contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }
    // to view
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from " + Table_Name, null);
        return cursor;
    }
    //to update
    public boolean updateData(String id, String name, String lastname, String marks ){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Column_1,id);
        contentValues.put(Column_2,name);
        contentValues.put(Column_3,lastname);
        contentValues.put(Column_4,marks);

        db.update(Table_Name,contentValues,"Id = ?",new String[]{id} );

        return true;
    }
    //to delete
    public Integer deleteData(String id){

        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(Table_Name, " Id = ? ", new String[]{id});

    }
}
