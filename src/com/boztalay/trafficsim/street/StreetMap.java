package com.boztalay.trafficsim.street;

import com.boztalay.trafficsim.TrafficSim;
import com.boztalay.trafficsim.math.Point;
import com.boztalay.trafficsim.math.Vector;

import java.util.ArrayList;

/**
 * Author: Ben Oztalay
 * Copyright 2013
 */

public class StreetMap {
    private ArrayList<Street> streets;
    private ArrayList<Intersection> intersections;

    private Point mapSize;

    public StreetMap(int mapWidth, int mapHeight) {
        streets = new ArrayList<Street>();
        intersections = new ArrayList<Intersection>();

        mapSize = new Point(mapWidth, mapHeight);

        generateStreets();
    }

    private void generateStreets() {
        streets.add(new Street(new Point(25, 25), new Vector(TrafficSim.app.radians(0), 200), 10));
        Street newStreet = new Street(new Point(25, 25), new Vector(TrafficSim.app.radians(0), 200), 10);

        Intersection newIntersection = new Intersection();
        newIntersection.addStreet(newStreet, 0.0f);

        streets.add(new Street(new Point(225, 25), new Vector(TrafficSim.app.radians(90), 200), 10));
        streets.add(new Street(new Point(225, 225), new Vector(TrafficSim.app.radians(180), 200), 10));
        streets.add(new Street(new Point(25, 225), new Vector(TrafficSim.app.radians(270), 200), 10));
    }

    public void display() {
        TrafficSim.app.pushMatrix();

        for(Street street : streets) {
            street.display();
        }

        for(Intersection intersection : intersections) {
            intersection.display();
        }

        TrafficSim.app.popMatrix();
    }
}
