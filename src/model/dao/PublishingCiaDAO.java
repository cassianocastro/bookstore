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
public class PublishingCiaDAO implements IDAO
{

    private final Connection connection;

    public PublishingCiaDAO() throws SQLException
    {
        this.connection = ConnectionSingleton.getInstance();
    }

    public void create(PublishingCia cia) throws SQLException
    {
        String SQL = "INSERT INTO editora "
            + "( nome, uf, cidade, bairro, rua, numero, complemento, cep ) "
            + "VALUES "
            + "( ?, ?, ?, ?, ?, ?, ?, ? )";

        try (PreparedStatement ps = this.connection.prepareStatement(SQL))
        {
            ps.setString(1, cia.getName());

            ps.setString(2, cia.getAddress().getUf());
            ps.setString(3, cia.getAddress().getCity());
            ps.setString(4, cia.getAddress().getBairro());
            ps.setString(5, cia.getAddress().getStreet());
            ps.setInt(6, cia.getAddress().getNumber());
            ps.setString(7, cia.getAddress().getCompl());
            ps.setString(8, cia.getAddress().getCep());

            ps.executeUpdate();
        }
    }

    @Override
    public List read() throws SQLException
    {
        List<PublishingCia> list = new LinkedList<>();
        String SQL = "SELECT * FROM editora";

        try (PreparedStatement statement = this.connection.prepareStatement(SQL);
            ResultSet rs = statement.executeQuery())
        {
            while ( rs.next() )
            {
                int ciaID = rs.getInt("editorID");
                String name = rs.getString("nome");

                String uf = rs.getString("uf");
                String city = rs.getString("cidade");
                String bairro = rs.getString("bairro");
                String street = rs.getString("rua");
                int number = rs.getInt("numero");
                String compl = rs.getString("complemento");
                String cep = rs.getString("cep");

                Address address = new Address(
                    uf, city, bairro, street, number, compl, cep
                );
                list.add(new PublishingCia(
                    ciaID, name, address
                ));
            }
        }
        return list;
    }

    public void update(PublishingCia cia) throws SQLException
    {
        String SQL = "UPDATE editora SET "
            + "nome = ?,"
            + "uf = ?,"
            + "cidade = ?,"
            + "bairro = ?,"
            + "rua = ?,"
            + "numero = ?,"
            + "complemento = ?,"
            + "cep = ? "
            + "WHERE editorID = ?";

        try (PreparedStatement ps = this.connection.prepareStatement(SQL))
        {
            ps.setString(1, cia.getName());

            ps.setString(2, cia.getAddress().getUf());
            ps.setString(3, cia.getAddress().getCity());
            ps.setString(4, cia.getAddress().getBairro());
            ps.setString(5, cia.getAddress().getStreet());
            ps.setInt(6, cia.getAddress().getNumber());
            ps.setString(7, cia.getAddress().getCompl());
            ps.setString(8, cia.getAddress().getCep());

            ps.setInt(9, cia.getCompanyID());
            ps.executeUpdate();
        }
    }

    @Override
    public void deleteBy(int ID) throws SQLException
    {
        String SQL = "DELETE FROM editora WHERE editorID = ?";

        try (PreparedStatement statement = this.connection.prepareStatement(SQL))
        {
            statement.setInt(1, ID);
            statement.executeUpdate();
        }
    }

    public List getNamesAndIDs() throws SQLException
    {
        List list  = new LinkedList();
        String SQL = "select editorID, nome from editora";

        try (PreparedStatement statement = this.connection.prepareStatement(SQL);
            ResultSet rs = statement.executeQuery())
        {
            while ( rs.next() )
            {
                JSONObject json = new JSONObject();
                json.put("publishingID", rs.getInt("editorID"));
                json.put("publishingName", rs.getString("nome"));
                list.add(json);
            }
        }
        return list;
    }
}