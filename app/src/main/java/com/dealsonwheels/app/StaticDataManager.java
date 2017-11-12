package com.dealsonwheels.app;

import android.util.Log;

import com.dealsonwheels.app.api.APIClient;
import com.dealsonwheels.app.api.APIInterface;
import com.dealsonwheels.app.api.InitAPIResponse;
import com.dealsonwheels.app.models.BodyType;
import com.dealsonwheels.app.models.Brand;
import com.dealsonwheels.app.models.Car;
import com.dealsonwheels.app.models.StaticData;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.dealsonwheels.app.Constants.currentUser;

/**
 * Created by mukesh on 4/11/17.
 */

public class StaticDataManager {


    public static void requestStaticData(){
//        JSONObject jsonObject = null;
        currentUser.staticData.init();
        Log.d("StaticDataManager", "requestStaticData: ");
        APIInterface apiInterface = APIClient.getAPIService(Constants.LOG_LEVEL);
        Call<InitAPIResponse> call = apiInterface.getInitData();
        call.enqueue(new Callback<InitAPIResponse>() {
            @Override
            public void onResponse(Call<InitAPIResponse> call, Response<InitAPIResponse> response) {
                Log.d("StaticDataManager", "onResponse: "+response.code());
                Constants.currentUser.staticData = response.body().getStaticData();
                Constants.currentUser.staticData.modifyData();
                Log.d("StaticDataManager", "onResponse: "+Constants.currentUser.staticData.toString());

            }

            @Override
            public void onFailure(Call<InitAPIResponse> call, Throwable t) {
                Log.e("StaticDataManager", "onFailure: "+t.getMessage() );
            }
        });



//        String demoData = "{ \"data\": {\n" +
//                "            \"city\": [\"Kolkata\", \"Jharkhand\"],\n" +
//                "            \"cars\" : [{ \"product_id\" : \"16\", \"product_name\" : \"Maruti suziki\"},{ \"product_id\" : \"12\", \"product_name\" : \"Lemborgini\"},{ \"product_id\" : \"110\", \"product_name\" : \"Lemborgini gallardo\"}" +
//                "],\n" +
//                "     \"body_type\" :  [{ \"id\" : \"92\", \"name\" : \"stream line\"  , \"image_url\" : \"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQv-A0O1AIOkXTRk_SAPBk3uGRCNpimOd_kB7AhkL6pdz72OQ1VhA\"},\n" +
//                "            { \"id\" : \"12\", \"name\" : \"stream line\"  , \"image_url\" : \"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQv-A0O1AIOkXTRk_SAPBk3uGRCNpimOd_kB7AhkL6pdz72OQ1VhA\"}\n" +
//                "],\n" +
//                "    \"brand\": [ { \"id\" : \"12\", \"name\" : \"maruti\"  , \"image_url\" : \"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQv-A0O1AIOkXTRk_SAPBk3uGRCNpimOd_kB7AhkL6pdz72OQ1VhA\"},\n" +
//                "            { \"id\" : \"1\", \"name\" : \"maruti\"  , \"image_url\" : \"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQv-A0O1AIOkXTRk_SAPBk3uGRCNpimOd_kB7AhkL6pdz72OQ1VhA\"},\n" +
//                "            { \"id\" : \"122\", \"name\" : \"maruti\"  , \"image_url\" : \"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQv-A0O1AIOkXTRk_SAPBk3uGRCNpimOd_kB7AhkL6pdz72OQ1VhA\"}\n" +
//                "]\n" +
//                "            },\n" +
//                "                \"success\" : true\n" +
//                "            }";
//
//        try {
//
//            jsonObject = new JSONObject(demoData);
//            for (int i = 0; i < jsonObject.optJSONObject("data").optJSONArray("brand").length(); i++){
//                JSONObject brandJson = new JSONObject(jsonObject.optJSONObject("data").optJSONArray("brand").get(i).toString());
//                currentUser.staticData.brandList.add(new Brand(brandJson.optInt("id"),brandJson.getString("name"),brandJson.optString("image_url")));
//            }
//
//            for (int i = 0; i < jsonObject.optJSONObject("data").optJSONArray("body_type").length(); i++){
//                JSONObject bodyTypeJson = new JSONObject(jsonObject.optJSONObject("data").optJSONArray("body_type").get(i).toString());
//                currentUser.staticData.bodyTypeList.add(new BodyType(bodyTypeJson.optInt("id"),bodyTypeJson.getString("name"),bodyTypeJson.optString("image_url")));
//            }
//
//            String cars[] = new String[jsonObject.optJSONObject("data").optJSONArray("cars").length()];
//            for (int i = 0; i < jsonObject.optJSONObject("data").optJSONArray("cars").length(); i++){
//
//                JSONObject carJson = new JSONObject(jsonObject.optJSONObject("data").optJSONArray("cars").get(i).toString());
//                currentUser.staticData.cars.add(new Car(carJson.optInt("product_id"),carJson.optString("product_name")));
//                cars[i] = carJson.optString("product_name");
//            }
//
//            Constants.CARS = cars;
//
////            currentUser.staticData.setCities(jsonObject.optJSONObject("data").optJSONArray("city"));
//        }catch (JSONException e){}

//        return jsonObject;
    }

    public static void parseAndSaveStaticData(JSONObject staticJsonData){



    }

}
