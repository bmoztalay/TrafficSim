class Intersection {
    private ArrayList<Street> streets;
    private FloatList positionsOnStreets;
 
    Intersection() {
        streets = new ArrayList<Street>();
        positionsOnStreets = new FloatList();
    }
   
   public void addStreet(Street street, float positionOnStreet) {
       streets.add(street);
       positionsOnStreets.append(positionOnStreet);  
   }
   
   public ArrayList<Street> getStreets() {
       return streets;   
   }
   
   public float getPositionOnStreet(Street street) {
       if(streets.contains(street)) {
           int streetIndex = streets.indexOf(street);
           return positionsOnStreets.get(streetIndex);
       } else {
           return -1.0f;   
       }
   }
   
   public void display() {
       pushMatrix();
    
       popMatrix();   
   }
}
