package com.dealsonwheels.app.new_car_fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dealsonwheels.app.Constants;
import com.dealsonwheels.app.NewCarDetailsSelectActivity;
import com.dealsonwheels.app.R;

import io.apptik.widget.MultiSlider;

/**
 * Created by mukesh on 4/11/17.
 */

public class PriceSelectFragment extends Fragment {

    private static final String TAG = "Price Select Fragment";
    private MultiSlider multiSlider;
    private TextView priceRange;

    public static PriceSelectFragment newInstance() {

        Bundle args = new Bundle();

        PriceSelectFragment fragment = new PriceSelectFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_price_select, container, false);

        priceRange = (TextView) rootView.findViewById(R.id.price_range);
        multiSlider = (MultiSlider) rootView.findViewById(R.id.range_slider);
        multiSlider.setMin(Constants.PRICE_SLIDER_MIN_RANGE);
        multiSlider.setMax(Constants.PRICE_SLIDER_MAX_RANGE);
        changePriceRange(multiSlider);
        multiSlider.setOnThumbValueChangeListener(new MultiSlider.OnThumbValueChangeListener() {
            @Override
            public void onValueChanged(MultiSlider multiSlider, MultiSlider.Thumb thumb, int thumbIndex, int value) {
                if (thumbIndex == 0) {
                    Log.e(TAG, "onValueChanged: min :: "+String.valueOf(value));
                } else {
                    Log.e(TAG, "onValueChanged: max :: "+String.valueOf(value));
                }
                changePriceRange(multiSlider);
            }
        });

        Button btnNext = (Button) rootView.findViewById(R.id.btn_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(getContext());
                Intent intent = new Intent(NewCarDetailsSelectActivity.PRICE_SELECTED);
                // You can also include some extra data.
                intent.putExtra("min_price", String.valueOf(123));
                intent.putExtra("max_price", String.valueOf(100000));

                lbm.sendBroadcast(intent);

            }
        });

        return rootView;
    }

    private void changePriceRange(MultiSlider multiSlider){
        if (multiSlider.getThumb(0).getValue() == Constants.PRICE_SLIDER_MIN_RANGE &&
                multiSlider.getThumb(1).getValue() == Constants.PRICE_SLIDER_MAX_RANGE){
            priceRange.setText("All Range");
        }else if (multiSlider.getThumb(0).getValue() == Constants.PRICE_SLIDER_MIN_RANGE &&
                multiSlider.getThumb(1).getValue() != Constants.PRICE_SLIDER_MAX_RANGE){
            priceRange.setText("Below "+multiSlider.getThumb(1).getValue()+" lakh");
        }else if (multiSlider.getThumb(0).getValue() != Constants.PRICE_SLIDER_MIN_RANGE &&
                multiSlider.getThumb(1).getValue() == Constants.PRICE_SLIDER_MAX_RANGE){
            priceRange.setText("Above "+multiSlider.getThumb(0).getValue()+" lakh");
        }else {
            priceRange.setText(multiSlider.getThumb(0).getValue()+" lakh - "+multiSlider.getThumb(1).getValue()+" lakh");
        }
    }
}
