package com.dealsonwheels.app;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.dealsonwheels.app.api.APIClient;
import com.dealsonwheels.app.api.APIInterface;
import com.dealsonwheels.app.api.CarDetailsAPIResponse;
import com.dealsonwheels.app.api.CarListAPIResponse;
import com.dealsonwheels.app.car_details_fragment.CarDetailsPagerAdapter;
import com.dealsonwheels.app.models.Car;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mukesh on 10/10/17.
 */

public class CarDetailsActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{

    private SliderLayout carSliderLayout;
    private static final String TAG = "Home";
    private CarDetailsPagerAdapter mCarDetailsPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private Car mCar;
    private Dialog getContactDialog;
    private TextView tvCarName,tvPrice,tvModel,tvLocation,tvYear,tvKilometer,tvFuelType,tvTransmission;
    private Button btnShowInterest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);
        carSliderLayout = (SliderLayout) findViewById(R.id.carSlides);
        tvCarName = (TextView) findViewById(R.id.car_name);
        tvModel = (TextView) findViewById(R.id.model);
        tvPrice = (TextView) findViewById(R.id.price);
        tvLocation = (TextView) findViewById(R.id.location);
        tvYear = (TextView) findViewById(R.id.year);
        tvKilometer = (TextView) findViewById(R.id.kilometer);
        tvFuelType = (TextView) findViewById(R.id.fuel_type);
        tvTransmission = (TextView) findViewById(R.id.transmission);
        btnShowInterest = (Button) findViewById(R.id.btn_show_interest);

        mViewPager = (ViewPager) findViewById(R.id.car_details_view_pager);
        mTabLayout = (TabLayout) findViewById(R.id.car_details_tabs);

//        HashMap<String,Integer> url_maps = new HashMap<String, Integer>();
//        file_maps.put("one title",R.drawable.one);
//        file_maps.put("two title",R.drawable.two);
//        file_maps.put("three title",R.drawable.three);

        fetchCarDetails(getIntent().getStringExtra("product_id"));

        Toast.makeText(getApplicationContext(),getIntent().getStringExtra("product_id"),Toast.LENGTH_SHORT).show();

    }

    private void fetchCarDetails(String productId) {
        Toast.makeText(getApplicationContext(),productId,Toast.LENGTH_SHORT).show();

        try {

            APIInterface apiInterface = APIClient.getAPIService(Constants.LOG_LEVEL);
            Call<Object> call = apiInterface.getCarDetails(Integer.valueOf(productId));
            call.enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {
                    if (response.isSuccessful()) {
                        Log.d(TAG, "onResponse: " + response.body().toString());
                        try {
                            JSONObject jsonResponse = new JSONObject(new Gson().toJson(response.body()));
                            saveCarDetails(jsonResponse.getJSONObject("Data"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

//                        saveCarDetails(response.body().getCarDetails());
                    }else {
                        Log.e(TAG, "onResponse: "+response.message());
                    }
                }

                @Override
                public void onFailure(Call<Object> call, Throwable t) {
                    Log.e(TAG, "onFailure: "+t.getMessage());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }


        String data = "{\"data\": {\"ProductId\": 1, \"ProductName\": \"Bugatti Veyron\",\"YearOfMake\": \"1998\",\"Kilometer\": \"2000\",\"FuelType\": \"Petrol\",\"Transmission\": \"Automatic\",\"SoldBy\": \"ABC\",\"NoOfOwner\": null,\"RegisteredAt\": null,\"Insurance\": null,\"LifeTimeTax\": null,\"ProfileID\": 1,\"PrimaryImageURL\": \"http://kitcarempire.com/wp-content/gallery/bugatti-veyron-toy-car/bugatti-veyron-replica-small-mini-08.jpg\",\"FirstName\": \"Mohan \",\"MiddileName\": \"Motore\",\"LastName\": \"Ltd\",\"Location\": \"Kolkata\",\"Chanel\": \"App\", \"ProfileImageURL\": null, \"Price\": 40000, \"OverviewData\": [{\"one\":\"one data\"}, {\"two\": \"two data\"}], \"FeaturesData\": [{\"one feature\":\"one data\"}, {\"two feature\": \"two data\"}], \"SpecificationData\": [{\"one spec\":\"one data\"}, {\"two spec\": \"two data\"}] }}";
//        saveCarDetails(data);
    }



    private void saveCarDetails(JSONObject rootJson) {
        try {
            JSONObject carJson = (JSONObject) rootJson.optJSONArray("Product").get(0);
            Log.d(TAG, "saveCarDetails: "+carJson.toString());
            mCar = new Car(carJson.optInt("ProductId"), carJson.optString("ProductName"),carJson.optInt("YearOfMake"),carJson.optInt("Kilometer"),
                    carJson.optString("FuelType"), carJson.optString("Transmission"), carJson.optString("SoldBy"), carJson.optString("NoOfOwner"), carJson.optString("RegisteredAt"),
                    carJson.optString("Insurance"), carJson.optString("LifeTimeTax"), carJson.optInt("ProfileID"), carJson.optString("PrimaryImageURL"),
                    carJson.optString("FirstName"),carJson.optString("MiddleName"),carJson.optString("LastName"),carJson.optString("Location"),carJson.optString("Channel"),
                    carJson.optString("ProfileImageURL"), carJson.optLong("Price"),toStringArray(carJson.optJSONArray("extraImages")));



            Log.d(TAG, "saveCarDetails: "+mCar);
            SetUpUIWithData(rootJson);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

//    private void saveCarDetails(String data) {
//        try {
//            Log.d(TAG, "saveCarDetails: "+data);
//            JSONObject carJson = new JSONObject(data).optJSONObject("data");
//
//            mCar = new Car(carJson.optInt("ProductId"), carJson.optString("ProductName"),carJson.optInt("YearOfMake"),carJson.optInt("Kilometer"),
//                    carJson.optString("FuelType"), carJson.optString("Transmission"), carJson.optString("SoldBy"), carJson.optString("NoOfOwner"), carJson.optString("RegisteredAt"),
//                    carJson.optString("Insurance"), carJson.optString("LifeTimeTax"), carJson.optInt("ProfileID"), carJson.optString("PrimaryImageURL"),
//                    carJson.optString("FirstName"),carJson.optString("MiddleName"),carJson.optString("LastName"),carJson.optString("Location"),carJson.optString("Channel"),
//                    carJson.optString("ProfileImageURL"), carJson.optLong("Price"));
//            Log.d(TAG, "saveCarDetails: "+mCar);
//            SetUpUIWithData(carJson);
//        }catch (JSONException e){
//            e.printStackTrace();
//        }
//    }

    private void SetUpUIWithData(JSONObject rootJson) throws JSONException {

        // slide show of images
        int image_counter = 1;
        HashMap<String,String> url_maps = new HashMap<String, String>();
        if (null != mCar.getPrimaryImageUrl())
            if (!mCar.getPrimaryImageUrl().equalsIgnoreCase("null") && mCar.getPrimaryImageUrl().length() > 0)
                url_maps.put(String.valueOf(image_counter++),mCar.getPrimaryImageUrl());
        if (null != mCar.getExtraImages()) {
            if (mCar.getExtraImages().length > 0) {
                for (int i = 0; i < mCar.getExtraImages().length; i++)
                    url_maps.put(String.valueOf(image_counter++), mCar.getExtraImages()[i]);
            } else if (url_maps.size() == 0)
                url_maps.put(String.valueOf(image_counter++), Constants.ERROR_IMAGE);
        }else if (url_maps.size() == 0)
            url_maps.put(String.valueOf(image_counter++),Constants.ERROR_IMAGE );

//        url_maps.put(String.valueOf(image_counter++), "http://kitcarempire.com/wp-content/gallery/bugatti-veyron-toy-car/bugatti-veyron-replica-small-mini-08.jpg");
//        url_maps.put(String.valueOf(image_counter++), "http://www.clker.com/cliparts/1/2/c/1/13975770108622529252013-lamborghini-gallardo-lp570-4-performante-editione-tecnica-photos_1-hi.png");
//        url_maps.put(String.valueOf(image_counter++), "https://cars.usnews.com/static/images/article/201510/125272/MINIP90139264_highRes.jpg");


        for(String name : url_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            carSliderLayout.addSlider(textSliderView);
        }
        carSliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        carSliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        carSliderLayout.stopAutoCycle();
        carSliderLayout.setmShowText(false);
//        imageSlider.setCustomAnimation(new DescriptionAnimation());
//        imageSlider.setDuration(0);
        carSliderLayout.addOnPageChangeListener(this);

        btnShowInterest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestContactNumberDialog(mCar.getProductId());
            }
        });

        // primary data
        tvCarName.setText(mCar.getProductName());
        tvModel.setText("B12-1mms2");
        tvPrice.setText(String.valueOf(mCar.getPrice()));
        tvLocation.setText(mCar.getLocation());

        // secondary data
        tvYear.setText(String.valueOf(mCar.getYearOfMake()));
        tvKilometer.setText(String.valueOf(mCar.getKilometer()));
        tvFuelType.setText(mCar.getFuelType());
        tvTransmission.setText(mCar.getTransmission());

        // view pager
        mCarDetailsPagerAdapter = new CarDetailsPagerAdapter(getSupportFragmentManager(),getApplicationContext(),rootJson);
        mViewPager.setAdapter(mCarDetailsPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    private void requestContactNumberDialog(final int productId) {

        getContactDialog = new Dialog(this);
        getContactDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getContactDialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        getContactDialog.setContentView(R.layout.dialog_request_contact_number);

        Button btnConfirmNumber = (Button) getContactDialog.findViewById(R.id.btn_confirm_number);
        RelativeLayout layoutClose = (RelativeLayout) getContactDialog.findViewById(R.id.layout_close);
        final EditText etContactNumber = (EditText) getContactDialog.findViewById(R.id.et_contact_number);

        getContactDialog.setCancelable(true);
        getContactDialog.setCanceledOnTouchOutside(true);

        getContactDialog.show();

        btnConfirmNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isContactNumberValid(etContactNumber.getText().toString())) {
                    sendUserContactNumber(etContactNumber.getText().toString(), productId);
                    getContactDialog.dismiss();

                }else
                    Toast.makeText(getApplicationContext(),R.string.error_invalid_contact_number,Toast.LENGTH_LONG).show();
            }
        });

        layoutClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
                getContactDialog.dismiss();
            }
        });
    }

    // send data to server through API
    private void sendUserContactNumber(String number,int productId) {

    }

    @Override
    protected void onStop() {
        carSliderLayout.stopAutoCycle();
        Log.e(TAG, "onStop: ");
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        ArrayList<String> items = new ArrayList<String>();
        Toast.makeText(this,slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
        if (null != mCar.getPrimaryImageUrl())
            if (!mCar.getPrimaryImageUrl().equalsIgnoreCase("null") && mCar.getPrimaryImageUrl().length() > 0)
                items.add(mCar.getPrimaryImageUrl());

        if (null != mCar.getExtraImages())
            if (mCar.getExtraImages().length > 0)
                for (int i = 0; i < mCar.getExtraImages().length ; i++)
                    items.add(mCar.getExtraImages()[i]);

        if (items.size() == 0 )
            Toast.makeText(getApplicationContext(),"There are no images to be displayed",Toast.LENGTH_LONG).show();
        else{
            Intent intent = new Intent(getApplicationContext(),CarImageSlidesActivity.class);
            intent.putStringArrayListExtra("car_images",items);
            startActivity(intent);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        Log.e("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}

    public String[] toStringArray(JSONArray array) {
        if(array==null)
            return null;

        String[] arr=new String[array.length()];
        for(int i=0; i<arr.length; i++) {
            arr[i]=array.optString(i);
        }
        return arr;
    }
}
