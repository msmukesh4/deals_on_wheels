package com.dealsonwheels.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.dealsonwheels.app.new_car_fragments.NewCarSelectViewPager;


/**
 * Created by mukesh on 4/11/17.
 */

public class NewCarDetailsSelectActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private NewCarSelectViewPager newCarSelectViewPager;
    public static final String BRAND_SELECTED = "brand_selected";
    public static final String PRICE_SELECTED = "price_selected";
    public static final String BODY_SELECTED = "body_selected";
    private String min_price,max_price,brand_id,body_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_car_select);

        mViewPager = (ViewPager) findViewById(R.id.car_select_view_pager);
        mTabLayout = (TabLayout) findViewById(R.id.car_select_tabs);

        // view pager
        newCarSelectViewPager = new NewCarSelectViewPager(getSupportFragmentManager(),getApplicationContext());
        mViewPager.setAdapter(newCarSelectViewPager);
        mTabLayout.setupWithViewPager(mViewPager);

        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);
        lbm.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                brand_id = intent.getStringExtra("brand_id");
                mTabLayout.getTabAt(1).select();
            }
        },new IntentFilter(BRAND_SELECTED));

        lbm.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                min_price = intent.getStringExtra("min_price");
                max_price = intent.getStringExtra("max_price");
                mTabLayout.getTabAt(2).select();
            }
        },new IntentFilter(PRICE_SELECTED));

        lbm.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
//                Toast.makeText(getApplicationContext(),"lets move forward",Toast.LENGTH_SHORT).show();
//                body_id = intent.getStringExtra("body_id");
                Intent mintent = new Intent(getApplicationContext(), CarsListActivity.class);
                mintent.putExtra("type", CarsListActivity.NEW_CARS);
                mintent.putExtra("min_price",min_price);
                mintent.putExtra("max_price",max_price);
                mintent.putExtra("body_type_id",body_id);
                mintent.putExtra("brand_type_id",brand_id);
                startActivity(mintent);
                finish();
            }
        },new IntentFilter(BODY_SELECTED));



    }

}
