package JDBC_java.JDBC_homework_task1;
import java.sql.*;

public class Main {

    private static final String URL = "jdbc:mysql://localhost:3306/MyJoinsDB";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "9IOLWZKDn";

    public static void main(String[] args) {

        registerDriver();

        Connection connection = null;
        Statement statement = null;

        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            statement = connection.createStatement();

            statement.executeQuery("SELECT telephone, address FROM data INNER JOIN personal ON data.id = personal.id_data");
            statement.executeQuery("SELECT telephone, birthday FROM data INNER JOIN personal on data.id = personal.id_data WHERE marital_status = 'not married'");
            statement.executeQuery("SELECT birthday, telephone FROM personal INNER JOIN post on post.id = personal.id_post INNER JOIN data on data.id = personal.id_data WHERE post.id = 1");

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
