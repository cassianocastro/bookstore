package view;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import model.dao.AuthorDAO;
import model.dao.ConnectionSingleton;
import org.json.JSONObject;

/**
 *
 *
 */
public class AuthorView extends JFrame
{

    public AuthorView()
    {
        super("Autores");

        this.initComponents();
        this.initListeners();
        this.loadTable();
        this.setPanelState(false);

        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setLocationRelativeTo(null);
        super.setVisible(true);
    }

    public void setButtonsCRUD(boolean isEnabled)
    {
        this.buttonNew .setEnabled(isEnabled);
        this.buttonEdit.setEnabled(isEnabled);
        this.buttonDel .setEnabled(isEnabled);
    }

    private void setPanelState(boolean isEnabled)
    {
        this.fieldAuthorID .setEnabled(isEnabled);
        this.fieldFirstName.setEnabled(isEnabled);
        this.fieldLastName .setEnabled(isEnabled);

        this.buttonWildCard.setEnabled(isEnabled);
        this.buttonCancel  .setEnabled(isEnabled);
        this.buttonShow    .setEnabled(isEnabled);
    }

    public void setTextFields(String[] args)
    {
        this.fieldAuthorID .setText(args[0]);
        this.fieldFirstName.setText(args[1]);
        this.fieldLastName .setText(args[2]);
    }

    public void clearFields()
    {
        setTextFields( new String[] {"", "", ""} );
    }

    public boolean hasSelectedRow()
    {
        int row = this.table.getSelectedRow();

        return row != -1;
    }

    public void editFields()
    {
        String[] args = new String[3];

        for ( int i = 0; i < args.length; i++ )
        {
            args[i] = table.getValueAt(table.getSelectedRow(), i).toString();
        }
        setTextFields(args);
    }

    public JSONObject getJSON()
    {
        JSONObject json = new JSONObject();
        String id = this.fieldAuthorID .getText();

        if (id.isEmpty())
            id = "0";
        json.put("authorID",  id);
        json.put("firstName", this.fieldFirstName.getText());
        json.put("lastName",  this.fieldLastName .getText());

        return json;
    }

    public String getTextFromFieldID()
    {
        return this.fieldAuthorID.getText();
    }

    public JTable getTable()
    {
        return table;
    }

    private void initListeners()
    {
        AuthorView authorView = this;

        this.buttonClose.addActionListener((ActionEvent e) ->
        {
            dispose();
        });

        this.buttonNew.addActionListener((ActionEvent e) ->
        {
            buttonWildCard.setText("Cadastrar");
            setPanelState(true);
            setButtonsCRUD(false);
        });

        this.buttonEdit.addActionListener((ActionEvent event) ->
        {
            if (hasSelectedRow())
            {
                editFields();
                buttonWildCard.setText("Atualizar");
                setPanelState(true);
                setButtonsCRUD(false);
            }
        });

        this.buttonShow.addActionListener((ActionEvent e) ->
        {
            String id = getTextFromFieldID();

            new ObrasView(id);
        });

        this.buttonCancel.addActionListener((ActionEvent event) ->
        {
            clearFields();
            setPanelState(false);
            setButtonsCRUD(true);
        });

        this.buttonDel.addActionListener((ActionEvent event) ->
        {
            JTable table1 = getTable();
            int row = table1.getSelectedRow();

            if ( row != -1 )
            {
                int confirm = JOptionPane.showConfirmDialog(
                    authorView,
                        "Confirma a exclusão do cadastro? ",
                        "Confirmação",
                        JOptionPane.YES_NO_OPTION
                );
                if ( confirm == JOptionPane.YES_OPTION )
                {
                    int id = (int) table1.getValueAt(row, 0);
                    try
                    {
                        Connection connection = ConnectionSingleton.getInstance();
                        new AuthorDAO(connection).delete(id);
                        loadTable();
                    } catch(SQLException e)
                    {
                        JOptionPane.showMessageDialog(authorView, e.getMessage());
                    }
                }
            }
        });

        this.buttonWildCard.addActionListener((ActionEvent event) ->
        {
            String label = buttonWildCard.getText();
            JSONObject json = getJSON();

            try
            {
                Connection connection = ConnectionSingleton.getInstance();

                if ( label.equals("Atualizar") )
                    new AuthorDAO(connection).update(json);
                else
                    new AuthorDAO(connection).create(json);
                loadTable();
            } catch (SQLException e)
            {
                JOptionPane.showMessageDialog(authorView, e.getMessage());
            }
            JOptionPane.showMessageDialog(authorView, "Registro Salvo.");
            clearFields();
            setPanelState(false);
            setButtonsCRUD(true);
        });
    }

    private void loadTable()
    {
        try
        {
            DefaultTableModel model = (DefaultTableModel) this.table.getModel();

            while (this.table.getRowCount() > 0)
            {
                model.removeRow(0);
            }
            Connection connection = ConnectionSingleton.getInstance();
            List<JSONObject> array = new AuthorDAO(connection).read();

            for ( JSONObject json : array )
            {
                model.addRow( new Object[]
                {
                    json.getInt("authorID"),
                    json.getString("firstName"),
                    json.getString("lastName")
                });
            }
        } catch (SQLException e)
        {
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
    private void initComponents()
    {

        jPanel6 = new JPanel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jPanel8 = new JPanel();
        buttonClose = new JButton();
        jPanel1 = new JPanel();
        jPanel2 = new JPanel();
        jSplitPane1 = new JSplitPane();
        buttonWildCard = new JButton();
        buttonCancel = new JButton();
        jPanel3 = new JPanel();
        fieldAuthorID = new JFormattedTextField();
        fieldFirstName = new JFormattedTextField();
        fieldLastName = new JFormattedTextField();
        buttonShow = new JButton();
        jScrollPane1 = new JScrollPane();
        table = new JTable();
        jPanel4 = new JPanel();
        buttonNew = new JButton();
        buttonEdit = new JButton();
        buttonDel = new JButton();
        jPanel7 = new JPanel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jPanel6.setBackground(new Color(194, 1, 20));

        jLabel2.setIcon(new ImageIcon(getClass().getResource("/resources/book(1).png"))); // NOI18N

        jLabel3.setIcon(new ImageIcon(getClass().getResource("/resources/book(1).png"))); // NOI18N

        GroupLayout jPanel6Layout = new GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(20, 20, 20))
        );

        jPanel8.setBackground(new Color(12, 18, 12));

        buttonClose.setFont(new Font("Dialog", 1, 14)); // NOI18N
        buttonClose.setForeground(new Color(236, 235, 243));
        buttonClose.setText("Fechar");
        buttonClose.setBorder(BorderFactory.createLineBorder(new Color(194, 1, 20), 3));
        buttonClose.setContentAreaFilled(false);
        buttonClose.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        buttonClose.setFocusPainted(false);

        GroupLayout jPanel8Layout = new GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(buttonClose, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
        );
        jPanel8Layout.setVerticalGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(buttonClose, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new Color(236, 235, 243));

        jPanel2.setBackground(new Color(236, 235, 243));
        jPanel2.setBorder(BorderFactory.createLineBorder(new Color(12, 18, 12), 3));

        jSplitPane1.setBackground(new Color(236, 235, 243));

        buttonWildCard.setForeground(new Color(12, 18, 12));
        buttonWildCard.setText("Salvar");
        buttonWildCard.setBorder(BorderFactory.createLineBorder(new Color(194, 1, 20), 3));
        buttonWildCard.setContentAreaFilled(false);
        buttonWildCard.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        buttonWildCard.setFocusPainted(false);
        buttonWildCard.setMaximumSize(new Dimension(100, 31));
        buttonWildCard.setMinimumSize(new Dimension(100, 31));
        buttonWildCard.setPreferredSize(new Dimension(100, 31));
        jSplitPane1.setLeftComponent(buttonWildCard);

        buttonCancel.setForeground(new Color(12, 18, 12));
        buttonCancel.setText("Cancelar");
        buttonCancel.setBorder(BorderFactory.createLineBorder(new Color(194, 1, 20), 3));
        buttonCancel.setContentAreaFilled(false);
        buttonCancel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        buttonCancel.setFocusPainted(false);
        buttonCancel.setPreferredSize(new Dimension(100, 31));
        jSplitPane1.setRightComponent(buttonCancel);

        jPanel3.setBackground(new Color(236, 235, 243));

        fieldAuthorID.setEditable(false);
        fieldAuthorID.setBackground(new Color(236, 235, 243));
        fieldAuthorID.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2), "ID Autor", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Liberation Sans", 0, 15), new Color(12, 18, 12))); // NOI18N
        fieldAuthorID.setForeground(new Color(12, 18, 12));
        fieldAuthorID.setCaretColor(new Color(194, 1, 20));
        fieldAuthorID.setSelectionColor(new Color(152, 58, 69));

        fieldFirstName.setBackground(new Color(236, 235, 243));
        fieldFirstName.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2), "Nome", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Liberation Sans", 0, 15), new Color(12, 18, 12))); // NOI18N
        fieldFirstName.setForeground(new Color(12, 18, 12));
        fieldFirstName.setCaretColor(new Color(194, 1, 20));
        fieldFirstName.setSelectionColor(new Color(152, 58, 69));

        fieldLastName.setBackground(new Color(236, 235, 243));
        fieldLastName.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2), "Sobrenome", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Liberation Sans", 0, 15), new Color(12, 18, 12))); // NOI18N
        fieldLastName.setForeground(new Color(12, 18, 12));
        fieldLastName.setCaretColor(new Color(194, 1, 20));
        fieldLastName.setSelectionColor(new Color(152, 58, 69));

        GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(fieldFirstName)
                    .addComponent(fieldAuthorID)
                    .addComponent(fieldLastName, GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(fieldAuthorID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldFirstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldLastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        buttonShow.setBackground(new Color(236, 235, 243));
        buttonShow.setForeground(new Color(12, 18, 12));
        buttonShow.setText("<html>\n<center>\nVer\n<br>\nObras\n</center>\n</html>");
        buttonShow.setBorder(BorderFactory.createLineBorder(new Color(12, 18, 12), 2));
        buttonShow.setContentAreaFilled(false);
        buttonShow.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        buttonShow.setFocusPainted(false);

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jSplitPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonShow, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonShow, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSplitPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jScrollPane1.setBackground(new Color(236, 235, 243));
        jScrollPane1.setBorder(BorderFactory.createLineBorder(new Color(12, 18, 12), 3));
        jScrollPane1.setForeground(new Color(12, 18, 12));
        jScrollPane1.setOpaque(false);

        table.setBackground(new Color(236, 235, 243));
        table.setForeground(new Color(12, 18, 12));
        table.setModel(new DefaultTableModel(
            new Object [][]
            {
                {null, null, null}
            },
            new String []
            {
                "ID do Autor", "Nome", "Sobrenome"
            }
        )
        {
            Class[] types = new Class []
            {
                Integer.class, String.class, String.class
            };
            boolean[] canEdit = new boolean []
            {
                false, false, false
            };

            public Class getColumnClass(int columnIndex)
            {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setFillsViewportHeight(true);
        table.setGridColor(new Color(12, 18, 12));
        table.setSelectionBackground(new Color(152, 58, 69));
        table.setSelectionForeground(new Color(236, 235, 243));
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0)
        {
            table.getColumnModel().getColumn(0).setPreferredWidth(150);
            table.getColumnModel().getColumn(1).setPreferredWidth(300);
            table.getColumnModel().getColumn(2).setPreferredWidth(300);
        }

        jPanel4.setBackground(new Color(236, 235, 243));

        buttonNew.setBackground(new Color(236, 235, 243));
        buttonNew.setForeground(new Color(12, 18, 12));
        buttonNew.setText("Novo");
        buttonNew.setBorder(BorderFactory.createLineBorder(new Color(194, 1, 20), 3));
        buttonNew.setContentAreaFilled(false);
        buttonNew.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        buttonNew.setFocusPainted(false);

        buttonEdit.setBackground(new Color(236, 235, 243));
        buttonEdit.setForeground(new Color(12, 18, 12));
        buttonEdit.setText("Editar");
        buttonEdit.setBorder(BorderFactory.createLineBorder(new Color(194, 1, 20), 3));
        buttonEdit.setContentAreaFilled(false);
        buttonEdit.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        buttonEdit.setFocusPainted(false);

        buttonDel.setBackground(new Color(236, 235, 243));
        buttonDel.setForeground(new Color(12, 18, 12));
        buttonDel.setText("Excluir");
        buttonDel.setBorder(BorderFactory.createLineBorder(new Color(194, 1, 20), 3));
        buttonDel.setContentAreaFilled(false);
        buttonDel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        buttonDel.setFocusPainted(false);

        GroupLayout jPanel4Layout = new GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonNew, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonEdit, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonDel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(buttonDel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonEdit, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonNew, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel7.setBackground(new Color(173, 30, 45));

        GroupLayout jPanel7Layout = new GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton buttonCancel;
    private JButton buttonClose;
    private JButton buttonDel;
    private JButton buttonEdit;
    private JButton buttonNew;
    private JButton buttonShow;
    private JButton buttonWildCard;
    private JFormattedTextField fieldAuthorID;
    private JFormattedTextField fieldFirstName;
    private JFormattedTextField fieldLastName;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel6;
    private JPanel jPanel7;
    private JPanel jPanel8;
    private JScrollPane jScrollPane1;
    private JSplitPane jSplitPane1;
    private JTable table;
    // End of variables declaration//GEN-END:variables
}