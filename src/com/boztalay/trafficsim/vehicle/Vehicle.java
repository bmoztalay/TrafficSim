package com.boztalay.trafficsim.vehicle;

import com.boztalay.trafficsim.TrafficSim;
import com.boztalay.trafficsim.math.Point;
import com.boztalay.trafficsim.math.Vector;

/**
 * Author: Ben Oztalay
 * Copyright 2013
 */

public class Vehicle {
    private static final float VEHICLE_LENGTH = 25;
    private static final float VEHICLE_WIDTH = 10;

    protected int vehicleColor;

    protected Point position;
    protected Vector direction;
    protected float velocity;

    public Vehicle() {
        vehicleColor = TrafficSim.app.color(50, 200, 100);

        position = new Point();
        direction = new Vector();
        velocity = 0.0f;
    }

    public void setPosition(float x, float y) {
        position.x = x;
        position.y = y;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public void update() {
        position.x += direction.xComponent() * velocity;
        position.y += direction.yComponent() * velocity;
    }

    public void display() {
        TrafficSim.app.pushMatrix();
        TrafficSim.app.rectMode(TrafficSim.app.CENTER);

        TrafficSim.app.translate(position.x, position.y);
        TrafficSim.app.rotate(direction.angle);

        TrafficSim.app.fill(vehicleColor);
        TrafficSim.app.rect(0, 0, VEHICLE_LENGTH, VEHICLE_WIDTH);

        TrafficSim.app.popMatrix();
    }
}
