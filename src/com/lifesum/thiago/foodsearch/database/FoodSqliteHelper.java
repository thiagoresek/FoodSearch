package com.lifesum.thiago.foodsearch.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FoodSqliteHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "search_food_database";
    public static final String TABLE_FOODS = "saved_foods";
    
    // Columns of our database (contain all information returned by the server)
    public static final String COLUMN_UNIQUE_ID = "_id";
    public static final String COLUMN_FIBER = "fiber";
    public static final String COLUMN_UNSATURATEDFAT = "unsaturatedfat";
    public static final String COLUMN_FAT = "fat";
    public static final String COLUMN_PROTEIN = "protein";
    public static final String COLUMN_SATURATEDFAT = "saturatedfat";
    public static final String COLUMN_SODIUM = "sodium";
    public static final String COLUMN_CARBOHYDRATES = "carbohydrates";
    public static final String COLUMN_SUGAR = "sugar";
    public static final String COLUMN_CHOLESTEROL = "cholesterol";
    public static final String COLUMN_GRAMSPERSERVING = "gramsperserving";
    public static final String COLUMN_POTASSIUM = "potassium";
    public static final String COLUMN_CATEGORYID = "categoryid";
    public static final String COLUMN_PCSINGRAM = "pcsingram";
    public static final String COLUMN_SERVINGCATEGORY = "servingcategory";
    public static final String COLUMN_TYPEOFMEASUREMENT = "typeofmeasurement";
    public static final String COLUMN_DEFAULTSERVING = "defaultserving";
    public static final String COLUMN_MLINGRAM = "mlingram";
    public static final String COLUMN_SHOWONLYSAMETYPE = "showonlysametype";
    public static final String COLUMN_CALORIES = "calories";
    public static final String COLUMN_SERVING_VERSION = "serving_version";
    public static final String COLUMN_MEASUREMENTID = "measurementid";
    public static final String COLUMN_SHOWMEASUREMENT = "showmeasurement";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_VERIFIED = "verified";
    public static final String COLUMN_HEADIMAGE = "headimage";
    public static final String COLUMN_BRAND = "brand";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_PCSTEXT = "pcstext";
    
    // Create DB
    private final String DATABASE_CREATE = "create table " + TABLE_FOODS +  " ( " 
            + COLUMN_UNIQUE_ID + " integer not null primary key autoincrement, "
            + COLUMN_BRAND + " text, "
            + COLUMN_HEADIMAGE + " text, "
            + COLUMN_CATEGORY + " text, "
            + COLUMN_TITLE + " text, "
            + COLUMN_PCSTEXT + " text, "
            + COLUMN_CATEGORYID + " integer, "
            + COLUMN_PCSINGRAM + " integer, "
            + COLUMN_SERVINGCATEGORY + " integer, "
            + COLUMN_TYPEOFMEASUREMENT + " integer, "
            + COLUMN_DEFAULTSERVING + " integer, "
            + COLUMN_MLINGRAM + " integer, "
            + COLUMN_SHOWONLYSAMETYPE + " integer, "
            + COLUMN_CALORIES + " integer, "
            + COLUMN_SERVING_VERSION + " integer, "
            + COLUMN_MEASUREMENTID + " integer, "
            + COLUMN_SHOWMEASUREMENT + " integer, "
            + COLUMN_ID + " integer, "
            + COLUMN_VERIFIED + " integer, "
            + COLUMN_FIBER + " real, "
            + COLUMN_UNSATURATEDFAT + " real, "
            + COLUMN_FAT + " real, "
            + COLUMN_PROTEIN + " real, "
            + COLUMN_SATURATEDFAT + " real, "
            + COLUMN_SODIUM + " real, "
            + COLUMN_CARBOHYDRATES + " real, "
            + COLUMN_SUGAR + " real, "
            + COLUMN_CHOLESTEROL + " real, "
            + COLUMN_GRAMSPERSERVING + " real, "
            + COLUMN_POTASSIUM + " real);";

    
    public FoodSqliteHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create database
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
    }

}
