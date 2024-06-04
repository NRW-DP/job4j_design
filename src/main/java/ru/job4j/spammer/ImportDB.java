package ru.job4j.spammer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;

public class ImportDB {

    private final Properties config;
    private final String dump;

    private final static String INSERT_USER_IN_TABLE = """
            INSERT INTO users(name, email) VALUES (?, ?)
            """;

    public ImportDB(Properties config, String dump) {
        this.config = config;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(dump))) {
            reader.lines()
                    .filter(line -> !line.trim().isEmpty())
                    .forEach(line -> {
                        String[] data = line.split(";");
                        if (data.length != 2 || data[0].trim().isEmpty() || data[1].trim().isEmpty()) {
                            throw new IllegalArgumentException("Invalid data format: " + line);
                        }
                        users.add(new User(data[0].trim(), data[1].trim()));
                    });
        }
        return users;
    }


    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(config.getProperty("db_spammer.driver"));
        try (Connection connection = DriverManager.getConnection(
                config.getProperty("db_spammer.url"),
                config.getProperty("db_spammer.username"),
                config.getProperty("db_spammer.password")
        )) {
            for (User user : users) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_IN_TABLE)) {
                    preparedStatement.setString(1, user.getName());
                    preparedStatement.setString(2, user.getEmail());
                    preparedStatement.execute();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream input = ImportDB.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(input);
        }

        String dumpPath = "./dump.txt";
        if (!Files.exists(Paths.get(dumpPath))) {
            throw new FileNotFoundException("Dump file not found: " + dumpPath);
        }

        ImportDB dataBase = new ImportDB(config, dumpPath);
        dataBase.save(dataBase.load());
    }
}
