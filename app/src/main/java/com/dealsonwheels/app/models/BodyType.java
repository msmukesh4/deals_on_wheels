package com.dealsonwheels.app.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mukesh on 4/11/17.
 */

public class BodyType implements Serializable{
    @SerializedName("ID") public int id;
    @SerializedName("ProductBodyType1") public String name;
    @SerializedName("ImageUrl") public String url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQv-A0O1AIOkXTRk_SAPBk3uGRCNpimOd_kB7AhkL6pdz72OQ1VhA";

    public BodyType(int id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    @Override
    public String toString() {
        return "BodyType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
