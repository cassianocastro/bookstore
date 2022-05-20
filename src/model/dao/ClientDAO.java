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

    public void insert(JSONObject json) throws SQLException
    {
        final String SQL = "INSERT INTO cliente(nome, sobrenome, cpf) VALUES (?, ?, ?)";

        try (var statement = this.connection.prepareStatement(SQL))
        {
            statement.setString(1, json.getString("firstName"));
            statement.setString(2, json.getString("lastName"));
            statement.setString(3, json.getString("cpf"));

            statement.execute();
        }
    }

    public void update(JSONObject json) throws SQLException
    {
        final String SQL = "UPDATE cliente SET nome = ?, sobrenome = ?, cpf = ? WHERE clienteID = ?";

        try (var statement = this.connection.prepareStatement(SQL))
        {
            statement.setString(1, json.getString("firstName"));
            statement.setString(2, json.getString("lastName"));
            statement.setString(3, json.getString("cpf"));
            statement.setInt(4, json.getInt("clientID"));

            statement.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException
    {
        final String SQL = "DELETE FROM cliente WHERE clienteID = ?";

        try (var statement = this.connection.prepareStatement(SQL))
        {
            statement.setInt(1, id);

            statement.execute();
        }
    }

    public List<JSONObject> read() throws SQLException
    {
        final String SQL = "SELECT * FROM cliente";

        try (var statement = this.connection.prepareStatement(SQL);
            var rs = statement.executeQuery())
        {
            List<JSONObject> list = new LinkedList<>();

            while ( rs.next() )
            {
                JSONObject json = new JSONObject();

                json.put("clientID", rs.getInt("clienteID"));
                json.put("firstName", rs.getString("nome"));
                json.put("lastName", rs.getString("sobrenome"));
                json.put("cpf", rs.getString("cpf"));

                list.add(json);
            }
            return list;
        }
    }

//    public Client findByID(int id) throws SQLException
//    {
//        final String SQL = "SELECT * FROM cliente WHERE clienteID = ?";
//
//        try (var statement = this.connection.prepareStatement(SQL))
//        {
//            statement.setInt(1, id);
//
//            try ( var rs = statement.executeQuery() )
//            {
//                if ( rs.next() )
//                {
//                    String firstName = rs.getString("nome");
//                    String lastName  = rs.getString("sobrenome");
//                    String cpf       = rs.getString("cpf");
//
//                    return new Client(id, firstName, lastName, cpf);
//                }
//            }
//        }
//        throw new SQLException("Client not found!");
//    }
}