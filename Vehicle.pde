class Vehicle {
    private static final float VEHICLE_LENGTH = 25;
    private static final float VEHICLE_WIDTH = 10;
    
    protected color vehicleColor;

    protected Point position;
    protected Vector direction;
    protected float velocity;
 
    Vehicle() {
        vehicleColor = color(50, 200, 100);
        
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
        pushMatrix();
        rectMode(CENTER);

        translate(position.x, position.y);
        rotate(direction.angle);
        
        fill(vehicleColor);
        rect(0, 0, VEHICLE_LENGTH, VEHICLE_WIDTH);
        
        popMatrix();
    }
}
