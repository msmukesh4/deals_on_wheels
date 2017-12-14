package com.dealsonwheels.app.new_car_fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dealsonwheels.app.NewCarDetailsSelectActivity;
import com.dealsonwheels.app.R;
import com.dealsonwheels.app.models.Brand;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.dealsonwheels.app.Constants.currentUser;

/**
 * Created by mukesh on 4/11/17.
 */

public class BrandFragment extends Fragment{

    private GridView brandGrid;
    public static BrandFragment newInstance() {

        Bundle args = new Bundle();

        BrandFragment fragment = new BrandFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_brand, container, false);

        brandGrid = (GridView) rootView.findViewById(R.id.brand_gridview);
        brandGrid.setAdapter(new BrandAdapter(getContext(),currentUser.staticData.brandList));

//        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//        textView.setText("New Car");


        return rootView;
    }

    private class BrandAdapter extends BaseAdapter{

        private Context context;
        private ArrayList<Brand> brandList;
        private LayoutInflater layoutInflater;

        BrandAdapter(Context context, ArrayList<Brand> brandList){
            this.context = context;
            this.brandList = brandList;
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return brandList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            BrandHolder brandHolder;
            if (convertView == null){
                convertView = layoutInflater.inflate(R.layout.item_brand,null);
                brandHolder = new BrandHolder(convertView);
                convertView.setTag(brandHolder);
            }else {
                brandHolder = (BrandHolder) convertView.getTag();
            }

//            Picasso.with(context).load(brandList.get(position).url).into(brandHolder.ivBrand);
            Picasso.with(context).load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQv-A0O1AIOkXTRk_SAPBk3uGRCNpimOd_kB7AhkL6pdz72OQ1VhA").into(brandHolder.ivBrand);
            brandHolder.tvName.setText(brandList.get(position).name);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,String.valueOf(brandList.get(position).id),Toast.LENGTH_SHORT).show();
                    LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(getContext());
                    Intent intent = new Intent(NewCarDetailsSelectActivity.BRAND_SELECTED);
                    // You can also include some extra data.
                    intent.putExtra("brand_id", String.valueOf(brandList.get(position).id));
                    intent.putExtra("brand_name", String.valueOf(brandList.get(position).name));

                    lbm.sendBroadcast(intent);

                }
            });

            return convertView;
        }
    }

    class BrandHolder{
        ImageView ivBrand;
        TextView tvName;
        BrandHolder(View view){
            ivBrand = (ImageView) view.findViewById(R.id.brand_image);
            tvName = (TextView) view.findViewById(R.id.brand_name);
        }
    }
}
