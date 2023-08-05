package model.dao;

import java.sql.*;
import java.util.*;
import model.entities.Client;
import model.utils.Name;

/**
 *
 */
public class ClientRepository
{

    private final Connection connection;

    public ClientRepository(Connection connection)
    {
        this.connection = connection;
    }

    public void insert(final Client client) throws SQLException
    {
        final String SQL = "INSERT INTO client(name, surname, cpf) VALUES (?, ?, ?)";

        try (var statement = this.connection.prepareStatement(SQL))
        {
            statement.setString(1, client.getName().getFirst());
            statement.setString(2, client.getName().getLast());
            statement.setString(3, client.getCPF());

            statement.executeUpdate();
        }
    }

    public void update(final Client client) throws SQLException
    {
        final String SQL = "UPDATE client SET name = ?, surname = ?, cpf = ? WHERE id = ?";

        try (var statement = this.connection.prepareStatement(SQL))
        {
            statement.setString(1, client.getName().getFirst());
            statement.setString(2, client.getName().getLast());
            statement.setString(3, client.getCPF());
            statement.setInt(4, client.getID());

            statement.executeUpdate();
        }
    }

    public void delete(final Client client) throws SQLException
    {
        final String SQL = "DELETE FROM client WHERE id = ?";

        try (var statement = this.connection.prepareStatement(SQL))
        {
            statement.setInt(1, client.getID());

            statement.executeUpdate();
        }
    }

    public List<Client> getAll() throws SQLException
    {
        final String SQL = "SELECT id, name, surname, cpf FROM client";

        try (var statement = this.connection.createStatement();
            var rs = statement.executeQuery(SQL))
        {
            List<Client> list = new LinkedList<>();

            while ( rs.next() )
            {
                int id       = rs.getInt("id");
                String first = rs.getString("nome");
                String last  = rs.getString("sobrenome");
                String cpf   = rs.getString("cpf");

                list.add(new Client(id, new Name(first, last), cpf));
            }
            
            return list;
        }
    }

    public Client findByID(final int ID) throws SQLException
    {
        final String SQL = "SELECT name, surname, cpf FROM client WHERE id = ?";

        try (var statement = this.connection.prepareStatement(SQL))
        {
            statement.setInt(1, ID);

            var rs = statement.executeQuery();

            if ( ! rs.next() )
            {
                throw new SQLException("Client not found!");
            }
            
            String firstName = rs.getString("name");
            String lastName  = rs.getString("surname");
            String cpf       = rs.getString("cpf");

            return new Client(ID, new Name(firstName, lastName), cpf);
        }
    }
}