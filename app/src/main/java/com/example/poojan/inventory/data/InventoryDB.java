package com.example.poojan.inventory.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class InventoryDB extends SQLiteOpenHelper {
    private static final int database_v_n=1;
    private static final String database_name="Inventory";
    public InventoryDB( Context context) {
        super(context, database_name,null, database_v_n);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_Inventory_table="CREATE TABLE " + InventoryTableName.ProductEntry.TABLE_NAME +
                "(" + InventoryTableName.ProductEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                InventoryTableName.ProductEntry.PRODUCT_NAME + " TEXT NOT NULL,"+
                InventoryTableName.ProductEntry.PRICE + " INTEGER NOT NULL," +
                InventoryTableName.ProductEntry.QUANTITY + " INTEGER NOT NULL," +
                InventoryTableName.ProductEntry.SELLER_NAME + " TEXT NOT NULL," +
                InventoryTableName.ProductEntry.PHONO_NO + " TEXT DEFAULT 0,"
                +InventoryTableName.ProductEntry.EMAIL + " TEXT DEFAULT 0);";
        db.execSQL(SQL_Inventory_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
