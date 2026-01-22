interface Readable {
    void read();
}

interface Writable {
    void write();
}

class FileHandler implements Readable, Writable {
    public void read() {
        System.out.println("Reading file");
    }

    public void write() {
        System.out.println("Writing file");
    }
}

public class Main {
    public static void main(String[] args) {
        FileHandler f = new FileHandler();
        f.read();
        f.write();
    }
}