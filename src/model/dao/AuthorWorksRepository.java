package model.dao;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import model.entities.Author;
import model.utils.Name;

/**
 *
 */
public class AuthorWorksRepository
{

    private final Connection connection;

    public AuthorWorksRepository(Connection connection)
    {
        this.connection = connection;
    }

    public List findBooksByAuthor(final int id) throws SQLException
    {
        final String SQL = "SELECT author.id, author.name, author.surname, book.title "
            + "FROM author, book WHERE author.id = book.autorID AND author.id = ?";

        try (var ps = this.connection.prepareStatement(SQL))
        {
            ps.setInt(1, id);

            try (var rs = ps.executeQuery())
            {
                List list = new LinkedList();

                if ( rs.next() )
                {
                    String first = rs.getString("name");
                    String last  = rs.getString("surname");
                    var books    = rs.getString("title");

                    Author author = new Author(id, new Name(first, last));

                    list.add(null);
                }
                
                return list;
            }
        }
    }
}