import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientBox extends UnicastRemoteObject implements IClientBox {
    public ClientBox() throws RemoteException {
        super();
    }

    public void stream(byte[] chunk) {

    }
}
