package JDBC_java.homework2.JDBC_homework_task1.dao;

import JDBC_java.homework2.JDBC_homework_task1.entity.Client;

import java.util.List;

public interface ClientDAO {
    void addClient(Client client);

    Client getClient(int id);

    List<Client> getAllClients();

    void updateClient(String phone, int clientId);

    void deleteClient(int id);

}
