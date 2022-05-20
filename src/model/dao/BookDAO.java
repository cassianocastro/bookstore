package model.dao;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import model.Book;

/**
 *
 *
 */
public class BookDAO implements IDAO
{

    private final Connection connection;

    public BookDAO() throws SQLException
    {
        this.connection = ConnectionSingleton.getInstance();
    }

    public void create(Book book) throws SQLException
    {
        String SQL = "INSERT INTO livro "
            + "(titulo, editorID, autorID, genero, acabamento, "
            + "codigoBarras, lancamento, numberPages, valorCompra, valorVenda) "
            + "values "
            + "( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

        try (PreparedStatement ps = this.connection.prepareStatement(SQL))
        {
            ps.setString(1, book.getTitle());
            ps.setInt(2, book.getPublishingID());
            ps.setInt(3, book.getAuthorID());
            ps.setString(4, book.getGender());
            ps.setString(5, book.getFinishing());
            ps.setInt(6, book.getCodeBar());
            ps.setInt(7, book.getReleaseYear());
            ps.setInt(8, book.getNumberPages());
            ps.setString(9, book.getBuyValue().toString());
            ps.setString(10, book.getSellValue().toString());
            ps.executeUpdate();
        }
    }

    public void update(Book book) throws SQLException
    {
        String SQL = "UPDATE livro SET "
            + "titulo = ?,"
            + "editorID = ?,"
            + "autorID = ?,"
            + "genero = ?,"
            + "acabamento = ?,"
            + "codigoBarras = ?,"
            + "lancamento = ?,"
            + "numberPages = ?, "
            + "valorCompra = ?, "
            + "valorVenda = ? "
            + "WHERE bookID = ?";

        try (PreparedStatement ps = this.connection.prepareStatement(SQL))
        {
            ps.setString(1, book.getTitle());
            ps.setInt(2, book.getPublishingID());
            ps.setInt(3, book.getAuthorID());
            ps.setString(4, book.getGender());
            ps.setString(5, book.getFinishing());
            ps.setInt(6, book.getCodeBar());
            ps.setInt(7, book.getReleaseYear());
            ps.setInt(8, book.getNumberPages());
            ps.setString(9, book.getBuyValue().toString());
            ps.setString(10, book.getSellValue().toString());
            ps.setInt(11, book.getBookID());
            ps.executeUpdate();
        }
    }

    public Book findByID(int index) throws SQLException
    {
        String SQL = "SELECT * FROM livro WHERE bookID = ?";

        try (PreparedStatement ps = this.connection.prepareStatement(SQL))
        {
            ps.setInt(1, index);
            ResultSet rs = ps.executeQuery();

            if ( rs.next() )
            {
                int bookID = rs.getInt("bookID");
                int editorID = rs.getInt("editorID");
                int authorID = rs.getInt("autorID");
                int codeBar = rs.getInt("codigoBarras");
                int releaseYear = rs.getInt("lancamento");
                int numberPages = rs.getInt("numberPages");
                String title = rs.getString("titulo");
                String finishing = rs.getString("acabamento");
                String gender = rs.getString("gender");
                String buyValue = rs.getString("valorCompra");
                String sellValue = rs.getString("valorVenda");

                return new Book(
                    bookID, editorID, authorID, codeBar, releaseYear,
                    numberPages, title, gender, finishing, new BigDecimal(sellValue), new BigDecimal(buyValue)
                );
            }
        }
        return null;
    }

    @Override
    public List read() throws SQLException
    {
        String SQL = "SELECT * FROM livro";
        List<Book> list = new LinkedList();

        try (PreparedStatement ps = this.connection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery())
        {
            while ( rs.next() )
            {
                int bookID = rs.getInt("bookID");
                int editorID = rs.getInt("editorID");
                int authorID = rs.getInt("autorID");
                int codeBar = rs.getInt("codigoBarras");
                int releaseYear = rs.getInt("lancamento");
                int numberPages = rs.getInt("numberPages");
                String title = rs.getString("titulo");
                String finishing = rs.getString("acabamento");
                String gender = rs.getString("genero");
                String buyValue = rs.getString("valorCompra");
                String sellValue = rs.getString("valorVenda");

                list.add(new Book(
                    bookID, editorID, authorID, codeBar, releaseYear,
                    numberPages, title, gender, finishing, new BigDecimal(sellValue), new BigDecimal(buyValue)
                ));
            }
        }
        return list;
    }

    @Override
    public void deleteBy(int ID) throws SQLException
    {
        String SQL = "DELETE FROM livro WHERE bookID = ?";

        try (PreparedStatement ps = this.connection.prepareStatement(SQL))
        {
            ps.setInt(1, ID);
            ps.executeUpdate();
        }
    }
}