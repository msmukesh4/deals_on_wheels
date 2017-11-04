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
import com.dealsonwheels.app.car_details_fragment.CarDetailsPagerAdapter;
import com.dealsonwheels.app.models.Car;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;

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
        String data = "{\"data\": {\"ProductId\": 1, \"ProductName\": \"Bugatti Veyron\",\"YearOfMake\": \"1998\",\"Kilometer\": \"2000\",\"FuelType\": \"Petrol\",\"Transmission\": \"Automatic\",\"SoldBy\": \"ABC\",\"NoOfOwner\": null,\"RegisteredAt\": null,\"Insurance\": null,\"LifeTimeTax\": null,\"ProfileID\": 1,\"PrimaryImageURL\": \"http://kitcarempire.com/wp-content/gallery/bugatti-veyron-toy-car/bugatti-veyron-replica-small-mini-08.jpg\",\"FirstName\": \"Mohan \",\"MiddileName\": \"Motore\",\"LastName\": \"Ltd\",\"Location\": \"Kolkata\",\"Chanel\": \"App\", \"ProfileImageURL\": null, \"Price\": 40000, \"OverviewData\": [{\"one\":\"one data\"}, {\"two\": \"two data\"}], \"FeaturesData\": [{\"one feature\":\"one data\"}, {\"two feature\": \"two data\"}], \"SpecificationData\": [{\"one spec\":\"one data\"}, {\"two spec\": \"two data\"}] }}";
        saveCarDetails(data);
    }

    private void saveCarDetails(String data) {
        try {
            Log.d(TAG, "saveCarDetails: "+data);
            JSONObject carJson = new JSONObject(data).optJSONObject("data");

            mCar = new Car(carJson.optInt("ProductId"), carJson.optString("ProductName"),carJson.optInt("YearOfMake"),carJson.optInt("Kilometer"),
                    carJson.optString("FuelType"), carJson.optString("Transmission"), carJson.optString("SoldBy"), carJson.optString("NoOfOwner"), carJson.optString("RegisteredAt"),
                    carJson.optString("Insurance"), carJson.optString("LifeTimeTax"), carJson.optInt("ProfileID"), carJson.optString("PrimaryImageURL"),
                    carJson.optString("FirstName"),carJson.optString("MiddleName"),carJson.optString("LastName"),carJson.optString("Location"),carJson.optString("Channel"),
                    carJson.optString("ProfileImageURL"), carJson.optLong("Price"));
            Log.d(TAG, "saveCarDetails: "+mCar);
            SetUpUIWithData(carJson);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void SetUpUIWithData(JSONObject carJson){

        // slide show of images
        HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("1", "http://kitcarempire.com/wp-content/gallery/bugatti-veyron-toy-car/bugatti-veyron-replica-small-mini-08.jpg");
        url_maps.put("2", "http://www.clker.com/cliparts/1/2/c/1/13975770108622529252013-lamborghini-gallardo-lp570-4-performante-editione-tecnica-photos_1-hi.png");
        url_maps.put("3", "https://cars.usnews.com/static/images/article/201510/125272/MINIP90139264_highRes.jpg");


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
        mCarDetailsPagerAdapter = new CarDetailsPagerAdapter(getSupportFragmentManager(),getApplicationContext(),carJson);
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
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(this,slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(),CarImageSlidesActivity.class));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        Log.e("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}
}
