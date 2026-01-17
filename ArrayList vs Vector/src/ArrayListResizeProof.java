import java.util.ArrayList;
import java.lang.reflect.Field;

public class ArrayListResizeProof {
    public static void main(String[] args) throws Exception {
        ArrayList<String> list = new ArrayList<>(10);

        for (int i = 0; i < 10; i++) {
            list.add("Item " + i);
        }
        System.out.println("--- ArrayList Status (Full) ---");
        System.out.println("Size: " + list.size());
        System.out.println("Capacity: " + getCapacity(list));

        System.out.println("\n... Adding 11th element ...");
        list.add("Item 10");

        System.out.println("\n--- ArrayList Status (After Resize) ---");
        System.out.println("Size: " + list.size());
        System.out.println("Capacity: " + getCapacity(list));

        System.out.println("\nVERDICT: 10 + (50% of 10) = 15.");
    }

    public static int getCapacity(ArrayList<?> l) throws Exception {
        Field dataField = ArrayList.class.getDeclaredField("elementData");
        dataField.setAccessible(true);
        return ((Object[]) dataField.get(l)).length;
    }
}