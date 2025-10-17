public class MainMusic {
    public static void main(String[] args) {
        System.out.println("\nMusic object ...set with setter");
        Music m = new Music();
        m.setTitle("Shape of You");
        m.setArtist("Ed Sheeran");
        System.out.println("Title: " + m.getTitle());
        System.out.println("Artist: " + m.getArtist());
        m.play();

        System.out.println("\nMusic1 object ...set with constructor");
        Music1 m1 = new Music1("Perfect", "Ed Sheeran");
        System.out.println("Title: " + m1.getTitle());
        System.out.println("Artist: " + m1.getArtist());
        m1.play();
    }
}
