package com.example.poojan.inventory;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.poojan.inventory.data.InventoryDB;
import com.example.poojan.inventory.data.InventoryTableName;

public class AddProductActivity extends AppCompatActivity {

    LinearLayout textview;
    TextView productname,Price,Quantity,Sellername,Phoneno,Email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        productname=(TextView)findViewById(R.id.product_name);
        Price=(TextView)findViewById(R.id.price);
        Quantity=(TextView)findViewById(R.id.quantity);
        Sellername=(TextView)findViewById(R.id.suppiler_name);
        Phoneno=(TextView)findViewById(R.id.phono);
        Email=(TextView)findViewById(R.id.email);
        textview=(LinearLayout)findViewById(R.id.snackbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ok,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.save:
                insert();
                Intent i=new Intent(AddProductActivity.this,MainActivity.class);
                startActivity(i);
                // Do nothing for now
                return true;
        }
        return true;
    }
    private void insert(){
        InventoryDB idb=new InventoryDB(this);
        SQLiteDatabase db=idb.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        String productString=productname.getText().toString().trim();
        String priceString=Price.getText().toString().trim();
        int Priceint=Integer.parseInt(priceString);
        String quString=Quantity.getText().toString().trim();
        int Quantityint=Integer.parseInt(quString);
        String SellerString=Sellername.getText().toString().trim();
        String PhonoString=Phoneno.getText().toString().trim();
        String EmailString=Email.getText().toString().trim();
        contentValues.put(InventoryTableName.ProductEntry.PRODUCT_NAME,productString);
        contentValues.put(InventoryTableName.ProductEntry.PRICE,Priceint);
        contentValues.put(InventoryTableName.ProductEntry.QUANTITY,Quantityint);
        contentValues.put(InventoryTableName.ProductEntry.SELLER_NAME,SellerString);
        contentValues.put(InventoryTableName.ProductEntry.PHONO_NO,PhonoString);
        contentValues.put(InventoryTableName.ProductEntry.EMAIL,EmailString);
        long f=db.insert(InventoryTableName.ProductEntry.TABLE_NAME,
                null,
                contentValues);
        if(f==-1)
        {

            Toast.makeText(this,"Data Enter Un Sucessfully",Toast.LENGTH_LONG).show();

        }
        else
        {
            Toast.makeText(this,"Data Enter Sucessfully",Toast.LENGTH_LONG).show();
        }


    }

}
