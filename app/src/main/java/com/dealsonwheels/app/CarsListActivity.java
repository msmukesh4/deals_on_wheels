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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dealsonwheels.app.api.APIClient;
import com.dealsonwheels.app.api.APIInterface;
import com.dealsonwheels.app.api.CarListAPIResponse;
import com.dealsonwheels.app.models.Car;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mukesh on 20/9/17.
 */

public class CarsListActivity extends AppCompatActivity {

    private static final String TAG = "Cars List Activity";
    private ListView listViewCars;
    public static final String OLD_CARS = "old_cars";
    public static final String NEW_CARS = "new_cars";
    private ArrayList<Car> carArrayList;
    private Dialog getContactDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);

        listViewCars = (ListView) findViewById(R.id.listview_cars);

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
                        createListView(response.body().getCarList());
                    else
                        Toast.makeText(getApplicationContext(),"No Cars Found with the specified data",Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<CarListAPIResponse> call, Throwable t) {
                    Log.e(TAG, "onFailure: "+t.getMessage());
                }
            });



//            String data = "{\"data\":[{\"ProductId\": 1, \"ProductName\": \"Bugatti Veyron\",\"YearOfMake\": \"1998\",\"Kilometer\": \"2000\",\"FuelType\": \"Petrol\",\"Transmission\": \"Automatic\",\"SoldBy\": \"ABC\",\"NoOfOwner\": null,\"RegisteredAt\": null,\"Insurance\": null,\"LifeTimeTax\": null,\"ProfileID\": 1,\"PrimaryImageURL\": \"http://kitcarempire.com/wp-content/gallery/bugatti-veyron-toy-car/bugatti-veyron-replica-small-mini-08.jpg\",\"FirstName\": \"Mohan \",\"MiddileName\": \"Motore\",\"LastName\": \"Ltd\",\"Location\": \"Kolkata\",\"Chanel\": \"App\", \"ProfileImageURL\": null, \"Price\": 40000 },{\"ProductId\": 2, \"ProductName\": \"Lemborghini Gallordo\",\"YearOfMake\": \"1998\",\"Kilometer\": \"2000\",\"FuelType\": \"Petrol\",\"Transmission\": \"Automatic\",\"SoldBy\": \"ABC\",\"NoOfOwner\": null,\"RegisteredAt\": null,\"Insurance\": null,\"LifeTimeTax\": null,\"ProfileID\": 1,\"PrimaryImageURL\": \"http://www.clker.com/cliparts/1/2/c/1/13975770108622529252013-lamborghini-gallardo-lp570-4-performante-editione-tecnica-photos_1-hi.png\",\"FirstName\": \"Mohan \",\"MiddileName\": \"Motore\",\"LastName\": \"Ltd\",\"Location\": \"Kolkata\",\"Chanel\": \"App\", \"ProfileImageURL\": null, \"Price\": 100000 },{\"ProductId\": 3, \"ProductName\": \"Maruti Car\",\"YearOfMake\": \"1998\",\"Kilometer\": \"2000\",\"FuelType\": \"Petrol\",\"Transmission\": \"Automatic\",\"SoldBy\": \"ABC\",\"NoOfOwner\": null,\"RegisteredAt\": null,\"Insurance\": null,\"LifeTimeTax\": null,\"ProfileID\": 1,\"PrimaryImageURL\": \"https://cars.usnews.com/static/images/article/201510/125272/MINIP90139264_highRes.jpg\",\"FirstName\": \"Mohan \",\"MiddileName\": \"Motore\",\"LastName\": \"Ltd\",\"Location\": \"Kolkata\",\"Chanel\": \"App\", \"ProfileImageURL\": null, \"Price\": 100000 }]}";
//            JSONObject jsonObject = new JSONObject(data);

//            http://www.clker.com/cliparts/1/2/c/1/13975770108622529252013-lamborghini-gallardo-lp570-4-performante-editione-tecnica-photos_1-hi.png

//            parseAndStoreData(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private void parseAndStoreData(JSONObject jsonObject) {
//        carArrayList = new ArrayList<>();
//        for (int i = 0; i < jsonObject.optJSONArray("data").length(); i++) {
//            JSONObject carJson = jsonObject.optJSONArray("data").optJSONObject(i);
//            Car tempCar = new Car(carJson.optInt("ProductId"), carJson.optString("ProductName"),carJson.optInt("YearOfMake"),carJson.optInt("Kilometer"),
//                    carJson.optString("FuelType"), carJson.optString("Transmission"), carJson.optString("SoldBy"), carJson.optString("NoOfOwner"), carJson.optString("RegisteredAt"),
//                    carJson.optString("Insurance"), carJson.optString("LifeTimeTax"), carJson.optInt("ProfileID"), carJson.optString("PrimaryImageURL"),
//                    carJson.optString("FirstName"),carJson.optString("MiddleName"),carJson.optString("LastName"),carJson.optString("Location"),carJson.optString("Channel"),
//                    carJson.optString("ProfileImageURL"), carJson.optLong("Price"));
//            carArrayList.add(tempCar);
//        }
//        createListView(carArrayList);
//    }

    private void createListView(ArrayList<Car> carArrayList) {
        if (carArrayList.size() > 0 ){
            listViewCars.setAdapter(new CarBaseAdapter(carArrayList,CarsListActivity.this));
            listViewCars.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getApplicationContext(),"position "+position,Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            Log.e(TAG, "createListView: no cars found");
        }
    }

    private class CarBaseAdapter extends BaseAdapter{

        private ArrayList<Car> carArrayList;
        private Context context;
        private LayoutInflater inflater;

        public CarBaseAdapter(ArrayList<Car> carArrayList, Context context){
            this.carArrayList = carArrayList;
            this.context = context;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return carArrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final CarListHolder carListHolder;
            if (convertView == null){
                convertView = inflater.inflate(R.layout.item_car_list,null);
                carListHolder = new CarListHolder(convertView);
                convertView.setTag(carListHolder);
            }else {
                carListHolder = (CarListHolder) convertView.getTag();
            }


            // image load with progress bar
            Target target = new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    Log.d(TAG, "onBitmapLoaded: ");
                    carListHolder.ivCarPrimaryImage.setImageBitmap(bitmap);
                    carListHolder.progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {
                    Log.d(TAG, "onBitmapFailed: ");
                    carListHolder.progressBar.setVisibility(View.GONE);
                    carListHolder.ivCarPrimaryImage.setImageDrawable(context.getResources().getDrawable(R.drawable.blank_image_car));
                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {
                    Log.d(TAG, "onPrepareLoad: ");
                    carListHolder.progressBar.setVisibility(View.VISIBLE);
                }
            };

            Picasso.with(context).load("http://www.clker.com/cliparts/1/2/c/1/13975770108622529252013-lamborghini-gallardo-lp570-4-performante-editione-tecnica-photos_1-hi.png")
                    .error(R.drawable.blank_image_car)
                    .into(target);
//
//            Picasso.with(context).load(carArrayList.get(position).getPrimaryImageUrl())
//                    .error(R.drawable.blank_image_car)
//                    .into(target);
            carListHolder.ivCarPrimaryImage.setTag(target);


            carListHolder.tvCarName.setText(carArrayList.get(position).getProductName());
            carListHolder.tvPrice.setText(String.valueOf(carArrayList.get(position).getPrice()));
            carListHolder.tvKilometer.setText(String.valueOf(carArrayList.get(position).getKilometer()));
            carListHolder.tvFuelType.setText(carArrayList.get(position).getFuelType());
            carListHolder.tvYear.setText(String.valueOf(carArrayList.get(position).getYearOfMake()));
            carListHolder.tvSellerName.setText(carArrayList.get(position).getSoldBy());
            carListHolder.tvLocation.setText(carArrayList.get(position).getLocation());

            carListHolder.btnShowInterest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,carArrayList.get(position).getProductName(),Toast.LENGTH_SHORT).show();
                    requestContactNumberDialog(position);
                }
            });

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), CarDetailsActivity.class);
                    intent.putExtra("product_id",String.valueOf(carArrayList.get(position).getProductId()));
                    startActivity(intent);
                }
            });


            return convertView;
        }

        private void requestContactNumberDialog(int position) {

            Log.d(TAG, "requestContactNumberDialog position: "+position);
            getContactDialog = new Dialog(context);
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
                        Toast.makeText(context,R.string.error_invalid_contact_number,Toast.LENGTH_LONG).show();
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
    }

    private class CarListHolder{
        ImageView ivCarPrimaryImage;
        ProgressBar progressBar;
        TextView tvCarName,tvPrice,tvKilometer,tvFuelType,tvYear,tvSellerName,tvLocation;
        Button btnShowInterest;

        CarListHolder(View view){
            ivCarPrimaryImage = (ImageView) view.findViewById(R.id.primary_image);
            tvCarName = (TextView) view.findViewById(R.id.car_name);
            tvPrice = (TextView) view.findViewById(R.id.price);
            tvKilometer = (TextView) view.findViewById(R.id.kilometer);
            tvFuelType = (TextView) view.findViewById(R.id.fuel_type);
            tvYear = (TextView) view.findViewById(R.id.year);
            tvSellerName = (TextView) view.findViewById(R.id.seller_name);
            tvLocation = (TextView) view.findViewById(R.id.location);
            btnShowInterest = (Button) view.findViewById(R.id.btn_show_interest);
            progressBar = (ProgressBar) view.findViewById(R.id.image_progress);
        }
    }
}
