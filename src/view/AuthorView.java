package view;

import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.dao.AuthorDAO;
import org.json.JSONObject;

/**
 * @author cassiano
 */
public class AuthorView extends JFrame {
    
    public AuthorView() {
        super("Autores");
        initComponents();
        initListeners();
        loadTable();
        setPanelState(false);
        
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setLocationRelativeTo(null);
        super.setVisible(true);
    }
    
    public void setButtonsCRUD(boolean isEnabled){
        this.buttonNew .setEnabled(isEnabled);
        this.buttonEdit.setEnabled(isEnabled);
        this.buttonDel .setEnabled(isEnabled);
    }
    
    public void setPanelState(boolean isEnabled){
        this.fieldAuthorID .setEnabled(isEnabled);
        this.fieldFirstName.setEnabled(isEnabled);
        this.fieldLastName .setEnabled(isEnabled);
        
        this.buttonWildCard.setEnabled(isEnabled);
        this.buttonCancel  .setEnabled(isEnabled);
        this.buttonShow    .setEnabled(isEnabled);
    }
    
    public void setTextFields(String[] args){
        
        this.fieldAuthorID .setText(args[0]);
        this.fieldFirstName.setText(args[1]);
        this.fieldLastName .setText(args[2]);
    }
    
    public void clearFields(){
        setTextFields( new String[] {"", "", ""} );
    }
    
    public boolean hasSelectedRow(){
        int row = this.table.getSelectedRow();
        return (row != -1);
    }
    
    public void editFields(){
        String[] args = new String[3];
        for (int i = 0; i < args.length; i++) {
            args[i] = table.getValueAt(table.getSelectedRow(), i).toString();
        }
        setTextFields(args);
    }
    
    public JSONObject getJSON(){
        JSONObject json = new JSONObject();
        String id = this.fieldAuthorID .getText();
        if (id.isEmpty())
            id = "0";
        json.put("authorID",  id);
        json.put("firstName", this.fieldFirstName.getText());
        json.put("lastName",  this.fieldLastName .getText());
        
        return json;
    }
    
    public String getTextFromFieldID(){
        return this.fieldAuthorID.getText();
    }
    
    public JTable getTable() {
        return table;
    }
    
    private void initListeners(){
        AuthorView authorView = this;
        
        this.buttonClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        this.buttonNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonWildCard.setText("Cadastrar");
                setPanelState(true);
                setButtonsCRUD(false);
            }
        });
        
        this.buttonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (hasSelectedRow()){
                    editFields();
                    buttonWildCard.setText("Atualizar");
                    setPanelState(true);
                    setButtonsCRUD(false);
                }
            }
        });
        
        this.buttonShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = getTextFromFieldID();
                new ObrasView( id );
            }
        });
        
        this.buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                clearFields();
                setPanelState(false);
                setButtonsCRUD(true);
            }
        });
        
        this.buttonDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JTable table = getTable();
                int row      = table.getSelectedRow();
                
                if (row != -1){
                    int confirm = JOptionPane.showConfirmDialog(
                        authorView,
                        "Confirma a exclusão do cadastro? ",
                        "Confirmação",
                        JOptionPane.YES_NO_OPTION
                    );
                    if (confirm == JOptionPane.YES_OPTION){
                        int id = (int) table.getValueAt(row, 0);
                        try{
                            new AuthorDAO().deleteBy(id);
                            loadTable();
                        }catch(SQLException e){
                            JOptionPane.showMessageDialog(authorView, e.getMessage());
                        }
                    }
                }
            }
        });
        
        this.buttonWildCard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String label = buttonWildCard.getText();
                JSONObject json = getJSON();
                try {
                    if (label.equals("Atualizar"))
                        new AuthorDAO().update(json);
                    else
                        new AuthorDAO().create(json);
                    loadTable();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(authorView, e.getMessage());
                }
                JOptionPane.showMessageDialog(authorView, "Registro Salvo.");
                clearFields();
                setPanelState(false);
                setButtonsCRUD(true);
            }
        });
    }
    
    public void loadTable(){
        try {
            DefaultTableModel model = (DefaultTableModel) this.table.getModel();

            while (this.table.getRowCount() > 0) {
                model.removeRow(0);
            }
            List<JSONObject> array = new AuthorDAO().read();
            for (JSONObject json : array) {
                model.addRow( new Object[]{
                    json.getInt("authorID"),
                    json.getString("firstName"),
                    json.getString("lastName")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        buttonClose = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        buttonWildCard = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        fieldAuthorID = new javax.swing.JFormattedTextField();
        fieldFirstName = new javax.swing.JFormattedTextField();
        fieldLastName = new javax.swing.JFormattedTextField();
        buttonShow = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        buttonNew = new javax.swing.JButton();
        buttonEdit = new javax.swing.JButton();
        buttonDel = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel6.setBackground(new java.awt.Color(194, 1, 20));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/book(1).png"))); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/book(1).png"))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(20, 20, 20))
        );

        jPanel8.setBackground(new java.awt.Color(12, 18, 12));

        buttonClose.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        buttonClose.setForeground(new java.awt.Color(236, 235, 243));
        buttonClose.setText("Fechar");
        buttonClose.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(194, 1, 20), 3));
        buttonClose.setContentAreaFilled(false);
        buttonClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonClose.setFocusPainted(false);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(buttonClose, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(buttonClose, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(236, 235, 243));

        jPanel2.setBackground(new java.awt.Color(236, 235, 243));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(12, 18, 12), 3));

        jSplitPane1.setBackground(new java.awt.Color(236, 235, 243));

        buttonWildCard.setForeground(new java.awt.Color(12, 18, 12));
        buttonWildCard.setText("Salvar");
        buttonWildCard.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(194, 1, 20), 3));
        buttonWildCard.setContentAreaFilled(false);
        buttonWildCard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonWildCard.setFocusPainted(false);
        buttonWildCard.setMaximumSize(new java.awt.Dimension(100, 31));
        buttonWildCard.setMinimumSize(new java.awt.Dimension(100, 31));
        buttonWildCard.setPreferredSize(new java.awt.Dimension(100, 31));
        jSplitPane1.setLeftComponent(buttonWildCard);

        buttonCancel.setForeground(new java.awt.Color(12, 18, 12));
        buttonCancel.setText("Cancelar");
        buttonCancel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(194, 1, 20), 3));
        buttonCancel.setContentAreaFilled(false);
        buttonCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonCancel.setFocusPainted(false);
        buttonCancel.setPreferredSize(new java.awt.Dimension(100, 31));
        jSplitPane1.setRightComponent(buttonCancel);

        jPanel3.setBackground(new java.awt.Color(236, 235, 243));

        fieldAuthorID.setEditable(false);
        fieldAuthorID.setBackground(new java.awt.Color(236, 235, 243));
        fieldAuthorID.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "ID Autor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(12, 18, 12))); // NOI18N
        fieldAuthorID.setForeground(new java.awt.Color(12, 18, 12));
        fieldAuthorID.setCaretColor(new java.awt.Color(194, 1, 20));
        fieldAuthorID.setSelectionColor(new java.awt.Color(152, 58, 69));

        fieldFirstName.setBackground(new java.awt.Color(236, 235, 243));
        fieldFirstName.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Nome", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(12, 18, 12))); // NOI18N
        fieldFirstName.setForeground(new java.awt.Color(12, 18, 12));
        fieldFirstName.setCaretColor(new java.awt.Color(194, 1, 20));
        fieldFirstName.setSelectionColor(new java.awt.Color(152, 58, 69));

        fieldLastName.setBackground(new java.awt.Color(236, 235, 243));
        fieldLastName.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Sobrenome", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(12, 18, 12))); // NOI18N
        fieldLastName.setForeground(new java.awt.Color(12, 18, 12));
        fieldLastName.setCaretColor(new java.awt.Color(194, 1, 20));
        fieldLastName.setSelectionColor(new java.awt.Color(152, 58, 69));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fieldFirstName)
                    .addComponent(fieldAuthorID)
                    .addComponent(fieldLastName, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(fieldAuthorID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        buttonShow.setBackground(new java.awt.Color(236, 235, 243));
        buttonShow.setForeground(new java.awt.Color(12, 18, 12));
        buttonShow.setText("<html>\n<center>\nVer\n<br>\nObras\n</center>\n</html>");
        buttonShow.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(12, 18, 12), 2));
        buttonShow.setContentAreaFilled(false);
        buttonShow.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonShow.setFocusPainted(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonShow, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonShow, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jScrollPane1.setBackground(new java.awt.Color(236, 235, 243));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(12, 18, 12), 3));
        jScrollPane1.setForeground(new java.awt.Color(12, 18, 12));
        jScrollPane1.setOpaque(false);

        table.setBackground(new java.awt.Color(236, 235, 243));
        table.setForeground(new java.awt.Color(12, 18, 12));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "ID do Autor", "Nome", "Sobrenome"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        table.setFillsViewportHeight(true);
        table.setGridColor(new java.awt.Color(12, 18, 12));
        table.setSelectionBackground(new java.awt.Color(152, 58, 69));
        table.setSelectionForeground(new java.awt.Color(236, 235, 243));
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setPreferredWidth(150);
            table.getColumnModel().getColumn(1).setPreferredWidth(300);
            table.getColumnModel().getColumn(2).setPreferredWidth(300);
        }

        jPanel4.setBackground(new java.awt.Color(236, 235, 243));

        buttonNew.setBackground(new java.awt.Color(236, 235, 243));
        buttonNew.setForeground(new java.awt.Color(12, 18, 12));
        buttonNew.setText("Novo");
        buttonNew.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(194, 1, 20), 3));
        buttonNew.setContentAreaFilled(false);
        buttonNew.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonNew.setFocusPainted(false);

        buttonEdit.setBackground(new java.awt.Color(236, 235, 243));
        buttonEdit.setForeground(new java.awt.Color(12, 18, 12));
        buttonEdit.setText("Editar");
        buttonEdit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(194, 1, 20), 3));
        buttonEdit.setContentAreaFilled(false);
        buttonEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonEdit.setFocusPainted(false);

        buttonDel.setBackground(new java.awt.Color(236, 235, 243));
        buttonDel.setForeground(new java.awt.Color(12, 18, 12));
        buttonDel.setText("Excluir");
        buttonDel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(194, 1, 20), 3));
        buttonDel.setContentAreaFilled(false);
        buttonDel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonDel.setFocusPainted(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonNew, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonDel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonDel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonEdit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(173, 30, 45));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonClose;
    private javax.swing.JButton buttonDel;
    private javax.swing.JButton buttonEdit;
    private javax.swing.JButton buttonNew;
    private javax.swing.JButton buttonShow;
    private javax.swing.JButton buttonWildCard;
    private javax.swing.JFormattedTextField fieldAuthorID;
    private javax.swing.JFormattedTextField fieldFirstName;
    private javax.swing.JFormattedTextField fieldLastName;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
