package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import model.dao.PublishingRepository;
import model.entities.PublishingCia;
import model.factories.ConnectionSingleton;
import view.PublishingView;

/**
 *
 */
public class PublishingsController
{

    private final PublishingView view;

    public PublishingsController()
    {
        this.view = new PublishingView(this);
    }

    public void addPublishing()
    {
        try (Connection connection = ConnectionSingleton.getInstance())
        {
            // new PublishingRepository(connection).insert(publishing);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
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

    public void updatePublishing()
    {
        try (Connection connection = ConnectionSingleton.getInstance())
        {
            // new PublishingRepository(connection).update(publishing);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void deletePublishing()
    {
        try (Connection connection = ConnectionSingleton.getInstance())
        {
            // new PublishingRepository(connection).delete(publishing);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
}