package com.example.sqldemoandroid43;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SQLHelperDemo extends SQLiteOpenHelper {

    private static final String DB_NAME = "Product.db";
    private static final int DB_VERSION = 1;

    private static final String DV_NAME = "name";
    private static final String DV_PRICE = "price";
    private static final String DV_NUMBER = "number";
    private static final String TB_DICHVU = "dichvu";
    private static final String TB_NHANVIEN = "nhanvien";

    public SQLHelperDemo(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlQuery = "CREATE TABLE dichvu(" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "price INTEGER," +
                "number INTEGER" +
                ")";

        String sqlQuery02 = "CREATE TABLE nhanvien(" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "price INTEGER," +
                "number INTEGER" +
                ")";

        db.execSQL(sqlQuery);
        db.execSQL(sqlQuery02);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion != newVersion) {
            String strQuery = "DROP TABLE IF EXISTS " + DB_NAME;
            db.execSQL(strQuery);
            onCreate(db);
        }
    }

    public void onAddProduct(String name, int price, int number) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DV_NAME, name);
        contentValues.put(DV_PRICE, price);
        contentValues.put(DV_NUMBER, number);

        sqLiteDatabase.insert(TB_DICHVU, null, contentValues);

        sqLiteDatabase.close();
        contentValues.clear();
    }

    public void onUpdateProduct(int id, int price, int number) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DV_PRICE, price);
        contentValues.put(DV_NUMBER, number);

        sqLiteDatabase.update(TB_DICHVU, contentValues, "id=?", new String[]{String.valueOf(id)});
    }

    public void onDeleteDichVu(int id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(TB_DICHVU, "id=?", new String[]{String.valueOf(id)});
    }

    public void onDeleteALL() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(TB_DICHVU, null, null);
    }

    public List<Product> onGetProduct() {
        List<Product> products = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(false, TB_DICHVU,
                null,
                null,
                null,
                null,
                null,
                null,
                null);

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(DV_NAME));
            int price = cursor.getInt(cursor.getColumnIndex(DV_PRICE));
            int number = cursor.getInt(cursor.getColumnIndex(DV_NUMBER));

            products.add(new Product(name, price, number));
        }

        return products;
    }


}
