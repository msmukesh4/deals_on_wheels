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
import com.dealsonwheels.app.models.BodyType;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.dealsonwheels.app.Constants.currentUser;

/**
 * Created by mukesh on 4/11/17.
 */

public class BodyTypeFragment extends Fragment {

    private GridView bodyTypeGrid;
    public static BodyTypeFragment newInstance() {

        Bundle args = new Bundle();

        BodyTypeFragment fragment = new BodyTypeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_brand, container, false);

        bodyTypeGrid = (GridView) rootView.findViewById(R.id.brand_gridview);
        bodyTypeGrid.setAdapter(new BodyTypeAdapter(getContext(),currentUser.staticData.bodyTypeList));

//        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//        textView.setText("New Car");


        return rootView;
    }

    private class BodyTypeAdapter extends BaseAdapter {

        private Context context;
        private ArrayList<BodyType> bodyTypeList;
        private LayoutInflater layoutInflater;

        BodyTypeAdapter(Context context, ArrayList<BodyType> brandList){
            this.context = context;
            this.bodyTypeList = brandList;
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return bodyTypeList.size();
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
            BodyTypeHolder bodyTypeHolder;
            if (convertView == null){
                convertView = layoutInflater.inflate(R.layout.item_body_type,null);
                bodyTypeHolder = new BodyTypeHolder(convertView);
                convertView.setTag(bodyTypeHolder);
            }else {
                bodyTypeHolder = (BodyTypeHolder) convertView.getTag();
            }

            Picasso.with(context).load(bodyTypeList.get(position).url).into(bodyTypeHolder.ivBodyType);
            bodyTypeHolder.tvName.setText(bodyTypeList.get(position).name);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,String.valueOf(bodyTypeList.get(position).id),Toast.LENGTH_SHORT).show();
                    LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(getContext());
                    Intent intent = new Intent(NewCarDetailsSelectActivity.BODY_SELECTED);
                    // You can also include some extra data.
                    intent.putExtra("body_id", String.valueOf(bodyTypeList.get(position).id));
                    intent.putExtra("body_name", String.valueOf(bodyTypeList.get(position).name));

                    lbm.sendBroadcast(intent);

                }
            });

            return convertView;
        }
    }

    class BodyTypeHolder {
        ImageView ivBodyType;
        TextView tvName;
        BodyTypeHolder(View view){
            ivBodyType = (ImageView) view.findViewById(R.id.body_type_image);
            tvName = (TextView) view.findViewById(R.id.body_type_name);
        }
    }

}
