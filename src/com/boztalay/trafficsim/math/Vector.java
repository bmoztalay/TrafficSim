package com.boztalay.trafficsim.math;

import com.boztalay.trafficsim.TrafficSim;

/**
 * Author: Ben Oztalay
 * Copyright 2013
 */

public class Vector {
    public float angle;
    public float magnitude;

    public Vector() {
        angle = 0.0f;
        magnitude = 1.0f;
    }

    public Vector(float angle, float magnitude) {
        this.angle = angle;
        this.magnitude = magnitude;
    }

    public void rotateBy(float angleInRadians) {
        angle += angleInRadians;

        if(angle > TrafficSim.app.TWO_PI) {
            angle -= TrafficSim.app.TWO_PI;
        } else if(angle < 0) {
            angle += TrafficSim.app.TWO_PI;
        }
    }

    public float xComponent() {
        return magnitude * TrafficSim.app.cos(angle);
    }

    public float yComponent() {
        return magnitude * TrafficSim.app.sin(angle);
    }
}
