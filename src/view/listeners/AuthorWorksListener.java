package view.listeners;

import controller.AuthorWorksController;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import view.AuthorWorksView;

/**
 *
 */
public class AuthorWorksListener
{
    
    private final AuthorWorksController controller;
    private final AuthorWorksView ui;
    
    public AuthorWorksListener(AuthorWorksView ui, AuthorWorksController controller)
    {    
        this.controller = controller;
        this.ui = ui;
        
        // this.updateTable();
        this.init();
    }
    
    private void init()
    {
        this.ui.getOkButton().addActionListener((ActionEvent e) -> this.ui.dispose());
    }
    
    public void updateTable()
    {
        String id = this.controller.getID();
        
        if ( id.isEmpty() ) return;

        int authorID = Integer.parseInt(id);
        
        var model = (DefaultTableModel) this.ui.getTable().getModel();

        while ( model.getRowCount() > 0 )
        {
            model.removeRow(0);
        }

        for ( Object json : this.controller.showBooksFromAuthor() )
        {
            model.addRow(
                new Object[]
                {
                    //json.getString("authorFirstName") + " " + json.getString("authorLastName"),
                    //json.getString("bookTitle")
                }
            );
        }
    }
}