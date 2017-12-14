package com.dealsonwheels.app.api;

import com.dealsonwheels.app.models.Car;
import com.dealsonwheels.app.models.CarDetails;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mukesh on 12/11/17.
 */

public class CarDetailsAPIResponse extends BaseAPIResponse {
    @SerializedName("Data")
    JSONObject carDetails;

    @Override
    public String toString() {
        return "CarListAPIResponse{" +
                "cars=" + carDetails +
                '}';
    }

    public JSONObject getCarDetails() {
        return carDetails;
    }

    public void setCarDetails(JSONObject carDetails) {
        this.carDetails = carDetails;
    }
}
