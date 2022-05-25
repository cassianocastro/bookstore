package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.TableChecker;
import model.dao.PublishingCiaDAO;
import model.entities.PublishingCia;
import model.factories.ConnectionSingleton;
import model.factories.PublishingCiaFactory;
import org.json.JSONObject;
import view.PublishingView;

/**
 *
 *
 */
public class PublishingController
{

    private final PublishingView view;

    public PublishingController()
    {
        this.view = new PublishingView(this);
    }

    public void newPub()
    {
        this.view.getSaveButton().setText("Cadastrar");
        this.view.setPanelState(true);
        this.view.setButtonsCRUD(false);
    }

    public void edit()
    {
        if ( new TableChecker().hasSelectedRow(this.view.getTable()) )
        {
            this.view.editFields();
            this.view.getSaveButton().setText("Atualizar");
            this.view.setPanelState(true);
            this.view.setButtonsCRUD(false);
        }
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
                    // new PublishingCiaDAO(connection).delete(id);
                } catch (SQLException e)
                {
                    System.out.println(e.getMessage());
                }
                this.loadTable();
            }
        }
    }

    public void save()
    {
        String labelWildCard = this.view.getSaveButton().getText();
        JSONObject json      = this.view.getJSON();
        PublishingCia publishingCia = new PublishingCiaFactory().buildFrom(json);

        try (Connection connection = ConnectionSingleton.getInstance())
        {
            if ( labelWildCard.equals("Atualizar") )
                new PublishingCiaDAO(connection).update(publishingCia);
            else
                new PublishingCiaDAO(connection).insert(publishingCia);
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        this.loadTable();
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
        List<PublishingCia> list = getAll();

        for ( PublishingCia cia : list )
        {
            model.addRow(
                new Object[]
                {
                    cia.getName(),
                    cia.getAddress().getUF(),
                    cia.getAddress().getCity(),
                    cia.getAddress().getDistrict(),
                    cia.getAddress().getCEP(),
                    cia.getAddress().getStreet(),
                    cia.getAddress().getNumber(),
                    cia.getAddress().getComplement()
                }
            );
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
}