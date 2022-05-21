package model.dao;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import model.entities.Author;
import model.Name;

/**
 *
 *
 */
public class AuthorDAO
{

    private final Connection connection;

    public AuthorDAO(Connection connection)
    {
        this.connection = connection;
    }

    public void insert(Author author) throws SQLException
    {
        final String SQL = "INSERT INTO autor(nome, sobrenome) VALUES (?, ?)";

        try (var statement = this.connection.prepareStatement(SQL))
        {
            statement.setString(1, author.getName().getFirst());
            statement.setString(2, author.getName().getLast());

            statement.execute();
        }
    }

    public void update(Author author) throws SQLException
    {
        final String SQL = "UPDATE autor SET nome = ?, sobrenome = ? WHERE autorID = ?";

        try (var statement = this.connection.prepareStatement(SQL))
        {
            statement.setString(1, author.getName().getFirst());
            statement.setString(2, author.getName().getLast());
            statement.setInt(3, author.getID());

            statement.executeUpdate();
        }
    }

    public void delete(Author author) throws SQLException
    {
        final String SQL = "DELETE FROM autor WHERE autorID = ?";

        try (var statement = this.connection.prepareStatement(SQL))
        {
            statement.setInt(1, author.getID());

            statement.execute();
        }
    }

    public List<Author> getAll() throws SQLException
    {
        final String SQL = "SELECT * FROM autor";

        try (var statement = this.connection.prepareStatement(SQL);
            var rs = statement.executeQuery())
        {
            List<Author> list = new LinkedList();

            while ( rs.next() )
            {
                int id       = rs.getInt("autorID");
                String first = rs.getString("nome");
                String last  = rs.getString("sobrenome");

                list.add(new Author(id, new Name(first, last)));
            }
            return list;
        }
    }
}