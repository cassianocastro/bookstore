package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.TableChecker;
import model.dao.PublishingCiaDAO;
import model.entities.PublishingCia;
import model.factories.ConnectionSingleton;
import view.BookView;
import view.PublishingTableView;

/**
 *
 *
 */
public class PublishingTableController
{

    private final PublishingTableView view;
    private final BookView parentView;
    private int id;

    public PublishingTableController(BookView parentView)
    {
        this.view       = new PublishingTableView(this);
        this.parentView = parentView;
    }

    public void loadTable()
    {
        var model = (DefaultTableModel) this.view.getTable().getModel();

        while ( model.getRowCount() > 0 )
        {
            model.removeRow(0);
        }
        List<PublishingCia> list = this.getAll();

        for ( PublishingCia cia : list )
        {
            model.addRow(new Object[] { cia.getName() });
        }
    }

    private List<PublishingCia> getAll()
    {
        try (Connection connection = ConnectionSingleton.getInstance())
        {
            return new PublishingCiaDAO(connection).getAll();
        } catch (SQLException e)
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
            parentView.getPublishingField().setText(String.valueOf(id));
        }
        this.view.dispose();
    }

    public int getID()
    {
        return this.id;
    }
}