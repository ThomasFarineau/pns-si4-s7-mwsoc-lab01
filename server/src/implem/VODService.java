package implem;

import interfaces.IClientBox;
import interfaces.IVODService;
import utils.Bill;
import utils.DataSaver;
import utils.MovieDesc;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VODService extends UnicastRemoteObject implements IVODService {

    private final List<MovieDesc> movieDescList;
    private final HashMap<String, byte[]> movieMap;

    /**
     * Constructor
     * @throws RemoteException if the constructor of UnicastRemoteObject throws it
     */
    public VODService() throws RemoteException {
        super();
        movieDescList = new ArrayList<>();
        movieMap = new HashMap<>();
        instantiateMovies();
    }

    /**
     * instantiate movies with fake data for the demo
     */
    private void instantiateMovies() {
        movieDescList.addAll(DataSaver.getMovies());
        byte[] alphabet = new byte[26];
        for (int i = 0; i < 26; i++) alphabet[i] = (byte) ('a' + i);
        movieDescList.forEach(movieDesc -> movieMap.put(movieDesc.getIsbn(), alphabet));
    }

    /**
     * @return the list of movies available on the VOD service
     */
    public List<MovieDesc> viewCatalog() {
        return movieDescList;
    }

    /**
     * Play a movie with multiple thread and return the bill
     *
     * @param isbn the isbn of the movie to play
     * @param box  the client box
     * @return the bill of the movie
     */
    public Bill playmovie(String isbn, IClientBox box) {
        byte[] movie = movieMap.get(isbn);
        MovieDesc found = movieDescList.stream().filter(movieDesc -> movieDesc.getIsbn().equals(isbn)).findFirst().orElse(null);
        if (found == null) return null;
        String movieName = found.getMovieName();

        Runnable runnable = () -> {
            try {
                Thread.sleep(5);
                for (int i = 0; i < movie.length; i = i + 6) {
                    int length = 6;
                    if (movie.length - i < length) length = movie.length - i;
                    byte[] chunk = new byte[length];
                    System.arraycopy(movie, i, chunk, 0, length);
                    box.stream(chunk);
                }
            } catch (RemoteException | InterruptedException e) {
                e.printStackTrace();
            }
        };
        runnable.run();
        return new Bill(movieName, new BigInteger("100"));
    }
}
