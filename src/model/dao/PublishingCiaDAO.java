package model.dao;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import model.Address;
import model.PublishingCia;
import org.json.JSONObject;

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
        final String SQL = "INSERT INTO editora (nome, uf, cidade, bairro, rua, numero, complemento, cep) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (var ps = this.connection.prepareStatement(SQL))
        {
            ps.setString(1, cia.getName());
            ps.setString(2, cia.getAddress().getUF());
            ps.setString(3, cia.getAddress().getCity());
            ps.setString(4, cia.getAddress().getDistrict());
            ps.setString(5, cia.getAddress().getStreet());
            ps.setInt(6, cia.getAddress().getNumber());
            ps.setString(7, cia.getAddress().getComplement());
            ps.setString(8, cia.getAddress().getCEP());

            ps.execute();
        }
    }

    public void update(PublishingCia cia) throws SQLException
    {
        final String SQL = "UPDATE editora SET nome = ?, uf = ?,"
            + "cidade = ?, bairro = ?, rua = ?, numero = ?, complemento = ?, cep = ? WHERE editorID = ?";

        try (var ps = this.connection.prepareStatement(SQL))
        {
            ps.setString(1, cia.getName());
            ps.setString(2, cia.getAddress().getUF());
            ps.setString(3, cia.getAddress().getCity());
            ps.setString(4, cia.getAddress().getDistrict());
            ps.setString(5, cia.getAddress().getStreet());
            ps.setInt(6, cia.getAddress().getNumber());
            ps.setString(7, cia.getAddress().getComplement());
            ps.setString(8, cia.getAddress().getCEP());
            ps.setInt(9, cia.getID());

            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException
    {
        final String SQL = "DELETE FROM editora WHERE editorID = ?";

        try (var statement = this.connection.prepareStatement(SQL))
        {
            statement.setInt(1, id);

            statement.execute();
        }
    }

    public List<PublishingCia> getAll() throws SQLException
    {
        final String SQL = "SELECT * FROM editora";

        try (var statement = this.connection.prepareStatement(SQL);
            var rs = statement.executeQuery())
        {
            List<PublishingCia> list = new LinkedList<>();

            while ( rs.next() )
            {
                int ciaID   = rs.getInt("editorID");
                String name = rs.getString("nome");

                String uf     = rs.getString("uf");
                String city   = rs.getString("cidade");
                String bairro = rs.getString("bairro");
                String street = rs.getString("rua");
                int number    = rs.getInt("numero");
                String compl  = rs.getString("complemento");
                String cep    = rs.getString("cep");

                Address address = new Address(uf, city, bairro, street, number, compl, cep);

                list.add(new PublishingCia(ciaID, name, address));
            }
            return list;
        }
    }

    public List<JSONObject> getNamesAndIDs() throws SQLException
    {
        final String SQL = "SELECT editorID, nome FROM editora";

        try (var statement = this.connection.prepareStatement(SQL);
            var rs = statement.executeQuery())
        {
            List<JSONObject> list = new LinkedList();

            while ( rs.next() )
            {
                JSONObject json = new JSONObject();

                json.put("publishingID", rs.getInt("editorID"));
                json.put("publishingName", rs.getString("nome"));

                list.add(json);
            }
            return list;
        }
    }
}