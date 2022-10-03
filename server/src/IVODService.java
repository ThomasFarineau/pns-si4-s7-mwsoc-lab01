import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class IVODService extends UnicastRemoteObject implements VODService {
    public IVODService() throws RemoteException {
        super();
    }

    public List<MovieDesc> viewCatalog() {
        return null;
    }

    public Bill playmovie(String isbn, IClientBox box) {
        return null;
    }
}
