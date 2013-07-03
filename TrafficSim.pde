ArrayList<Car> cars;
StreetMap streetMap;

void setup() {
    size(250, 250);
    
    streetMap = new StreetMap(250, 250);
    
    cars = new ArrayList<Car>();
    makeCars();
}

void makeCars() {
//    Car car1 = new Car();
//    car1.setPosition(125, 235);
//    cars.add(car1);
}

void draw() {
    background(128);

    streetMap.display();

    for(Car car : cars) {
        car.update();
        car.display();
    }
}

