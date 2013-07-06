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
    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 500;

    public static TrafficSim app;

    private ArrayList<Car> cars;
    private StreetMap streetMap;

    public void setup() {
        app = this;

        size(WINDOW_WIDTH, WINDOW_HEIGHT);

        streetMap = new StreetMap(WINDOW_WIDTH, WINDOW_HEIGHT);

        makeCars();
    }

    private void makeCars() {
        cars = new ArrayList<Car>();

//        Car car1 = new Car();
//        car1.setPosition(125, 235);
//        cars.add(car1);
    }

    public void draw() {
        background(128);

        streetMap.draw();

        for(Car car : cars) {
            car.update();
            car.display();
        }
    }
}
