package utils;

import java.io.Serializable;

public class MovieDesc implements Serializable {
    private final String movieName;
    private final String isbn;
    private final String synopsis;

    public MovieDesc(String movieName, String isbn, String synopsis) {
        this.movieName = movieName;
        this.isbn = isbn;
        this.synopsis = synopsis;
    }

    /**
     * @return the movie name
     */
    public String getMovieName() {
        return movieName;
    }

    /**
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @return the toString method
     */
    @Override
    public String toString() {
        return isbn + " " + synopsis;
    }
}
