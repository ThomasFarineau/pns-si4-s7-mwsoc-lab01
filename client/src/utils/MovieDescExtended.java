package utils;

public class MovieDescExtended extends MovieDesc {
    byte[] teaser;

    public MovieDescExtended(String movieName, String isbn, String synopsis, byte[] teaser) {
        super(movieName, isbn, synopsis);
        this.teaser = teaser;
    }
}
