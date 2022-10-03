package implem;

import exceptions.InvalidCredentialsException;
import exceptions.SignUpFailed;
import interfaces.IConnection;
import interfaces.IVODService;
import utils.Bill;
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
            afterLogin(beforeLogin(connection));
        } catch (Exception e) {
            System.out.println("Client err: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void afterLogin(IVODService vodService) throws RemoteException {
        // Default client actions from flow
        List<MovieDesc> catalog = vodService.viewCatalog();
        Scanner scanner = new Scanner(System.in);

        // Get and print catalog
        printDescs(catalog);
        String isbn = getIsbn(catalog);
        // Play movie
        playMovie(vodService, isbn);
        System.out.print("Do you want to watch another movie ? (y/n) ");
        String choice = scanner.nextLine();
        switch (choice) {
            case "y" -> afterLogin(vodService);
            case "n" -> System.exit(0);
            default -> System.out.println("Invalid choice !");
        }
    }

    private static void playMovie(IVODService vodService, String isbn) throws RemoteException {
        ClientBox clientBox = new ClientBox();
        Bill bill = vodService.playmovie(isbn, clientBox);
        System.out.println("You have been charged " + bill.getOutrageousPrice() + " euros.");
    }

    private static String getIsbn(List<MovieDesc> catalog) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the ISBN of the movie you want to watch:");
        String isbn = scanner.nextLine();
        while (!isbnValid(isbn, catalog)) {
            System.out.println("ISBN not valid !");
            isbn = scanner.nextLine();
        }
        return isbn;
    }

    private static void printDescs(List<MovieDesc> catalog) {
        catalog.forEach(System.out::println);
    }

    private static boolean isbnValid(String isbn, List<MovieDesc> catalog) {
        return catalog.stream().anyMatch(desc -> desc.getIsbn().equals(isbn));
    }

    private static IVODService beforeLogin(IConnection connection) throws RemoteException {
        System.out.println("Choose an action:");
        System.out.println("1. Sign up");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        switch (choice) {
            case "1" -> {
                System.out.println("Mail: ");
                String mail = scanner.next();
                System.out.println("Password: ");
                String pwd = scanner.next();
                try {
                    connection.signUp(mail, pwd);
                } catch (SignUpFailed e) {
                    e.printStackTrace();
                }
                return beforeLogin(connection);
            }
            case "2" -> {
                System.out.println("Mail: ");
                String mail = scanner.next();
                System.out.println("Password: ");
                String pwd = scanner.next();
                try {
                    return connection.login(mail, pwd);
                } catch (InvalidCredentialsException e) {
                    e.printStackTrace();
                    return beforeLogin(connection);
                }
            }
            case "3" -> System.exit(0);
            default -> System.out.println("Invalid choice");
        }
        return null;
    }

}