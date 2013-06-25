class Vehicle {
    static final float VEHICLE_LENGTH = 25;
    static final float VEHICLE_WIDTH = 10;
    
    color vehicleColor;
    
    Point position;
    Vector direction;
    float velocity;
 
    Vehicle() {
        vehicleColor = color(50, 200, 100);
        
        position = new Point();
        direction = new Vector();
        velocity = 0;
    }
    
    void update() {
        position.x += direction.xComponent() * velocity;  
        position.y += direction.yComponent() * velocity; 
    }
    
    void display() {
        pushMatrix();
        rectMode(CENTER);

        translate(position.x, position.y);
        rotate(direction.angle);
        
        fill(vehicleColor);
        rect(0, 0, VEHICLE_LENGTH, VEHICLE_WIDTH);
        
        popMatrix();
    }
}
