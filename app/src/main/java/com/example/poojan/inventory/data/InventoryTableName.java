package com.example.poojan.inventory.data;

import android.provider.BaseColumns;

public final class InventoryTableName {
    private InventoryTableName(){}
    public final static class ProductEntry implements BaseColumns{
        public static final String TABLE_NAME="PRODUCT";
        public static final String _ID=BaseColumns._ID;
        public static final String PRODUCT_NAME="product_name";
        public static final String PRICE="price";
        public static final String QUANTITY="quantity";
        public static final String SELLER_NAME="seller_name";
        public static final String PHONO_NO="phono_no";
        public static final String EMAIL="email";


    }
}
