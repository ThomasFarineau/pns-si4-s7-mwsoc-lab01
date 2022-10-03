package utils;

public class MovieDesc {
    String movieName;
    String isbn;
    String synopsis;

    @Override
    public String toString() {
        return isbn + " " + synopsis;
    }
}
