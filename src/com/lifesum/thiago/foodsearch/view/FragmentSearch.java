package com.lifesum.thiago.foodsearch.view;

import java.util.ArrayList;

import android.app.Activity;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lifesum.thiago.foodsearch.R;
import com.lifesum.thiago.foodsearch.connection.ConnectionManager;
import com.lifesum.thiago.foodsearch.database.FoodSqliteHelper;
import com.lifesum.thiago.foodsearch.model.FoodEntry;
import com.lifesum.thiago.foodsearch.model.JSONFormat;

// This represents the screen when the user is searching for foods on the server
public class FragmentSearch extends ListFragment {

	ImageButton mSearch = null;
	EditText mEditTextSearch = null;
	ArrayList<FoodEntry> mFoods = null;
	ListView mList = null;
	FoodSqliteHelper mDbHelper = null;
	View mRoot = null;

	public static Fragment newInstance() {
		FragmentSearch f = new FragmentSearch();
		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_search,
				null);

		mRoot = root;
		mDbHelper = new FoodSqliteHelper(getActivity().getApplicationContext());
		mSearch = (ImageButton) root.findViewById(R.id.buttonSearch);
		mEditTextSearch = (EditText) root.findViewById(R.id.editTextSearch);
		mList = (ListView) root.findViewById(android.R.id.list);

		mSearch.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				performSearch();
			}
		});

		// Show search button on keyboard
		mEditTextSearch
				.setOnEditorActionListener(new TextView.OnEditorActionListener() {
					@Override
					public boolean onEditorAction(TextView v, int actionId,
							KeyEvent event) {
						if (actionId == EditorInfo.IME_ACTION_SEARCH) {
							performSearch();
							return true;
						}
						return false;
					}
				});

		return root;
	}

	// Perform the search on a separate thread
	private void performSearch() {
		ConnectionManager cm = new ConnectionManager();

		// Show progress bar while search is in progress
		ProgressBar progress = (ProgressBar) mRoot
				.findViewById(R.id.progressBarSearch);
		progress.setVisibility(View.VISIBLE);

		if (mEditTextSearch.getText() != null) {
			String text = mEditTextSearch.getText().toString().trim();
			if (!TextUtils.isEmpty(text)) {
				cm.queryServer(text, getActivity().getApplicationContext(),
						this);
			}
		}

		// Hide keyboard after search
		hideSoftKeyboard(getActivity());
	}

	public static void hideSoftKeyboard(Activity activity) {
		InputMethodManager inputMethodManager = (InputMethodManager) activity
				.getSystemService(Activity.INPUT_METHOD_SERVICE);
		inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus()
				.getWindowToken(), 0);
	}

	// Called after the search completes
	public void searchResultCallback(ArrayList<FoodEntry> foods) {
		mFoods = foods;
		updateUiWithSearchResults();
	}

	private void updateUiWithSearchResults() {
		// Hide the progress bar - search was completed
		ProgressBar progress = (ProgressBar) mRoot
				.findViewById(R.id.progressBarSearch);
		progress.setVisibility(View.GONE);

		MatrixCursor cursor = new MatrixCursor(new String[] {
				"_id", JSONFormat.TAG_TITLE, JSONFormat.TAG_BRAND });
		// Create the adapter to use for the list view
		for (FoodEntry f : mFoods) {
			cursor.addRow(new Object[] { f.getId(), f.getTitle(), f.getBrand() });
		}

		if (mList != null) {
			CustomCursorAdapter adapter = new CustomCursorAdapter(
					getActivity().getApplicationContext(),
					R.layout.row_layout,
					cursor,
					new String[] { JSONFormat.TAG_TITLE, JSONFormat.TAG_BRAND },
					new int[] { R.id.textViewTitle, R.id.textViewBrand }, 0, true, mFoods, mDbHelper);

			mList.setAdapter(adapter);
			mList.setClickable(true);
			mList.setVisibility(View.VISIBLE);
		}
	}


	@Override
	public void onDestroy() {
		super.onDestroy();

		if (mDbHelper != null) {
			mDbHelper.close();
		}
	}
}
