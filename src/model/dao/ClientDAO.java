package model.dao;

import java.sql.*;
import java.util.*;
import org.json.JSONObject;

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

    public void create(JSONObject json) throws SQLException
    {
        String SQL = "INSERT INTO cliente "
            + "( nome, sobrenome, cpf ) "
            + "VALUES "
            + "( ?, ?, ? )";

        try (PreparedStatement statement = this.connection.prepareStatement(SQL))
        {
            statement.setString(1, json.getString("firstName"));
            statement.setString(2, json.getString("lastName"));
            statement.setString(3, json.getString("cpf"));
            statement.executeUpdate();
        }
    }

    public List read() throws SQLException
    {
        List<JSONObject> list = new LinkedList<>();
        String SQL = "SELECT * FROM cliente";

        try (PreparedStatement statement = this.connection.prepareStatement(SQL);
            ResultSet rs = statement.executeQuery())
        {
            while ( rs.next() )
            {
                JSONObject json = new JSONObject();
                json.put("clientID", rs.getInt("clienteID"));
                json.put("firstName", rs.getString("nome"));
                json.put("lastName", rs.getString("sobrenome"));
                json.put("cpf", rs.getString("cpf"));

                list.add(json);
            }
        }
        return list;
    }

    public void update(JSONObject json) throws SQLException
    {
        String SQL = "UPDATE cliente SET "
            + "nome = ?,"
            + "sobrenome = ?,"
            + "cpf = ? "
            + "WHERE clienteID = ?";

        try (PreparedStatement statement = this.connection.prepareStatement(SQL))
        {
            statement.setString(1, json.getString("firstName"));
            statement.setString(2, json.getString("lastName"));
            statement.setString(3, json.getString("cpf"));
            statement.setInt(4, json.getInt("clientID"));
            statement.executeUpdate();
        }
    }

    public void deleteBy(int ID) throws SQLException
    {
        String SQL = "DELETE FROM cliente WHERE clienteID = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(SQL))
        {
            statement.setInt(1, ID);
            statement.executeUpdate();
        }
    }
    /*
    public ClientPerson findByID(int index) throws SQLException
    {
        String SQL = "SELECT * FROM cliente WHERE clienteID = ?";

        try(PreparedStatement statement = this.connection.prepareStatement(SQL))
        {
            statement.setInt(1, index);

            try ( ResultSet rs = statement.executeQuery() )
            {
                if ( rs.next() )
                {
                    int clienteID    = rs.getInt("clienteID");
                    String firstName = rs.getString("nome");
                    String lastName  = rs.getString("sobrenome");
                    String cpf       = rs.getString("cpf");

                    return new ClientPerson(
                        clienteID, firstName, lastName, cpf
                    );
                }
            }
        }
        return null;
    }
    */
}