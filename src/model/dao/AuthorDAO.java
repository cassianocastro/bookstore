package model.dao;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import org.json.*;

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

    public void create(JSONObject json) throws SQLException
    {
        final String SQL = "INSERT INTO autor(nome, sobrenome) VALUES (?, ?)";

        try (var statement = this.connection.prepareStatement(SQL))
        {
            statement.setString(1, json.getString("firstName"));
            statement.setString(2, json.getString("lastName"));

            statement.execute();
        }
    }

    public void update(JSONObject json) throws SQLException
    {
        final String SQL = "UPDATE autor SET nome = ?, sobrenome = ? WHERE autorID = ?";

        try (var statement = this.connection.prepareStatement(SQL))
        {
            statement.setString(1, json.getString("firstName"));
            statement.setString(2, json.getString("lastName"));
            statement.setInt(3, json.getInt("authorID"));

            statement.executeUpdate();
        }
    }

    public void delete(int ID) throws SQLException
    {
        final String SQL = "DELETE FROM autor WHERE autorID = ?";

        try (var statement = this.connection.prepareStatement(SQL))
        {
            statement.setInt(1, ID);

            statement.execute();
        }
    }

    public List<JSONObject> read() throws SQLException
    {
        final String SQL = "SELECT * FROM autor";

        try (var statement = this.connection.prepareStatement(SQL);
            var rs = statement.executeQuery())
        {
            List<JSONObject> list = new LinkedList();

            while ( rs.next() )
            {
                JSONObject json = new JSONObject();

                json.put("authorID", rs.getInt("autorID"));
                json.put("firstName", rs.getString("nome"));
                json.put("lastName", rs.getString("sobrenome"));

                list.add(json);
            }
            return list;
        }
    }
}