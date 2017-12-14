package com.dealsonwheels.app.models;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

/**
 * Created by mukesh on 13/12/17.
 */

public class CarDetails {
    @SerializedName("Product")
    public Car car;

    @SerializedName("OverviewData")
    public JSONObject overviewJson;

    @SerializedName("SpecificationData")
    public JSONObject specificationJson;

}
