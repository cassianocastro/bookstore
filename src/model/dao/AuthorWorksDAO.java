package model.dao;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import model.entities.Author;
import model.Name;
import org.json.JSONObject;

/**
 *
 *
 */
public class ObrasDAO
{

    private final Connection connection;

    public ObrasDAO(Connection connection)
    {
        this.connection = connection;
    }

    public List<JSONObject> findBooksByAuthor(int id) throws SQLException
    {
        final String SQL = "SELECT autor.autorID, autor.nome, autor.sobrenome, livro.titulo "
            + "FROM autor, livro WHERE autor.autorID = livro.autorID AND autor.autorID = ?";

        try (var ps = this.connection.prepareStatement(SQL))
        {
            ps.setInt(1, id);

            try (var rs = ps.executeQuery())
            {
                List<JSONObject> list = new LinkedList();

                if ( rs.next() )
                {
                    String first = rs.getString("nome");
                    String last  = rs.getString("sobrenome");
                    var books    = rs.getString("titulo");

                    Author author = new Author(id, new Name(first, last));

                    JSONObject json = new JSONObject();
                    json.put("author", author);
                    json.put("books", books);

                    list.add(json);
                }
                return list;
            }
        }
    }
}