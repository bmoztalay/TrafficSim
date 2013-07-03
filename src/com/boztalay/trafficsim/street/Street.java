package com.boztalay.trafficsim.street;

import com.boztalay.trafficsim.TrafficSim;
import com.boztalay.trafficsim.math.Point;
import com.boztalay.trafficsim.math.Vector;

import java.util.ArrayList;

/**
 * Author: Ben Oztalay
 * Copyright 2013
 */

public class Street {
    private static final float ROAD_WIDTH = 30;

    private Point startPoint;
    private Vector streetVector;
    private ArrayList<Intersection> intersections;

    private float speedLimit;

    public Street(Point startPoint, Vector streetVector, float speedLimit) {
        this.startPoint = startPoint;
        this.streetVector = streetVector;
        this.intersections = new ArrayList<Intersection>();

        this.speedLimit = speedLimit;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Vector getStreetVector() {
        return streetVector;
    }

    public float getSpeedLimit() {
        return speedLimit;
    }

    public void addIntersection(Intersection intersection) {
        intersections.add(intersection);
    }

    public void display() {
        TrafficSim.app.pushMatrix();
        TrafficSim.app.rectMode(TrafficSim.app.CORNER);

        TrafficSim.app.translate(startPoint.x, startPoint.y);
        TrafficSim.app.rotate(streetVector.angle);

        TrafficSim.app.fill(200);
        TrafficSim.app.rect(0, -(ROAD_WIDTH / 2), streetVector.magnitude, ROAD_WIDTH);

        TrafficSim.app.popMatrix();
    }
}
