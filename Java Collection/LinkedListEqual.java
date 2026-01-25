import java.util.*;

public class LinkedListEqual {
    public static void main(String[] args) {
        LinkedList<Integer> l1 = new LinkedList<>(Arrays.asList(1,2,3));
        LinkedList<Integer> l2 = new LinkedList<>(Arrays.asList(1,2,3));

        System.out.println("Equal: " + l1.equals(l2));
    }
}