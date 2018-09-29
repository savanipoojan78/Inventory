package com.example.poojan.inventory;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.poojan.inventory.data.InventoryDB;
import com.example.poojan.inventory.data.InventoryTableName;

public class MainActivity extends AppCompatActivity {


    private InventoryDB inventoryDB;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,AddProductActivity.class);
                startActivity(i);
            }
        });
         inventoryDB=new InventoryDB(this);
        textView=(TextView)findViewById(R.id.display);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    protected void onStart() {
        super.onStart();
        displaydata();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_data_dummy) {
            insert();
            displaydata();
            return true;
        }
        if(id==R.id.delete_data)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void insert(){

        SQLiteDatabase db=inventoryDB.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(InventoryTableName.ProductEntry.PRODUCT_NAME,"Logitech Mouse");
        contentValues.put(InventoryTableName.ProductEntry.PRICE,Integer.parseInt("400"));
        contentValues.put(InventoryTableName.ProductEntry.QUANTITY,Integer.parseInt("5"));
        contentValues.put(InventoryTableName.ProductEntry.SELLER_NAME,"Amazon");
        contentValues.put(InventoryTableName.ProductEntry.PHONO_NO,"9033880905");
        contentValues.put(InventoryTableName.ProductEntry.EMAIL,"savanipoojan78@gmail.com");


        long f=db.insert(InventoryTableName.ProductEntry.TABLE_NAME,
                null,
                contentValues);
        if(f==-1)
        {

            Snackbar.make(textView, "Data Insert UnSucessfully", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

        }
        else
        {
            Snackbar.make(textView, "Data Insert Sucessfully", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
    private void displaydata(){
        SQLiteDatabase db=inventoryDB.getReadableDatabase();
        String[] projection={
                InventoryTableName.ProductEntry._ID,
                InventoryTableName.ProductEntry.PRODUCT_NAME,
                InventoryTableName.ProductEntry.PRICE,
                InventoryTableName.ProductEntry.QUANTITY,
                InventoryTableName.ProductEntry.SELLER_NAME,
                InventoryTableName.ProductEntry.PHONO_NO,
                InventoryTableName.ProductEntry.EMAIL,
        };
        Cursor cursor=db.query(
                InventoryTableName.ProductEntry.TABLE_NAME,
                projection,null,null,null,null,null);
        TextView display=(TextView)findViewById(R.id.display);
        try{
            display.setText("Number Of Rows in This Table"+ " "+cursor.getCount());

            while(cursor.moveToNext()){
                display.append("\n\n\n"+InventoryTableName.ProductEntry._ID + " :-"+"\t" +cursor.getString(cursor.getColumnIndex(InventoryTableName.ProductEntry._ID)));
                display.append("\n"+InventoryTableName.ProductEntry.PRODUCT_NAME + " :-"+"\t" +cursor.getString(cursor.getColumnIndex(InventoryTableName.ProductEntry.PRODUCT_NAME)));
                display.append("\n"+InventoryTableName.ProductEntry.PRICE + " :-"+"\t" +cursor.getString(cursor.getColumnIndex(InventoryTableName.ProductEntry.PRICE)));
                display.append("\n"+InventoryTableName.ProductEntry.QUANTITY + " :-"+"\t" +cursor.getString(cursor.getColumnIndex(InventoryTableName.ProductEntry.QUANTITY)));
                display.append("\n"+InventoryTableName.ProductEntry.SELLER_NAME + " :-"+"\t" +cursor.getString(cursor.getColumnIndex(InventoryTableName.ProductEntry.SELLER_NAME)));
                display.append("\n"+InventoryTableName.ProductEntry.PHONO_NO + " :-"+"\t" +cursor.getString(cursor.getColumnIndex(InventoryTableName.ProductEntry.PHONO_NO)));
                display.append("\n"+InventoryTableName.ProductEntry.EMAIL + " :-"+"\t" +cursor.getString(cursor.getColumnIndex(InventoryTableName.ProductEntry.EMAIL)));


            }
        }
        finally {
            cursor.close();
        }

    }
}
