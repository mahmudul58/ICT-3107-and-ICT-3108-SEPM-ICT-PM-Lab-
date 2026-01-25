import java.util.*;

public class KthSmallest {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(7, 4, 1, 9, 2));
        int k = 3;
        Collections.sort(list);
        System.out.println("Kth Smallest: " + list.get(k - 1));
    }
}