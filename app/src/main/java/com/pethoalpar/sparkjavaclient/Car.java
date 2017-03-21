package com.pethoalpar.sparkjavaclient;

/**
 * Created by petho on 2017-03-21.
 */

public class Car {
    private String model;
    private String fuel;
    private int year;

    public Car(String model, String fuel, int year) {
        this.model = model;
        this.fuel = fuel;
        this.year = year;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
