package model.dao;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import model.Author;
import model.Book;

/**
 *
 *
 */
public class BookDAO
{

    private final Connection connection;

    public BookDAO(Connection connection)
    {
        this.connection = connection;
    }

    public void insert(Book book) throws SQLException
    {
        final String SQL = "INSERT INTO livro (titulo, editorID, autorID, genero, acabamento, "
            + "codigoBarras, lancamento, numberPages, valorCompra, valorVenda) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (var ps = this.connection.prepareStatement(SQL))
        {
            ps.setString(1, book.getTitle());
            ps.setInt(2, book.getPublishing().getID());
            ps.setInt(3, book.getAuthor().getID());
            ps.setString(4, book.getGender());
            ps.setString(5, book.getFinishing());
            ps.setInt(6, book.getCode());
            ps.setInt(7, book.getReleaseYear());
            ps.setInt(8, book.getNumberPages());
            ps.setString(9, book.getBuyValue().toString());
            ps.setString(10, book.getSellValue().toString());

            ps.execute();
        }
    }

    public void update(Book book) throws SQLException
    {
        final String SQL = "UPDATE livro SET titulo = ?, editorID = ?, autorID = ?,"
            + "genero = ?, acabamento = ?, codigoBarras = ?, lancamento = ?,"
            + "numberPages = ?, valorCompra = ?, valorVenda = ? WHERE bookID = ?";

        try (var ps = this.connection.prepareStatement(SQL))
        {
            ps.setString(1, book.getTitle());
            ps.setInt(2, book.getPublishing().getID());
            ps.setInt(3, book.getAuthor().getID());
            ps.setString(4, book.getGender());
            ps.setString(5, book.getFinishing());
            ps.setInt(6, book.getCode());
            ps.setInt(7, book.getReleaseYear());
            ps.setInt(8, book.getNumberPages());
            ps.setString(9, book.getBuyValue().toString());
            ps.setString(10, book.getSellValue().toString());
            ps.setInt(11, book.getID());

            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException
    {
        final String SQL = "DELETE FROM livro WHERE bookID = ?";

        try (var ps = this.connection.prepareStatement(SQL))
        {
            ps.setInt(1, id);

            ps.execute();
        }
    }

    public List<Book> getAll() throws SQLException
    {
        final String SQL = "SELECT * FROM livro";

        try (var ps = this.connection.prepareStatement(SQL);
            var rs = ps.executeQuery())
        {
            List<Book> list = new LinkedList();

            while ( rs.next() )
            {
                int id = rs.getInt("bookID");
                int editorID = rs.getInt("editorID");
                int authorID = rs.getInt("autorID");
                int code     = rs.getInt("codigoBarras");
                int year = rs.getInt("lancamento");
                int pages = rs.getInt("numberPages");
                String title = rs.getString("titulo");
                String finishing = rs.getString("acabamento");
                String gender = rs.getString("genero");
                String buyValue = rs.getString("valorCompra");
                String sellValue = rs.getString("valorVenda");

                Author author = new Author(authorID);

                list.add(new Book(id, editorID, author, code, year, pages, title, gender, finishing,
                    new BigDecimal(sellValue), new BigDecimal(buyValue)
                ));
            }
            return list;
        }
    }

    public Book findByID(int id) throws SQLException
    {
        final String SQL = "SELECT * FROM livro WHERE bookID = ?";

        try (var ps = this.connection.prepareStatement(SQL))
        {
            ps.setInt(1, id);

            try (var rs = ps.executeQuery())
            {
                if ( rs.next() )
                {
                    int editorID = rs.getInt("editorID");
                    int authorID = rs.getInt("autorID");
                    int code = rs.getInt("codigoBarras");
                    int year = rs.getInt("lancamento");
                    int pages = rs.getInt("numberPages");
                    String title = rs.getString("titulo");
                    String finishing = rs.getString("acabamento");
                    String gender = rs.getString("gender");
                    String buyValue = rs.getString("valorCompra");
                    String sellValue = rs.getString("valorVenda");

                    Author author = new Author(authorID);

                    return new Book(
                        id, editorID, author, code, year, pages, title, gender, finishing,
                        new BigDecimal(sellValue), new BigDecimal(buyValue)
                    );
                }
            }
        }
        throw new SQLException("Book not found!");
    }
}