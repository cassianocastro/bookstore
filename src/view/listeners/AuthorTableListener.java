package view.listeners;

import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import controller.AuthorsTableController;
import model.entities.Author;
import model.utils.TableChecker;
import view.AuthorsTableView;

/**
 *
 */
public class AuthorTableListener
{
    
    private final AuthorsTableController controller;
    private final AuthorsTableView ui;
    
    public AuthorTableListener(AuthorsTableView ui, AuthorsTableController controller)
    {
        this.controller = controller;
        this.ui = ui;
        
        // this.controller.showAuthors();
        
        this.init();
    }
    
    private void init()
    {
        this.ui.getOkButton().addActionListener((ActionEvent e) -> this.ok());

        this.ui.getCancelButton().addActionListener((ActionEvent e) -> this.ui.dispose());
    }
    
    private void ok()
    {
        var table = this.ui.getTable();
        
        if ( ! new TableChecker().hasSelectedRow(table) ) return;
        
        int id = (int) table.getValueAt(table.getSelectedRow(), 0);
        
        // this.parent.getAuthorField().setText(String.valueOf(id));
        
        this.ui.dispose();
    }
    
    public void updateTable()
    {
        var model = (DefaultTableModel) this.ui.getTable().getModel();

        while ( model.getRowCount() > 0 )
        {
            model.removeRow(0);
        }
        
        for ( Author author : this.controller.showAuthors() )
        {
            model.addRow(new Object[] { author.getName().toString() });
        }
    }
}