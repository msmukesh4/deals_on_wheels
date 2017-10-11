package com.dealsonwheels.app.car_details_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.dealsonwheels.app.R;
import com.dealsonwheels.app.SelectActivity;
import com.dealsonwheels.app.homepage_fragments.NewCarFragment;

/**
 * Created by mukesh on 11/10/17.
 */

public class FeaturesFragment extends Fragment {

    public FeaturesFragment() {}

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Fragment newInstance() {
        FeaturesFragment fragment = new FeaturesFragment();
//        Bundle args = new Bundle();
//        args.putInt(ARG_SECTION_NUMBER, 1);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_features, container, false);
//        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//        textView.setText("New Car");

        return rootView;
    }
}
