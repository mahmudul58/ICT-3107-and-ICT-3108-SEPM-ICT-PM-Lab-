import java.util.*;

public class QueueStackPQ {
    public static void main(String[] args) {

        PriorityQueue<Integer> queue =
                new PriorityQueue<>(); // Queue

        PriorityQueue<Integer> stack =
                new PriorityQueue<>(Collections.reverseOrder()); // Stack

        queue.add(10); queue.add(20); queue.add(30);
        stack.add(10); stack.add(20); stack.add(30);

        System.out.println("Queue:");
        while(!queue.isEmpty())
            System.out.println(queue.poll());

        System.out.println("Stack:");
        while(!stack.isEmpty())
            System.out.println(stack.poll());
    }
}