package com.dealsonwheels.app.car_details_fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dealsonwheels.app.R;
import com.dealsonwheels.app.models.Car;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mukesh on 11/10/17.
 */

public class SpecificationFragment extends Fragment {

    private ListView listView;
    private JSONArray specificationJson;
    private static final String TAG = "Specification Fragment";
    public SpecificationFragment() {}

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Fragment newInstance(JSONObject carJson) {
        SpecificationFragment fragment = new SpecificationFragment();
        Bundle args = new Bundle();
        args.putString("specificationArray",carJson.optJSONArray("SpecificationData").toString());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_specifications, container, false);
        listView = (ListView) rootView.findViewById(R.id.listView);
        setupListView();

        return rootView;
    }

    private void setupListView() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String sSpecification = bundle.getString("specificationArray");
            try {
                specificationJson = new JSONArray(sSpecification);
                Log.d(TAG, "setupListView: Specification JSON :: "+specificationJson.toString());
                listView.setAdapter(new SpecificationAdapter(getContext(),specificationJson));
            }catch (JSONException e){
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            Log.e(TAG, "setupListView: no arguments found in bundle" );
        }

    }

    private class SpecificationAdapter extends BaseAdapter {

        private Context context;
        private LayoutInflater inflater;
        private JSONArray specificationJson;

        SpecificationAdapter(Context context, JSONArray specificationJson){
            this.context = context;
            this.specificationJson = specificationJson;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return specificationJson.length();
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
        public View getView(int position, View convertView, ViewGroup parent) {
            SpecificationHolder specificationHolder;
            if (convertView == null){
                convertView = inflater.inflate(R.layout.item_details_tabs,parent,false);
                specificationHolder = new SpecificationHolder(convertView);
                convertView.setTag(specificationHolder);

            }else {
                specificationHolder = (SpecificationHolder) convertView.getTag();
            }

            String key = specificationJson.optJSONObject(position).keys().next();
            specificationHolder.tvKey.setText(key);
            specificationHolder.tvValue.setText(specificationJson.optJSONObject(position).optString(key));


            return convertView;
        }
    }

    private class SpecificationHolder{
        TextView tvKey,tvValue;
        SpecificationHolder(View view){
            tvKey = (TextView) view.findViewById(R.id.tv_key);
            tvValue = (TextView) view.findViewById(R.id.tv_value);
        }
    }

}
