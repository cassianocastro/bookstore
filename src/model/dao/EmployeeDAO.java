package model.dao;

import java.sql.*;
import java.util.*;
import model.Employee;
import model.Address;
import model.Sex;

/**
 *
 *
 */
public class EmployeeDAO
{

    private final Connection connection;

    public EmployeeDAO(Connection connection)
    {
        this.connection = connection;
    }

    public void insert(Employee employee) throws SQLException
    {
        final String SQL = "INSERT INTO funcionario(nome, sobrenome, matricula, depto, cargo, cpf, uf, cidade, "
            + "bairro, rua, numero, complemento, cep, sexo, dataNasc) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (var ps = this.connection.prepareStatement(SQL))
        {
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());

            ps.setInt(3, employee.getMatricula());
            ps.setString(4, employee.getDepto());
            ps.setString(5, employee.getCargo());
            ps.setString(6, employee.getCPF());

            ps.setString(7, employee.getAddress().getUf());
            ps.setString(8, employee.getAddress().getCity());
            ps.setString(9, employee.getAddress().getBairro());

            ps.setString(10, employee.getAddress().getStreet());
            ps.setInt(11, employee.getAddress().getNumber());
            ps.setString(12, employee.getAddress().getCompl());
            ps.setString(13, employee.getAddress().getCep());

            ps.setString(14, employee.getSex().getDescricao());
            ps.setDate(15, new java.sql.Date(employee.getDate().getTime()));

            ps.execute();
        }
    }

    public void update(Employee employee) throws SQLException
    {
        final String SQL = "UPDATE funcionario SET nome = ?,"
            + "sobrenome = ?, matricula = ?, depto = ?, cargo = ?, cpf = ?, uf = ?, cidade = ?, bairro = ?, rua = ?,"
            + "numero = ?, complemento = ?, cep = ?, sexo = ?, dataNasc = ? WHERE funcionarioID = ?";

        try (var ps = this.connection.prepareStatement(SQL))
        {
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());

            ps.setInt(3, employee.getMatricula());
            ps.setString(4, employee.getDepto());
            ps.setString(5, employee.getCargo());
            ps.setString(6, employee.getCPF());

            ps.setString(7, employee.getAddress().getUf());
            ps.setString(8, employee.getAddress().getCity());
            ps.setString(9, employee.getAddress().getBairro());
            ps.setString(10, employee.getAddress().getStreet());
            ps.setInt(11, employee.getAddress().getNumber());
            ps.setString(12, employee.getAddress().getCompl());
            ps.setString(13, employee.getAddress().getCep());

            ps.setString(14, employee.getSex().getDescricao());
            ps.setDate(15, new java.sql.Date(employee.getDate().getTime()));
            ps.setInt(16, employee.getId());

            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException
    {
        final String SQL = "DELETE FROM funcionario WHERE funcionarioID = ?";

        try (var statement = this.connection.prepareStatement(SQL))
        {
            statement.setInt(1, id);

            statement.execute();
        }
    }

    public List read() throws SQLException
    {
        final String SQL = "SELECT * FROM funcionario";

        try (var statement = this.connection.prepareStatement(SQL);
            var rs = statement.executeQuery())
        {
            List<Employee> list = new LinkedList<>();

            while ( rs.next() )
            {
                int id              = rs.getInt("funcionarioID");
                String firstName    = rs.getString("nome");
                String lastName     = rs.getString("sobrenome");
                Sex sex             = Sex.valueOf(rs.getString("sexo"));
                String cpf          = rs.getString("cpf");
                java.util.Date date = rs.getDate("dataNasc");

                int matricula = rs.getInt("matricula");
                String depto  = rs.getString("depto");
                String cargo  = rs.getString("cargo");

                String uf     = rs.getString("uf");
                String city   = rs.getString("cidade");
                String bairro = rs.getString("bairro");
                String street = rs.getString("rua");
                int number    = rs.getInt("numero");
                String compl  = rs.getString("complemento");
                String cep    = rs.getString("cep");

                Address address = new Address(uf, city, bairro, street, number, compl, cep);

                list.add(new Employee(id, firstName, lastName, address, cpf, matricula, depto, cargo, date, sex));
            }
            return list;
        }
    }

    public Employee findByID(int id) throws SQLException
    {
        final String SQL = "SELECT * FROM funcionario WHERE funcionarioID = ?";

        try (var statement = this.connection.prepareStatement(SQL))
        {
            statement.setInt(1, id);

            try (var rs = statement.executeQuery())
            {
                if ( rs.next() )
                {
                    String firstName    = rs.getString("nome");
                    String lastName     = rs.getString("sobrenome");
                    String cpf          = rs.getString("cpf");
                    Sex sex             = Sex.valueOf(rs.getString("sexo"));
                    java.util.Date date = rs.getDate("dataNasc");

                    int matricula = rs.getInt("matricula");
                    String depto  = rs.getString("depto");
                    String cargo  = rs.getString("cargo");

                    String uf     = rs.getString("uf");
                    String city   = rs.getString("cidade");
                    String bairro = rs.getString("bairro");
                    String street = rs.getString("rua");
                    int number    = rs.getInt("numero");
                    String compl  = rs.getString("complemento");
                    String cep    = rs.getString("cep");

                    Address address = new Address(uf, city, bairro, street, number, compl, cep);

                    return new Employee(id, firstName, lastName, address, cpf, matricula, depto, cargo, date, sex);
                }
            }
        }
        throw new SQLException("Employee not found!");
    }
}