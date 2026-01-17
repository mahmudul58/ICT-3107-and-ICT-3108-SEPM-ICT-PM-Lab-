import java.util.Scanner;

public class RandomDemo {
    public static void main(String[] args) {
        System.out.println("--- Custom PRNG Algorithm Demo ---");

        System.out.println("Random Integer: " + MyCustomRandom.nextInt());

        System.out.print("10 Dice Rolls: ");
        for(int i = 0; i < 10; i++) {
            System.out.print(MyCustomRandom.nextInt(1, 6) + " ");
        }
        System.out.println();

        System.out.println("Random Double (0.0-1.0): " + MyCustomRandom.nextDouble());
        System.out.println("Random Float (0.0-1.0): " + MyCustomRandom.nextFloat());

        System.out.println("\n--- Array Demo ---");
        fillAndPrintArray();
    }

    public static void fillAndPrintArray() {
        double[] myList = new double[5];

        System.out.println("Filling array with custom random doubles...");

        for(int i = 0; i < myList.length; i++) {
            myList[i] = MyCustomRandom.nextDouble();
        }
        
        System.out.print("Generated Array values: ");
        for (double v : myList) {
            System.out.printf("%.4f  ", v);
        }
        System.out.println();
    }
}