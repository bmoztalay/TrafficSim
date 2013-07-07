package com.boztalay.trafficsim;

import com.boztalay.trafficsim.car.Car;
import com.boztalay.trafficsim.street.StreetMap;
import com.sun.imageio.spi.RAFImageInputStreamSpi;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Random;

/**
 * Author: Ben Oztalay
 * Copyright 2013
 */

public class TrafficSim extends PApplet {
    private static final int WINDOW_WIDTH = 640;
    private static final int WINDOW_HEIGHT = 480;
    private static final int NUM_CARS = 15;
    private static final int CAR_ADD_DELAY = 750;

    public static TrafficSim app;

    private ArrayList<Car> cars;
    private StreetMap streetMap;

    private int lastMillisMeasure;
    private Random rand;

    public void setup() {
        app = this;
        size(WINDOW_WIDTH, WINDOW_HEIGHT);

        streetMap = new StreetMap(WINDOW_WIDTH - 1, WINDOW_HEIGHT - 1);
        cars = new ArrayList<Car>();
        rand = new Random();

        lastMillisMeasure = millis();
    }

    private void addNewCar() {
        Car car = new Car();
        int streetIndex = rand.nextInt(streetMap.getStreets().size());
        car.setStreet(streetMap.getStreets().get(streetIndex), true, 0.0f);
        cars.add(car);
    }

    public void draw() {
        background(128);

        if(cars.size() < NUM_CARS) {
            if(millis() > lastMillisMeasure + CAR_ADD_DELAY) {
                lastMillisMeasure = millis();
                addNewCar();
            }
        }

        streetMap.draw();

        for(Car car : cars) {
            car.update();
            car.display();
        }
    }
}
