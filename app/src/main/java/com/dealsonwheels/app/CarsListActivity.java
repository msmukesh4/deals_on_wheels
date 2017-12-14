package com.dealsonwheels.app;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dealsonwheels.app.adapters.CarListAdapter;
import com.dealsonwheels.app.api.APIClient;
import com.dealsonwheels.app.api.APIInterface;
import com.dealsonwheels.app.api.CarListAPIResponse;
import com.dealsonwheels.app.models.Car;
import com.dealsonwheels.app.models.CarListViewItem;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mukesh on 20/9/17.
 */

public class CarsListActivity extends AppCompatActivity implements CarListAdapter.CarListListener{

    private static final String TAG = "Cars List Activity";
    private RecyclerView recyclerViewCars;
    public static final String OLD_CARS = "old_cars";
    public static final String NEW_CARS = "new_cars";
    private Dialog getContactDialog;
    private CarListAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list_with_recycler);

        recyclerViewCars = (RecyclerView) findViewById(R.id.recycler_view_cars);

        String listType = getIntent().getStringExtra("type");
        if (null == listType)
            listType = OLD_CARS;
        if(listType != null){
            try {
                JSONObject params = new JSONObject();
                params.put("prdType",listType);
                params.put("Min_Price", getIntent().getStringExtra("min_price"));
                params.put("Max_Price", getIntent().getStringExtra("max_price"));
                params.put("Body_Type", getIntent().getStringExtra("body_type_id"));
                params.put("BrandId", getIntent().getStringExtra("brand_type_id"));
                fetchCarList(listType, params);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            Log.e(TAG, "onCreate: Invalid List Type parameter" );
            Toast.makeText(getApplicationContext(),R.string.general_error_msg,Toast.LENGTH_LONG).show();
        }

    }

    // fetch cars list from the server
    private void fetchCarList(String listType, JSONObject params) {
        try {

            APIInterface apiInterface = APIClient.getAPIService(Constants.LOG_LEVEL);
            Call<CarListAPIResponse> call = apiInterface.getCarList(params);
            call.enqueue(new Callback<CarListAPIResponse>() {
                @Override
                public void onResponse(Call<CarListAPIResponse> call, Response<CarListAPIResponse> response) {

                    Log.d("StaticDataManager", "onResponse: "+response.code());
                    Log.d(TAG, "onResponse: "+response.body().toString());
                    if (response.body().getCarList().size() > 0)
                        createList(response.body().getCarList());
                    else
                        Toast.makeText(getApplicationContext(),"No Cars Found with the specified data",Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<CarListAPIResponse> call, Throwable t) {
                    Log.e(TAG, "onFailure: "+t.getMessage());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void createList(ArrayList<Car> carArrayList) {
        if (carArrayList.size() > 0 ){
            adapter = new CarListAdapter(this,this);
            for (Car car : carArrayList)
                adapter.addCar(car);
            adapter.addAD(new Car(12,"Hello"));
            recyclerViewCars.setAdapter(adapter);

        }else {
            Log.e(TAG, "createList: no cars found");
        }
    }

    @Override
    public void onShowInterestClicked(CarListViewItem item) {
        Log.d(TAG, "onShowInterestClicked item: "+item.getCar().getProductId());
        getContactDialog = new Dialog(CarsListActivity.this);
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
                    sendUserContactNumber(etContactNumber.getText().toString());
                    getContactDialog.dismiss();

                }else
                    Toast.makeText(CarsListActivity.this,R.string.error_invalid_contact_number,Toast.LENGTH_LONG).show();
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
    private void sendUserContactNumber(String s) {

    }

    @Override
    public void onItemClicked(CarListViewItem item) {
        Intent intent = new Intent(CarsListActivity.this, CarDetailsActivity.class);
        intent.putExtra("product_id",String.valueOf(item.getCar().getProductId()));
        startActivity(intent);
    }

    @Override
    public void onAdClicked(CarListViewItem item) {

    }
}
