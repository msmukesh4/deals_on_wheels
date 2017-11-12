package com.dealsonwheels.app.api;

import com.dealsonwheels.app.models.StaticData;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mukesh on 12/11/17.
 */

public class InitAPIResponse extends BaseAPIResponse {
    @SerializedName("Data") StaticData staticData;

    @Override
    public String toString() {
        return "InitAPIResponse{" +
                "staticData=" + staticData +
                '}';
    }

    public StaticData getStaticData() {
        return staticData;
    }

    public void setStaticData(StaticData staticData) {
        this.staticData = staticData;
    }
}
