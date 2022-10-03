package implem;

import exceptions.InvalidCredentialsException;
import exceptions.SignUpFailed;
import interfaces.IConnection;
import interfaces.IVODService;
import utils.MovieDesc;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

public class MainClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 2001);
            IConnection connection = (IConnection) registry.lookup("Connection");
            IVODService vodService = null;

            // Authenticate
            while (vodService == null) {
                vodService = chooseConnectionAction(connection);
            }

            // Get and print catalog
            List<MovieDesc> catalog =  vodService.viewCatalog();
            catalog.forEach(System.out::println);

            // Get ISBN
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the ISBN of the movie you want to watch:");
            String isbn = scanner.nextLine();
            while (!isbnValid(isbn, catalog)) {
                System.out.println("ISBN not valid !");
                isbn = scanner.nextLine();
            }

            System.out.println("Vous avez choisi l'ISBN : " + isbn);

            // Play movie
            ClientBox clientBox = new ClientBox();
            vodService.playmovie(isbn, clientBox);

        } catch (Exception e) {
            System.out.println("Client err: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static boolean isbnValid(String isbn, List<MovieDesc> descs) {
        for (MovieDesc desc : descs)
            if (desc.getIsbn().equals(isbn))
                return true;
        return false;
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