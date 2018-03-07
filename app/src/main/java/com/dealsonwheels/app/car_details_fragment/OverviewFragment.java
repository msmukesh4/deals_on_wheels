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

import org.json.simple.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Created by mukesh on 11/10/17.
 */

public class OverviewFragment extends Fragment {

    private ListView listView;
    private org.json.simple.JSONObject overviewJson;
    private static final String TAG = "Overview Fragment";
    private static OverviewFragment fragment;
    public OverviewFragment() {}

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Fragment newInstance(String overviewJson) {
        if (fragment == null) {
            fragment = new OverviewFragment();
            Bundle args = new Bundle();
            args.putString("overViewArray", overviewJson.toString());
            fragment.setArguments(args);
        }
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
                overviewJson = (org.json.simple.JSONObject) new JSONParser().parse(sOverview);
                Log.d(TAG, "setupListView: OverView JSON :: "+overviewJson.toString());
                listView.setAdapter(new OverviewAdapter(getContext(),overviewJson));
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
        private JSONObject overviewJson;
        private Object[] key;

        OverviewAdapter(Context context, JSONObject overviewJson){
            this.context = context;
            this.overviewJson = overviewJson;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            key = overviewJson.keySet().toArray();
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
            OverviewHolder overviewHolder;
            if (convertView == null){
                convertView = inflater.inflate(R.layout.item_details_tabs,parent,false);
                overviewHolder = new OverviewHolder(convertView);
                convertView.setTag(overviewHolder);

            }else {
                overviewHolder = (OverviewHolder) convertView.getTag();
            }

            try {
                if(!key[position].toString().equalsIgnoreCase("id")) {
                    overviewHolder.tvKey.setText(key[position].toString());
                    JSONArray jsonArray = (JSONArray) overviewJson.get(key[position].toString());
                    StringBuilder val = new StringBuilder();
                    for (int i = 0; i < jsonArray.size(); i++) {
                        if (i < jsonArray.size() - 1)
                            val.append(jsonArray.get(i)).append(", \n");
                        else
                            val.append(jsonArray.get(i)).append(".");
                    }
                    overviewHolder.tvValue.setText(val.toString());
                }
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

    private class OverviewHolder{
        TextView tvKey,tvValue;
        OverviewHolder(View view){
            tvKey = (TextView) view.findViewById(R.id.tv_key);
            tvValue = (TextView) view.findViewById(R.id.tv_value);
        }
    }
}
