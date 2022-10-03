package utils;

public class MovieDesc {
    String movieName;
    String isbn;
    String synopsis;

    public MovieDesc(String movieName, String isbn, String synopsis) {
        this.movieName = movieName;
        this.isbn = isbn;
        this.synopsis = synopsis;
    }

    @Override
    public String toString() {
        return isbn + " " + synopsis;
    }
}
