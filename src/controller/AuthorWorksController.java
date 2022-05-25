package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.dao.AuthorWorksDAO;
import model.factories.ConnectionSingleton;
import org.json.JSONObject;
import view.AuthorWorksView;

/**
 *
 *
 */
public class AuthorWorksController
{

    private final AuthorWorksView view;
    private String id;

    public AuthorWorksController()
    {
        this.view = new AuthorWorksView(this);
    }

    public void setID(String id)
    {
        this.id = id;
    }

    public void loadTable()
    {
        if ( id.isEmpty() ) return;

        int authorID = Integer.parseInt(id);
        var model    = (DefaultTableModel) this.view.getTable().getModel();

        while ( model.getRowCount() > 0 )
        {
            model.removeRow(0);
        }

        List<JSONObject> list = this.getAll();

        for ( JSONObject json : list )
        {
            model.addRow(
                new Object[]
                {
                    json.getString("authorFirstName") + " " + json.getString("authorLastName"),
                    json.getString("bookTitle")
                }
            );
        }
    }

    private List<JSONObject> getAll()
    {
        try (Connection connection = ConnectionSingleton.getInstance())
        {
            return new AuthorWorksDAO(connection).findBooksByAuthor(0);
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return Collections.emptyList();
    }
}