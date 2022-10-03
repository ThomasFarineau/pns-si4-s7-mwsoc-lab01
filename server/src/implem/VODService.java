package implem;

import interfaces.IClientBox;
import interfaces.IVODService;
import utils.Bill;
import utils.DataSaver;
import utils.MovieDesc;
import utils.MovieDescExtended;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

public class VODService extends UnicastRemoteObject implements IVODService {
    List<MovieDesc> movieDescList;
    HashMap<String, byte[]> movieMap;

    public VODService() throws RemoteException {
        super();
        movieDescList = new ArrayList<>();
        movieMap = new HashMap<>();
        instantiateMovies();
    }

    private void instantiateMovies() {
        movieDescList.addAll(DataSaver.getMovies());
        byte[] alphabet = new byte[26];
        for (int i = 0; i < 26; i++) alphabet[i] = (byte) ('a' + i);
        movieDescList.forEach(movieDesc -> movieMap.put(movieDesc.getIsbn(), alphabet));
    }

    public List<MovieDesc> viewCatalog() {
        return movieDescList;
    }

    public Bill playmovie(String isbn, IClientBox box) {
        byte[] movie = movieMap.get(isbn);
        String movieName = movieDescList.stream().filter(movieDesc -> movieDesc.getIsbn().equals(isbn)).findFirst().get().getMovieName();

        Runnable runnable = () -> {
            try {
                Thread.sleep(5);
                for (int i = 0; i < movie.length; i = i + 6) {
                    int length = 6;
                    if (movie.length - i < length) {
                        length = movie.length - i;
                    }
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
