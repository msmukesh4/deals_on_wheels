package com.dealsonwheels.app.homepage_fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dealsonwheels.app.CarsListActivity;
import com.dealsonwheels.app.CarsListActivityOld;
import com.dealsonwheels.app.Constants;
import com.dealsonwheels.app.R;

import io.apptik.widget.MultiSlider;

/**
 * Created by mukesh on 13/9/17.
 */

public class OldCarFragment extends Fragment{

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String TAG = "Old Car Fragment";
    private TextView priceRange;
    private Button btnFindCars;

    public OldCarFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Fragment newInstance() {
        OldCarFragment fragment = new OldCarFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, 1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_oldcar, container, false);
//        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//        textView.setText("Old Car");

        btnFindCars = (Button) rootView.findViewById(R.id.btn_find_car);
        btnFindCars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CarsListActivity.class);
                intent.putExtra("type", CarsListActivity.OLD_CARS);
                startActivity(intent);
            }
        });

        priceRange = (TextView) rootView.findViewById(R.id.price_range);
        MultiSlider multiSlider = (MultiSlider) rootView.findViewById(R.id.range_slider);
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
