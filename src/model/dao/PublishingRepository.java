package model.dao;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import model.entities.Address;
import model.entities.PublishingCia;

/**
 *
 *
 */
public class PublishingCiaDAO
{

    private final Connection connection;

    public PublishingCiaDAO(Connection connection)
    {
        this.connection = connection;
    }

    public void insert(PublishingCia cia) throws SQLException
    {
        final String SQL = "INSERT INTO editora(nome, address) VALUES (?, ?)";

        try (var ps = this.connection.prepareStatement(SQL))
        {
            ps.setString(1, cia.getName());
            ps.setInt(2, cia.getAddress().getID());

            ps.executeUpdate();
        }
    }

    public void update(PublishingCia cia) throws SQLException
    {
        final String SQL = "UPDATE editora SET nome = ?, address = ? WHERE editorID = ?";

        try (var ps = this.connection.prepareStatement(SQL))
        {
            ps.setString(1, cia.getName());
            ps.setInt(2, cia.getAddress().getID());
            ps.setInt(3, cia.getID());

            ps.executeUpdate();
        }
    }

    public void delete(PublishingCia cia) throws SQLException
    {
        final String SQL = "DELETE FROM editora WHERE editorID = ?";

        try (var statement = this.connection.prepareStatement(SQL))
        {
            statement.setInt(1, cia.getID());

            statement.executeUpdate();
        }
    }

    public List<PublishingCia> getAll() throws SQLException
    {
        final String SQL = "SELECT editorID, nome, address FROM editora";

        try (var statement = this.connection.createStatement();
            var rs = statement.executeQuery(SQL))
        {
            List<PublishingCia> list = new LinkedList<>();

            while ( rs.next() )
            {
                int ciaID     = rs.getInt("editorID");
                String name   = rs.getString("nome");
                int addressID = rs.getInt("address");

                // Address address = new Address(uf, city, bairro, street, number, compl, cep);

                list.add(new PublishingCia(ciaID, name, null));
            }
            return list;
        }
    }
}