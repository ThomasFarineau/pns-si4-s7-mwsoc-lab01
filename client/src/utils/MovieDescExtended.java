package utils;

public class MovieDescExtended extends MovieDesc {
    byte[] teaser;

    public MovieDescExtended(String movieName, String isbn, String synopsis, byte[] teaser) {
        super(movieName, isbn, synopsis);
        this.teaser = teaser;
    }

    /**
     *
     * @return the teaser of the movie with the movie name and the synopsis
     */
    @Override
    public String toString() {
        StringBuilder toReturn = new StringBuilder(super.toString() + " ");
        for (byte b : teaser) toReturn.append((char) b);
        return toReturn.toString();
    }
}
