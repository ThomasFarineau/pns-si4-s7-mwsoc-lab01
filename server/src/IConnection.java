import exceptions.InvalidCredentialsException;

import java.rmi.RemoteException;

public interface IConnection {
    boolean signUp(String mail, String pwd) throws RemoteException, SignUpFailed;
    IVODService login(String mail, String pwd) throws RemoteException, InvalidCredentialsException;
}
