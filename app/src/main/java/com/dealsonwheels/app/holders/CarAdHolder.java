package com.dealsonwheels.app.holders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dealsonwheels.app.R;
import com.dealsonwheels.app.adapters.CarListAdapter;
import com.dealsonwheels.app.models.CarListViewItem;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * Created by mukesh on 13/12/17.
 */

public class CarAdHolder extends RecyclerView.ViewHolder{
    private static final String TAG = "Car List Holder";
    ImageView ivCarPrimaryImage;
    ProgressBar progressBar;
    TextView tvCarName,tvPrice,tvKilometer,tvFuelType,tvYear,tvSellerName,tvLocation;
    Button btnShowInterest;
    RelativeLayout parent;

    public CarAdHolder(View view){
        super(view);
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
        parent = view.findViewById(R.id.rl_parent);
    }

    public static CarAdHolder create(Context context, ViewGroup parent){
        return new CarAdHolder(LayoutInflater.from(context).inflate(R.layout.item_car_ad_list,parent,false));
    }

    public static void bind(final Context context, final CarAdHolder carListHolder, final CarListViewItem item, int position, final CarListAdapter.CarListListener listListener) {
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

        Picasso.with(context).load("http://kitcarempire.com/wp-content/gallery/bugatti-veyron-toy-car/bugatti-veyron-replica-small-mini-08.jpg")
                .error(R.drawable.blank_image_car)
                .into(target);


        carListHolder.ivCarPrimaryImage.setTag(target);


        carListHolder.tvCarName.setText(item.getCar().getProductName());
//        carListHolder.tvPrice.setText(String.valueOf(item.getCar().getPrice()));
//        carListHolder.tvKilometer.setText(String.valueOf(item.getCar().getKilometer()));
//        carListHolder.tvFuelType.setText(item.getCar().getFuelType());
//        carListHolder.tvYear.setText(String.valueOf(item.getCar().getYearOfMake()));
//        carListHolder.tvSellerName.setText(item.getCar().getSoldBy());
//        carListHolder.tvLocation.setText(item.getCar().getLocation());
        carListHolder.tvPrice.setVisibility(View.GONE);
        carListHolder.tvKilometer.setVisibility(View.GONE);
        carListHolder.tvFuelType.setVisibility(View.GONE);
        carListHolder.tvYear.setVisibility(View.GONE);
        carListHolder.tvSellerName.setVisibility(View.GONE);
        carListHolder.tvLocation.setVisibility(View.GONE);
        carListHolder.btnShowInterest.setVisibility(View.GONE);
//        carListHolder.btnShowInterest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context,item.getCar().getProductName(),Toast.LENGTH_SHORT).show();
//                listListener.onShowInterestClicked(item);
//            }
//        });

        carListHolder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listListener.onAdClicked(item);
            }
        });
    }
}
