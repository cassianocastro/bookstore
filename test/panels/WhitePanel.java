/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panels;

import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 *
 * @author cassiano
 */
public class WhitePanel extends JPanel{
    
    private BookPanel book;
    private TablePanel pub;
    
    public WhitePanel() {
        super.setLayout(new BorderLayout(6,6));
        
        this.book = new BookPanel();
        this.pub = new TablePanel();
        
        super.add(book, "North");
        super.add(pub, "South");
    }
    
    
}
