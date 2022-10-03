import exceptions.InvalidCredentialsException;
import exceptions.SignInFailed;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Connection extends UnicastRemoteObject {
    List<Client> clientList;
    List<MovieDesc> movies;

    protected Connection() throws RemoteException {
        clientList = new ArrayList<>();
        movies = new ArrayList<>();
    }

    boolean signUp(String mail, String pwd) throws RemoteException, SignInFailed {
        return false;
    }

    IVODService login(String mail, String pwd) throws RemoteException, InvalidCredentialsException {
        return null;
    }

}
