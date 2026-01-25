public class ParkingAgent extends Thread {

    private final ParkingPool pool;
    private final String agentName;

    public ParkingAgent(String name, ParkingPool pool) {
        this.agentName = name;
        this.pool = pool;
    }

    public void run() {
        while (true) {
            try {
                RegistrarParking car = pool.getCar();
                System.out.println(agentName + " parked Car " + car.getCarId());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}