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

public class OverviewFragment extends Fragment {

    private ListView listView;
    private JSONArray overviewJson;
    private static final String TAG = "Overview Fragment";
    public OverviewFragment() {}

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Fragment newInstance(JSONObject carJson) {
        OverviewFragment fragment = new OverviewFragment();
        Bundle args = new Bundle();
        args.putString("overViewArray",carJson.optJSONArray("OverviewData").toString());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_overview, container, false);

        listView = (ListView) rootView.findViewById(R.id.listView);
        setupListView();
        return rootView;
    }

    private void setupListView() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String sOverview = bundle.getString("overViewArray");
            try {
                overviewJson = new JSONArray(sOverview);
                Log.d(TAG, "setupListView: OverView JSON :: "+overviewJson.toString());
                listView.setAdapter(new OverviewAdapter(getContext(),overviewJson));
            }catch (JSONException e){
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            Log.e(TAG, "setupListView: no arguments found in bundle" );
        }

    }

    private class OverviewAdapter extends BaseAdapter{

        private Context context;
        private LayoutInflater inflater;
        private JSONArray overviewJson;

        OverviewAdapter(Context context, JSONArray overviewJson){
            this.context = context;
            this.overviewJson = overviewJson;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return overviewJson.length();
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
            OverviewHolder overviewHolder;
            if (convertView == null){
                convertView = inflater.inflate(R.layout.item_details_tabs,parent,false);
                overviewHolder = new OverviewHolder(convertView);
                convertView.setTag(overviewHolder);

            }else {
                overviewHolder = (OverviewHolder) convertView.getTag();
            }

            String key = overviewJson.optJSONObject(position).keys().next();
            overviewHolder.tvKey.setText(key);
            overviewHolder.tvValue.setText(overviewJson.optJSONObject(position).optString(key));


            return convertView;
        }
    }

    private class OverviewHolder{
        TextView tvKey,tvValue;
        OverviewHolder(View view){
            tvKey = (TextView) view.findViewById(R.id.tv_key);
            tvValue = (TextView) view.findViewById(R.id.tv_value);
        }
    }
}
