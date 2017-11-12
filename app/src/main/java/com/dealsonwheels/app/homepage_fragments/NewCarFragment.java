package com.dealsonwheels.app.homepage_fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dealsonwheels.app.NewCarDetailsSelectActivity;
import com.dealsonwheels.app.R;
import com.dealsonwheels.app.SelectActivity;

/**
 * Created by mukesh on 13/9/17.
 */

public class NewCarFragment extends Fragment{

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String[] CARS = new String[] {
            "BMW 200M", "AUDI 900Q", "BETTLE 3S", "BUCATI VERON", "LEMBORGINI GALLADO", "LEMBORGINI ADO", "LEMBORGINI LADO"
    };



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
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_newcar, container, false);
//        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        Button btnFindCars = (Button) rootView.findViewById(R.id.btn_find_car);
//        textView.setText("New Car");

        EditText etCarName = (EditText) rootView.findViewById(R.id.et_car_name);
        etCarName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),SelectActivity.class);
                intent.putExtra("type","car_name");
                getActivity().startActivityForResult(intent,1);
            }
        });

        btnFindCars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),NewCarDetailsSelectActivity.class);
                getActivity().startActivity(intent);
            }
        });

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
//                android.R.layout.simple_spinner_dropdown_item, CARS);
//
//        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) rootView.findViewById(R.id.auto);
//        autoCompleteTextView.setAdapter(adapter);


        return rootView;
    }

}
