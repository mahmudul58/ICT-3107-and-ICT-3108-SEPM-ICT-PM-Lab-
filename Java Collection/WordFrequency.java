import java.util.*;

public class WordFrequency {
    public static void main(String[] args) {
        String text = "java is easy and java is powerful";
        TreeMap<String, Integer> map = new TreeMap<>();

        for (String word : text.split(" ")) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        System.out.println(map);
    }
}