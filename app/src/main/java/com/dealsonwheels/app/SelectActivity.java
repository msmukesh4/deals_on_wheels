package com.dealsonwheels.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import com.dealsonwheels.app.models.StaticData;

import static com.dealsonwheels.app.Constants.currentUser;

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
                            Intent intent = new Intent(SelectActivity.this,CarDetailsActivity.class);
                            intent.putExtra("product_id", String.valueOf(currentUser.staticData.cars.get(position+1).getProductId()));

                            Log.e(TAG, "onItemClick: "+parent.getItemAtPosition(position));
                            Log.e(TAG, "product_id: "+currentUser.staticData.cars.get(position+1).getProductId());
                            startActivity(intent);
                            finish();
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
