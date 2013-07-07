package com.boztalay.trafficsim.street;

import com.boztalay.trafficsim.TrafficSim;
import com.boztalay.trafficsim.math.Point;
import processing.core.PConstants;
import processing.data.FloatList;

import java.util.ArrayList;

/**
 * Author: Ben Oztalay
 * Copyright 2013
 */

public class Intersection {
    private ArrayList<Street> streets;
    private FloatList positionsOnStreets;

    private Point positionOnScreen;

    public Intersection() {
        streets = new ArrayList<Street>();
        positionsOnStreets = new FloatList();

        positionOnScreen = new Point();
    }

    public void addStreet(Street street, float positionOnStreet) {
        streets.add(street);
        positionsOnStreets.append(positionOnStreet);

        if(streets.size() == 1) {
            calculatePositionOnScreenFromFirstStreet();
        }
    }

    private void calculatePositionOnScreenFromFirstStreet() {
        positionOnScreen = calculatePositionOnScreenFromStreet(streets.get(0));
    }

    private Point calculatePositionOnScreenFromStreet(Street street) {
        float positionOnStreet = getPositionOnStreet(street);

        float positionX = street.getOrigin().x + (positionOnStreet * TrafficSim.cos(street.getStreetVector().angle));
        float positionY = street.getOrigin().y + (positionOnStreet * TrafficSim.sin(street.getStreetVector().angle));

        return new Point(positionX, positionY);
    }

    private boolean areFloatsEquivalent(float float1, float float2) {
        final float epsilon = 0.001f;

        return ((float1 < (float2 + epsilon)) && (float1 > (float2 - epsilon)));
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

    public void draw() {
        TrafficSim.app.pushMatrix();
        TrafficSim.app.rectMode(PConstants.CENTER);

        TrafficSim.app.fill(255, 0, 0);
        TrafficSim.app.stroke(0, 0, 0);
        TrafficSim.app.rect(positionOnScreen.x, positionOnScreen.y, Street.ROAD_WIDTH, Street.ROAD_WIDTH);

        TrafficSim.app.popMatrix();
    }
}
