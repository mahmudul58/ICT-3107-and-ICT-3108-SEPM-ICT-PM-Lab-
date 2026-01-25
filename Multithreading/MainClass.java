import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {

        ParkingPool pool = new ParkingPool();

        new ParkingAgent("Agent-1", pool).start();
        new ParkingAgent("Agent-2", pool).start();
        new ParkingAgent("Agent-3", pool).start();

        Scanner sc = new Scanner(System.in);
        int count = 0;

        while (count < 10) {
            System.out.println("Press Enter to register a car...");
            sc.nextLine();
            pool.addCar(new RegistrarParking());
            count++;
        }
        sc.close();
    }
}