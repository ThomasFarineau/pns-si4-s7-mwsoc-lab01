package utils;

import interfaces.IClientBox;
import interfaces.IVODService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class VODService extends UnicastRemoteObject implements IVODService {
    public VODService() throws RemoteException {
        super();
    }

    public List<MovieDesc> viewCatalog() {
        return null;
    }

    public Bill playmovie(String isbn, IClientBox box) {
        return null;
    }
}
