package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;
    private final Properties properties;
    private final static String CREATE_TABLE = """
            CREATE TABLE IF NOT EXISTS %s (id SERIAL PRIMARY KEY);
            """;
    private final static String DELETE_TABLE = """
            DROP TABLE IF EXISTS %s;
            """;
    private final static String ADD_COLUMN = """
            ALTER TABLE %s ADD COLUMN %s %s;
            """;
    private final static String DELETE_COLUMN = """
            ALTER TABLE %s DROP COLUMN %s;
            """;
    private final static String RENAME_COLUMN = """
            ALTER TABLE %s RENAME COLUMN %s TO %s;
            """;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void loadProperties() {
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            if (in == null) {
                throw new RuntimeException("Failed to find app.properties file");
            }
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load app.properties file", e);
        }
    }

    private void initConnection() {
        loadProperties();
        try {
            Class.forName(properties.getProperty("db.driver"));
            String url = properties.getProperty("db.url");
            String login = properties.getProperty("db.login");
            String password = properties.getProperty("db.password");
            connection = DriverManager.getConnection(url, login, password);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void createTable(String tableName) {
        execute(String.format(CREATE_TABLE, tableName));
    }

    public void dropTable(String tableName) {
        execute(String.format(DELETE_TABLE, tableName));
    }

    public void addColumn(String tableName, String columnName, String type) {
        execute(String.format(ADD_COLUMN, tableName, columnName, type));
    }

    public void dropColumn(String tableName, String columnName) {
        execute(String.format(DELETE_COLUMN, tableName, columnName));
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        execute(String.format(RENAME_COLUMN, tableName, columnName, newColumnName));
    }

    private void execute(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("SQL execution error: " + sql, e);
        }
    }


    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) {
        Properties propertiesTest = new Properties();
        try (TableEditor editor = new TableEditor(propertiesTest)) {
            editor.loadProperties();
            String tableName = "test_table";

            System.out.println("Creating table:");
            editor.createTable(tableName);
            System.out.println(editor.getTableScheme(tableName));

            System.out.println("Adding column:");
            editor.addColumn(tableName, "name", "varchar(255)");
            System.out.println(editor.getTableScheme(tableName));

            System.out.println("Renaming column:");
            editor.renameColumn(tableName, "name", "full_name");
            System.out.println(editor.getTableScheme(tableName));

            System.out.println("Dropping column:");
            editor.dropColumn(tableName, "full_name");
            System.out.println(editor.getTableScheme(tableName));

            System.out.println("Dropping table:");
            editor.dropTable(tableName);
            System.out.println("Table dropped.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}