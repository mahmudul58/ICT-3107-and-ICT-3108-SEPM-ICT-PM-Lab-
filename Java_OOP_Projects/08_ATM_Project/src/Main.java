import java.util.Scanner;

class ATM {
    private double balance = 500;

    void deposit(double amt) {
        balance += amt;
    }

    void withdraw(double amt) {
        if(amt <= balance) balance -= amt;
        else System.out.println("Insufficient balance");
    }

    void showBalance() {
        System.out.println("Balance: " + balance);
    }
}

public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM();
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("1.Deposit 2.Withdraw 3.Balance 4.Exit");
            int c = sc.nextInt();

            if(c == 1) atm.deposit(sc.nextDouble());
            else if(c == 2) atm.withdraw(sc.nextDouble());
            else if(c == 3) atm.showBalance();
            else break;
        }
    }
}