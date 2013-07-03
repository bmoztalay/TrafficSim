package com.boztalay.trafficsim.street;

import com.boztalay.trafficsim.TrafficSim;
import processing.data.FloatList;

import java.util.ArrayList;

/**
 * Author: Ben Oztalay
 * Copyright 2013
 */

public class Intersection {
    private ArrayList<Street> streets;
    private FloatList positionsOnStreets;

    public Intersection() {
        streets = new ArrayList<Street>();
        positionsOnStreets = new FloatList();
    }

    public void addStreet(Street street, float positionOnStreet) {
        streets.add(street);
        positionsOnStreets.append(positionOnStreet);
    }

    public ArrayList<Street> getStreets() {
        return streets;
    }

    public float getPositionOnStreet(Street street) {
        if(streets.contains(street)) {
            int streetIndex = streets.indexOf(street);
            return positionsOnStreets.get(streetIndex);
        } else {
            return -1.0f;
        }
    }

    public void display() {
        TrafficSim.app.pushMatrix();

        TrafficSim.app.popMatrix();
    }
}
