package view;

import model.factories.PublishingCiaFactory;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.PublishingCia;
import model.dao.PublishingCiaDAO;
import org.json.JSONObject;

/**
 *
 *
 */
public class PublishingView extends JFrame
{

    public PublishingView()
    {
        super("Editoras");

        initComponents();
        initListeners();
        loadTable();
        setPanelState(false);

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

    public void setPanelState(boolean isEnabled)
    {
        this.fieldPublishingID.setEnabled(isEnabled);
        this.fieldName        .setEnabled(isEnabled);

        this.fieldUF          .setEnabled(isEnabled);
        this.fieldCity        .setEnabled(isEnabled);
        this.fieldDistrict    .setEnabled(isEnabled);
        this.fieldCEP         .setEnabled(isEnabled);
        this.fieldStreet      .setEnabled(isEnabled);
        this.fieldNumber      .setEnabled(isEnabled);
        this.fieldCompl       .setEnabled(isEnabled);

        this.buttonSave       .setEnabled(isEnabled);
        this.buttonCancel     .setEnabled(isEnabled);
    }

    public void setTextFields(String[] args)
    {
        this.fieldPublishingID.setText(args[0]);
        this.fieldName        .setText(args[1]);
        this.fieldUF          .setText(args[2]);
        this.fieldCity        .setText(args[3]);
        this.fieldDistrict    .setText(args[4]);
        this.fieldCEP         .setText(args[5]);
        this.fieldStreet      .setText(args[6]);
        this.fieldNumber      .setText(args[7]);
        this.fieldCompl       .setText(args[8]);
    }

    public String[] getTextFields()
    {
        String[] args = new String[9];

        args[0] = this.fieldPublishingID.getText();
        args[1] = this.fieldName        .getText();
        args[2] = this.fieldUF          .getText();
        args[3] = this.fieldCity        .getText();
        args[4] = this.fieldDistrict    .getText();
        args[5] = this.fieldCEP         .getText();
        args[6] = this.fieldStreet      .getText();
        args[7] = this.fieldNumber      .getText();
        args[8] = this.fieldCompl       .getText();

        return args;
    }

    public void clearFields()
    {
        String[] args = new String[9];

        for ( String arg : args )
        {
            arg = "";
        }
        setTextFields(args);
    }

    public boolean hasSelectedRow()
    {
        int row = this.table.getSelectedRow();

        return row != -1;
    }

    public void editFields()
    {
        String[] args = new String[9];

        for ( int i = 0; i < args.length; i++ )
        {
            args[i] = table.getValueAt(this.table.getSelectedRow(), i).toString();
        }

        setTextFields(args);
    }

    public boolean foo()
    {
        String[] args = getTextFields();

        if (args[0].isEmpty())
            args[0] = "0";

        for ( String arg : args )
        {
            if ( arg.isEmpty() ) return false;
        }
        this.getJSON();

        return true;
    }

    public JSONObject getJSON()
    {
        JSONObject json = new JSONObject();
        String id = this.fieldPublishingID.getText();

        if (id.isEmpty())
            id = "0";

        json.put("companyID",     id);
        json.put("companyName",   this.fieldName  .getText());
        json.put("addressCity",   this.fieldCity  .getText());
        json.put("addressDistrict", this.fieldDistrict.getText());
        json.put("addressStreet", this.fieldStreet.getText());
        json.put("addressNumber", this.fieldNumber.getText());
        json.put("addressCompl",  this.fieldCompl .getText());
        json.put("addressCEP",    this.fieldCEP   .getText());
        json.put("addressUF",     this.fieldUF    .getText());

        return json;
    }

    private void initListeners()
    {
        this.buttonClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        this.buttonNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonSave.setText("Cadastrar");
                setPanelState(true);
                setButtonsCRUD(false);
            }
        });

        this.buttonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (hasSelectedRow()){
                    editFields();
                    buttonSave.setText("Atualizar");
                    setPanelState(true);
                    setButtonsCRUD(false);
                }
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
                int row = table.getSelectedRow();

                if (row != -1){
                    int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "Confirma a exclusão do cadastro? ",
                        "Confirmação",
                        JOptionPane.YES_NO_OPTION
                    );
                    if (confirm == JOptionPane.YES_OPTION){
                        int id = (int) table.getValueAt(row, 0);
                        try{
                            new PublishingCiaDAO().deleteBy(id);
                            loadTable();
                        }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        }
                    }
                }
            }
        });

        this.buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String labelWildCard = buttonSave.getText();
                JSONObject json      = getJSON();
                PublishingCia publishingCia = new PublishingCiaFactory().buildFrom(json);
                try {
                    if (labelWildCard.equals("Atualizar"))
                        new PublishingCiaDAO().update(publishingCia);
                    else
                        new PublishingCiaDAO().create(publishingCia);
                    loadTable();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
                clearFields();
                setPanelState(false);
                setButtonsCRUD(true);
            }
        });
    }

    public void loadTable()
    {
        try
        {
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            while ( table.getRowCount() > 0 )
            {
                model.removeRow(0);
            }

            List<PublishingCia> list = new PublishingCiaDAO().read();

            for ( PublishingCia publishingCia : list )
            {
                model.addRow(
                    new Object[]
                    {
                        publishingCia.getCompanyID(),
                        publishingCia.getName(),
                        publishingCia.getAddress().getUf(),
                        publishingCia.getAddress().getCity(),
                        publishingCia.getAddress().getBairro(),
                        publishingCia.getAddress().getCep(),
                        publishingCia.getAddress().getStreet(),
                        publishingCia.getAddress().getNumber(),
                        publishingCia.getAddress().getCompl()
                    }
                );
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
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        buttonNew = new javax.swing.JButton();
        buttonEdit = new javax.swing.JButton();
        buttonDel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        buttonClose = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        fieldName = new javax.swing.JTextField();
        fieldPublishingID = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        fieldCity = new javax.swing.JFormattedTextField();
        fieldDistrict = new javax.swing.JFormattedTextField();
        fieldStreet = new javax.swing.JFormattedTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        fieldCompl = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        fieldUF = new javax.swing.JFormattedTextField();
        fieldNumber = new javax.swing.JFormattedTextField();
        fieldCEP = new javax.swing.JFormattedTextField();
        buttonSave = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel5.setBackground(new java.awt.Color(236, 235, 243));

        jPanel4.setBackground(new java.awt.Color(236, 235, 243));

        buttonNew.setForeground(new java.awt.Color(12, 18, 12));
        buttonNew.setText("Novo");
        buttonNew.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(194, 1, 20), 3));
        buttonNew.setContentAreaFilled(false);
        buttonNew.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonNew.setFocusPainted(false);

        buttonEdit.setForeground(new java.awt.Color(12, 18, 12));
        buttonEdit.setText("Editar");
        buttonEdit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(194, 1, 20), 3));
        buttonEdit.setContentAreaFilled(false);
        buttonEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonEdit.setFocusPainted(false);

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

        jScrollPane1.setBackground(new java.awt.Color(236, 235, 243));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(12, 18, 12), 3));

        table.setBackground(new java.awt.Color(236, 235, 243));
        table.setForeground(new java.awt.Color(12, 18, 12));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Editora", "Nome da Cia.", "UF/Estado", "Cidade", "Bairro", "CEP", "Rua", "Número", "Complemento"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        table.setFillsViewportHeight(true);
        table.setGridColor(new java.awt.Color(12, 18, 12));
        table.setSelectionBackground(new java.awt.Color(152, 58, 69));
        table.setSelectionForeground(new java.awt.Color(236, 235, 243));
        table.setShowGrid(true);
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setPreferredWidth(150);
            table.getColumnModel().getColumn(1).setPreferredWidth(300);
            table.getColumnModel().getColumn(2).setPreferredWidth(100);
            table.getColumnModel().getColumn(3).setPreferredWidth(200);
            table.getColumnModel().getColumn(4).setPreferredWidth(200);
            table.getColumnModel().getColumn(5).setPreferredWidth(100);
            table.getColumnModel().getColumn(6).setPreferredWidth(200);
            table.getColumnModel().getColumn(7).setPreferredWidth(100);
            table.getColumnModel().getColumn(8).setPreferredWidth(500);
        }

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

        jPanel6.setBackground(new java.awt.Color(12, 18, 12));
        jPanel6.setPreferredSize(new java.awt.Dimension(118, 35));

        buttonClose.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        buttonClose.setForeground(new java.awt.Color(236, 235, 243));
        buttonClose.setText("Fechar");
        buttonClose.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(194, 1, 20), 3));
        buttonClose.setContentAreaFilled(false);
        buttonClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonClose.setFocusPainted(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonClose, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(buttonClose, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        jPanel10.setBackground(new java.awt.Color(194, 1, 20));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bookstore.png"))); // NOI18N

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bookstore.png"))); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(20, 20, 20))
        );

        jPanel11.setBackground(new java.awt.Color(236, 235, 243));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(12, 18, 12), 3));

        fieldName.setBackground(new java.awt.Color(236, 235, 243));
        fieldName.setForeground(new java.awt.Color(12, 18, 12));
        fieldName.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(12, 18, 12), 2), "Editora", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(12, 18, 12))); // NOI18N
        fieldName.setCaretColor(new java.awt.Color(194, 1, 20));
        fieldName.setSelectedTextColor(new java.awt.Color(236, 235, 243));
        fieldName.setSelectionColor(new java.awt.Color(152, 58, 69));

        fieldPublishingID.setEditable(false);
        fieldPublishingID.setBackground(new java.awt.Color(236, 235, 243));
        fieldPublishingID.setForeground(new java.awt.Color(12, 18, 12));
        fieldPublishingID.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(12, 18, 12), 2), "ID", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(12, 18, 12))); // NOI18N
        fieldPublishingID.setCaretColor(new java.awt.Color(194, 1, 20));
        fieldPublishingID.setSelectedTextColor(new java.awt.Color(236, 235, 243));
        fieldPublishingID.setSelectionColor(new java.awt.Color(152, 58, 69));

        jPanel2.setBackground(new java.awt.Color(236, 235, 243));

        fieldCity.setBackground(new java.awt.Color(236, 235, 243));
        fieldCity.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Cidade", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(12, 18, 12))); // NOI18N
        fieldCity.setForeground(new java.awt.Color(12, 18, 12));
        fieldCity.setCaretColor(new java.awt.Color(194, 1, 20));
        fieldCity.setSelectedTextColor(new java.awt.Color(236, 235, 243));
        fieldCity.setSelectionColor(new java.awt.Color(152, 58, 69));

        fieldDistrict.setBackground(new java.awt.Color(236, 235, 243));
        fieldDistrict.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Bairro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(12, 18, 12))); // NOI18N
        fieldDistrict.setForeground(new java.awt.Color(12, 18, 12));
        fieldDistrict.setCaretColor(new java.awt.Color(194, 1, 20));
        fieldDistrict.setSelectedTextColor(new java.awt.Color(236, 235, 243));
        fieldDistrict.setSelectionColor(new java.awt.Color(152, 58, 69));

        fieldStreet.setBackground(new java.awt.Color(236, 235, 243));
        fieldStreet.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Rua", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(12, 18, 12))); // NOI18N
        fieldStreet.setForeground(new java.awt.Color(12, 18, 12));
        fieldStreet.setCaretColor(new java.awt.Color(194, 1, 20));
        fieldStreet.setSelectedTextColor(new java.awt.Color(236, 235, 243));
        fieldStreet.setSelectionColor(new java.awt.Color(152, 58, 69));

        fieldCompl.setBackground(new java.awt.Color(236, 235, 243));
        fieldCompl.setColumns(20);
        fieldCompl.setForeground(new java.awt.Color(12, 18, 12));
        fieldCompl.setRows(5);
        fieldCompl.setTabSize(4);
        fieldCompl.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Complemento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(12, 18, 12))); // NOI18N
        fieldCompl.setCaretColor(new java.awt.Color(194, 1, 20));
        fieldCompl.setSelectedTextColor(new java.awt.Color(236, 235, 243));
        fieldCompl.setSelectionColor(new java.awt.Color(152, 58, 69));
        jScrollPane2.setViewportView(fieldCompl);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fieldStreet, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fieldDistrict)
                    .addComponent(fieldCity, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fieldCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldDistrict, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldStreet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(236, 235, 243));

        fieldUF.setBackground(new java.awt.Color(236, 235, 243));
        fieldUF.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "UF/Estado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(12, 18, 12))); // NOI18N
        fieldUF.setForeground(new java.awt.Color(12, 18, 12));
        try {
            fieldUF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("UU")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        fieldUF.setCaretColor(new java.awt.Color(194, 1, 20));
        fieldUF.setSelectedTextColor(new java.awt.Color(236, 235, 243));
        fieldUF.setSelectionColor(new java.awt.Color(152, 58, 69));

        fieldNumber.setBackground(new java.awt.Color(236, 235, 243));
        fieldNumber.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Número", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(12, 18, 12))); // NOI18N
        fieldNumber.setForeground(new java.awt.Color(12, 18, 12));
        fieldNumber.setCaretColor(new java.awt.Color(194, 1, 20));
        fieldNumber.setSelectedTextColor(new java.awt.Color(236, 235, 243));
        fieldNumber.setSelectionColor(new java.awt.Color(152, 58, 69));

        fieldCEP.setBackground(new java.awt.Color(236, 235, 243));
        fieldCEP.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "CEP", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(12, 18, 12))); // NOI18N
        fieldCEP.setForeground(new java.awt.Color(12, 18, 12));
        try {
            fieldCEP.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        fieldCEP.setCaretColor(new java.awt.Color(194, 1, 20));
        fieldCEP.setSelectedTextColor(new java.awt.Color(236, 235, 243));
        fieldCEP.setSelectionColor(new java.awt.Color(152, 58, 69));

        buttonSave.setBackground(new java.awt.Color(236, 235, 243));
        buttonSave.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        buttonSave.setForeground(new java.awt.Color(12, 18, 12));
        buttonSave.setText("Salvar");
        buttonSave.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(194, 1, 20), 3));
        buttonSave.setContentAreaFilled(false);
        buttonSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonSave.setFocusPainted(false);

        buttonCancel.setBackground(new java.awt.Color(236, 235, 243));
        buttonCancel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        buttonCancel.setForeground(new java.awt.Color(12, 18, 12));
        buttonCancel.setText("Cancelar");
        buttonCancel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(194, 1, 20), 3));
        buttonCancel.setContentAreaFilled(false);
        buttonCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonCancel.setFocusPainted(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fieldNumber)
                    .addComponent(fieldCEP)
                    .addComponent(fieldUF, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonCancel, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fieldUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(fieldName, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fieldPublishingID, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldPublishingID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))
                        .addGap(0, 9, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonClose;
    private javax.swing.JButton buttonDel;
    private javax.swing.JButton buttonEdit;
    private javax.swing.JButton buttonNew;
    private javax.swing.JButton buttonSave;
    private javax.swing.JFormattedTextField fieldCEP;
    private javax.swing.JFormattedTextField fieldCity;
    private javax.swing.JTextArea fieldCompl;
    private javax.swing.JFormattedTextField fieldDistrict;
    private javax.swing.JTextField fieldName;
    private javax.swing.JFormattedTextField fieldNumber;
    private javax.swing.JTextField fieldPublishingID;
    private javax.swing.JFormattedTextField fieldStreet;
    private javax.swing.JFormattedTextField fieldUF;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}