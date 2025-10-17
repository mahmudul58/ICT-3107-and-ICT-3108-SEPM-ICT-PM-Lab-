public class University1 {
    private final String name;
    private final String location;

    public University1(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public void showInfo() {
        System.out.println(name+" is open for admission!");
    }
}
