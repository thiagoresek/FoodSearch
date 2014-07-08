package com.lifesum.thiago.foodsearch.model;

import android.os.Parcel;
import android.os.Parcelable;

/***
 * This class represents a food entry as returned by the server (contains all
 * info, even though some are not used in this sample app). Implements
 * parcelable so that it can be sent to other Activities via Intent
 * 
 * @author Thiago
 * 
 */
public class FoodEntry implements Parcelable {

	private double fiber, unsaturatedfat, fat, protein, saturatedfat, sodium,
			carbohydrates, sugar, cholesterol, gramsperserving, potassium;

	public double getFiber() {
		return fiber;
	}

	public void setFiber(double fiber) {
		this.fiber = fiber;
	}

	public double getUnsaturatedfat() {
		return unsaturatedfat;
	}

	public void setUnsaturatedfat(double unsaturatedfat) {
		this.unsaturatedfat = unsaturatedfat;
	}

	public double getFat() {
		return fat;
	}

	public void setFat(double fat) {
		this.fat = fat;
	}

	public double getProtein() {
		return protein;
	}

	public void setProtein(double protein) {
		this.protein = protein;
	}

	public double getSaturatedfat() {
		return saturatedfat;
	}

	public void setSaturatedfat(double saturatedfat) {
		this.saturatedfat = saturatedfat;
	}

	public double getSodium() {
		return sodium;
	}

	public void setSodium(double sodium) {
		this.sodium = sodium;
	}

	public double getCarbohydrates() {
		return carbohydrates;
	}

	public void setCarbohydrates(double carbohydrates) {
		this.carbohydrates = carbohydrates;
	}

	public double getSugar() {
		return sugar;
	}

	public void setSugar(double sugar) {
		this.sugar = sugar;
	}

	public double getCholesterol() {
		return cholesterol;
	}

	public void setCholesterol(double cholesterol) {
		this.cholesterol = cholesterol;
	}

	public double getGramsperserving() {
		return gramsperserving;
	}

	public void setGramsperserving(double gramsperserving) {
		this.gramsperserving = gramsperserving;
	}

	public double getPotassium() {
		return potassium;
	}

	public void setPotassium(double potassium) {
		this.potassium = potassium;
	}

	public int getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}

	public int getPcsingram() {
		return pcsingram;
	}

	public void setPcsingram(int pcsingram) {
		this.pcsingram = pcsingram;
	}

	public int getServingcategory() {
		return servingcategory;
	}

	public void setServingcategory(int servingcategory) {
		this.servingcategory = servingcategory;
	}

	public int getTypeofmeasurement() {
		return typeofmeasurement;
	}

	public void setTypeofmeasurement(int typeofmeasurement) {
		this.typeofmeasurement = typeofmeasurement;
	}

	public int getDefaultserving() {
		return defaultserving;
	}

	public void setDefaultserving(int defaultserving) {
		this.defaultserving = defaultserving;
	}

	public int getMlingram() {
		return mlingram;
	}

	public void setMlingram(int mlingram) {
		this.mlingram = mlingram;
	}

	public int getShowonlysametype() {
		return showonlysametype;
	}

	public void setShowonlysametype(int showonlysametype) {
		this.showonlysametype = showonlysametype;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public int getServing_version() {
		return serving_version;
	}

	public void setServing_version(int serving_version) {
		this.serving_version = serving_version;
	}

	public int getMeasurementid() {
		return measurementid;
	}

	public void setMeasurementid(int measurementid) {
		this.measurementid = measurementid;
	}

	public int getShowmeasurement() {
		return showmeasurement;
	}

	public void setShowmeasurement(int showmeasurement) {
		this.showmeasurement = showmeasurement;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public String getHeadimage() {
		return headimage;
	}

	public void setHeadimage(String headimage) {
		this.headimage = headimage;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPcstext() {
		return pcstext;
	}

	public void setPcstext(String pcstext) {
		this.pcstext = pcstext;
	}

	private int categoryid, pcsingram, servingcategory, typeofmeasurement,
			defaultserving, mlingram, showonlysametype, calories,
			serving_version, measurementid, showmeasurement;

	private long id;

	private boolean verified;

	private String headimage, brand, category, title, pcstext;

	public FoodEntry() {
		// Empty constructor
	}

	public FoodEntry(double fiber, double unsaturatedfat, double fat,
			double protein, double saturatedfat, double sodium,
			double carbohydrates, double sugar, double cholesterol,
			double gramsperserving, double potassium, int categoryid,
			int pcsingram, int servingcategory, int typeofmeasurement,
			int defaultserving, int mlingram, int showonlysametype,
			int calories, int serving_version, int measurementid,
			int showmeasurement, long id, boolean verified, String headimage,
			String brand, String category, String title, String pcstext) {
		super();
		this.fiber = fiber;
		this.unsaturatedfat = unsaturatedfat;
		this.fat = fat;
		this.protein = protein;
		this.saturatedfat = saturatedfat;
		this.sodium = sodium;
		this.carbohydrates = carbohydrates;
		this.sugar = sugar;
		this.cholesterol = cholesterol;
		this.gramsperserving = gramsperserving;
		this.potassium = potassium;
		this.categoryid = categoryid;
		this.pcsingram = pcsingram;
		this.servingcategory = servingcategory;
		this.typeofmeasurement = typeofmeasurement;
		this.defaultserving = defaultserving;
		this.mlingram = mlingram;
		this.showonlysametype = showonlysametype;
		this.calories = calories;
		this.serving_version = serving_version;
		this.measurementid = measurementid;
		this.showmeasurement = showmeasurement;
		this.id = id;
		this.verified = verified;
		this.headimage = headimage;
		this.brand = brand;
		this.category = category;
		this.title = title;
		this.pcstext = pcstext;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO in a real app, we need to add other fields - for simplicity, keep just the ones we use
		dest.writeString(title);
		dest.writeString(brand);
		dest.writeString(category);
		dest.writeInt(calories);
		dest.writeDouble(fat);
		dest.writeDouble(protein);
	}

	public static final Parcelable.Creator<FoodEntry> CREATOR = new Creator<FoodEntry>() {
		public FoodEntry createFromParcel(Parcel source) {
			FoodEntry food = new FoodEntry();
			food.setTitle(source.readString());
			food.setBrand(source.readString());
			food.setCategory(source.readString());
			food.setCalories(source.readInt());
			food.setFat(source.readDouble());
			food.setProtein(source.readDouble());
			return food;
		}

		public FoodEntry[] newArray(int size) {
			return new FoodEntry[size];
		}
	};

	// private FoodEntry(Parcel in) {
	// String[] data = new String[3];
	//
	// in.readStringArray(data);
	// this.title = data[0];
	// this.brand= data[1];
	// this.category = data[2];
	// }
	//

}
