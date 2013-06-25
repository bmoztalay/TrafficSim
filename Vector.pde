class Vector {
    float angle;
    float magnitude;
 
    Vector() {
        angle = 0;
        magnitude = 1;   
    }
 
    Vector(float _angle, float _magnitude) {
        angle = _angle;
        magnitude = _magnitude;
    }
    
    void rotateBy(float angleInRadians) {
        angle += angleInRadians;
        
        if(angle > TWO_PI) {
            angle -= TWO_PI;
        } else if(angle < 0) {
            angle += TWO_PI;   
        }
    }

    float xComponent() {
        return magnitude * cos(angle);   
    }
    
    float yComponent() {
        return magnitude * sin(angle);   
    }
}
