package com.boztalay.trafficsim.vehicle;

/**
 * Author: Ben Oztalay
 * Copyright 2013
 */

public class Car extends Vehicle {

    public void update() {
        velocity = 2;
        direction.rotateBy(-0.02f);

        super.update();
    }
}
