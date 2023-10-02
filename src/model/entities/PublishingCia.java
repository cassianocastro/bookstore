package model.entities;

import java.util.Calendar;

import model.utils.Name;

/**
 *
 */
public class PublishingCia extends PJ
{

    public PublishingCia(int id, Name name, Calendar date, Address address, String cnpj)
    {
        super(id, name, date, address, cnpj);
    }
}