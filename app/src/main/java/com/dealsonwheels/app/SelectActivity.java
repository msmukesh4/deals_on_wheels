package com.dealsonwheels.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

/**
 * Created by mukesh on 17/9/17.
 */

public class SelectActivity extends AppCompatActivity {

    private static final String TAG = "Select Activity";
    private String select_type;
    private ImageView ivClose,ivBack;
    private AutoCompleteTextView autoCompleteTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.selector);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivClose = (ImageView) findViewById(R.id.iv_close);

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoCompleteTextView.setText("");
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        select_type = getIntent().getStringExtra("type");
        if (select_type != null){

            switch (select_type){
                case "car_name":
                    //Auto complete list view for car list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(SelectActivity.this,
                        android.R.layout.simple_spinner_dropdown_item, Constants.CARS);
                    autoCompleteTextView.setAdapter(adapter);
                    autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Log.e(TAG, "onItemClick: "+parent.getItemAtPosition(position));
                        }
                    });

                    break;
                default:
                    Log.e(TAG, "unsupported select type" );
                    finish();
                    break;
            }

        }else {
            Log.e(TAG, "no type is selected");

        }

    }
}
