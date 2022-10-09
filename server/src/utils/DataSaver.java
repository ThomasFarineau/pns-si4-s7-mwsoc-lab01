package utils;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataSaver {
    private static Path getDataPath() {
        String path = Paths.get("").toAbsolutePath().toString();
        //create server folder if it doesn't exist
        File serverFolder = new File(path + "\\server");
        if (!serverFolder.exists()) {
            serverFolder.mkdir();
        }
        //create data folder if it doesn't exist
        File dataFolder = new File(path + "\\server\\data");
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }
        return Paths.get(path, "server/data");
    }

    private static File getClientFile() {
        Path dataPath = getDataPath();
        File clientFile = new File(dataPath.toString(), "client.csv");
        if (!clientFile.exists()) {
            try {
                clientFile.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return clientFile;
    }

    public static void saveClient(String mail, String pwd) {
        File client = getClientFile();
        try {
            FileWriter writer = new FileWriter(client, true);
            BufferedWriter out = new BufferedWriter(writer);
            out.write(mail + "," + pwd);
            out.write(System.lineSeparator());
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Client> getClients() {
        File client = getClientFile();
        if (client.exists()) {
            List<Client> clientList = new ArrayList<>();
            try {
                FileReader reader = new FileReader(client);
                BufferedReader in = new BufferedReader(reader);
                String line;
                while ((line = in.readLine()) != null) {
                    String[] data = line.split(",");
                    clientList.add(new Client(data[0], data[1]));
                }
                in.close();
                return clientList;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            return new ArrayList<>();
        }
    }

    public static List<MovieDesc> getMovies() {
        File movie = new File(getDataPath().toString(), "movie.csv");
        if (movie.exists()) {
            List<MovieDesc> movieList = new ArrayList<>();
            try {
                FileReader reader = new FileReader(movie);
                BufferedReader in = new BufferedReader(reader);
                String line;
                while ((line = in.readLine()) != null) {
                    String[] data = line.split("§");
                    movieList.add(data.length == 3 ? new MovieDesc(data[0], data[1], data[2]) : new MovieDescExtended(data[0], data[1], data[2], data[3].getBytes()));
                }
                in.close();
                return movieList;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            return new ArrayList<>();
        }
    }
}
