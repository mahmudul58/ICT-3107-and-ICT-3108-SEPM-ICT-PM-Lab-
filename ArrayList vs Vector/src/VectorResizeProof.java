import java.util.Vector;

public class VectorResizeProof {
    public static void main(String[] args) {
        // 1. Create Vector with explicit initial capacity of 10
        Vector<String> vector = new Vector<>(10);

        // 2. Fill the vector to its capacity
        for (int i = 0; i < 10; i++) {
            vector.add("Item " + i);
        }

        System.out.println("--- Vector Status (Full) ---");
        System.out.println("Size: " + vector.size());
        System.out.println("Capacity: " + vector.capacity()); // Vector has a built-in capacity() method

        // 3. Add the 11th element to trigger resize
        System.out.println("\n... Adding 11th element ...");
        vector.add("Item 10");

        System.out.println("\n--- Vector Status (After Resize) ---");
        System.out.println("Size: " + vector.size());
        System.out.println("Capacity: " + vector.capacity());

        System.out.println("\nVERDICT: 10 + (100% of 10) = 20.");
    }
}