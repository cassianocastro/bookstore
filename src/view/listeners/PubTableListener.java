package view.listeners;

import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import controller.PublishingTablesController;
import model.entities.PublishingCia;
import model.utils.TableChecker;
import view.PublishingTableView;

/**
 *
 */
public class PubTableListener
{
    
    private final PublishingTablesController controller;
    private final PublishingTableView ui;
    
    public PubTableListener(PublishingTableView ui, PublishingTablesController controller)
    {
        this.controller = controller;
        this.ui = ui;

        this.controller.showPublishings();
        
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

//        this.ui.parent.getPublishingField().setText(String.valueOf(id));
        
        this.ui.dispose();
    }
    
    public void updateTable()
    {
        var model = (DefaultTableModel) this.ui.getTable().getModel();

        while ( model.getRowCount() > 0 )
        {
            model.removeRow(0);
        }

        for ( PublishingCia cia : this.controller.showPublishings() )
        {
            model.addRow(new Object[] { cia.getName().toString() });
        }
    }
}