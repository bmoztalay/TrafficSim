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
        detectIntersections();
    }

    private void generateStreets() {
        streets.add(new Street(new Point(0, 250), new Vector(TrafficSim.radians(0), 500), 10));
        streets.add(new Street(new Point(250, 0), new Vector(TrafficSim.radians(90), 500), 10));
    }

    private void detectIntersections() {
        for(int i = 0; i < streets.size(); i++) {
            for(int j = i + 1; j < streets.size(); j++) {
                findIntersectionsOfStreets(streets.get(i), streets.get(j));
            }
        }
    }

    private void findIntersectionsOfStreets(Street street1, Street street2) {
        Vector vector1 = street1.getStreetVector();
        Vector vector2 = street2.getStreetVector();

        if(areVectorsParallel(vector1, vector2)) {
            return;
        }

        //Do all the heavy lifting up front
        float sinTheta1 = TrafficSim.sin(vector1.angle);
        float sinTheta2 = TrafficSim.sin(vector2.angle);
        float cosTheta2 = TrafficSim.cos(vector2.angle);
        float sinTheta2MinusTheta1 = TrafficSim.sin(vector2.angle - vector1.angle);

        //I derived this big mess in my notebook...
        float pointOfIntersectionOnStreet1 = ((sinTheta2 * (street2.getOrigin().x - street1.getOrigin().x)) + (cosTheta2 * (street1.getOrigin().y - street2.getOrigin().y))) /
                                                                                                    sinTheta2MinusTheta1;

        if(pointOfIntersectionOnStreet1 < 0.0f || pointOfIntersectionOnStreet1 > vector1.magnitude) {
            //This means that the vectors can't intersect because they don't actually touch.
            //The point of intersection isn't actually on the vector.
            return;
        }

        //If the point of intersection on vector 1 exists, then let's find where vector 1 intersects vector 2.
        //I also derived this in my notebook
        float pointOfIntersectionOnStreet2 = (street1.getOrigin().y - street2.getOrigin().y + (pointOfIntersectionOnStreet1 * sinTheta1)) /
                                                                                         sinTheta2;

        if(pointOfIntersectionOnStreet2 < 0.0f || (pointOfIntersectionOnStreet2 > vector2.magnitude)) {
            //Same thing happened for vector 2. They can't touch because the point of intersection on
            //this vector isn't actually on the vector. The vector would have to extend in some direction
            //to touch the first vector
            return;
        }

        //If we make it here, it means that the two streets do intersect, and we know where along those streets
        //the intersection happened. Now we have to make the Intersection and link everything up

        Intersection intersection = new Intersection();
        intersection.addStreet(street1, pointOfIntersectionOnStreet1);
        intersection.addStreet(street2, pointOfIntersectionOnStreet2);

        intersections.add(intersection);

        street1.addIntersection(intersection);
        street2.addIntersection(intersection);
    }

    private boolean areVectorsParallel(Vector vector1, Vector vector2) {
        return (vector1.angle == vector2.angle);
    }

    public void draw() {
        TrafficSim.app.pushMatrix();

        for(Street street : streets) {
            street.draw();
        }

        for(Intersection intersection : intersections) {
            intersection.draw();
        }

        TrafficSim.app.popMatrix();
    }
}
