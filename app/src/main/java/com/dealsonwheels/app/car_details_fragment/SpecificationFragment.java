package com.dealsonwheels.app.car_details_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dealsonwheels.app.R;

/**
 * Created by mukesh on 11/10/17.
 */

public class SpecificationFragment extends Fragment {

    public SpecificationFragment() {}

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Fragment newInstance() {
        SpecificationFragment fragment = new SpecificationFragment();
//        Bundle args = new Bundle();
//        args.putInt(ARG_SECTION_NUMBER, 1);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_specifications, container, false);
//        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//        textView.setText("New Car");

        return rootView;
    }
}
