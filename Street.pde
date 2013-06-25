class Street {
    static final float ROAD_WIDTH = 30;
    
    Point startPoint;
    Vector streetVector;
    
    float speedLimit;

    Street(Point _startPoint, Vector _streetVector, float _speedLimit) {
        startPoint = _startPoint;
        streetVector = _streetVector;
        
        speedLimit = _speedLimit;
    }   
    
    
    void display() {
        pushMatrix();
        rectMode(CORNER);
     
        translate(startPoint.x, startPoint.y);
        rotate(streetVector.angle);
        
        fill(200);
        rect(0, -(ROAD_WIDTH / 2), streetVector.magnitude, ROAD_WIDTH);
     
        popMatrix();   
    }
}
