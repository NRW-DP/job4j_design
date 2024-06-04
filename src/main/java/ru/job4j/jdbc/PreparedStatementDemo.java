package ru.job4j.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PreparedStatementDemo {
    private Connection connection;

    private final static String INSERT_IN_TABLE = """
            INSERT INTO cities(name, population) VALUES (?, ?)
            """;
    private final static String UPDATE_TABLE = """
            UPDATE cities SET name = ?, population = ?, WHERE id = ?
            """;
    private final static String DELETE_ID_FROM_TABLE = """
            DELETE FROM cities WHERE id = ?
            """;
    private final static String SELECT_TABLE = """
            SELECT * FROM cities;
            """;

    public PreparedStatementDemo() throws Exception {
        initConnection();
    }

    public void initConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/spammer";
        String login = "postgres";
        String password = "password";
        connection = DriverManager.getConnection(url, login, password);
    }

    public void insert(City city) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_IN_TABLE)) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean update(City city) {
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_TABLE)) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.setInt(3, city.getId());
            result = statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_ID_FROM_TABLE)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<City> findAll() {
        List<City> citiesList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_TABLE)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while ((resultSet.next())) {
                    citiesList.add(new City(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("population")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return citiesList;
    }
}
