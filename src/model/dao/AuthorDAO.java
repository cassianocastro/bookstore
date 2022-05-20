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

    public AuthorDAO() throws SQLException
    {
        this.connection = ConnectionSingleton.getInstance();
    }

    public void create(JSONObject json) throws SQLException
    {
        String SQL = "INSERT INTO autor "
            + "( nome, sobrenome ) "
            + "VALUES "
            + "( ?, ? )";

        try (PreparedStatement statement = this.connection.prepareStatement(SQL))
        {
            statement.setString(1, json.getString("firstName"));
            statement.setString(2, json.getString("lastName"));
            statement.executeUpdate();
        }
    }

    public List read() throws SQLException
    {
        List array = new LinkedList();
        String SQL = "SELECT * FROM autor";

        try (PreparedStatement statement = this.connection.prepareStatement(SQL);
            ResultSet rs = statement.executeQuery())
        {
            while ( rs.next() )
            {
                JSONObject json = new JSONObject();
                json.put("authorID", rs.getInt("autorID"));
                json.put("firstName", rs.getString("nome"));
                json.put("lastName", rs.getString("sobrenome"));

                array.add(json);
            }
        }
        return array;
    }

    public void update(JSONObject json) throws SQLException
    {
        String SQL = "UPDATE autor SET "
            + "nome = ?,"
            + "sobrenome = ? "
            + "WHERE autorID = ?";

        try (PreparedStatement statement = this.connection.prepareStatement(SQL))
        {
            statement.setString(1, json.getString("firstName"));
            statement.setString(2, json.getString("lastName"));
            statement.setInt(3, json.getInt("authorID"));
            statement.executeUpdate();
        }
    }

    public void deleteBy(int ID) throws SQLException
    {
        String SQL = "DELETE FROM autor WHERE autorID = ?";

        try (PreparedStatement statement = this.connection.prepareStatement(SQL))
        {
            statement.setInt(1, ID);
            statement.executeUpdate();
        }
    }
}