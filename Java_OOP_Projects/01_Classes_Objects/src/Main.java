class Car {
    String color;
    int speed;

    void drive() {
        System.out.println("Car is driving");
    }
}

public class Main {
    public static void main(String[] args) {
        Car car = new Car();
        car.color = "Blue";
        car.speed = 80;
        car.drive();
    }
}