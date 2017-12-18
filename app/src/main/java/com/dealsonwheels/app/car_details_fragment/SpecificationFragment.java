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

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Created by mukesh on 11/10/17.
 */

public class SpecificationFragment extends Fragment {

    private ListView listView;
    private JSONObject specificationJson;
    private static final String TAG = "Specification Fragment";
    private static SpecificationFragment fragment;
    public SpecificationFragment() {}

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Fragment newInstance(String specificationJson) {
        if (null == fragment) {
            fragment = new SpecificationFragment();
            Bundle args = new Bundle();
            args.putString("specificationArray", specificationJson);
            fragment.setArguments(args);
        }
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
                specificationJson = (JSONObject) new JSONParser().parse(sSpecification);
                Log.d(TAG, "setupListView: Specification JSON :: "+specificationJson.toString());
                listView.setAdapter(new SpecificationAdapter(getContext(),specificationJson));
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
        private JSONObject specificationJson;
        private Object[] key;

        SpecificationAdapter(Context context, JSONObject specificationJson){
            this.context = context;
            this.specificationJson = specificationJson;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            key = specificationJson.keySet().toArray();
        }

        @Override
        public int getCount() {
            return key.length;
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

            try {
                specificationHolder.tvKey.setText(key[position].toString());
                JSONArray jsonArray = (JSONArray) specificationJson.get(key[position].toString());
                StringBuilder val = new StringBuilder();
                for (int i = 0; i < jsonArray.size(); i++) {
                    if (i < jsonArray.size()-1)
                        val.append(jsonArray.get(i)).append(", \n");
                    else
                        val.append(jsonArray.get(i)).append(".");
                }
                specificationHolder.tvValue.setText(val.toString());
            }catch (Exception e){
                e.printStackTrace();
            }

            return convertView;
        }
    }

    @Override
    public void onStop() {
        Log.e(TAG, "onStop: ");
        fragment = null;
        super.onStop();
    }

    private class SpecificationHolder{
        TextView tvKey,tvValue;
        SpecificationHolder(View view){
            tvKey = (TextView) view.findViewById(R.id.tv_key);
            tvValue = (TextView) view.findViewById(R.id.tv_value);
        }
    }

}
