package com.dealsonwheels.app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dealsonwheels.app.models.Car;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by mukesh on 20/9/17.
 */

public class CarsListActivity extends AppCompatActivity {

    private static final String TAG = "Cars List Activity";
    private ListView listViewCars;
    public static final String OLD_CARS = "old_cars";
    public static final String NEW_CARS = "new_cars";
    private ArrayList<Car> carArrayList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);

        listViewCars = (ListView) findViewById(R.id.listview_cars);

        String listType = getIntent().getStringExtra("type");
        if(listType != null){

            fetchCarList(listType);

        }else {
            Log.e(TAG, "onCreate: Invalid List Type parameter" );
        }

    }

    // fetch cars list from the server
    private void fetchCarList(String listType) {
        try {
            String data = "{\"data\":[{\"ProductId\": 1, \"ProductName\": \"Mercedes Benz C-Class\",\"YearOfMake\": \"1998\",\"Kilometer\": \"2000\",\"FuelType\": \"Petrol\",\"Transmission\": \"Automatic\",\"SoldBy\": \"ABC\",\"NoOfOwner\": null,\"RegisteredAt\": null,\"Insurance\": null,\"LifeTimeTax\": null,\"ProfileID\": 1,\"PrimaryImageURL\": null,\"FirstName\": \"Mohan \",\"MiddileName\": \"Motore\",\"LastName\": \"Ltd\",\"Location\": \"Kolkata\",\"Chanel\": \"App\", \"ProfileImageURL\": null },{\"ProductId\": 1, \"ProductName\": \"Second Product name\",\"YearOfMake\": \"1998\",\"Kilometer\": \"2000\",\"FuelType\": \"Petrol\",\"Transmission\": \"Automatic\",\"SoldBy\": \"ABC\",\"NoOfOwner\": null,\"RegisteredAt\": null,\"Insurance\": null,\"LifeTimeTax\": null,\"ProfileID\": 1,\"PrimaryImageURL\": null,\"FirstName\": \"Mohan \",\"MiddileName\": \"Motore\",\"LastName\": \"Ltd\",\"Location\": \"Kolkata\",\"Chanel\": \"App\", \"ProfileImageURL\": null }]}";
            JSONObject jsonObject = new JSONObject(data);

            parseAndStoreData(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseAndStoreData(JSONObject jsonObject) {
        carArrayList = new ArrayList<>();
        for (int i = 0; i < jsonObject.optJSONArray("data").length(); i++) {
            JSONObject carJson = jsonObject.optJSONArray("data").optJSONObject(i);
            Car tempCar = new Car(carJson.optInt("ProductId"), carJson.optString("ProductName"),carJson.optInt("YearOfMake"),carJson.optInt("Kilometer"),
                    carJson.optString("FuelType"), carJson.optString("Transmission"), carJson.optString("SoldBy"), carJson.optString("NoOfOwner"), carJson.optString("RegisteredAt"),
                    carJson.optString("Insurance"), carJson.optString("LifeTimeTax"), carJson.optInt("ProfileID"), carJson.optString("PrimaryImageURL"),
                    carJson.optString("FirstName"),carJson.optString("MiddleName"),carJson.optString("LastName"),carJson.optString("Location"),carJson.optString("Channel"),carJson.optString("ProfileImageURL"));
            carArrayList.add(tempCar);
        }
        createListView(carArrayList);
    }

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
        public View getView(int position, View convertView, ViewGroup parent) {
            CarListHolder carListHolder;
            if (convertView == null){
                convertView = inflater.inflate(R.layout.item_car_list,null);
                carListHolder = new CarListHolder(convertView);
                convertView.setTag(carListHolder);
            }else {
                carListHolder = (CarListHolder) convertView.getTag();
            }

            carListHolder.tvCarName.setText(carArrayList.get(position).getProductName());

            return convertView;
        }
    }

    private class CarListHolder{
        ImageView ivCarPrimaryImage;
        TextView tvCarName;

        CarListHolder(View view){
            ivCarPrimaryImage = (ImageView) view.findViewById(R.id.primary_image);
            tvCarName = (TextView) view.findViewById(R.id.car_name);
        }
    }
}
