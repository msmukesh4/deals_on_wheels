package com.dealsonwheels.app.new_car_fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.dealsonwheels.app.NewCarDetailsSelectActivity;
import com.dealsonwheels.app.R;

/**
 * Created by mukesh on 4/11/17.
 */

public class PriceSelectFragment extends Fragment {

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
}
