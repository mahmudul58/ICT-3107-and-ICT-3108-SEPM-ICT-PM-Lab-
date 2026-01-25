import java.util.LinkedList;
import java.util.Queue;

public class ParkingPool {
    private final Queue<RegistrarParking> queue = new LinkedList<>();

    public synchronized void addCar(RegistrarParking car) {
        queue.add(car);
        System.out.println("Car " + car.getCarId() + " entered parking queue");
        notifyAll();
    }

    public synchronized RegistrarParking getCar() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        return queue.poll();
    }
}