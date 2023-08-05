package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.utils.TableChecker;
import model.entities.Book;
import model.factories.ConnectionSingleton;
import view.BookView;

/**
 *
 */
public class BooksController
{

    private final BookView view;

    public BooksController()
    {
        this.view = new BookView(this);
    }

    public void newBook()
    {
        this.view.getWildCard().setText("Cadastrar");
        this.view.templateMethod(true, false);
    }

    public void edit()
    {
        if ( new TableChecker().hasSelectedRow(this.view.getTable()) )
        {
            this.view.editFields();
            this.view.getWildCard().setText("Atualizar");
            this.view.templateMethod(true, false);
        }
    }

    public void del()
    {
        javax.swing.JTable table = this.view.getTable();

        if ( new TableChecker().hasSelectedRow(table) )
        {
            int response = JOptionPane.showConfirmDialog(
                null,
                "Confirma a exclusão do cadastro? ",
                "Confirmação",
                JOptionPane.YES_NO_OPTION
            );

            if ( response == JOptionPane.YES_OPTION )
            {
                int row = table.getSelectedRow();
                int id  = (int) table.getValueAt(row, 0);

                // controller.invoke("DelCommand", new JSONObject().put("bookID", id));
                this.loadTable();
            }
        }
    }

    public void cancel()
    {
        this.view.clearFields();
        this.view.templateMethod(false, true);
    }

    public void wildcard()
    {
        if ( this.view.fieldsOkay() )
        {
            if ( this.view.getWildCard().getText().equals("Atualizar") )
                ;// controller.invoke("EditCommand", json);
            else
                ;// controller.invoke("NewCommand", json);

            this.loadTable();
            this.view.clearFields();
            this.view.templateMethod(false, true);
        }
    }

    public void showPub()
    {
        new PublishingTablesController(view);
    }

    public void showAuthor()
    {
        new AuthorsTableController(view);
    }

    public void loadTable()
    {
        var model = (DefaultTableModel) this.view.getTable().getModel();
        this.clearTable(model);

        List<Book> list = this.getAll();
        this.addRowsToTable(model, list);
    }

    private List<Book> getAll()
    {
        try (Connection connection = ConnectionSingleton.getInstance())
        {

        }
        catch (SQLException e)
        {

        }
        
        return Collections.emptyList();
    }

    private void clearTable(DefaultTableModel model)
    {
        while ( model.getRowCount() > 0 )
        {
            model.removeRow(0);
        }
    }

    private void addRowsToTable(DefaultTableModel model, List<Book> list)
    {
        for ( Book book : list )
        {
            model.addRow(
                new Object[]
                {
                    book.getCode(),
                    book.getPublishing().getID(),
                    book.getAuthor().getID(),
                    book.getTitle(),
                    book.getGender(),
                    book.getFinishing(),
                    book.getNumberPages(),
                    book.getReleaseYear(),
                    book.getSellValue().toString(),
                    book.getBuyValue().toString()
                }
            );
        }
    }
}