package com.dealsonwheels.app.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.dealsonwheels.app.holders.CarAdHolder;
import com.dealsonwheels.app.holders.CarListHolder;
import com.dealsonwheels.app.models.Car;
import com.dealsonwheels.app.models.CarListViewItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mukesh on 13/12/17.
 */

public class CarListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private CarListListener listListener;
    private Context context;
    List<CarListViewItem> carList;

    public CarListAdapter(CarListListener listListener, Context context){
        this.listListener = listListener;
        this.context = context;
        this.carList = new ArrayList<>();
    }

    public void addCar(Car car){
        carList.add(new CarListViewItem(car, CarListViewItem.CAR));
        notifyItemInserted(carList.size());
    }

    public void addAD(Car car){
        Log.e("Car list Adapter", "addAD: " );
        carList.add(new CarListViewItem(car, CarListViewItem.AD));
        notifyItemInserted(carList.size());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case CarListViewItem.CAR:
                return CarListHolder.create(context,parent);
            case CarListViewItem.AD:
                return CarAdHolder.create(context,parent);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)){
            case CarListViewItem.CAR:
                CarListHolder.bind(context,(CarListHolder) holder,carList.get(position),
                        position,listListener);
                return;

            case CarListViewItem.AD:
                CarAdHolder.bind(context, (CarAdHolder) holder,carList.get(position), position, listListener);
                return;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return carList.get(position).getViewType();
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public interface CarListListener{
        void onShowInterestClicked(CarListViewItem item);
        void onItemClicked(CarListViewItem item);
        void onAdClicked(CarListViewItem item);

    }
}
