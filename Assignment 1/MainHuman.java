public class MainHuman {
    public static void main(String[] args) {
        System.out.println("\nHuman object ...set with setter");
        Human h = new Human();
        h.setName("Mahmudul");
        h.setAge(22);
        System.out.println("Name: " + h.getName());
        System.out.println("Age: " + h.getAge());
        h.showStatus();

        System.out.println("\nHuman1 object ...set with constructor");
        Human1 h1 = new Human1("Amit", 22);
        System.out.println("Name: " + h1.getName());
        System.out.println("Age: " + h1.getAge());
        h1.showStatus();
    }
}
