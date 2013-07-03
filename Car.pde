class Car extends Vehicle {

    void update() {
        velocity = 2;
        direction.rotateBy(-0.02);
     
        super.update();   
    }
}
