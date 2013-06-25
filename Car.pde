class Car extends Vehicle {

    void update() {
        direction.rotateBy(-0.02);
     
        super.update();   
    }
}
