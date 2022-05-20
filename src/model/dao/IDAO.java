package model.dao;

import java.sql.SQLException;
import java.util.List;

/**
 *
 *
 */
public interface IDAO
{

    public List read() throws SQLException;

    public void deleteBy(int ID) throws SQLException;

}