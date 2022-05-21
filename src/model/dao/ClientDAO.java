package model.dao;

import java.sql.*;
import java.util.*;
import model.entities.Client;
import model.Name;

/**
 *
 *
 */
public class ClientDAO
{

    private final Connection connection;

    public ClientDAO(Connection connection)
    {
        this.connection = connection;
    }

    public void insert(Client client) throws SQLException
    {
        final String SQL = "INSERT INTO cliente(nome, sobrenome, cpf) VALUES (?, ?, ?)";

        try (var statement = this.connection.prepareStatement(SQL))
        {
            statement.setString(1, client.getName().getFirst());
            statement.setString(2, client.getName().getLast());
            statement.setString(3, client.getCPF());

            statement.execute();
        }
    }

    public void update(Client client) throws SQLException
    {
        final String SQL = "UPDATE cliente SET nome = ?, sobrenome = ?, cpf = ? WHERE clienteID = ?";

        try (var statement = this.connection.prepareStatement(SQL))
        {
            statement.setString(1, client.getName().getFirst());
            statement.setString(2, client.getName().getLast());
            statement.setString(3, client.getCPF());
            statement.setInt(4, client.getID());

            statement.executeUpdate();
        }
    }

    public void delete(Client client) throws SQLException
    {
        final String SQL = "DELETE FROM cliente WHERE clienteID = ?";

        try (var statement = this.connection.prepareStatement(SQL))
        {
            statement.setInt(1, client.getID());

            statement.execute();
        }
    }

    public List<Client> getAll() throws SQLException
    {
        final String SQL = "SELECT * FROM cliente";

        try (var statement = this.connection.prepareStatement(SQL);
            var rs = statement.executeQuery())
        {
            List<Client> list = new LinkedList<>();

            while ( rs.next() )
            {
                int id       = rs.getInt("clienteID");
                String first = rs.getString("nome");
                String last  = rs.getString("sobrenome");
                String cpf   = rs.getString("cpf");

                list.add(new Client(id, new Name(first, last), cpf));
            }
            return list;
        }
    }

    public Client findByID(int id) throws SQLException
    {
        final String SQL = "SELECT * FROM cliente WHERE clienteID = ?";

        try (var statement = this.connection.prepareStatement(SQL))
        {
            statement.setInt(1, id);

            try ( var rs = statement.executeQuery() )
            {
                if ( rs.next() )
                {
                    String firstName = rs.getString("nome");
                    String lastName  = rs.getString("sobrenome");
                    String cpf       = rs.getString("cpf");

                    return new Client(id, new Name(firstName, lastName), cpf);
                }
            }
        }
        throw new SQLException("Client not found!");
    }
}