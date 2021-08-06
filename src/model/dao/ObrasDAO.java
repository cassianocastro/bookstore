package model.dao;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONObject;

/**
 * @author cassiano
 */
public class ObrasDAO {
    
    private Connection connection;
    
    public ObrasDAO() throws SQLException{
        this.connection = ConnectionSingleton.getInstance();
    }
    
    public List read(int authorID) throws SQLException{
        String SQL = "SELECT autor.autorID, autor.nome, autor.sobrenome, livro.titulo "
                   + "FROM autor, livro "
                   + "WHERE autor.autorID = livro.autorID "
                   + "AND autor.autorID = ?";
        List<JSONObject> list = new LinkedList();
        
        try(PreparedStatement ps = this.connection.prepareStatement(SQL)){
            ps.setInt(1, authorID);
            ResultSet rs = ps.executeQuery();
            
            while ( rs.next() ){
                JSONObject json = new JSONObject();
                json.put("authorID",        rs.getInt("autorID"));
                json.put("authorFirstName", rs.getString("nome"));
                json.put("authorLastName",  rs.getString("sobrenome"));
                json.put("bookTitle",       rs.getString("titulo"));
                
                list.add(json);
            }
        }
        return list;
    }
    
}
