package com.dealsonwheels.app.api;

import com.dealsonwheels.app.models.Car;
import com.dealsonwheels.app.models.StaticData;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by mukesh on 12/11/17.
 */

public class CarListAPIResponse extends BaseAPIResponse {
    @SerializedName("Data") ArrayList<Car> cars;

    @Override
    public String toString() {
        return "CarListAPIResponse{" +
                "cars=" + cars +
                '}';
    }

    public ArrayList<Car> getCarList() {
        return cars;
    }

    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
    }
}
