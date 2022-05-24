package model.factories;

import model.entities.Address;
import model.entities.PublishingCia;
import org.json.JSONObject;

/**
 *
 *
 */
public class PublishingCiaFactory
{

    public PublishingCia buildFrom(JSONObject json)
    {
        String ID      = json.getString("companyID");
        String name    = json.getString("companyName");
        String address = json.getString("address");

        return new PublishingCia(Integer.parseInt(ID), name, null /* new Address() */);
    }
}