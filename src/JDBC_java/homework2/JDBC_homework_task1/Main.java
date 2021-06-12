package JDBC_java.homework2.JDBC_homework_task1;

import JDBC_java.homework2.JDBC_homework_task1.dao.ClientDAO;
import JDBC_java.homework2.JDBC_homework_task1.dao.DAOFactory;
import JDBC_java.homework2.JDBC_homework_task1.dao.IDAOFactory;
import JDBC_java.homework2.JDBC_homework_task1.entity.Client;

public class Main {
    public static void main(String[] args) {
        IDAOFactory factory = DAOFactory.getInstance();
        ClientDAO clientDAO = factory.getClientDAO();

        Client client = new Client("Vadik", 21, "8(982)55-55-555");

        clientDAO.addClient(client);

        clientDAO.deleteClient(1);
    }
}
