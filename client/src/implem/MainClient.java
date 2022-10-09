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

    /**
     * @param vodService the VOD service to use to play the movie
     * @throws RemoteException if the constructor of UnicastRemoteObject throws it
     */
    private static void afterLogin(IVODService vodService) throws RemoteException {
        // Default client actions from flow
        List<MovieDesc> catalog = vodService.viewCatalog();
        Scanner scanner = new Scanner(System.in);
        // Get and print catalog
        printDescs(catalog);
        String isbn = getIsbn(catalog);
        // Play movie and print bill
        playMovie(vodService, isbn);
        System.out.print("Do you want to watch another movie ? (y/n) ");
        String choice = scanner.nextLine();
        switch (choice) {
            // Play another movie
            case "y" -> afterLogin(vodService);
            // Exit
            case "n" -> System.exit(0);
            // Invalid choice
            default -> System.out.println("Invalid choice !");
        }
    }

    /**
     * @param vodService the VOD service to use to play the movie
     * @param isbn       the isbn of the movie to play
     * @throws RemoteException if the constructor of UnicastRemoteObject throws it
     */
    private static void playMovie(IVODService vodService, String isbn) throws RemoteException {
        ClientBox clientBox = new ClientBox();
        // Play movie
        Bill bill = vodService.playmovie(isbn, clientBox);
        // Print bill
        System.out.println("You have been charged " + bill.outrageousPrice() + " euros.");
    }

    /**
     * @param catalog the catalog with the movies
     * @return the isbn of the movie to play
     */
    private static String getIsbn(List<MovieDesc> catalog) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the ISBN of the movie you want to watch:");
        String isbn = scanner.nextLine();
        // Check if the isbn is valid and in the catalog (if not, ask again)
        while (!isbnValid(isbn, catalog)) {
            System.out.println("ISBN not valid !");
            isbn = scanner.nextLine();
        }
        return isbn;
    }

    /**
     * @param catalog the catalog with the movies to print
     */
    private static void printDescs(List<MovieDesc> catalog) {
        catalog.forEach(System.out::println);
    }

    /**
     * @param isbn    the isbn to check
     * @param catalog the catalog with the movies
     * @return true if the movie is in the catalog and false otherwise
     */
    private static boolean isbnValid(String isbn, List<MovieDesc> catalog) {
        return catalog.stream().anyMatch(desc -> desc.getIsbn().equals(isbn));
    }

    /**
     * @param connection the connection to use when logging in
     * @return the VOD service to use to play the movie
     * @throws RemoteException if the constructor of UnicastRemoteObject throws it
     */
    private static IVODService beforeLogin(IConnection connection) throws RemoteException {
        // Default client actions from flow
        System.out.println("Choose an action:");
        System.out.println("1. Sign up");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        switch (choice) {
            // Sign up
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
                // Recursive call
                return beforeLogin(connection);
            }
            // Login
            case "2" -> {
                System.out.println("Mail: ");
                String mail = scanner.next();
                System.out.println("Password: ");
                String pwd = scanner.next();
                try {
                    // Get VOD service
                    return connection.login(mail, pwd);
                } catch (InvalidCredentialsException e) {
                    e.printStackTrace();
                    // Retry
                    return beforeLogin(connection);
                }
            }
            // Exit
            case "3" -> System.exit(0);
            // Invalid choice
            default -> {
                System.out.println("Invalid choice");
                return beforeLogin(connection);
            }
        }
        // Should never happen
        return null;
    }

}