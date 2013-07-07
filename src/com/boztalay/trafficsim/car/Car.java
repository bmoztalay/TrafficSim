package com.boztalay.trafficsim.car;

import com.boztalay.trafficsim.TrafficSim;
import com.boztalay.trafficsim.math.Point;
import com.boztalay.trafficsim.math.Vector;
import com.boztalay.trafficsim.street.Intersection;
import com.boztalay.trafficsim.street.Street;
import processing.core.PConstants;

import java.util.Random;

/**
 * Author: Ben Oztalay
 * Copyright 2013
 */

public class Car {
    public static final float CAR_LENGTH = 25;
    public static final float CAR_WIDTH = 10;

    private int carColor;

    private Point position;
    private float velocity;
    private float acceleration;

    private Street currentStreet;
    private boolean directionOnStreet;
    private float positionOnStreet;

    public Car() {
        Random rand = new Random();
        carColor = TrafficSim.app.color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));

        position = new Point();
        velocity = 0.0f;
        acceleration = 0.0f;

        directionOnStreet = true;
        positionOnStreet = 0.0f;
    }

    public void setStreet(Street street, boolean directionOnStreet, float positionOnStreet) {
        this.currentStreet = street;
        this.directionOnStreet = directionOnStreet;
        this.positionOnStreet = positionOnStreet;

        this.velocity = currentStreet.getSpeedLimit();

        calculatePosition();
    }

    private void calculatePosition() {
        float positionX = currentStreet.getOrigin().x + (positionOnStreet * TrafficSim.cos(currentStreet.getStreetVector().angle));
        float positionY = currentStreet.getOrigin().y + (positionOnStreet * TrafficSim.sin(currentStreet.getStreetVector().angle));

        position.x = positionX;
        position.y = positionY;
    }

    public void update() {
        //TODO take acceleration into account; base the velocity on actual units with actual time, not framerate
        float deltaPosition = velocity;
        if(!directionOnStreet) {
            deltaPosition *= -1;
        }

        positionOnStreet += deltaPosition;
        if(positionOnStreet < 0.0f) {
            positionOnStreet = 0.0f;
            directionOnStreet = !directionOnStreet;
        } else if(positionOnStreet > currentStreet.getStreetVector().magnitude) {
            positionOnStreet = currentStreet.getStreetVector().magnitude;
            directionOnStreet = !directionOnStreet;
        }

        Intersection detectedIntersection = detectIfAtIntersection();
        if(detectedIntersection != null) {
            Random rand = new Random();
            int newStreetIndex = rand.nextInt(detectedIntersection.getStreets().size());
            Street streetToMoveTo = detectedIntersection.getStreets().get(newStreetIndex);

            boolean newDirection;

            float intersectionPositionOnNewStreet = detectedIntersection.getPositionOnStreet(streetToMoveTo);
            if(intersectionPositionOnNewStreet < 1.0f) {
                newDirection = true;
            } else if(intersectionPositionOnNewStreet > streetToMoveTo.getStreetVector().magnitude - 1.0f) {
                newDirection = false;
            } else {
                newDirection = rand.nextBoolean();
            }

            setStreet(streetToMoveTo, newDirection, intersectionPositionOnNewStreet);
        }

        calculatePosition();
    }

    private Intersection detectIfAtIntersection() {
        if(currentStreet.getIntersections().size() == 0) {
            return null;
        }

        for(Intersection intersection : currentStreet.getIntersections())  {
            float intersectionPositionOnStreet = intersection.getPositionOnStreet(currentStreet);
            if(directionOnStreet && positionOnStreet < intersectionPositionOnStreet) {
                if(positionOnStreet > (intersectionPositionOnStreet - (Street.ROAD_WIDTH / 2.0f))) {
                    return intersection;
                }
            } else if(!directionOnStreet && positionOnStreet > intersectionPositionOnStreet) {
                if(positionOnStreet < (intersectionPositionOnStreet + (Street.ROAD_WIDTH / 2.0f))) {
                    return intersection;
                }
            }
        }

        return null;
    }

    public void display() {
        TrafficSim.app.pushMatrix();
        TrafficSim.app.rectMode(PConstants.CENTER);

        TrafficSim.app.translate(position.x, position.y);
        TrafficSim.app.rotate(currentStreet.getStreetVector().angle);

        TrafficSim.app.fill(carColor);
        TrafficSim.app.rect(0, 0, CAR_LENGTH, CAR_WIDTH);

        TrafficSim.app.popMatrix();
    }
}
