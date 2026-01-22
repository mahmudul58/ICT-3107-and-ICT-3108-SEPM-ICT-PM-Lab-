import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double a = sc.nextDouble();
        double b = sc.nextDouble();
        char op = sc.next().charAt(0);

        double r = 0;

        if(op == '+') r = a + b;
        else if(op == '-') r = a - b;
        else if(op == '*') r = a * b;
        else if(op == '/') r = a / b;

        System.out.println("Result: " + r);
    }
}