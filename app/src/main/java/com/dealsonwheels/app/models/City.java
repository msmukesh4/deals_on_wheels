package com.dealsonwheels.app.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mukesh on 12/11/17.
 */

public class City implements Serializable {
    @SerializedName("ID") public int id;
    @SerializedName("CityName") public String name;

    public City(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
