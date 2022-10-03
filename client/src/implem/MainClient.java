package implem;

import exceptions.InvalidCredentialsException;
import exceptions.SignUpFailed;
import interfaces.IConnection;
import interfaces.IVODService;
import utils.MovieDesc;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class MainClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 2001);
            IConnection connection = (IConnection) registry.lookup("Connection");
            IVODService vodService = null;
            while (vodService == null) {
                vodService = chooseConnectionAction(connection);
            }
            vodService.viewCatalog().forEach(System.out::println);


        } catch (Exception e) {
            System.out.println("Client err: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static IVODService chooseConnectionAction(IConnection connection) throws RemoteException {
        System.out.println("Choose an action:");
        System.out.println("1. Sign up");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        Scanner scanner = new Scanner(System.in);
        int choice;
        try {
            choice = Integer.parseInt(scanner.next());
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice");
            return null;
        }
        switch (choice) {
            case 1 -> {
                System.out.println("Mail: ");
                String mail = scanner.next();
                System.out.println("Password: ");
                String pwd = scanner.next();
                try {
                    connection.signUp(mail, pwd);
                } catch (SignUpFailed e) {
                    e.printStackTrace();
                }
            }
            case 2 -> {
                System.out.println("Mail: ");
                String mail = scanner.next();
                System.out.println("Password: ");
                String pwd = scanner.next();
                try {
                    return connection.login(mail, pwd);
                } catch (InvalidCredentialsException e) {
                    e.printStackTrace();
                }
            }
            case 3 -> System.exit(0);
            default -> System.out.println("Invalid choice");
        }
        return null;
    }

}