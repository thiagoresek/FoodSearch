package com.lifesum.thiago.foodsearch.connection;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lifesum.thiago.foodsearch.model.FoodEntry;
import com.lifesum.thiago.foodsearch.model.JSONFormat;

// Parses the response as returned by the server into a list of FoodEntry elements
public class ParseResponse {

	public static ArrayList<FoodEntry> parseResponse(String response) {
		if (response == null) {
			return null;
		}

		ArrayList<FoodEntry> returnedFoods = new ArrayList<>();

		try {
			JSONObject jsonObj = new JSONObject(response);

			// Get the response object and the list of food returned
			JSONObject resp = jsonObj.getJSONObject("response");
			JSONArray foods = resp.getJSONArray("list");

			// looping through elements returned
			for (int i = 0; i < foods.length(); i++) {
				JSONObject c = foods.getJSONObject(i);

				// Get elements from JSON and insert in the list
				String title = c.getString(JSONFormat.TAG_TITLE);
				String headImage = c.getString(JSONFormat.TAG_HEADIMAGE);
				String brand = c.getString(JSONFormat.TAG_BRAND);
				String category = c.getString(JSONFormat.TAG_CATEGORY);
				String pcstext = c.getString(JSONFormat.TAG_PCSTEXT);

				int categoryid = c.getInt(JSONFormat.TAG_CATEGORYID);
				int pcsingram = c.getInt(JSONFormat.TAG_PCSINGRAM);
				int servingcategory = c.getInt(JSONFormat.TAG_SERVING_VERSION);
				int typeofmeasurement = c
						.getInt(JSONFormat.TAG_TYPEOFMEASUREMENT);
				int defaultserving = c.getInt(JSONFormat.TAG_DEFAULTSERVING);
				int mlingram = c.getInt(JSONFormat.TAG_MLINGRAM);
				int showonlysametype = c
						.getInt(JSONFormat.TAG_SHOWONLYSAMETYPE);
				int calories = c.getInt(JSONFormat.TAG_CALORIES);
				int serving_version = c.getInt(JSONFormat.TAG_SERVING_VERSION);
				int measurementid = c.getInt(JSONFormat.TAG_MEASUREMENTID);
				int showmeasurement = c.getInt(JSONFormat.TAG_SHOWMEASUREMENT);

				double fiber = c.getDouble(JSONFormat.TAG_FIBER);
				double unsaturatedfat = c
						.getDouble(JSONFormat.TAG_UNSATURATEDFAT);
				double fat = c.getDouble(JSONFormat.TAG_FAT);
				double protein = c.getDouble(JSONFormat.TAG_PROTEIN);
				double saturatedfat = c.getDouble(JSONFormat.TAG_SATURATEDFAT);
				double sodium = c.getDouble(JSONFormat.TAG_SODIUM);
				double carbohydrates = c
						.getDouble(JSONFormat.TAG_CARBOHYDRATES);
				double sugar = c.getDouble(JSONFormat.TAG_SUGAR);
				double cholesterol = c.getDouble(JSONFormat.TAG_CHOLESTEROL);
				double gramsperserving = c
						.getDouble(JSONFormat.TAG_GRAMSPERSERVING);
				double potassium = c.getDouble(JSONFormat.TAG_POTASSIUM);

				long id = c.getLong(JSONFormat.TAG_ID);
				boolean verified = c.getBoolean(JSONFormat.TAG_VERIFIED);

				returnedFoods.add(new FoodEntry(fiber, unsaturatedfat, fat,
						protein, saturatedfat, sodium, carbohydrates, sugar,
						cholesterol, gramsperserving, potassium, categoryid,
						pcsingram, servingcategory, typeofmeasurement,
						defaultserving, mlingram, showonlysametype, calories,
						serving_version, measurementid, showmeasurement, id,
						verified, headImage, brand, category, title, pcstext));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return returnedFoods;
	}
}
