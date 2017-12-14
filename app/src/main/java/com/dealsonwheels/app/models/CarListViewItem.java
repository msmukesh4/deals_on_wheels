package com.dealsonwheels.app.models;

/**
 * Created by mukesh on 13/12/17.
 */

public class CarListViewItem<T> {
    public static final int CAR = 0;
    public static final int AD = 2;

    Car car;
    int viewType;

    public CarListViewItem(Car car, int viewType){
        this.car = car;
        this.viewType = viewType;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }
}
