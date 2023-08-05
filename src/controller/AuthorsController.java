package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.utils.TableChecker;
import model.dao.AuthorRepository;
import model.entities.Author;
import model.factories.ConnectionSingleton;
import view.AuthorView;

/**
 *
 */
public class AuthorsController
{

    private final AuthorView view;

    public AuthorsController()
    {
        this.view = new AuthorView(this);
    }

    public void newAuthor()
    {
        this.view.getWildCard().setText("Cadastrar");
        this.view.setPanelState(true);
        this.view.setButtonsCRUD(false);
    }

    public void edit()
    {
        if ( new TableChecker().hasSelectedRow(this.view.getTable()) )
        {
            this.view.editFields();
            this.view.getWildCard().setText("Atualizar");
            this.view.setPanelState(true);
            this.view.setButtonsCRUD(false);
        }
    }

    public void showa()
    {
        String id = this.view.getTextFromFieldID();

        new AuthorWorksController().setID(id);
    }

    public void cancel()
    {
        this.view.clearFields();
        this.view.setPanelState(false);
        this.view.setButtonsCRUD(true);
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

                try (Connection connection = ConnectionSingleton.getInstance())
                {
                    // new AuthorDAO(connection).delete(id);
                }
                catch (SQLException e)
                {
                    System.out.println(e.getMessage());
                }
                
                this.loadTable();
            }
        }
    }

    public void wildcard()
    {
        String label  = this.view.getWildCard().getText();
        Author author = this.view.getAuthor();

        try (Connection connection = ConnectionSingleton.getInstance())
        {
            if ( label.equals("Atualizar") )
                new AuthorRepository(connection).update(author);
            else
                new AuthorRepository(connection).insert(author);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        
        this.loadTable();
        JOptionPane.showMessageDialog(this.view, "Registro Salvo.");

        this.view.clearFields();
        this.view.setPanelState(false);
        this.view.setButtonsCRUD(true);
    }

    public void loadTable()
    {
        var model = (DefaultTableModel) this.view.getTable().getModel();

        while ( model.getRowCount() > 0 )
        {
            model.removeRow(0);
        }
        
        List<Author> list = getAll();

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
}