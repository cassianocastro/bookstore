package model.dao;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
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

    public List<JSONObject> read(int id) throws SQLException
    {
        final String SQL = "SELECT autor.autorID, autor.nome, autor.sobrenome, livro.titulo "
            + "FROM autor, livro WHERE autor.autorID = livro.autorID AND autor.autorID = ?";

        try (var ps = this.connection.prepareStatement(SQL))
        {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery())
            {
                List<JSONObject> list = new LinkedList();

                while ( rs.next() )
                {
                    JSONObject json = new JSONObject();

                    json.put("authorID", rs.getInt("autorID"));
                    json.put("authorFirstName", rs.getString("nome"));
                    json.put("authorLastName", rs.getString("sobrenome"));
                    json.put("bookTitle", rs.getString("titulo"));

                    list.add(json);
                }
                return list;
            }
        }
    }
}