class Vector {
    public float angle;
    public float magnitude;
 
    Vector() {
        angle = 0.0f;
        magnitude = 1.0f;   
    }
 
    Vector(float angle, float magnitude) {
        this.angle = angle;
        this.magnitude = magnitude;
    }
    
    public void rotateBy(float angleInRadians) {
        angle += angleInRadians;
        
        if(angle > TWO_PI) {
            angle -= TWO_PI;
        } else if(angle < 0) {
            angle += TWO_PI;   
        }
    }

    public float xComponent() {
        return magnitude * cos(angle);   
    }
    
    public float yComponent() {
        return magnitude * sin(angle);   
    }
}
