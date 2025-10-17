public class Music {
    private String title;
    private String artist;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public void play() {
        System.out.println("'"+title+"' is playing now...");
    }
}
