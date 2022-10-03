package interfaces;

import exceptions.InvalidCredentialsException;
import exceptions.SignUpFailed;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IConnection extends Remote {
    boolean signUp(String mail, String pwd) throws RemoteException, SignUpFailed;
    IVODService login(String mail, String pwd) throws RemoteException, InvalidCredentialsException;
}
