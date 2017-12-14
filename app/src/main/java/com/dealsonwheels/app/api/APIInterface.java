package com.dealsonwheels.app.api;

import com.dealsonwheels.app.Constants;
import com.dealsonwheels.app.models.Car;
import com.dealsonwheels.app.models.StaticData;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by mukesh on 11/11/17.
 */

public interface APIInterface {

    @GET(Constants.INITIAL_DATA)
    Call<InitAPIResponse> getInitData();

    @POST(Constants.PRODUCT_LIST)
    Call<CarListAPIResponse> getCarList(@Body JSONObject params);

    @POST(Constants.PRODUCT_DETAILS)
    Call<CarDetailsAPIResponse> getCarDetails(@Query("ProductId") int id);
}
