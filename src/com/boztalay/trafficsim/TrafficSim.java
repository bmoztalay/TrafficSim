package com.boztalay.trafficsim;

import processing.core.PApplet;

/**
 * Author: Ben Oztalay
 * Copyright 2013
 */

public class TrafficSim extends PApplet {

    public void setup() {
        size(250, 250);

        car1 = new Car();
        car1.position.x = 125;
        car1.position.y = 235;
        car1.velocity = 2;

        street1 = new Street(new Point(25, 25), new Vector(radians(0), 200), 10);
        street2 = new Street(new Point(225, 25), new Vector(radians(90), 200), 10);
        street3 = new Street(new Point(225, 225), new Vector(radians(180), 200), 10);
        street4 = new Street(new Point(25, 225), new Vector(radians(270), 200), 10);
    }

   public  void draw() {
        background(128);

        street1.display();
        street2.display();
        street3.display();
        street4.display();

        car1.update();
        car1.display();

        fill(255);
    }
}
