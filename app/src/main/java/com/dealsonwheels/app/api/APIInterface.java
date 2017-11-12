package com.dealsonwheels.app.api;

import com.dealsonwheels.app.Constants;
import com.dealsonwheels.app.models.StaticData;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by mukesh on 11/11/17.
 */

public interface APIInterface {

    @GET(Constants.INITIAL_DATA)
    Call<InitAPIResponse> getInitData();


}