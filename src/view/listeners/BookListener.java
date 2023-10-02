package view.listeners;

import controller.BooksController;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.entities.Book;
import model.utils.TableChecker;
import view.BookView;

/**
 *
 */
public class BookListener
{
    
    private final BooksController controller;
    private final BookView ui;
    
    public BookListener(BookView ui, BooksController controller)
    {
        this.controller = controller;
        this.ui = ui;
        
        this.setFormState(false);
        // this.controller.showBooks();
        
        this.init();
    }

    private void init()
    {
        this.ui.getAddButton().addActionListener((ActionEvent e)    -> this.enableForm());

        this.ui.getUpdateButton().addActionListener((ActionEvent e) -> this.fillFieldsAndEnableForm());

        this.ui.getDeleteButton().addActionListener((ActionEvent e) -> this.confirmExclusion());
        
        this.ui.getSubmitButton().addActionListener((ActionEvent e) -> this.submit());

        this.ui.getCancelButton().addActionListener((ActionEvent e) -> this.cancel());
        
        this.ui.getCloseButton().addActionListener((ActionEvent e)  -> this.ui.dispose());
    }

    public void setFormState(boolean state)
    {
        this.ui.getIdField().setEnabled(state);
        this.ui.getPublishingField().setEnabled(state);
        this.ui.getAuthorField().setEnabled(state);

        this.ui.getTitleField().setEnabled(state);
        this.ui.getGenderField().setEnabled(state);
        this.ui.getFinishingField().setEnabled(state);
        this.ui.getPagesField().setEnabled(state);
        this.ui.getReleaseField().setEnabled(state);

        this.ui.getCodeField().setEnabled(state);
        this.ui.getBuyField().setEnabled(state);
        this.ui.getSellField().setEnabled(state);

        this.ui.getSubmitButton().setEnabled(state);
        this.ui.getCancelButton().setEnabled(state);
    }

    public void setMenuButtonsState(boolean state)
    {
        this.ui.getAddButton().setEnabled(state);
        this.ui.getUpdateButton().setEnabled(state);
        this.ui.getDeleteButton().setEnabled(state);
    }

    public void editFields()
    {
        var table = this.ui.getTable();
        
        String[] args = new String[11];

        for ( int i = 0; i < args.length; ++i )
        {
            args[i] = table.getValueAt(table.getSelectedRow(), i).toString();
        }
        
        setTextFields(args);
    }

    public void clearFields()
    {
        setTextFields(new String[] { "", "", "", "", "", "", "", "", "", "", "" });
    }

    public void setTextFields(String[] args)
    {
        this.ui.getIdField().setText(args[0]);
        this.ui.getPublishingField().setText(args[1]);
        this.ui.getAuthorField().setText(args[2]);

        this.ui.getTitleField().setText(args[3]);
        this.ui.getGenderField().setText(args[4]);
        this.ui.getFinishingField().setText(args[5]);
        this.ui.getPagesField().setText(args[6]);
        this.ui.getReleaseField().setText(args[7]);

        this.ui.getCodeField().setText(args[8]);
        this.ui.getSellField().setText(args[9]);
        this.ui.getBuyField().setText(args[10]);
    }

    public boolean fieldsOk()
    {
        String[] args = this.getTextFields();

        if ( args[0].isEmpty() ) args[0] = "0";

        if ( args[1].isEmpty() ) args[1] = "1";

        if ( args[2].isEmpty() ) args[2] = "1";

        for ( String arg : args )
        {
            if ( arg.isEmpty() ) return false;
        }

        return true;
    }

    public String[] getTextFields()
    {
        String[] args = new String[11];

        args[0] = this.ui.getIdField().getText();
        args[1] = this.ui.getPublishingField().getText();
        args[2] = this.ui.getAuthorField().getText();

        args[3] = this.ui.getTitleField().getText();
        args[4] = this.ui.getGenderField().getText();
        args[5] = this.ui.getFinishingField().getText();
        args[6] = this.ui.getPagesField().getText();
        args[7] = this.ui.getReleaseField().getText();

        args[8] = this.ui.getCodeField().getText();
        args[9] = this.ui.getSellField().getText();
        args[10] = this.ui.getBuyField().getText();

        return args;
    }
    
    private void enableForm()
    {
        this.setFormState(true);
        this.setMenuButtonsState(false);
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
        
        int id = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());

        this.controller.deleteBook();
        this.controller.showBooks();
    }
    
    private void submit()
    {
        if ( ! this.fieldsOk() ) return;
        
        if ( true ) // If book not has ID, then add, else update
            this.controller.addBook();
        else
            this.controller.updateBook();

        this.controller.showBooks();

        this.clearFields();
        this.setFormState(false);
        this.setMenuButtonsState(true);
    }
    
    private void cancel()
    {
        this.clearFields();
        this.setFormState(false);
        this.setMenuButtonsState(true);
    }
    
    public void updateTable()
    {
        var model = (DefaultTableModel) this.ui.getTable().getModel();
        
        while ( model.getRowCount() > 0 )
        {
            model.removeRow(0);
        }
        
        for ( final Book book : this.controller.showBooks() )
        {
            model.addRow(new Object[] {
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
            });
        }
    }
}
