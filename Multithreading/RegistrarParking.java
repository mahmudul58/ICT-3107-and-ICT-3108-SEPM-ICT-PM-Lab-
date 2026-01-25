public class RegistrarParking {
    private static int counter = 1;
    private final int carId;

    public RegistrarParking() {
        this.carId = counter++;
    }

    public int getCarId() {
        return carId;
    }
}