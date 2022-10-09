package interfaces;

import exceptions.InvalidCredentialsException;
import exceptions.SignUpFailed;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IConnection extends Remote {

    /**
     * @param mail    the mail of the client
     * @param pwd    the password of the client
     * @return if the account is created
     * @throws RemoteException if the constructor of UnicastRemoteObject throws it
     * @throws SignUpFailed if the email is already used
     */
    boolean signUp(String mail, String pwd) throws RemoteException, SignUpFailed;

    /**
     * @param mail   the mail of the client
     * @param pwd  the password of the client
     * @return the VOD service if the credentials are correct
     * @throws RemoteException if the constructor of UnicastRemoteObject throws it
     * @throws InvalidCredentialsException if the credentials are incorrect
     */
    IVODService login(String mail, String pwd) throws RemoteException, InvalidCredentialsException;
}
