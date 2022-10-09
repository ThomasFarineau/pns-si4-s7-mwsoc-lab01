package interfaces;

import utils.Bill;
import utils.MovieDesc;

import java.rmi.Remote;
        import java.rmi.RemoteException;
        import java.util.List;

public interface IVODService extends Remote {

    /**
     * @return the list of movies available on the VOD service
     */
    List<MovieDesc> viewCatalog() throws RemoteException;

    /**
     * Play a movie with multiple thread and return the bill
     *
     * @param isbn the isbn of the movie to play
     * @param box  the client box
     * @return the bill of the movie
     */
    Bill playmovie(String isbn, IClientBox box) throws RemoteException;
}
