package pl.coderslab.entity;

import java.sql.*;

public class DbUtil {
    private static final String DELETE_QUERY = "DELETE FROM tableName where id = ?";
    protected static final String QUERRY_SHOW_ALL_RECORDS = "SELECT * FROM Users;";

    public static Connection connect() throws SQLException {
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/DAO?useSSL=false&characterEncoding=utf8&serverTimezone=UTC",
                "root", "coderslab");
        return conn;
    }

    public static void insert(Connection conn, String query, String... params) {
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                statement.setString(i + 1, params[i]);
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printData(Connection conn, String query, String... columnNames) throws SQLException {
            try (PreparedStatement statement = conn.prepareStatement(QUERRY_SHOW_ALL_RECORDS);
             ResultSet resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                for (String columnName : columnNames) {
                    System.out.print(resultSet.getString(columnName));
                    System.out.println();
                }
            }
                System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void remove(Connection conn, String tableName, int id) {
        try (PreparedStatement statement = conn.prepareStatement(DELETE_QUERY.replace("tableName", tableName));) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
