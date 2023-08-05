package model.dao;

import java.sql.*;
import model.entities.Address;

/**
 *
 *
 */
public class AddressDAO
{

    private final Connection connection;

    public AddressDAO(Connection connection)
    {
        this.connection = connection;
    }

    public void insert(Address address) throws SQLException
    {
        final String SQL = "INSERT INTO address(uf, city, district, street, number, complement, cep)"
            + " VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (var statement = this.connection.prepareStatement(SQL))
        {
            statement.setString(1, address.getUF());
            statement.setString(2, address.getCity());
            statement.setString(3, address.getDistrict());
            statement.setString(4, address.getStreet());
            statement.setInt(5, address.getNumber());
            statement.setString(6, address.getComplement());
            statement.setString(7, address.getCEP());

            statement.executeUpdate();
        }
    }

    public void update(Address address)
    {

    }

    public void delete(Address address)
    {

    }
}