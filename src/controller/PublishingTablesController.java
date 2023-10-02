package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import model.dao.PublishingRepository;
import model.entities.PublishingCia;
import model.factories.ConnectionSingleton;
import view.BookView;
import view.PublishingTableView;

/**
 *
 */
public class PublishingTablesController
{

    private final PublishingTableView view;

    public PublishingTablesController(BookView parent)
    {
        this.view = new PublishingTableView(this, parent);
    }

    public List<PublishingCia> showPublishings()
    {
        try (Connection connection = ConnectionSingleton.getInstance())
        {
            return new PublishingRepository(connection).getAll();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return Collections.emptyList();
    }
}