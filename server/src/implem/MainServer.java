package implem;

import interfaces.IConnection;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MainServer {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(2001);
            IConnection connection = new Connection();
            registry.rebind("Connection", connection);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}