package view;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import model.factories.PublishingCiaFactory;
import model.entities.PublishingCia;
import model.dao.ConnectionSingleton;
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
        this.buttonClose.addActionListener((ActionEvent e) ->
        {
            dispose();
        });

        this.buttonNew.addActionListener((ActionEvent e) ->
        {
            buttonSave.setText("Cadastrar");
            setPanelState(true);
            setButtonsCRUD(false);
        });

        this.buttonEdit.addActionListener((ActionEvent event) ->
        {
            if ( hasSelectedRow() )
            {
                editFields();
                buttonSave.setText("Atualizar");
                setPanelState(true);
                setButtonsCRUD(false);
            }
        });

        this.buttonCancel.addActionListener((ActionEvent event) ->
        {
            clearFields();
            setPanelState(false);
            setButtonsCRUD(true);
        });

        this.buttonDel.addActionListener((ActionEvent event) ->
        {
            int row = table.getSelectedRow();

            if ( row != -1 )
            {
                int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Confirma a exclusão do cadastro? ",
                    "Confirmação",
                    JOptionPane.YES_NO_OPTION
                );

                if ( confirm == JOptionPane.YES_OPTION )
                {
                    int id = (int) table.getValueAt(row, 0);

                    try
                    {
                        Connection connection = ConnectionSingleton.getInstance();
                        new PublishingCiaDAO(connection).delete(id);
                        loadTable();
                    } catch(SQLException e)
                    {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                }
            }
        });

        this.buttonSave.addActionListener((ActionEvent event) ->
        {
            String labelWildCard = buttonSave.getText();
            JSONObject json      = getJSON();
            PublishingCia publishingCia = new PublishingCiaFactory().buildFrom(json);

            try
            {
                Connection connection = ConnectionSingleton.getInstance();
                if ( labelWildCard.equals("Atualizar") )
                    new PublishingCiaDAO(connection).update(publishingCia);
                else
                    new PublishingCiaDAO(connection).insert(publishingCia);
                loadTable();
            } catch (SQLException e)
            {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            clearFields();
            setPanelState(false);
            setButtonsCRUD(true);
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

            Connection connection = ConnectionSingleton.getInstance();
            List<PublishingCia> list = new PublishingCiaDAO(connection).getAll();

            for ( PublishingCia publishingCia : list )
            {
                model.addRow(
                    new Object[]
                    {
                        publishingCia.getID(),
                        publishingCia.getName(),
                        publishingCia.getAddress().getUF(),
                        publishingCia.getAddress().getCity(),
                        publishingCia.getAddress().getDistrict(),
                        publishingCia.getAddress().getCEP(),
                        publishingCia.getAddress().getStreet(),
                        publishingCia.getAddress().getNumber(),
                        publishingCia.getAddress().getComplement()
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
    private void initComponents()
    {

        jPanel5 = new JPanel();
        jPanel4 = new JPanel();
        buttonNew = new JButton();
        buttonEdit = new JButton();
        buttonDel = new JButton();
        jScrollPane1 = new JScrollPane();
        table = new JTable();
        jPanel7 = new JPanel();
        jPanel6 = new JPanel();
        buttonClose = new JButton();
        jPanel10 = new JPanel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jPanel11 = new JPanel();
        fieldName = new JTextField();
        fieldPublishingID = new JTextField();
        jPanel2 = new JPanel();
        fieldCity = new JFormattedTextField();
        fieldDistrict = new JFormattedTextField();
        fieldStreet = new JFormattedTextField();
        jScrollPane2 = new JScrollPane();
        fieldCompl = new JTextArea();
        jPanel3 = new JPanel();
        fieldUF = new JFormattedTextField();
        fieldNumber = new JFormattedTextField();
        fieldCEP = new JFormattedTextField();
        buttonSave = new JButton();
        buttonCancel = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jPanel5.setBackground(new Color(236, 235, 243));

        jPanel4.setBackground(new Color(236, 235, 243));

        buttonNew.setForeground(new Color(12, 18, 12));
        buttonNew.setText("Novo");
        buttonNew.setBorder(BorderFactory.createLineBorder(new Color(194, 1, 20), 3));
        buttonNew.setContentAreaFilled(false);
        buttonNew.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        buttonNew.setFocusPainted(false);

        buttonEdit.setForeground(new Color(12, 18, 12));
        buttonEdit.setText("Editar");
        buttonEdit.setBorder(BorderFactory.createLineBorder(new Color(194, 1, 20), 3));
        buttonEdit.setContentAreaFilled(false);
        buttonEdit.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        buttonEdit.setFocusPainted(false);

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

        jScrollPane1.setBackground(new Color(236, 235, 243));
        jScrollPane1.setBorder(BorderFactory.createLineBorder(new Color(12, 18, 12), 3));

        table.setBackground(new Color(236, 235, 243));
        table.setForeground(new Color(12, 18, 12));
        table.setModel(new DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null, null, null, null, null, null}
            },
            new String []
            {
                "ID Editora", "Nome da Cia.", "UF/Estado", "Cidade", "Bairro", "CEP", "Rua", "Número", "Complemento"
            }
        )
        {
            Class[] types = new Class []
            {
                Integer.class, String.class, String.class, String.class, String.class, String.class, String.class, Integer.class, String.class
            };

            public Class getColumnClass(int columnIndex)
            {
                return types [columnIndex];
            }
        });
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setFillsViewportHeight(true);
        table.setGridColor(new Color(12, 18, 12));
        table.setSelectionBackground(new Color(152, 58, 69));
        table.setSelectionForeground(new Color(236, 235, 243));
        table.setShowGrid(true);
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0)
        {
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

        jPanel7.setBackground(new Color(173, 30, 45));

        GroupLayout jPanel7Layout = new GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel6.setBackground(new Color(12, 18, 12));
        jPanel6.setPreferredSize(new Dimension(118, 35));

        buttonClose.setFont(new Font("Dialog", 1, 14)); // NOI18N
        buttonClose.setForeground(new Color(236, 235, 243));
        buttonClose.setText("Fechar");
        buttonClose.setBorder(BorderFactory.createLineBorder(new Color(194, 1, 20), 3));
        buttonClose.setContentAreaFilled(false);
        buttonClose.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        buttonClose.setFocusPainted(false);

        GroupLayout jPanel6Layout = new GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonClose, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel6Layout.setVerticalGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(buttonClose, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        jPanel10.setBackground(new Color(194, 1, 20));

        jLabel3.setIcon(new ImageIcon(getClass().getResource("/resources/bookstore.png"))); // NOI18N

        jLabel4.setIcon(new ImageIcon(getClass().getResource("/resources/bookstore.png"))); // NOI18N

        GroupLayout jPanel10Layout = new GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(jPanel10Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(jPanel10Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel3)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(20, 20, 20))
        );

        jPanel11.setBackground(new Color(236, 235, 243));
        jPanel11.setBorder(BorderFactory.createLineBorder(new Color(12, 18, 12), 3));

        fieldName.setBackground(new Color(236, 235, 243));
        fieldName.setForeground(new Color(12, 18, 12));
        fieldName.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(12, 18, 12), 2), "Editora", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Liberation Sans", 0, 15), new Color(12, 18, 12))); // NOI18N
        fieldName.setCaretColor(new Color(194, 1, 20));
        fieldName.setSelectedTextColor(new Color(236, 235, 243));
        fieldName.setSelectionColor(new Color(152, 58, 69));

        fieldPublishingID.setEditable(false);
        fieldPublishingID.setBackground(new Color(236, 235, 243));
        fieldPublishingID.setForeground(new Color(12, 18, 12));
        fieldPublishingID.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(12, 18, 12), 2), "ID", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Liberation Sans", 0, 15), new Color(12, 18, 12))); // NOI18N
        fieldPublishingID.setCaretColor(new Color(194, 1, 20));
        fieldPublishingID.setSelectedTextColor(new Color(236, 235, 243));
        fieldPublishingID.setSelectionColor(new Color(152, 58, 69));

        jPanel2.setBackground(new Color(236, 235, 243));

        fieldCity.setBackground(new Color(236, 235, 243));
        fieldCity.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2), "Cidade", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Liberation Sans", 0, 15), new Color(12, 18, 12))); // NOI18N
        fieldCity.setForeground(new Color(12, 18, 12));
        fieldCity.setCaretColor(new Color(194, 1, 20));
        fieldCity.setSelectedTextColor(new Color(236, 235, 243));
        fieldCity.setSelectionColor(new Color(152, 58, 69));

        fieldDistrict.setBackground(new Color(236, 235, 243));
        fieldDistrict.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2), "Bairro", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Liberation Sans", 0, 15), new Color(12, 18, 12))); // NOI18N
        fieldDistrict.setForeground(new Color(12, 18, 12));
        fieldDistrict.setCaretColor(new Color(194, 1, 20));
        fieldDistrict.setSelectedTextColor(new Color(236, 235, 243));
        fieldDistrict.setSelectionColor(new Color(152, 58, 69));

        fieldStreet.setBackground(new Color(236, 235, 243));
        fieldStreet.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2), "Rua", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Liberation Sans", 0, 15), new Color(12, 18, 12))); // NOI18N
        fieldStreet.setForeground(new Color(12, 18, 12));
        fieldStreet.setCaretColor(new Color(194, 1, 20));
        fieldStreet.setSelectedTextColor(new Color(236, 235, 243));
        fieldStreet.setSelectionColor(new Color(152, 58, 69));

        fieldCompl.setBackground(new Color(236, 235, 243));
        fieldCompl.setColumns(20);
        fieldCompl.setForeground(new Color(12, 18, 12));
        fieldCompl.setRows(5);
        fieldCompl.setTabSize(4);
        fieldCompl.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2), "Complemento", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Liberation Sans", 0, 15), new Color(12, 18, 12))); // NOI18N
        fieldCompl.setCaretColor(new Color(194, 1, 20));
        fieldCompl.setSelectedTextColor(new Color(236, 235, 243));
        fieldCompl.setSelectionColor(new Color(152, 58, 69));
        jScrollPane2.setViewportView(fieldCompl);

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(fieldStreet, GroupLayout.Alignment.TRAILING)
                    .addComponent(fieldDistrict)
                    .addComponent(fieldCity, GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fieldCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldDistrict, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldStreet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBackground(new Color(236, 235, 243));

        fieldUF.setBackground(new Color(236, 235, 243));
        fieldUF.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2), "UF/Estado", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Liberation Sans", 0, 15), new Color(12, 18, 12))); // NOI18N
        fieldUF.setForeground(new Color(12, 18, 12));
        try
        {
            fieldUF.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("UU")));
        } catch (ParseException ex)
        {
            ex.printStackTrace();
        }
        fieldUF.setCaretColor(new Color(194, 1, 20));
        fieldUF.setSelectedTextColor(new Color(236, 235, 243));
        fieldUF.setSelectionColor(new Color(152, 58, 69));

        fieldNumber.setBackground(new Color(236, 235, 243));
        fieldNumber.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2), "Número", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Liberation Sans", 0, 15), new Color(12, 18, 12))); // NOI18N
        fieldNumber.setForeground(new Color(12, 18, 12));
        fieldNumber.setCaretColor(new Color(194, 1, 20));
        fieldNumber.setSelectedTextColor(new Color(236, 235, 243));
        fieldNumber.setSelectionColor(new Color(152, 58, 69));

        fieldCEP.setBackground(new Color(236, 235, 243));
        fieldCEP.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2), "CEP", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Liberation Sans", 0, 15), new Color(12, 18, 12))); // NOI18N
        fieldCEP.setForeground(new Color(12, 18, 12));
        try
        {
            fieldCEP.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("#####-###")));
        } catch (ParseException ex)
        {
            ex.printStackTrace();
        }
        fieldCEP.setCaretColor(new Color(194, 1, 20));
        fieldCEP.setSelectedTextColor(new Color(236, 235, 243));
        fieldCEP.setSelectionColor(new Color(152, 58, 69));

        buttonSave.setBackground(new Color(236, 235, 243));
        buttonSave.setFont(new Font("Dialog", 1, 14)); // NOI18N
        buttonSave.setForeground(new Color(12, 18, 12));
        buttonSave.setText("Salvar");
        buttonSave.setBorder(BorderFactory.createLineBorder(new Color(194, 1, 20), 3));
        buttonSave.setContentAreaFilled(false);
        buttonSave.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        buttonSave.setFocusPainted(false);

        buttonCancel.setBackground(new Color(236, 235, 243));
        buttonCancel.setFont(new Font("Dialog", 1, 14)); // NOI18N
        buttonCancel.setForeground(new Color(12, 18, 12));
        buttonCancel.setText("Cancelar");
        buttonCancel.setBorder(BorderFactory.createLineBorder(new Color(194, 1, 20), 3));
        buttonCancel.setContentAreaFilled(false);
        buttonCancel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        buttonCancel.setFocusPainted(false);

        GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(fieldNumber)
                    .addComponent(fieldCEP)
                    .addComponent(fieldUF, GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonSave, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonCancel, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fieldUF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldCEP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonSave, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonCancel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout jPanel11Layout = new GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(jPanel11Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(fieldName, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fieldPublishingID, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(jPanel11Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldPublishingID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        GroupLayout jPanel5Layout = new GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
                    .addComponent(jPanel7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel11, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))
                        .addGap(0, 9, Short.MAX_VALUE))
                    .addGroup(GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel10, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton buttonCancel;
    private JButton buttonClose;
    private JButton buttonDel;
    private JButton buttonEdit;
    private JButton buttonNew;
    private JButton buttonSave;
    private JFormattedTextField fieldCEP;
    private JFormattedTextField fieldCity;
    private JTextArea fieldCompl;
    private JFormattedTextField fieldDistrict;
    private JTextField fieldName;
    private JFormattedTextField fieldNumber;
    private JTextField fieldPublishingID;
    private JFormattedTextField fieldStreet;
    private JFormattedTextField fieldUF;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JPanel jPanel10;
    private JPanel jPanel11;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel5;
    private JPanel jPanel6;
    private JPanel jPanel7;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JTable table;
    // End of variables declaration//GEN-END:variables
}