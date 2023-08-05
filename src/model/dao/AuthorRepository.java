package model.dao;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import model.entities.Author;
import model.utils.Name;

/**
 *
 */
public class AuthorRepository
{

    private final Connection connection;

    public AuthorRepository(Connection connection)
    {
        this.connection = connection;
    }

    public void insert(final Author author) throws SQLException
    {
        final String SQL = "INSERT INTO author(name, surname) VALUES (?, ?)";

        try (var statement = this.connection.prepareStatement(SQL))
        {
            statement.setString(1, author.getName().getFirst());
            statement.setString(2, author.getName().getLast());

            statement.executeUpdate();
        }
    }

    public void update(final Author author) throws SQLException
    {
        final String SQL = "UPDATE author SET name = ?, surname = ? WHERE id = ?";

        try (var statement = this.connection.prepareStatement(SQL))
        {
            statement.setString(1, author.getName().getFirst());
            statement.setString(2, author.getName().getLast());
            statement.setInt(3, author.getID());

            statement.executeUpdate();
        }
    }

    public void delete(final Author author) throws SQLException
    {
        final String SQL = "DELETE FROM author WHERE id = ?";

        try (var statement = this.connection.prepareStatement(SQL))
        {
            statement.setInt(1, author.getID());

            statement.executeUpdate();
        }
    }

    public List<Author> getAll() throws SQLException
    {
        final String SQL = "SELECT id, name, surname FROM author";

        try (var statement = this.connection.createStatement();
            var rs = statement.executeQuery(SQL))
        {
            List<Author> list = new LinkedList();

            while ( rs.next() )
            {
                int id       = rs.getInt("id");
                String first = rs.getString("name");
                String last  = rs.getString("surname");

                list.add(new Author(id, new Name(first, last)));
            }
            
            return list;
        }
    }
}