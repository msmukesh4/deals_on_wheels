package com.dealsonwheels.app.homepage_fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dealsonwheels.app.R;

/**
 * Created by mukesh on 13/9/17.
 */

public class HomePagerAdapter extends FragmentPagerAdapter {

    private Context context;
    public HomePagerAdapter(FragmentManager fm,Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return NewCarFragment.newInstance();
            case 1:
                return OldCarFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "NEW CAR";
            case 1:
                return "OLD CAR";
        }
        return "TAB";
    }

    public int getImageResource(int position){

        switch (position){

            case 0:
                return R.drawable.tab_icon_new_car;
            case 1:
                return R.drawable.tab_icon_old_car;
        }
        return R.mipmap.ic_launcher;
    }

    public View getTabView(int position) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom_tabs, null);
        TextView tv = (TextView) v.findViewById(R.id.text_view);
        tv.setText(getPageTitle(position));
        ImageView img = (ImageView) v.findViewById(R.id.image_view);
        img.setImageResource(getImageResource(position));
        return v;
    }
}
