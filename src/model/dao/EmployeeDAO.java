package model.dao;

import java.sql.*;
import java.util.*;
import model.EmployeePerson;
import model.Address;
import model.Sex;

/**
 *
 *
 */
public class EmployeeDAO implements IDAO
{

    private final Connection connection;

    public EmployeeDAO(Connection connection)
    {
        this.connection = connection;
    }

    public void create(EmployeePerson funcionario) throws SQLException
    {
        String SQL = "INSERT INTO funcionario "
            + "( nome, sobrenome, matricula, depto, cargo, cpf, uf, cidade, "
            + "bairro, rua, numero, complemento, cep, sexo, dataNasc ) "
            + "VALUES "
            + "( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

        try (PreparedStatement ps = this.connection.prepareStatement(SQL))
        {
            ps.setString(1, funcionario.getFirstName());
            ps.setString(2, funcionario.getLastName());

            ps.setInt(3, funcionario.getMatricula());
            ps.setString(4, funcionario.getDepto());
            ps.setString(5, funcionario.getCargo());
            ps.setString(6, funcionario.getCPF());

            ps.setString(7, funcionario.getAddress().getUf());
            ps.setString(8, funcionario.getAddress().getCity());
            ps.setString(9, funcionario.getAddress().getBairro());

            ps.setString(10, funcionario.getAddress().getStreet());
            ps.setInt(11, funcionario.getAddress().getNumber());
            ps.setString(12, funcionario.getAddress().getCompl());
            ps.setString(13, funcionario.getAddress().getCep());

            ps.setString(14, funcionario.getSex().getDescricao());
            ps.setDate(15, new java.sql.Date(funcionario.getDate().getTime()));
            ps.executeUpdate();
        }
    }

    @Override
    public List read() throws SQLException
    {
        List<EmployeePerson> list = new LinkedList<>();
        String SQL = "SELECT * FROM funcionario";

        try (PreparedStatement statement = this.connection.prepareStatement(SQL);
            ResultSet rs = statement.executeQuery())
        {
            while ( rs.next() )
            {
                int funcionarioID = rs.getInt("funcionarioID");
                String firstName = rs.getString("nome");
                String lastName = rs.getString("sobrenome");

                int matricula = rs.getInt("matricula");
                String depto = rs.getString("depto");
                String cargo = rs.getString("cargo");
                String cpf = rs.getString("cpf");

                String uf = rs.getString("uf");
                String city = rs.getString("cidade");
                String bairro = rs.getString("bairro");
                String street = rs.getString("rua");
                int number = rs.getInt("numero");
                String compl = rs.getString("complemento");
                String cep = rs.getString("cep");
                Sex sex = Sex.valueOf(rs.getString("sexo"));
                java.util.Date date = rs.getDate("dataNasc");

                Address address = new Address(
                    uf, city, bairro, street, number, compl, cep
                );
                list.add(new EmployeePerson(
                    funcionarioID, firstName, lastName, address, cpf,
                    matricula, depto, cargo, date, sex
                ));
            }
        }
        return list;
    }

    public void update(EmployeePerson funcionario) throws SQLException
    {
        String SQL = "UPDATE funcionario SET "
            + "nome = ?,"
            + "sobrenome = ?,"
            + "matricula = ?,"
            + "depto = ?,"
            + "cargo = ?,"
            + "cpf = ?,"
            + "uf = ?,"
            + "cidade = ?,"
            + "bairro = ?,"
            + "rua = ?,"
            + "numero = ?,"
            + "complemento = ?,"
            + "cep = ?,"
            + "sexo = ?,"
            + "dataNasc = ? "
            + "WHERE funcionarioID = ?";

        try (PreparedStatement ps = this.connection.prepareStatement(SQL))
        {
            ps.setString(1, funcionario.getFirstName());
            ps.setString(2, funcionario.getLastName());

            ps.setInt(3, funcionario.getMatricula());
            ps.setString(4, funcionario.getDepto());
            ps.setString(5, funcionario.getCargo());
            ps.setString(6, funcionario.getCPF());

            ps.setString(7, funcionario.getAddress().getUf());
            ps.setString(8, funcionario.getAddress().getCity());
            ps.setString(9, funcionario.getAddress().getBairro());

            ps.setString(10, funcionario.getAddress().getStreet());
            ps.setInt(11, funcionario.getAddress().getNumber());
            ps.setString(12, funcionario.getAddress().getCompl());
            ps.setString(13, funcionario.getAddress().getCep());

            ps.setString(14, funcionario.getSex().getDescricao());
            ps.setDate(15, new java.sql.Date(funcionario.getDate().getTime()));
            ps.setInt(16, funcionario.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void deleteBy(int ID) throws SQLException
    {
        String SQL = "DELETE FROM funcionario WHERE funcionarioID = ?";

        try (PreparedStatement statement = this.connection.prepareStatement(SQL))
        {
            statement.setInt(1, ID);
            statement.executeUpdate();
        }
    }

    public EmployeePerson findByID(int index) throws SQLException
    {
        String SQL = "SELECT * FROM funcionario WHERE funcionarioID = ?";

        try (PreparedStatement statement = this.connection.prepareStatement(SQL))
        {
            statement.setInt(1, index);
            try (ResultSet rs = statement.executeQuery())
            {
                if ( rs.next() )
                {
                    int funcionarioID = rs.getInt("funcionarioID");
                    String firstName = rs.getString("nome");
                    String lastName = rs.getString("sobrenome");

                    int matricula = rs.getInt("matricula");
                    String depto = rs.getString("depto");
                    String cargo = rs.getString("cargo");
                    String cpf = rs.getString("cpf");

                    String uf = rs.getString("uf");
                    String city = rs.getString("cidade");
                    String bairro = rs.getString("bairro");
                    String street = rs.getString("rua");
                    int number = rs.getInt("numero");
                    String compl = rs.getString("complemento");
                    String cep = rs.getString("cep");
                    Sex sex = Sex.valueOf(rs.getString("sexo"));
                    java.util.Date date = rs.getDate("dataNasc");

                    Address address = new Address(
                        uf, city, bairro, street, number, compl, cep
                    );
                    return new EmployeePerson(
                        funcionarioID, firstName, lastName, address, cpf,
                        matricula, depto, cargo, date, sex
                    );
                }
            }
        }
        return null;
    }
}