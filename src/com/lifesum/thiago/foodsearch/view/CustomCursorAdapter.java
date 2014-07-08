package com.lifesum.thiago.foodsearch.view;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.lifesum.thiago.foodsearch.R;
import com.lifesum.thiago.foodsearch.database.DBOperationsHelper;
import com.lifesum.thiago.foodsearch.database.FoodSqliteHelper;
import com.lifesum.thiago.foodsearch.model.FoodEntry;

/**
 * A custom adapter, based on SimpleCursorAdapter, to show either search results
 * or stored foods. Since most of the behavior is the same, we use only one
 * adapter
 * 
 * @author Thiago
 * 
 */
public class CustomCursorAdapter extends SimpleCursorAdapter {

	private Context mContext = null;

	private boolean mOnlineSearch; // true for the adapter that shows results
									// obtained from the server, false for the
									// adapter that shows results stored locally

	ArrayList<FoodEntry> mFoods = null;
	FoodSqliteHelper mDbHelper = null;

	public CustomCursorAdapter(Context context, int layout, Cursor c,
			String[] from, int[] to, int flags, boolean onlineSearch,
			ArrayList<FoodEntry> foods, FoodSqliteHelper dbHelper) {
		super(context, layout, c, from, to, flags);
		mContext = context;
		mOnlineSearch = onlineSearch;
		mFoods = foods;
		mDbHelper = dbHelper;
	}

	public CustomCursorAdapter(Context context, int layout, Cursor c,
			String[] from, int[] to, int flags, boolean onlineSearch) {
		this(context, layout, c, from, to, flags, onlineSearch, null, null);
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View v = convertView;
		ViewHolder viewHolder;

		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.row_layout, null);

			// We use the ViewHolder design pattern here to avoid calling
			// findViewById every time
			viewHolder = new ViewHolder();
			viewHolder.imageButtonInfo = (ImageButton) v
					.findViewById(R.id.imageButtonInfo);
			viewHolder.imageButtonSave = (ImageButton) v
					.findViewById(R.id.imageButtonSave);
			viewHolder.textViewBrand = (TextView) v
					.findViewById(R.id.textViewBrand);
			viewHolder.textViewTitle = (TextView) v
					.findViewById(R.id.textViewTitle);
			v.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) v.getTag();
		}

		Cursor cursor = getCursor();
		cursor.moveToPosition(position);
		if (cursor != null) {
			int columnTitle = cursor
					.getColumnIndex(FoodSqliteHelper.COLUMN_TITLE);
			int columnBrand = cursor
					.getColumnIndex(FoodSqliteHelper.COLUMN_BRAND);
			int columnId = cursor
					.getColumnIndex(FoodSqliteHelper.COLUMN_UNIQUE_ID);
			viewHolder.textViewTitle.setText(cursor.getString(columnTitle));
			viewHolder.textViewBrand.setText(cursor.getString(columnBrand));

			// save ID as tag so we can query DB later
			viewHolder.imageButtonInfo.setTag(cursor.getLong(columnId));

			if (mOnlineSearch) { // If it's the list of online results, show
									// "save" button
				viewHolder.imageButtonSave.setVisibility(View.VISIBLE);
			} else {
				viewHolder.imageButtonSave.setVisibility(View.GONE);
			}

		}

		viewHolder.imageButtonInfo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onButtonInfoClick((Long) v.getTag(), position);
			}
		});

		viewHolder.imageButtonSave.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onButtonSaveClick(position);
			}
		});

		return v;

	}

	static class ViewHolder {
		TextView textViewTitle;
		TextView textViewBrand;
		ImageButton imageButtonInfo;
		ImageButton imageButtonSave;
	}

	
	// Handles a click on the "Info" button - start activity to show details
	private void onButtonInfoClick(long id, int position) {
		Intent intent = new Intent(mContext, FoodDetailsActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		if (mOnlineSearch) {
			intent.putExtra(FoodDetailsActivity.EXTRA_FOOD_ENTRY,
					mFoods.get(position));
		} else {
			intent.putExtra(FoodDetailsActivity.EXTRA_ID, id);
		}
		mContext.startActivity(intent);

	}

	// Handles a click on the "Save" button - insert into DB
	private void onButtonSaveClick(int position) {
		String userFeedback = null;
		if (DBOperationsHelper.insert(mDbHelper, mFoods.get(position)) != -1) {
			userFeedback = mContext
					.getString(R.string.food_saved_into_local_database);
		} else {
			userFeedback = mContext.getString(R.string.error_while_saving_food);
		}
		Toast.makeText(mContext, userFeedback, Toast.LENGTH_SHORT).show();
	}
}
