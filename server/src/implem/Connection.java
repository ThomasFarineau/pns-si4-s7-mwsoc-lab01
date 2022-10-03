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

public class Connection extends UnicastRemoteObject implements IConnection {
    List<Client> clientList;
    IVODService movies;

    protected Connection() throws RemoteException {
        super();
        clientList = DataSaver.getClients();
        movies = new VODService();
    }

    @Override
    public boolean signUp(String mail, String pwd) throws RemoteException, SignUpFailed {
        if (clientAlreadyPresent(mail))
            throw new SignUpFailed();
        DataSaver.saveClient(mail, pwd);
        return clientList.add(new Client(mail, pwd));
    }

    @Override
    public IVODService login(String mail, String pwd) throws RemoteException, InvalidCredentialsException {
        for (Client client : clientList) {
            if (client.getMail().equals(mail))
                if (client.getPwd().equals(pwd))
                    return movies;
        }
        throw new InvalidCredentialsException();
    }

    private boolean clientAlreadyPresent(String mail) {
        for (Client client : clientList)
            if (client.getMail().equals(mail))
                return true;
        return false;
    }

}
