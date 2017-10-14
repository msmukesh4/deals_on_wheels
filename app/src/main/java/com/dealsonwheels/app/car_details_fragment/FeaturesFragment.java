package com.dealsonwheels.app.car_details_fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.dealsonwheels.app.R;
import com.dealsonwheels.app.SelectActivity;
import com.dealsonwheels.app.homepage_fragments.NewCarFragment;
import com.dealsonwheels.app.models.Car;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mukesh on 11/10/17.
 */

public class FeaturesFragment extends Fragment {

    private ListView listView;
    private JSONArray featuresJson;
    private static final String TAG = "Features Fragment";

    public FeaturesFragment() {}

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Fragment newInstance(JSONObject carJson) {
        Log.d(TAG, "newInstance: "+carJson);
        FeaturesFragment fragment = new FeaturesFragment();
        Bundle args = new Bundle();
        args.putString("featuresArray",carJson.optJSONArray("FeaturesData").toString());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_features, container, false);
        listView = (ListView) rootView.findViewById(R.id.listView);
        setupListView();

        return rootView;
    }

    private void setupListView() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String sFeatures = bundle.getString("featuresArray");
            try {
                featuresJson = new JSONArray(sFeatures);
                Log.d(TAG, "setupListView: Featutres JSON :: "+featuresJson.toString());
                listView.setAdapter(new FeaturesAdapter(getContext(),featuresJson));
            }catch (JSONException e){
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            Log.e(TAG, "setupListView: no arguments found in bundle" );
        }

    }

    private class FeaturesAdapter extends BaseAdapter {

        private Context context;
        private LayoutInflater inflater;
        private JSONArray featuresJson;

        FeaturesAdapter(Context context, JSONArray featuresJson){
            this.context = context;
            this.featuresJson = featuresJson;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return featuresJson.length();
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
            FeaturesHolder featuresHolder;
            if (convertView == null){
                convertView = inflater.inflate(R.layout.item_details_tabs,parent,false);
                featuresHolder = new FeaturesHolder(convertView);
                convertView.setTag(featuresHolder);

            }else {
                featuresHolder = (FeaturesHolder) convertView.getTag();
            }

            String key = featuresJson.optJSONObject(position).keys().next();
            featuresHolder.tvKey.setText(key);
            featuresHolder.tvValue.setText(featuresJson.optJSONObject(position).optString(key));


            return convertView;
        }
    }

    private class FeaturesHolder{
        TextView tvKey,tvValue;
        FeaturesHolder(View view){
            tvKey = (TextView) view.findViewById(R.id.tv_key);
            tvValue = (TextView) view.findViewById(R.id.tv_value);
        }
    }
}
