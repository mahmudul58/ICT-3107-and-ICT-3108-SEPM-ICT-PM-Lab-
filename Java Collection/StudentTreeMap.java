import java.util.*;

class Student {
    int id;
    String name;

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String toString() {
        return id + " " + name;
    }
}

public class StudentTreeMap {
    public static void main(String[] args) {
        TreeMap<Integer, Student> map = new TreeMap<>();

        map.put(101, new Student(101, "Rahim"));
        map.put(102, new Student(102, "Karim"));

        System.out.println(map);
    }
}