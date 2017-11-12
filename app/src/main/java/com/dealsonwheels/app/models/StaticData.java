package com.dealsonwheels.app.models;

import android.util.Log;

import com.dealsonwheels.app.Constants;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mukesh on 4/11/17.
 */

public class StaticData implements Serializable{
    @SerializedName("Brands") public ArrayList<Brand> brandList;
    @SerializedName("BodyTypes") public ArrayList<BodyType> bodyTypeList;
    @SerializedName("Cars") public ArrayList<Car> cars;
    @SerializedName("Cities") public ArrayList<City> cites;

    public void init(){
        brandList = new ArrayList<>();
        bodyTypeList = new ArrayList<>();
        cars = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "StaticData{" +
                "brandList=" + brandList +
                ", bodyTypeList=" + bodyTypeList +
                ", cars=" + cars +
                ", cites=" + cites +
                '}';
    }

    public ArrayList<Brand> getBrandList() {
        return brandList;
    }

    public void setBrandList(ArrayList<Brand> brandList) {
        this.brandList = brandList;
    }

    public ArrayList<BodyType> getBodyTypeList() {
        return bodyTypeList;
    }

    public void setBodyTypeList(ArrayList<BodyType> bodyTypeList) {
        this.bodyTypeList = bodyTypeList;
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
    }

    public ArrayList<City> getCites() {
        return cites;
    }

    public void setCites(ArrayList<City> cites) {
        this.cites = cites;
    }

    public void modifyData(){
        Constants.LOCATION = new String[cites.size()];
        for (int i = 0; i < cites.size(); i++) {
            try {
                Constants.LOCATION[i] = cites.get(i).name;
            }catch (Exception e){
                Log.e("Static data model", "setCarNames: "+e.getMessage());
            }
        }

        Constants.CARS = new String[cars.size()];
        for (int i = 0; i < cars.size(); i++) {
            Constants.CARS[i] = cars.get(i).productName;
        }

    }
}
