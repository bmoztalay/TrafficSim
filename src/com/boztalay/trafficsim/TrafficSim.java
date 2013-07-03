package com.boztalay.trafficsim;

import com.boztalay.trafficsim.street.StreetMap;
import com.boztalay.trafficsim.vehicle.Car;
import processing.core.PApplet;

import java.util.ArrayList;

/**
 * Author: Ben Oztalay
 * Copyright 2013
 */

public class TrafficSim extends PApplet {
    public static TrafficSim app;

    private ArrayList<Car> cars;
    private StreetMap streetMap;

    public void setup() {
        app = this;

        size(250, 250);

        streetMap = new StreetMap(250, 250);

        cars = new ArrayList<Car>();
        makeCars();
    }

    private void makeCars() {
        Car car1 = new Car();
        car1.setPosition(125, 235);
        cars.add(car1);
    }

    public void draw() {
        background(128);

        streetMap.display();

        for(Car car : cars) {
            car.update();
            car.display();
        }
    }
}
