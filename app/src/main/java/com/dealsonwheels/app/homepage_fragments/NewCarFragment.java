package com.dealsonwheels.app.homepage_fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dealsonwheels.app.R;

/**
 * Created by mukesh on 13/9/17.
 */

public class NewCarFragment extends Fragment{

    private static final String ARG_SECTION_NUMBER = "section_number";

    public NewCarFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Fragment newInstance() {
        NewCarFragment fragment = new NewCarFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, 1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_newcar, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText("New Car");
        return rootView;
    }

}
