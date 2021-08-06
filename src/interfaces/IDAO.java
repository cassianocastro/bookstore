package interfaces;

import java.sql.SQLException;
import java.util.List;

/**
 * @author cassiano
 */
public interface IDAO {

    public List read() throws SQLException;

    public void deleteBy(int ID) throws SQLException;

}
