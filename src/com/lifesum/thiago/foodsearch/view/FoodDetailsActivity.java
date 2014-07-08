package com.lifesum.thiago.foodsearch.view;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import com.lifesum.thiago.foodsearch.R;
import com.lifesum.thiago.foodsearch.database.DBOperationsHelper;
import com.lifesum.thiago.foodsearch.database.FoodSqliteHelper;
import com.lifesum.thiago.foodsearch.model.FoodEntry;

/***
 * Simple activity to show some information about a stored food (in a real app,
 * user should be able to see all relevant information. For simplicity sake, I
 * chose just some information). Can be used to show info about a stored food or
 * about a food that was searched in the server
 * 
 * @author Thiago
 * 
 */
public class FoodDetailsActivity extends Activity {

	FoodSqliteHelper mDbHelper = null;

	public static final String EXTRA_ID = "id";
	public static final String EXTRA_FOOD_ENTRY = "food_entry";

	private String mTitle = null, mBrand = null;
	private int mCalories = 0;
	private double mFat = 0.0, mProtein = 0.0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_food_details);

		long id = getIntent().getLongExtra(EXTRA_ID, -1);

		if (id != -1) { // offline query - get from DB
			getFieldsFromDb(id);
		} else {// online query - field is sent locally via Intent
			FoodEntry food = (FoodEntry) getIntent().getParcelableExtra(
					EXTRA_FOOD_ENTRY);
			getFieldsFromExtra(food);
		}

		updateUI(); //update UI with values
	}

	private void getFieldsFromDb(long id) {
		mDbHelper = new FoodSqliteHelper(getApplicationContext());
		Cursor c = DBOperationsHelper.queryById(mDbHelper, id);

		// Since IDs are unique, we can assume only one element
		if (c != null && c.getCount() >= 1) {
			c.moveToFirst();
			mTitle = c.getString(c
					.getColumnIndex(FoodSqliteHelper.COLUMN_TITLE));
			mBrand = c.getString(c
					.getColumnIndex(FoodSqliteHelper.COLUMN_BRAND));
			mCalories = c.getInt(c
					.getColumnIndex(FoodSqliteHelper.COLUMN_CALORIES));
			mFat = c.getDouble(c.getColumnIndex(FoodSqliteHelper.COLUMN_FAT));
			mProtein = c.getDouble(c
					.getColumnIndex(FoodSqliteHelper.COLUMN_PROTEIN));
			c.close();
		}
	}

	private void getFieldsFromExtra(FoodEntry food) {
		mTitle = food.getTitle();
		mBrand = food.getBrand();
		mCalories = food.getCalories();
		mFat = food.getFat();
		mProtein = food.getProtein();
	}

	private void updateUI() {
		TextView textViewTitle = (TextView) findViewById(R.id.textViewDetailsTitleValue);
		TextView textViewBrand = (TextView) findViewById(R.id.textViewDetailsBrandValue);
		TextView textViewCalories = (TextView) findViewById(R.id.textViewDetailsCaloriesValue);
		TextView textViewFat = (TextView) findViewById(R.id.textViewDetailsFatValue);
		TextView textViewProtein = (TextView) findViewById(R.id.textViewDetailsProteinValue);

		textViewTitle.setText(mTitle);
		textViewBrand.setText(mBrand);
		textViewCalories.setText(mCalories + " kcal");
		textViewFat.setText(mFat + "g");
		textViewProtein.setText(mProtein + "g");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		if (mDbHelper != null) {
			mDbHelper.close();
		}
	}
}
