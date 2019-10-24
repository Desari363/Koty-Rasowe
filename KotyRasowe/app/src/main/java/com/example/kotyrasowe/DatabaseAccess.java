package com.example.kotyrasowe;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    Cursor c = null;


    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);

    }

    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open() {
        this.db = openHelper.getReadableDatabase();
    }

    public void close() {
        if (db != null) {
            this.db.close();
        }
    }

    //Wyszukiwarka

    public String getString(String query) {
        c = db.rawQuery(query, new String[]{});
        StringBuffer buffer = new StringBuffer();
        c.moveToFirst();
        String result = c.getString(0);
        buffer.append("" + result);

        return buffer.toString();
    }


    //Lista

    public List<String> getStringList(String query) {
        List<String> result = new ArrayList<>();
        c = db.rawQuery(query, new String[]{});

        while (c.moveToNext()) {
            result.add(c.getString(0));
        }

        return result;
    }


    //Pobranie obrazów i dodanie do Bitmapy

    public List<Bitmap> getBitmapList(String query, Resources resources) {

        List<Bitmap> list = new ArrayList<>();

        Cursor cursor = db.rawQuery(query, new String[]{});

        while (cursor.moveToNext()) {
            byte[] data = (cursor.getBlob(0));
            Bitmap bt = BitmapFactory.decodeByteArray(data, 0, data.length);
            list.add(bt);
        }
        cursor.close();
        return list;
    }



    public List<List<String>> getColumnsValues(String query, int numCols) {
        List<List<String>> features = new ArrayList<>();

        c = db.rawQuery(query, new String[]{});

        for (int i = 0; i < numCols; i = i + 1) {
            List<String> bb = new ArrayList<>();
            features.add(bb);

            while (c.moveToNext()) {
                bb.add(c.getString(i));
            }
            c.moveToFirst();
        }
        return features;
    }

}


