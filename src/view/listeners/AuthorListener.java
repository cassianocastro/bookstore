package view.listeners;

import controller.AuthorsController;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.entities.Author;
import model.utils.Name;
import model.utils.TableChecker;
import view.AuthorView;

/**
 *
 */
public class AuthorListener
{
    
    private final AuthorsController controller;
    private final AuthorView ui;
    
    public AuthorListener(AuthorView frame, AuthorsController controller)
    {
        this.controller = controller;
        this.ui = frame;
        
        this.init();
        
        this.setFormEnabled(false);
        // this.updateTable();
    }
    
    public void init()
    {
        {
            var button = this.ui.getAddButton();
            
            button.addActionListener((ActionEvent e) -> this.enableForm());
        }
        
        {
            var button = this.ui.getUpdateButton();
            
            button.addActionListener((ActionEvent e) -> this.fillFieldsAndEnableForm());
        }
        
        {
            var button = this.ui.getShowButton();
            
            button.addActionListener((ActionEvent e) -> this.controller.showAuthorsWorks());
        }
        
        {
            var button = this.ui.getDeleteButton();
            
            button.addActionListener((ActionEvent e) -> this.confirmExclusion());
        }
        
        {
            var button = this.ui.getSubmitButton();
            
            button.addActionListener((ActionEvent e) -> this.submit());
        }
        
        {
            var button = this.ui.getCancelButton();
            
            button.addActionListener((ActionEvent e) -> this.ui.dispose());
        }
    }
    
    public void setMenuButtonsEnabled(boolean state)
    {
        this.ui.getAddButton().setEnabled(state);
        this.ui.getUpdateButton().setEnabled(state);
        this.ui.getDeleteButton().setEnabled(state);
    }

    public void setFormEnabled(boolean state)
    {
        this.ui.getIDField().setEnabled(state);
        this.ui.getNameField().setEnabled(state);
        
        this.ui.getShowButton().setEnabled(state);
        this.ui.getSubmitButton().setEnabled(state);
        this.ui.getCancelButton().setEnabled(state);
    }

    public void editFields()
    {
        var table = this.ui.getTable();
        
        String[] args = new String[2];

        for ( int i = 0; i < args.length; ++i )
        {
            args[i] = table.getValueAt(table.getSelectedRow(), i).toString();
        }
        
        setTextFields(args);
    }

    public void clearFields()
    {
        setTextFields(new String[] { "", "" });
    }

    public void setTextFields(String[] args)
    {
        this.ui.getIDField().setText(args[0]);
        this.ui.getNameField().setText(args[1]);
    }

    public Author getAuthor()
    {
        String idField = this.ui.getIDField().getText();

        if ( idField.isEmpty() ) idField = "0";

        int id = Integer.parseInt(idField);
        
        String input   = this.ui.getNameField().getText();
        
        String[] array = input.split(" ");
        String name    = array[0];
        String surname = array[1];

        return new Author(id, new Name(name, surname));
    }
    
    private void enableForm()
    {
        this.setFormEnabled(true);
        this.setMenuButtonsEnabled(false);
    }
    
    private void fillFieldsAndEnableForm()
    {
        if ( ! new TableChecker().hasSelectedRow(this.ui.getTable()) ) return;

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
        
        this.controller.deleteAuthor();
        this.updateTable();
    }
    
    private void submit()
    {
        Author author = this.getAuthor();

        if ( true ) // If author has ID, then update, else add
            this.controller.addAuthor();
        else
            this.controller.updateAuthor();
        
        this.updateTable();
        
        JOptionPane.showMessageDialog(this.ui, "Registro Salvo.");

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

        for ( final Author author : this.controller.showAuthors() )
        {
            model.addRow(new Object[] { author.getName().toString() });
        }
    }
}