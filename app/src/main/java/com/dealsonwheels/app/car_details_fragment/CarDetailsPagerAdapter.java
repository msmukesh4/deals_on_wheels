package com.dealsonwheels.app.car_details_fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dealsonwheels.app.R;
import com.dealsonwheels.app.homepage_fragments.NewCarFragment;
import com.dealsonwheels.app.homepage_fragments.OldCarFragment;
import com.dealsonwheels.app.models.Car;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mukesh on 11/10/17.
 */

public class CarDetailsPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    private JSONObject rootJson;
    private JSONObject overviewJson;
    private JSONObject specificationJson;
    private JSONObject featuresJson;
    public CarDetailsPagerAdapter(FragmentManager fm, Context context, JSONObject rootJson) throws JSONException {
        super(fm);
        this.rootJson = rootJson;
        this.context = context;

        overviewJson = (JSONObject) rootJson.optJSONArray("OverviewData").get(0);
//        featuresJson = (JSONObject) rootJson.optJSONArray("FeaturesData").get(0);
        specificationJson = (JSONObject) rootJson.optJSONArray("SpecificationData").get(0);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return OverviewFragment.newInstance(overviewJson.toString());
//            case 1:
//                return OverviewFragment.newInstance(overviewJson.toString());
            case 1:
                return SpecificationFragment.newInstance(specificationJson.toString());
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Overview";
//            case 1:
//                return "Features";
            case 1:
                return "Specifications";
        }
        return "TAB";
    }

//    public int getImageResource(int position){
//
//        switch (position){
//
//            case 0:
//                return R.drawable.tab_icon_new_car;
//            case 1:
//                return R.drawable.tab_icon_old_car;
//        }
//        return R.mipmap.ic_launcher;
//    }
//
//    public View getTabView(int position) {
//        View v = LayoutInflater.from(context).inflate(R.layout.custom_tabs, null);
//        TextView tv = (TextView) v.findViewById(R.id.text_view);
//        tv.setText(getPageTitle(position));
//        ImageView img = (ImageView) v.findViewById(R.id.image_view);
//        img.setImageResource(getImageResource(position));
//        return v;
//    }
}
