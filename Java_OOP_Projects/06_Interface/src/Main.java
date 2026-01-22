interface Printer {
    void print();
}

class HPPrinter implements Printer {
    public void print() {
        System.out.println("Printing...");
    }
}

public class Main {
    public static void main(String[] args) {
        Printer p = new HPPrinter();
        p.print();
    }
}