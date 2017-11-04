package com.dealsonwheels.app.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by mukesh on 4/11/17.
 */

public class StaticData {
    public ArrayList<Brand> brandList;
    public ArrayList<BodyType> bodyTypeList;
    public ArrayList<Car> cars;
    public String[] cites;

    public void init(){
        brandList = new ArrayList<>();
        bodyTypeList = new ArrayList<>();
        cars = new ArrayList<>();
    }

    public void setCities(JSONArray jsonArray){
        cites = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                cites[i] = jsonArray.get(i).toString();
            }catch (JSONException e){
                Log.e("Static data model", "setCarNames: "+e.getMessage());
            }

        }
    }
}
