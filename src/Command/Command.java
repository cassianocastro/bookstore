/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import org.json.JSONObject;

/**
 *
 * @author cassiano
 */
public interface Command {
    
    public void execute(JSONObject json);
}
