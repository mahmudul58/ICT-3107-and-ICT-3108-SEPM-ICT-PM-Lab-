class Vehicle {
    protected String type = "Vehicle";
}

class Bike extends Vehicle {
    void show() {
        System.out.println(type + " running");
    }
}

public class Main {
    public static void main(String[] args) {
        Bike b = new Bike();
        b.show();
    }
}