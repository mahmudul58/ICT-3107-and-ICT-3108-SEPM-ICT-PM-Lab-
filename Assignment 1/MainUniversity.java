public class MainUniversity {
    public static void main(String[] args) {
        System.out.println("\nUniversity object ...set with setter");
        University u = new University();
        u.setName("MBSTU");
        u.setLocation("Tangail");
        System.out.println("Name: " + u.getName());
        System.out.println("Location: " + u.getLocation());
        u.showInfo();

        System.out.println("\nUniversity1 object ...set with constructor");
        University1 u1 = new University1("DU", "Dhaka");
        System.out.println("Name: " + u1.getName());
        System.out.println("Location: " + u1.getLocation());
        u1.showInfo();
    }
}
