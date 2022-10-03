package utils;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataSaver {
    private static Path getDataPath() {
        String path = Paths.get("").toAbsolutePath().toString();
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
    }
}
