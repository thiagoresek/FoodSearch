package com.lifesum.thiago.foodsearch.connection;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.os.AsyncTask;

import com.lifesum.thiago.foodsearch.model.FoodEntry;
import com.lifesum.thiago.foodsearch.view.FragmentSearch;

// Utility class to manage connection and communication with the server
public class ConnectionManager {

	// Server information
	private static final String URL = "https://api.lifesum.com/v1/search/query?type=food&search=";
	private static final String AUTH_TOKEN = "a794ecd348a3f71894426c65c37fea35da89a295bcbad687ca68a96fbfc7d371";

	private ArrayList<FoodEntry> mFoods = null;
	private FragmentSearch mFragment;
	
	public void queryServer(String param, Context context, FragmentSearch fragment) {
		mFragment = fragment;
		new GetOnSeparateThread().execute(param);
	}
	
	// Query the server as an AsyncTask (avoid ANR)
	private class GetOnSeparateThread extends AsyncTask<String, Void, ArrayList<FoodEntry>> {
		@Override
		protected ArrayList<FoodEntry> doInBackground(String... params) {

			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(URL + params[0]);
			post.setHeader("Authorization", AUTH_TOKEN);
			
			HttpResponse httpResponse = null;
			HttpEntity httpEntity = null;
			String response = null;
			
			try {
				httpResponse = client.execute(post);
				httpEntity = httpResponse.getEntity();
				response = EntityUtils.toString(httpEntity);
			} catch (ClientProtocolException cpe) {
				// We should treat these exceptions
			} catch (IOException ioe) {
				// We should treat these exceptions
			}
			
			ArrayList<FoodEntry> foods = ParseResponse.parseResponse(response);
			return foods;
		}

		@Override
		protected void onPostExecute(ArrayList<FoodEntry> foods) {
			super.onPostExecute(foods);
			mFoods = foods;
			sendSearchResultToActivity();
		}
	}
	

	private void sendSearchResultToActivity(){
		// Call fragment callback to display search results
		mFragment.searchResultCallback(mFoods);
	}

}
