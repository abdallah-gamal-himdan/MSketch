package com.example.msketch.Helpers;

import static android.app.DownloadManager.COLUMN_ID;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import  android.database.sqlite.SQLiteOpenHelper;

import com.example.msketch.ui.clients.ClientsViewModel;

import java.util.ArrayList;
import java.util.List;




public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context) {
        super(context,"MSketch.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create table clients(id Identity(1,1) primary key,code nvarchar(50),clientName nvarchar(200)," +
                "mobile nvarchar(50),IsDeleted bit default(0),IsActive bit default(1))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop table if exists clients");
    }

    public Boolean InsertClient(String code , String clientName , String mobile)
    {
     SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("code",code);
        values.put("clientName",clientName);
        values.put("mobile",mobile);
        long result = DB.insert("clients",null,values);

        return  (result ==-1) ? false : true;

    }

    public Boolean UpdateClient(String id , String code , String clientName , String mobile)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("code",code);
        values.put("clientName",clientName);
        values.put("mobile",mobile);
        Cursor cursor = DB.rawQuery("Select * from clients where id = ?",new String[]{id});
        if(cursor.getCount() > 0) {
            long result = DB.update("clients", values, "id=?", new String[]{id});


            return (result == -1) ? false : true;
        }
        return  false;
    }

    public ArrayList<ClientsViewModel> GetAllClients() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from clients where IsDeleted = ?", new String[]{"false"});
        // To increase performance first get the index of each column in the cursor
        final int idIndex = cursor.getColumnIndex(COLUMN_ID);
        try {

            // If moveToFirst() returns false then cursor is empty
            if (!cursor.moveToFirst()) {
                return new ArrayList<>();
            }

            final ArrayList<ClientsViewModel> products = new ArrayList<>();

            do {

                // Read the values of a row in the table using the indexes acquired above
                final int id = cursor.getInt(idIndex);
                final String code = cursor.getString(idIndex);
                final String clientName = cursor.getString(idIndex);
                final String Mobile = cursor.getString(idIndex);

                products.add(new ClientsViewModel(id, code, clientName, Mobile));

            } while (cursor.moveToNext());
            return products;
        } finally {
            // Don't forget to close the Cursor once you are done to avoid memory leaks.
            // Using a try/finally like in this example is usually the best way to handle this
            cursor.close();

            // close the database
            DB.close();
        }


    }
}
