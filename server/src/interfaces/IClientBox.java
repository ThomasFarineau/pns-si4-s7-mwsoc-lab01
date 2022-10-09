package interfaces;

import java.rmi.RemoteException;

public interface IClientBox extends java.rmi.Remote {
    /**
     * @param chunk the chunk of the movie to play
     * @throws RemoteException if the constructor of UnicastRemoteObject throws it
     */
    void stream(byte[] chunk) throws RemoteException;
}
