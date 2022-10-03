package implem;

import interfaces.IClientBox;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientBox extends UnicastRemoteObject implements IClientBox {
    public ClientBox() throws RemoteException {
        super();
    }

    public void stream(byte[] chunk) {
        for (byte b : chunk) System.out.print((char) b);
        System.out.println("");
    }
}
