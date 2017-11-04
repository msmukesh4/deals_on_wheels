package com.dealsonwheels.app.new_car_fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.dealsonwheels.app.models.BodyType;

import org.json.JSONObject;

/**
 * Created by mukesh on 4/11/17.
 */

public class NewCarSelectViewPager extends FragmentPagerAdapter {

    private Context context;
    public NewCarSelectViewPager(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return BrandFragment.newInstance();
            case 1:
                return PriceSelectFragment.newInstance();
            case 2:
                return BodyTypeFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Brand";
            case 1:
                return "Price";
            case 2:
                return "Body Type";
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
