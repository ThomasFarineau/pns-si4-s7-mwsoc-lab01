package implem;


import exceptions.InvalidCredentialsException;
import exceptions.SignUpFailed;
import interfaces.IConnection;
import interfaces.IVODService;
import utils.Client;
import utils.DataSaver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Connection extends UnicastRemoteObject implements IConnection {
    private final List<Client> clientList = new ArrayList<>();
    private final VODService movies;

    protected Connection() throws RemoteException {
        super();
        clientList.addAll(DataSaver.getClients());
        movies = new VODService();
    }

    /**
     * @param mail    the mail of the client
     * @param pwd    the password of the client
     * @return if the account is created
     * @throws RemoteException if the constructor of UnicastRemoteObject throws it
     * @throws SignUpFailed if the email is already used
     */
    @Override
    public boolean signUp(String mail, String pwd) throws RemoteException, SignUpFailed {
        if (clientAlreadyPresent(mail)) throw new SignUpFailed();
        DataSaver.saveClient(mail, pwd);
        return clientList.add(new Client(mail, pwd));
    }

    /**
     * @param mail   the mail of the client
     * @param pwd  the password of the client
     * @return the VOD service if the credentials are correct
     * @throws RemoteException if the constructor of UnicastRemoteObject throws it
     * @throws InvalidCredentialsException if the credentials are incorrect
     */
    @Override
    public IVODService login(String mail, String pwd) throws RemoteException, InvalidCredentialsException {
        for (Client client : clientList) {
            if (client.mail().equals(mail) && client.pwd().equals(pwd)) return movies;
        }
        throw new InvalidCredentialsException();
    }

    /**
     * @param mail the mail of the client
     * @return if the email is already used
     */
    private boolean clientAlreadyPresent(String mail) {
        return clientList.stream().anyMatch(client -> client.mail().equals(mail));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Connection that = (Connection) o;
        return Objects.equals(clientList, that.clientList) && Objects.equals(movies, that.movies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), clientList, movies);
    }
}
