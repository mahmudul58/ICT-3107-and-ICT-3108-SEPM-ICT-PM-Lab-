public class Human1 {
    private String name;
    private int age;

    public Human1(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void showStatus() {
        System.out.println(name+" is walking!");
    }
}
