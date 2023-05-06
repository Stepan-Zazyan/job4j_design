package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception {
        connection = getConnection();
    }

    private Connection getConnection() throws Exception {
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("db.properties")) {
            properties.load(in);
        }
        Class.forName(properties.getProperty("driver"));
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        return DriverManager.getConnection(url, login, password);
    }


    public String createTable(String tableName) {
        return "CREATE TABLE IF NOT EXISTS " + "job4j." + tableName
                            + " (id SERIAL PRIMARY KEY, "
                            + "simple_name text);";
    }

    public String dropTable(String tableName) {
        return "drop TABLE job4j." + tableName + ";";
    }

    public String addColumn(String tableName, String columnName, String type) {
        return "ALTER TABLE " + tableName + " ADD COLUMN " + columnName + " " + type;
    }


    public String dropColumn(String tableName, String columnName) {
        return "ALTER TABLE " + tableName + " DROP COLUMN " + columnName;
    }


    public String renameColumn(String tableName, String columnName, String newColumnName) {
                return "ALTER TABLE " + tableName
                        + " RENAME COLUMN " + columnName
                        + " to " + newColumnName;
    }

    public void exec(String str) throws Exception {
       try (Statement statement = connection.createStatement()) {
           statement.execute(str);
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

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("db.properties")) {
            config.load(in);
        }
        try (TableEditor te = new TableEditor(config)) {
            te.exec(te.createTable("jdbc_table"));
            System.out.println(te.getTableScheme("jdbc_table"));

            te.exec(te.addColumn("jdbc_table", "psanother_column", "text"));
            System.out.println(te.getTableScheme("jdbc_table"));

            te.exec(te.renameColumn("jdbc_table", "psanother_column", "chikky"));
            System.out.println(te.getTableScheme("jdbc_table"));

            te.exec(te.dropColumn("jdbc_table", "chikky"));
            System.out.println(te.getTableScheme("jdbc_table"));

            te.exec(te.dropTable("jdbc_table"));
            System.out.println(te.getTableScheme("jdbc_table"));
            te.getConnection().close();
        }
    }
}
