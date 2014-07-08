package com.lifesum.thiago.foodsearch.view;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.ProgressBar;

import com.lifesum.thiago.foodsearch.R;
import com.lifesum.thiago.foodsearch.database.FoodInfoContentProvider;
import com.lifesum.thiago.foodsearch.database.FoodSqliteHelper;
import com.lifesum.thiago.foodsearch.model.JSONFormat;

// Represents the screen with user's saved foods. User LoaderManager to query in a separate thread
public class FragmentSavedFoods extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    // This is the Adapter being used to display the list's data
    CustomCursorAdapter mAdapter;
    
    private ViewGroup mRoot = null;
    
    // These are the rows that we will retrieve
    static final String[] PROJECTION = new String[] {FoodSqliteHelper.COLUMN_UNIQUE_ID,
    	FoodSqliteHelper.COLUMN_TITLE,
        FoodSqliteHelper.COLUMN_BRAND};

    // This is the select criteria
    static final String SELECTION = "((" + 
            FoodSqliteHelper.COLUMN_TITLE + " NOTNULL) AND (" +
            FoodSqliteHelper.COLUMN_TITLE + " != '' ))";

    
    public static Fragment newInstance() {
        FragmentSavedFoods f = new FragmentSavedFoods();
        return f;
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_saved, null);
        mRoot = root;
        return root;
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
    	super.onActivityCreated(savedInstanceState);
        
        // Create a progress bar to display while the list loads
        ProgressBar progressBar = new ProgressBar(getActivity().getApplicationContext());
        progressBar.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        progressBar.setIndeterminate(true);
        

        String[] fromColumns = { JSONFormat.TAG_TITLE, JSONFormat.TAG_BRAND};
        int[] toViews = { R.id.textViewTitle, R.id.textViewBrand };
        
        mAdapter = new CustomCursorAdapter(getActivity().getApplicationContext(), 
                R.layout.row_layout, null,
                fromColumns, toViews, 0, false);
        setListAdapter(mAdapter);

        // Prepare the loader.  Either re-connect with an existing one,
        // or start a new one.
        getLoaderManager().initLoader(0, null, this);
    }
    @Override
    public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
        return new CursorLoader(getActivity().getApplicationContext(), FoodInfoContentProvider.CONTENT_URI,
                PROJECTION, SELECTION, null, null);
    }
    
    @Override
    public void onLoaderReset(Loader<Cursor> data) {
        mAdapter.swapCursor(null);
    }
    
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
    	if(data.getCount() == 0){ // If there are no foods stores, show empty view
          getListView().setEmptyView(mRoot.findViewById(R.id.emptyView));
    	}else{
    		View emptyView = mRoot.findViewById(R.id.emptyView);
    		emptyView.setVisibility(View.GONE);
    	}
    	
        mAdapter.swapCursor(data);
    }
}
