package com.lifesum.thiago.foodsearch.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lifesum.thiago.foodsearch.model.FoodEntry;

// Helper class for common database operations
public class DBOperationsHelper {

	public static long insert(FoodSqliteHelper dbHelper, FoodEntry f) {
		// TODO: check if this ID already exists. should entries be unique?
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues cv = createContentValuesFromEntry(f);
		return db.insert(FoodSqliteHelper.TABLE_FOODS, null, cv);
	}

	public static Cursor query(FoodSqliteHelper dbHelper, String[] columns,
			String selection, String[] selectionArgs, String groupBy,
			String having, String orderBy) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		return db.query(FoodSqliteHelper.TABLE_FOODS, columns, selection,
				selectionArgs, groupBy, having, orderBy);
	}

	/**
	 * Search for an element in the DB given its ID
	 * @param dbHelper
	 * @param id
	 * @return
	 */
	public static Cursor queryById(FoodSqliteHelper dbHelper, long id) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		// We just project the columns we will show to the user
		String[] columns = { FoodSqliteHelper.COLUMN_UNIQUE_ID,
				FoodSqliteHelper.COLUMN_TITLE, FoodSqliteHelper.COLUMN_BRAND,
				FoodSqliteHelper.COLUMN_CALORIES, FoodSqliteHelper.COLUMN_FAT,
				FoodSqliteHelper.COLUMN_PROTEIN };

		String selection = FoodSqliteHelper.COLUMN_UNIQUE_ID + " = ?";
		String[] selectionArgs = { Long.toString(id) };
		return db.query(FoodSqliteHelper.TABLE_FOODS, columns, selection,
				selectionArgs, null, null, null);
	}

	// Fill in a ContentValue with all info contained in FoodEntry
	private static ContentValues createContentValuesFromEntry(FoodEntry f) {
		ContentValues cv = new ContentValues();
		cv.put(FoodSqliteHelper.COLUMN_FIBER, f.getFiber());
		cv.put(FoodSqliteHelper.COLUMN_UNSATURATEDFAT, f.getUnsaturatedfat());
		cv.put(FoodSqliteHelper.COLUMN_FAT, f.getFat());
		cv.put(FoodSqliteHelper.COLUMN_PROTEIN, f.getProtein());
		cv.put(FoodSqliteHelper.COLUMN_SATURATEDFAT, f.getSaturatedfat());
		cv.put(FoodSqliteHelper.COLUMN_SODIUM, f.getSodium());
		cv.put(FoodSqliteHelper.COLUMN_CARBOHYDRATES, f.getCarbohydrates());
		cv.put(FoodSqliteHelper.COLUMN_SUGAR, f.getSugar());
		cv.put(FoodSqliteHelper.COLUMN_CHOLESTEROL, f.getCholesterol());
		cv.put(FoodSqliteHelper.COLUMN_GRAMSPERSERVING, f.getGramsperserving());
		cv.put(FoodSqliteHelper.COLUMN_POTASSIUM, f.getPotassium());
		cv.put(FoodSqliteHelper.COLUMN_CATEGORYID, f.getCategoryid());
		cv.put(FoodSqliteHelper.COLUMN_PCSINGRAM, f.getPcsingram());
		cv.put(FoodSqliteHelper.COLUMN_SERVINGCATEGORY, f.getServingcategory());
		cv.put(FoodSqliteHelper.COLUMN_TYPEOFMEASUREMENT,
				f.getTypeofmeasurement());
		cv.put(FoodSqliteHelper.COLUMN_DEFAULTSERVING, f.getDefaultserving());
		cv.put(FoodSqliteHelper.COLUMN_MLINGRAM, f.getMlingram());
		cv.put(FoodSqliteHelper.COLUMN_SHOWONLYSAMETYPE,
				f.getShowonlysametype());
		cv.put(FoodSqliteHelper.COLUMN_CALORIES, f.getCalories());
		cv.put(FoodSqliteHelper.COLUMN_SERVING_VERSION, f.getServing_version());
		cv.put(FoodSqliteHelper.COLUMN_MEASUREMENTID, f.getMeasurementid());
		cv.put(FoodSqliteHelper.COLUMN_SHOWMEASUREMENT, f.getShowmeasurement());
		cv.put(FoodSqliteHelper.COLUMN_ID, f.getId());
		cv.put(FoodSqliteHelper.COLUMN_VERIFIED, f.isVerified());
		cv.put(FoodSqliteHelper.COLUMN_HEADIMAGE, f.getHeadimage());
		cv.put(FoodSqliteHelper.COLUMN_BRAND, f.getBrand());
		cv.put(FoodSqliteHelper.COLUMN_CATEGORY, f.getCategory());
		cv.put(FoodSqliteHelper.COLUMN_TITLE, f.getTitle());
		cv.put(FoodSqliteHelper.COLUMN_PCSTEXT, f.getPcstext());
		return cv;
	}
}
