package implem;

import interfaces.IConnection;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MainServer {
    public static void main(String[] args) {
        try {
            Registry reg = LocateRegistry.createRegistry(2001);
            IConnection d = new Connection();
            reg.rebind("MonOD", d);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}