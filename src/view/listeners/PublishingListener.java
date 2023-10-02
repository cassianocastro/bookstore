package view.listeners;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controller.PublishingsController;
import model.entities.PublishingCia;
import model.factories.PublishingsFactory;
import model.utils.TableChecker;
import view.PublishingView;

/**
 *
 */
public class PublishingListener
{
    
    private final PublishingsController controller;
    private final PublishingView ui;
    
    public PublishingListener(PublishingView ui, PublishingsController controller)
    {
        this.controller = controller;
        this.ui = ui;
        
        // this.controller.showPublishings();
        this.setFormEnabled(false);
    }
    
    private void initListeners()
    {
        this.ui.getAddButton().addActionListener((ActionEvent e)    -> this.enableForm());

        this.ui.getUpdateButton().addActionListener((ActionEvent e) -> this.fillFieldsAndEnableForm());

        this.ui.getDeleteButton().addActionListener((ActionEvent e) -> this.confirmExclusion());

        this.ui.getSubmitButton().addActionListener((ActionEvent e) -> this.submit());
        
        this.ui.getCancelButton().addActionListener((ActionEvent e) -> this.cancel());
        
        this.ui.getCloseButton().addActionListener((ActionEvent e)  -> this.ui.dispose());
    }

    public void setFormEnabled(boolean state)
    {
        this.ui.getIdField().setEnabled(state);
        this.ui.getNameField().setEnabled(state);

        this.ui.getUfField().setEnabled(state);
        this.ui.getCityField().setEnabled(state);
        this.ui.getDistrictField().setEnabled(state);
        this.ui.getCepField().setEnabled(state);
        this.ui.getStreetField().setEnabled(state);
        this.ui.getNumberField().setEnabled(state);
        this.ui.getComplField().setEnabled(state);

        this.ui.getSubmitButton().setEnabled(state);
        this.ui.getCancelButton().setEnabled(state);
    }

    public void setMenuButtonsEnabled(boolean state)
    {
        this.ui.getAddButton().setEnabled(state);
        this.ui.getUpdateButton().setEnabled(state);
        this.ui.getDeleteButton().setEnabled(state);
    }

    public void editFields()
    {
        var table = this.ui.getTable();
        
        String[] args = new String[9];

        for ( int i = 0; i < args.length; ++i )
        {
            args[i] = table.getValueAt(table.getSelectedRow(), i).toString();
        }

        setTextFields(args);
    }

    public void clearFields()
    {   
        setTextFields(new String[] { "", "", "", "", "", "", "", "", "" });
    }

    public void setTextFields(String[] args)
    {
        this.ui.getIdField().setText(args[0]);
        this.ui.getNameField().setText(args[1]);
        
        this.ui.getUfField()      .setText(args[2]);
        this.ui.getCityField()    .setText(args[3]);
        this.ui.getDistrictField().setText(args[4]);
        this.ui.getCepField()     .setText(args[5]);
        this.ui.getStreetField()  .setText(args[6]);
        this.ui.getNumberField()  .setText(args[7]);
        this.ui.getComplField()   .setText(args[8]);
    }

    public boolean foo()
    {
        String[] args = this.getTextFields();

        if ( args[0].isEmpty() ) args[0] = "0";

        for ( String arg : args )
        {
            if ( arg.isEmpty() ) return false;
        }

        return true;
    }

    public String[] getTextFields()
    {
        String[] args = new String[9];

        args[0] = this.ui.getIdField().getText();
        args[1] = this.ui.getNameField().getText();
        
        args[2] = this.ui.getUfField()      .getText();
        args[3] = this.ui.getCityField()    .getText();
        args[4] = this.ui.getDistrictField().getText();
        args[5] = this.ui.getCepField()     .getText();
        args[6] = this.ui.getStreetField()  .getText();
        args[7] = this.ui.getNumberField()  .getText();
        args[8] = this.ui.getComplField()   .getText();

        return args;
    }
    
    private void enableForm()
    {
        this.setFormEnabled(true);
        this.setMenuButtonsEnabled(false);
    }
    
    private void fillFieldsAndEnableForm()
    {
        if ( ! new TableChecker().hasSelectedRow(this.ui.getTable())) return;

        this.editFields();
        this.enableForm();
    }
    
    private void confirmExclusion()
    {
        var table = this.ui.getTable();
        
        if ( ! new TableChecker().hasSelectedRow(table) ) return;
        
        int response = JOptionPane.showConfirmDialog(
            this.ui,
            "Confirma a exclusão do cadastro? ",
            "Confirmação",
            JOptionPane.YES_NO_OPTION
        );

        if ( response != JOptionPane.YES_OPTION ) return;
        
        int id = (int) table.getValueAt(table.getSelectedRow(), 0);

        this.controller.deletePublishing();
        this.controller.showPublishings();
    }
    
    private void submit()
    {
        var pub = new PublishingsFactory().create();
        
        if ( true )
            this.controller.addPublishing();
        else
            this.controller.updatePublishing();
        
        this.controller.showPublishings();
        
        this.clearFields();
        this.setFormEnabled(false);
        this.setMenuButtonsEnabled(true);
    }
    
    private void cancel()
    {
        this.clearFields();
        this.setFormEnabled(false);
        this.setMenuButtonsEnabled(true);
    }
    
    public void updateTable()
    {
        var model = (DefaultTableModel) this.ui.getTable().getModel();

        while ( model.getRowCount() > 0 )
        {
            model.removeRow(0);
        }
        
        for ( final PublishingCia cia : this.controller.showPublishings() )
        {
            model.addRow(new Object[] {
                cia.getName(),
                cia.getAddress().getUF(),
                cia.getAddress().getCity(),
                cia.getAddress().getDistrict(),
                cia.getAddress().getCEP(),
                cia.getAddress().getStreet(),
                cia.getAddress().getNumber(),
                cia.getAddress().getComplement()
            });
        }
    }
}