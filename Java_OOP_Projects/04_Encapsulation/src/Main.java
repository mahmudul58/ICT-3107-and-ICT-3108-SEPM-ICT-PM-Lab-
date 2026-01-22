class Account {
    private double balance;

    public void addMoney(double amount) {
        if(amount > 0) balance += amount;
    }

    public double getBalance() {
        return balance;
    }
}

public class Main {
    public static void main(String[] args) {
        Account a = new Account();
        a.addMoney(1000);
        System.out.println(a.getBalance());
    }
}