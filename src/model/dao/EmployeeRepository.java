package model.dao;

import java.sql.*;
import java.util.*;
import model.entities.Employee;
import model.entities.Address;
import model.utils.Contract;
import model.utils.Name;
import model.utils.Sex;

/**
 *
 */
public class EmployeeRepository
{

    private final Connection connection;

    public EmployeeRepository(Connection connection)
    {
        this.connection = connection;
    }

    public void insert(final Employee employee) throws SQLException
    {
        final String SQL = "INSERT INTO employee(name, surname, matricula, depto, cargo, cpf, address, sex, dataNasc) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (var ps = this.connection.prepareStatement(SQL))
        {
            ps.setString(1, employee.getName().getFirst());
            ps.setString(2, employee.getName().getLast());
            ps.setInt(3, employee.getContract().getMatricula());
            ps.setString(4, employee.getContract().getDepto());
            ps.setString(5, employee.getContract().getCargo());
            ps.setString(6, employee.getCPF());
            ps.setInt(7, employee.getAddress().getID());
            ps.setString(8, employee.getSex().getDescription());
            ps.setDate(9, new java.sql.Date(employee.getDate().getTimeInMillis()));

            ps.executeUpdate();
        }
    }

    public void update(final Employee employee) throws SQLException
    {
        final String SQL = "UPDATE employee SET name = ?,"
            + "surname = ?, matricula = ?, depto = ?, cargo = ?, cpf = ?, address = ?, sex = ?, dataNasc = ? WHERE id = ?";

        try (var ps = this.connection.prepareStatement(SQL))
        {
            ps.setString(1, employee.getName().getFirst());
            ps.setString(2, employee.getName().getLast());
            ps.setInt(3, employee.getContract().getMatricula());
            ps.setString(4, employee.getContract().getDepto());
            ps.setString(5, employee.getContract().getCargo());
            ps.setString(6, employee.getCPF());
            ps.setInt(7, employee.getAddress().getID());
            ps.setString(8, employee.getSex().getDescription());
            ps.setDate(9, new java.sql.Date(employee.getDate().getTimeInMillis()));
            ps.setInt(10, employee.getID());

            ps.executeUpdate();
        }
    }

    public void delete(final Employee employee) throws SQLException
    {
        final String SQL = "DELETE FROM employee WHERE id = ?";

        try (var statement = this.connection.prepareStatement(SQL))
        {
            statement.setInt(1, employee.getID());

            statement.executeUpdate();
        }
    }

    public List<Employee> getAll() throws SQLException
    {
        final String SQL = "SELECT * FROM employee";

        try (var statement = this.connection.createStatement();
            var rs = statement.executeQuery(SQL))
        {
            List<Employee> list = new LinkedList<>();

            while ( rs.next() )
            {
                int id              = rs.getInt("id");
                String firstName    = rs.getString("name");
                String lastName     = rs.getString("surname");
                Sex sex             = Sex.valueOf(rs.getString("sex"));
                String cpf          = rs.getString("cpf");
                Calendar date       = Calendar.getInstance();
                date.setTime(rs.getDate("dataNasc"));

                int matricula = rs.getInt("matricula");
                String depto  = rs.getString("depto");
                String cargo  = rs.getString("cargo");
                int addressID = rs.getInt("address");

                Name name       = new Name(firstName, lastName);
                Contract contract = new Contract(matricula, depto, cargo);
                // Address address = new Address(uf, city, bairro, street, number, compl, cep);

                list.add(new Employee(id, name, null, cpf, date, sex, contract));
            }
            
            return list;
        }
    }

    public Employee findByID(final int ID) throws SQLException
    {
        final String SQL = "SELECT * FROM employee WHERE id = ?";

        try (var statement = this.connection.prepareStatement(SQL))
        {
            statement.setInt(1, ID);

            var rs = statement.executeQuery();

            if ( rs.next() )
            {
                throw new SQLException("Employee not found!");
            }
            
            String firstName    = rs.getString("name");
            String lastName     = rs.getString("surname");
            String cpf          = rs.getString("cpf");
            Sex sex             = Sex.valueOf(rs.getString("sex"));
            Calendar date       = Calendar.getInstance();
            date.setTime(rs.getDate("dataNasc"));

            int matricula = rs.getInt("matricula");
            String depto  = rs.getString("depto");
            String cargo  = rs.getString("cargo");
            int addressID = rs.getInt("address");

            Name name       = new Name(firstName, lastName);
            Contract contract = new Contract(matricula, depto, cargo);
            // Address address = new Address(uf, city, bairro, street, number, compl, cep);

            return new Employee(ID, name, null, cpf, date, sex, contract);
        }
    }
}