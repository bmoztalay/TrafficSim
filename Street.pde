class Street {
    private static final float ROAD_WIDTH = 30;
    
    private Point startPoint;
    private Vector streetVector;
    private ArrayList<Intersection> intersections;
    
    private float speedLimit;

    Street(Point startPoint, Vector streetVector, float speedLimit) {
        this.startPoint = startPoint;
        this.streetVector = streetVector;
        this.intersections = new ArrayList<Intersection>();
        
        this.speedLimit = speedLimit;
    }   
    
    public Point getStartPoint() {
        return startPoint;
    }
    
    public Vector getStreetVector() {
        return streetVector;   
    }
    
    public float getSpeedLimit() {
        return speedLimit;   
    }
    
    public void addIntersection(Intersection intersection) {
        intersections.add(intersection);   
    }
    
    public void display() {
        pushMatrix();
        rectMode(CORNER);
     
        translate(startPoint.x, startPoint.y);
        rotate(streetVector.angle);
        
        fill(200);
        rect(0, -(ROAD_WIDTH / 2), streetVector.magnitude, ROAD_WIDTH);
     
        popMatrix();   
    }
}
