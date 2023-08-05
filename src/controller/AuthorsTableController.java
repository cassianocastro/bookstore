package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.utils.TableChecker;
import model.dao.AuthorRepository;
import model.entities.Author;
import model.factories.ConnectionSingleton;
import view.AuthorsTableView;
import view.BookView;

/**
 *
 */
public class AuthorsTableController
{

    private final AuthorsTableView view;
    private final BookView parentView;
    private int id;

    public AuthorsTableController(BookView parentView)
    {
        this.view       = new AuthorsTableView(this);
        this.parentView = parentView;
    }

    public void loadTable()
    {
        var model = (DefaultTableModel) this.view.getTable().getModel();

        while ( model.getRowCount() > 0 )
        {
            model.removeRow(0);
        }
        
        List<Author> list = this.getAll();

        for ( Author author : list )
        {
            model.addRow(new Object[] { author.getName().toString() });
        }
    }

    private List<Author> getAll()
    {
        try (Connection connection = ConnectionSingleton.getInstance())
        {
            return new AuthorRepository(connection).getAll();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        
        return Collections.emptyList();
    }

    public void okay()
    {
        javax.swing.JTable table = this.view.getTable();

        if ( new TableChecker().hasSelectedRow(table) )
        {
            int row = table.getSelectedRow();
            id      = (int) table.getValueAt(row, 0);
            parentView.getAuthorField().setText(String.valueOf(id));
        }
        
        this.view.dispose();
    }

    public int getID()
    {
        return this.id;
    }
}