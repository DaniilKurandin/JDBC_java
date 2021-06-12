package JDBC_java.homework2.JDBC_homework_task1.dao;

import JDBC_java.homework2.JDBC_homework_task1.connection.Connector;
import JDBC_java.homework2.JDBC_homework_task1.entity.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientJDBCDao implements ClientDAO {

    @Override
    public void addClient(Client client) {
        Connection connection = Connector.getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("INSERT INTO clients(name, age, phone) VALUES (?, ? , ?)");

            statement.setNString(1, client.getName());
            statement.setInt(2, client.getAge());
            statement.setNString(3, client.getPhone());

            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnections(connection, statement);
        }


    }

    @Override
    public Client getClient(int id) {
        Connection connection = Connector.getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("SELECT c.name, c.age, c.phone FROM clients as c WHERE c.id = ?");

            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                String name = rs.getString(1);
                int age = rs.getInt(2);
                String phone = rs.getString(3);

                Client client = new Client();

                client.setName(name);
                client.setAge(age);
                client.setPhone(phone);

                return client;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnections(connection, statement);
        }

        return null;
    }

    @Override
    public List<Client> getAllClients() {
        List<Client> allClients = new ArrayList<>();
        Connection connection = Connector.getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("SELECT c.id, c.name, c.age, c.phone FROM clients as c;");

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Client client = new Client(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("phone")
                );

                allClients.add(client);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnections(connection, statement);
        }

        return allClients;
    }

    @Override
    public void updateClient(String phone, int clientId) {
        Connection connection = Connector.getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("UPDATE clients SET phone = ? WHERE id = ?");

            statement.setNString(1, phone);
            statement.setInt(2, clientId);

            int update = statement.executeUpdate();

            System.out.println("Values updated: " + update);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnections(connection, statement);
        }

    }

    @Override
    public void deleteClient(int id) {
        Connection connection = Connector.getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("DELETE FROM clients WHERE id = ?");

            statement.setInt(1, id);

            int delete = statement.executeUpdate();

            System.out.println("Values delete: " + delete);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnections(connection, statement);
        }

    }

    private void closeConnections(Connection connection, PreparedStatement statement) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
