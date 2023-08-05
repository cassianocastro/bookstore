package model.dao;

import java.sql.*;
import java.util.*;
import model.entities.Employee;
import model.entities.Address;
import model.Name;
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
        final String SQL = "INSERT INTO funcionario(nome, sobrenome, matricula, depto, cargo, cpf, address, sexo, dataNasc) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (var ps = this.connection.prepareStatement(SQL))
        {
            ps.setString(1, employee.getName().getFirst());
            ps.setString(2, employee.getName().getLast());
            ps.setInt(3, employee.getMatricula());
            ps.setString(4, employee.getDepto());
            ps.setString(5, employee.getCargo());
            ps.setString(6, employee.getCPF());
            ps.setInt(7, employee.getAddress().getID());
            ps.setString(8, employee.getSex().getDescription());
            ps.setDate(9, new java.sql.Date(employee.getDate().getTimeInMillis()));

            ps.executeUpdate();
        }
    }

    public void update(Employee employee) throws SQLException
    {
        final String SQL = "UPDATE funcionario SET nome = ?,"
            + "sobrenome = ?, matricula = ?, depto = ?, cargo = ?, cpf = ?, address = ?, sexo = ?, dataNasc = ? WHERE funcionarioID = ?";

        try (var ps = this.connection.prepareStatement(SQL))
        {
            ps.setString(1, employee.getName().getFirst());
            ps.setString(2, employee.getName().getLast());
            ps.setInt(3, employee.getMatricula());
            ps.setString(4, employee.getDepto());
            ps.setString(5, employee.getCargo());
            ps.setString(6, employee.getCPF());
            ps.setInt(7, employee.getAddress().getID());
            ps.setString(8, employee.getSex().getDescription());
            ps.setDate(9, new java.sql.Date(employee.getDate().getTimeInMillis()));
            ps.setInt(10, employee.getID());

            ps.executeUpdate();
        }
    }

    public void delete(Employee employee) throws SQLException
    {
        final String SQL = "DELETE FROM funcionario WHERE funcionarioID = ?";

        try (var statement = this.connection.prepareStatement(SQL))
        {
            statement.setInt(1, employee.getID());

            statement.executeUpdate();
        }
    }

    public List<Employee> getAll() throws SQLException
    {
        final String SQL = "SELECT * FROM funcionario";

        try (var statement = this.connection.createStatement();
            var rs = statement.executeQuery(SQL))
        {
            List<Employee> list = new LinkedList<>();

            while ( rs.next() )
            {
                int id              = rs.getInt("funcionarioID");
                String firstName    = rs.getString("nome");
                String lastName     = rs.getString("sobrenome");
                Sex sex             = Sex.valueOf(rs.getString("sexo"));
                String cpf          = rs.getString("cpf");
                Calendar date       = Calendar.getInstance();
                date.setTime(rs.getDate("dataNasc"));

                int matricula = rs.getInt("matricula");
                String depto  = rs.getString("depto");
                String cargo  = rs.getString("cargo");
                int addressID = rs.getInt("address");

                Name name       = new Name(firstName, lastName);
                // Address address = new Address(uf, city, bairro, street, number, compl, cep);

                list.add(new Employee(id, name, null, cpf, matricula, depto, cargo, date, sex));
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

            var rs = statement.executeQuery();

            if ( rs.next() )
            {
                throw new SQLException("Employee not found!");
            }
            String firstName    = rs.getString("nome");
            String lastName     = rs.getString("sobrenome");
            String cpf          = rs.getString("cpf");
            Sex sex             = Sex.valueOf(rs.getString("sexo"));
            Calendar date       = Calendar.getInstance();
            date.setTime(rs.getDate("dataNasc"));

            int matricula = rs.getInt("matricula");
            String depto  = rs.getString("depto");
            String cargo  = rs.getString("cargo");
            int addressID = rs.getInt("address");

            Name name       = new Name(firstName, lastName);
            // Address address = new Address(uf, city, bairro, street, number, compl, cep);

            return new Employee(id, name, null, cpf, matricula, depto, cargo, date, sex);
        }
    }
}