package factories;

import org.json.JSONObject;

/**
 *
 * @author cassiano
 */
public class JSONFactory {
    
    private JSONObject json;

    public JSONFactory() {
        this.json = new JSONObject();
    }
    
    public void buildFrom(String[] args){
        
        json.put("bookID",      args[0]);
        json.put("publishingID",args[1]);
        json.put("authorID",    args[2]);
        
        json.put("title",       args[3]);
        json.put("gender",      args[4]);
        
        json.put("finishing",   args[5]);
        json.put("numberPages", args[6]);
        json.put("releaseYear", args[7]);
        
        json.put("code",        args[8]);
        json.put("sellValue",   args[9]);
        json.put("buyValue",    args[10]);

    }
    
    public JSONObject getJSON(){
        return this.json;
    }
}
