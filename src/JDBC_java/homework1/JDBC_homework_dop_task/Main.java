package JDBC_java.homework1.JDBC_homework_dop_task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/MyJoinsDB";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "9IOLWZKDn";

    private static final String DELETE = "DELETE FROM testdb WHERE id = ?";
    private static final String SELECT = "SELECT person FROM testdb WHERE id = ?";
    private static final String UPDATE = "UPDATE person SET name = 'TestNew' WHERE id = ?";

    public static void main(String[] args) {
        registerDriver();

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);

            statement = connection.prepareStatement(SELECT);

            statement.setInt(1, 1);

            statement = connection.prepareStatement(UPDATE);

            statement.setInt(1, 1);

            statement = connection.prepareStatement(DELETE);

            statement.setInt(1, 1);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private static void registerDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loading success!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
