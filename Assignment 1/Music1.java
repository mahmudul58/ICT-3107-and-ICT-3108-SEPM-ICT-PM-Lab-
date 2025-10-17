public class Music1 {
    private String title;
    private String artist;

    public Music1(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public void play() {
        System.out.println("'"+title+"' is playing now...");
    }
}
