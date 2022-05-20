package model.factories;

import model.Address;
import model.PublishingCia;
import org.json.JSONObject;

/**
 *
 *
 */
public class PublishingCiaFactory
{

    public PublishingCia buildFrom(JSONObject json)
    {
        String companyID     = json.getString("companyID");
        String companyName   = json.getString("companyName");

        String addressCity   = json.getString("addressCity");
        String addressBairro = json.getString("addressDistrict");
        String addressStreet = json.getString("addressStreet");
        String addressNumber = json.getString("addressNumber");
        String addressCompl  = json.getString("addressCompl");
        String addressCEP    = json.getString("addressCEP");
        String addressUF     = json.getString("addressUF");

        return new PublishingCia(
            Integer.parseInt(companyID),
            companyName,
            new Address(
                addressUF,
                addressCity,
                addressBairro,
                addressStreet,
                Integer.parseInt(addressNumber),
                addressCompl,
                addressCEP
            )
        );
    }
}