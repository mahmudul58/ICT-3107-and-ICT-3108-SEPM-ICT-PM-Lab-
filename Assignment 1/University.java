public class University {
    private String name;
    private String location;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void showInfo() {
        System.out.println(name+" is open for admission!");
    }
}
