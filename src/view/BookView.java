package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import controller.BooksController;

/**
 *
 */
public class BookView extends JFrame
{

    private final BooksController controller;

    public BookView(BooksController controller)
    {
        super("Livros");

        this.controller = controller;

        this.initComponents();
        this.initListeners();
        this.setPanelState(false);
        // this.controller.loadTable();

        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setLocationRelativeTo(null);
        super.setVisible(true);
    }

    private void initListeners()
    {
        this.buttonClose.addActionListener((ActionEvent e) ->
        {
            super.dispose();
        });

        this.buttonNew.addActionListener((ActionEvent e) ->
        {
            this.controller.newBook();
        });

        this.buttonEdit.addActionListener((ActionEvent event) ->
        {
            this.controller.edit();
        });

        this.buttonDel.addActionListener((ActionEvent e) ->
        {
            this.controller.del();
        });

        this.buttonCancel.addActionListener((ActionEvent event) ->
        {
            this.controller.cancel();
        });

        this.buttonWildCard.addActionListener((ActionEvent event) ->
        {
            this.controller.wildcard();
        });

        this.buttonShowPublishing.addActionListener((ActionEvent e) ->
        {
            this.controller.showPub();
        });

        this.buttonShowAuthor.addActionListener((ActionEvent e) ->
        {
            this.controller.showAuthor();
        });
    }

    public void templateMethod(boolean panelState, boolean buttonsState)
    {
        setPanelState(panelState);
        setButtonsCRUD(buttonsState);
    }

    public void setPanelState(boolean state)
    {
        this.fieldBookID      .setEnabled(state);
        this.fieldPublishingID.setEnabled(state);
        this.fieldAuthorID    .setEnabled(state);

        this.fieldTitle       .setEnabled(state);
        this.fieldGender      .setEnabled(state);
        this.fieldFinishing   .setEnabled(state);
        this.fieldNumberPages .setEnabled(state);
        this.fieldYear        .setEnabled(state);

        this.fieldCodeBar     .setEnabled(state);
        this.fieldBuy         .setEnabled(state);
        this.fieldSell        .setEnabled(state);

        this.buttonWildCard   .setEnabled(state);
        this.buttonCancel     .setEnabled(state);

        this.buttonShowPublishing.setEnabled(state);
        this.buttonShowAuthor    .setEnabled(state);
    }

    public void setButtonsCRUD(boolean state)
    {
        this.buttonNew .setEnabled(state);
        this.buttonEdit.setEnabled(state);
        this.buttonDel .setEnabled(state);
    }

    public void editFields()
    {
        String[] args = new String[11];

        for ( int i = 0; i < args.length; i++ )
        {
            args[i] = table.getValueAt(this.table.getSelectedRow(), i).toString();
        }
        
        setTextFields(args);
    }

    public void clearFields()
    {
        String[] args = new String[11];

        for ( String arg : args )
        {
            arg = "";
        }
        
        setTextFields(args);
    }

    public void setTextFields(String[] args)
    {
        this.fieldBookID      .setText(args[0]);
        this.fieldPublishingID.setText(args[1]);
        this.fieldAuthorID    .setText(args[2]);

        this.fieldTitle      .setText(args[3]);
        this.fieldGender     .setText(args[4]);
        this.fieldFinishing  .setText(args[5]);
        this.fieldNumberPages.setText(args[6]);
        this.fieldYear       .setText(args[7]);

        this.fieldCodeBar    .setText(args[8]);
        this.fieldSell       .setText(args[9]);
        this.fieldBuy        .setText(args[10]);
    }

    public boolean fieldsOkay()
    {
        String[] args = getTextFields();

        if (args[0].isEmpty())
            args[0] = "0";

        if (args[1].isEmpty())
            args[1] = "1";

        if (args[2].isEmpty())
            args[2] = "1";

        for ( String arg : args )
        {
            if ( arg.isEmpty() ) return false;
        }
        
        this.getJSON(args);

        return true;
    }

    public String[] getTextFields()
    {
        String[] args = new String[11];

        args[0] = this.fieldBookID      .getText();
        args[1] = this.fieldPublishingID.getText();
        args[2] = this.fieldAuthorID    .getText();

        args[3] = this.fieldTitle       .getText();
        args[4] = this.fieldGender      .getText();
        args[5] = this.fieldFinishing   .getText();
        args[6] = this.fieldNumberPages .getText();
        args[7] = this.fieldYear        .getText();

        args[8] = this.fieldCodeBar     .getText();
        args[9] = this.fieldSell        .getText();
        args[10] = this.fieldBuy        .getText();

        return args;
    }

    public Object getJSON(String[] args)
    {
//        JSONObject json = new JSONObject();
//
//        json.put("ID",        args[0]);
//        json.put("publishing",args[1]);
//        json.put("author",    args[2]);
//
//        json.put("title",     args[3]);
//        json.put("gender",    args[4]);
//
//        json.put("finishing", args[5]);
//        json.put("pages",     args[6]);
//        json.put("year",      args[7]);
//
//        json.put("code",      args[8]);
//        json.put("sellValue", args[9]);
//        json.put("buyValue",  args[10]);

        return null;
    }

    public JFormattedTextField getPublishingField()
    {
        return this.fieldPublishingID;
    }

    public JFormattedTextField getAuthorField()
    {
        return this.fieldAuthorID;
    }

    public JButton getWildCard()
    {
        return this.buttonWildCard;
    }

    public JTable getTable()
    {
        return this.table;
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

        jPanel1 = new JPanel();
        jScrollPane1 = new JScrollPane();
        table = new JTable();
        jPanel5 = new JPanel();
        buttonNew = new JButton();
        buttonEdit = new JButton();
        buttonDel = new JButton();
        jPanel6 = new JPanel();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jPanel7 = new JPanel();
        jPanel8 = new JPanel();
        buttonClose = new JButton();
        fieldsPanel = new JPanel();
        jPanel2 = new JPanel();
        fieldTitle = new JFormattedTextField();
        fieldGender = new JFormattedTextField();
        fieldNumberPages = new JFormattedTextField();
        fieldFinishing = new JFormattedTextField();
        jPanel3 = new JPanel();
        fieldYear = new JFormattedTextField();
        fieldCodeBar = new JFormattedTextField();
        fieldSell = new JFormattedTextField();
        fieldBuy = new JFormattedTextField();
        jPanel4 = new JPanel();
        fieldBookID = new JFormattedTextField();
        fieldPublishingID = new JFormattedTextField();
        fieldAuthorID = new JFormattedTextField();
        buttonShowAuthor = new JButton();
        buttonShowPublishing = new JButton();
        buttonWildCard = new JButton();
        buttonCancel = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new Color(236, 235, 243));

        jScrollPane1.setBackground(new Color(236, 235, 243));
        jScrollPane1.setBorder(BorderFactory.createLineBorder(new Color(12, 18, 12), 3));

        table.setBackground(new Color(236, 235, 243));
        table.setForeground(new Color(12, 18, 12));
        table.setModel(new DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String []
            {
                "Código de Barras", "Editora", "Autor", "Título", "Gênero", "Acabamento", "Número de Páginas", "Ano de Lançamento", "Valor de Venda", "Valor de Compra"
            }
        )
        {
            Class[] types = new Class []
            {
                Integer.class, String.class, String.class, String.class, String.class, String.class, Integer.class, Integer.class, String.class, String.class
            };
            boolean[] canEdit = new boolean []
            {
                false, false, false, false, false, false, false, false, false, false
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
        table.setShowGrid(true);
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0)
        {
            table.getColumnModel().getColumn(0).setPreferredWidth(150);
            table.getColumnModel().getColumn(1).setPreferredWidth(100);
            table.getColumnModel().getColumn(2).setPreferredWidth(100);
            table.getColumnModel().getColumn(3).setPreferredWidth(300);
            table.getColumnModel().getColumn(4).setPreferredWidth(150);
            table.getColumnModel().getColumn(5).setPreferredWidth(150);
            table.getColumnModel().getColumn(6).setPreferredWidth(150);
            table.getColumnModel().getColumn(7).setPreferredWidth(150);
            table.getColumnModel().getColumn(8).setPreferredWidth(120);
            table.getColumnModel().getColumn(9).setPreferredWidth(120);
        }

        jPanel5.setBackground(new Color(236, 235, 243));

        buttonNew.setBackground(new Color(236, 235, 243));
        buttonNew.setFont(new Font("Dialog", 1, 14)); // NOI18N
        buttonNew.setForeground(new Color(12, 18, 12));
        buttonNew.setText("Novo");
        buttonNew.setBorder(BorderFactory.createLineBorder(new Color(194, 1, 20), 3));
        buttonNew.setContentAreaFilled(false);
        buttonNew.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        buttonNew.setFocusPainted(false);

        buttonEdit.setBackground(new Color(236, 235, 243));
        buttonEdit.setFont(new Font("Dialog", 1, 14)); // NOI18N
        buttonEdit.setForeground(new Color(12, 18, 12));
        buttonEdit.setText("Editar");
        buttonEdit.setBorder(BorderFactory.createLineBorder(new Color(194, 1, 20), 3));
        buttonEdit.setContentAreaFilled(false);
        buttonEdit.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        buttonEdit.setFocusPainted(false);

        buttonDel.setBackground(new Color(236, 235, 243));
        buttonDel.setFont(new Font("Dialog", 1, 14)); // NOI18N
        buttonDel.setForeground(new Color(12, 18, 12));
        buttonDel.setText("Excluir");
        buttonDel.setBorder(BorderFactory.createLineBorder(new Color(194, 1, 20), 3));
        buttonDel.setContentAreaFilled(false);
        buttonDel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        buttonDel.setFocusPainted(false);

        GroupLayout jPanel5Layout = new GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(buttonNew, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonEdit, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonDel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(buttonDel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonEdit, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonNew, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel6.setBackground(new Color(194, 1, 20));

        jLabel1.setIcon(new ImageIcon(getClass().getResource("/lib/img/books.png"))); // NOI18N

        jLabel2.setIcon(new ImageIcon(getClass().getResource("/lib/img/books.png"))); // NOI18N

        GroupLayout jPanel6Layout = new GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(20, 20, 20))
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

        fieldsPanel.setBackground(new Color(236, 235, 243));
        fieldsPanel.setBorder(BorderFactory.createLineBorder(new Color(12, 18, 12), 3));

        jPanel2.setBackground(new Color(236, 235, 243));

        fieldTitle.setBackground(new Color(236, 235, 243));
        fieldTitle.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2), "Título", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Liberation Sans", 0, 15), new Color(12, 18, 12))); // NOI18N
        fieldTitle.setForeground(new Color(12, 18, 12));
        fieldTitle.setCaretColor(new Color(194, 1, 20));
        fieldTitle.setSelectedTextColor(new Color(236, 235, 243));
        fieldTitle.setSelectionColor(new Color(152, 58, 69));

        fieldGender.setBackground(new Color(236, 235, 243));
        fieldGender.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2), "Gênero", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Liberation Sans", 0, 15), new Color(12, 18, 12))); // NOI18N
        fieldGender.setForeground(new Color(12, 18, 12));
        fieldGender.setCaretColor(new Color(194, 1, 20));
        fieldGender.setSelectedTextColor(new Color(236, 235, 243));
        fieldGender.setSelectionColor(new Color(152, 58, 69));

        fieldNumberPages.setBackground(new Color(236, 235, 243));
        fieldNumberPages.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2), "Nº de Páginas", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Liberation Sans", 0, 15), new Color(12, 18, 12))); // NOI18N
        fieldNumberPages.setForeground(new Color(12, 18, 12));
        fieldNumberPages.setCaretColor(new Color(194, 1, 20));
        fieldNumberPages.setSelectedTextColor(new Color(236, 235, 243));
        fieldNumberPages.setSelectionColor(new Color(152, 58, 69));

        fieldFinishing.setBackground(new Color(236, 235, 243));
        fieldFinishing.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2), "Acabamento", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Liberation Sans", 0, 15), new Color(12, 18, 12))); // NOI18N
        fieldFinishing.setForeground(new Color(12, 18, 12));
        fieldFinishing.setCaretColor(new Color(194, 1, 20));
        fieldFinishing.setSelectedTextColor(new Color(236, 235, 243));
        fieldFinishing.setSelectionColor(new Color(152, 58, 69));

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(fieldGender, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                    .addComponent(fieldNumberPages)
                    .addComponent(fieldFinishing, GroupLayout.Alignment.TRAILING)
                    .addComponent(fieldTitle))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fieldTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldGender, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldNumberPages, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldFinishing, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new Color(236, 235, 243));

        fieldYear.setBackground(new Color(236, 235, 243));
        fieldYear.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2), "Ano de Lançamento", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Liberation Sans", 0, 15), new Color(12, 18, 12))); // NOI18N
        fieldYear.setForeground(new Color(12, 18, 12));
        try
        {
            fieldYear.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("####")));
        } catch (ParseException ex)
        {
            ex.printStackTrace();
        }
        fieldYear.setCaretColor(new Color(194, 1, 20));
        fieldYear.setSelectedTextColor(new Color(236, 235, 243));
        fieldYear.setSelectionColor(new Color(152, 58, 69));

        fieldCodeBar.setBackground(new Color(236, 235, 243));
        fieldCodeBar.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2), "Código de Barras", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Liberation Sans", 0, 15), new Color(12, 18, 12))); // NOI18N
        fieldCodeBar.setForeground(new Color(12, 18, 12));
        fieldCodeBar.setCaretColor(new Color(194, 1, 20));
        fieldCodeBar.setSelectedTextColor(new Color(236, 235, 243));
        fieldCodeBar.setSelectionColor(new Color(152, 58, 69));

        fieldSell.setBackground(new Color(236, 235, 243));
        fieldSell.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2), "Valor de Venda", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Liberation Sans", 0, 15), new Color(12, 18, 12))); // NOI18N
        fieldSell.setForeground(new Color(12, 18, 12));
        fieldSell.setCaretColor(new Color(194, 1, 20));
        fieldSell.setSelectedTextColor(new Color(236, 235, 243));
        fieldSell.setSelectionColor(new Color(152, 58, 69));

        fieldBuy.setBackground(new Color(236, 235, 243));
        fieldBuy.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2), "Valor de Compra", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Liberation Sans", 0, 15), new Color(12, 18, 12))); // NOI18N
        fieldBuy.setForeground(new Color(12, 18, 12));
        fieldBuy.setCaretColor(new Color(194, 1, 20));
        fieldBuy.setSelectedTextColor(new Color(236, 235, 243));
        fieldBuy.setSelectionColor(new Color(152, 58, 69));

        GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(fieldYear)
                    .addComponent(fieldCodeBar, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                    .addComponent(fieldSell)
                    .addComponent(fieldBuy, GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fieldYear, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldCodeBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldSell, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldBuy, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new Color(236, 235, 243));

        fieldBookID.setEditable(false);
        fieldBookID.setBackground(new Color(236, 235, 243));
        fieldBookID.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2), "ID do Livro", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Liberation Sans", 0, 15), new Color(12, 18, 12))); // NOI18N
        fieldBookID.setForeground(new Color(12, 18, 12));
        fieldBookID.setCaretColor(new Color(194, 1, 20));
        fieldBookID.setSelectedTextColor(new Color(236, 235, 243));
        fieldBookID.setSelectionColor(new Color(152, 58, 69));

        fieldPublishingID.setEditable(false);
        fieldPublishingID.setBackground(new Color(236, 235, 243));
        fieldPublishingID.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2), "Editora", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Liberation Sans", 0, 15), new Color(12, 18, 12))); // NOI18N
        fieldPublishingID.setForeground(new Color(12, 18, 12));
        fieldPublishingID.setCaretColor(new Color(194, 1, 20));
        fieldPublishingID.setSelectedTextColor(new Color(236, 235, 243));
        fieldPublishingID.setSelectionColor(new Color(152, 58, 69));

        fieldAuthorID.setEditable(false);
        fieldAuthorID.setBackground(new Color(236, 235, 243));
        fieldAuthorID.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2), "Autor", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Liberation Sans", 0, 15), new Color(12, 18, 12))); // NOI18N
        fieldAuthorID.setForeground(new Color(12, 18, 12));
        fieldAuthorID.setCaretColor(new Color(194, 1, 20));
        fieldAuthorID.setSelectedTextColor(new Color(236, 235, 243));
        fieldAuthorID.setSelectionColor(new Color(152, 58, 69));

        buttonShowAuthor.setBackground(new Color(236, 235, 243));
        buttonShowAuthor.setForeground(new Color(12, 18, 12));
        buttonShowAuthor.setText("Ver");
        buttonShowAuthor.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));
        buttonShowAuthor.setContentAreaFilled(false);
        buttonShowAuthor.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        buttonShowAuthor.setFocusPainted(false);

        buttonShowPublishing.setBackground(new Color(236, 235, 243));
        buttonShowPublishing.setForeground(new Color(12, 18, 12));
        buttonShowPublishing.setText("Ver");
        buttonShowPublishing.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));
        buttonShowPublishing.setContentAreaFilled(false);
        buttonShowPublishing.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        buttonShowPublishing.setFocusPainted(false);

        buttonWildCard.setBackground(new Color(236, 235, 243));
        buttonWildCard.setFont(new Font("Dialog", 1, 14)); // NOI18N
        buttonWildCard.setForeground(new Color(12, 18, 12));
        buttonWildCard.setText("Salvar");
        buttonWildCard.setBorder(BorderFactory.createLineBorder(new Color(194, 1, 20), 3));
        buttonWildCard.setContentAreaFilled(false);
        buttonWildCard.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        buttonWildCard.setFocusPainted(false);

        buttonCancel.setBackground(new Color(236, 235, 243));
        buttonCancel.setFont(new Font("Dialog", 1, 14)); // NOI18N
        buttonCancel.setForeground(new Color(12, 18, 12));
        buttonCancel.setText("Cancelar");
        buttonCancel.setBorder(BorderFactory.createLineBorder(new Color(194, 1, 20), 3));
        buttonCancel.setContentAreaFilled(false);
        buttonCancel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        buttonCancel.setFocusPainted(false);

        GroupLayout jPanel4Layout = new GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(fieldBookID)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                            .addComponent(fieldPublishingID, GroupLayout.Alignment.LEADING)
                            .addComponent(fieldAuthorID, GroupLayout.Alignment.LEADING)
                            .addComponent(buttonWildCard, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(buttonCancel, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(buttonShowAuthor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonShowPublishing, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fieldBookID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(fieldAuthorID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonShowAuthor, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(fieldPublishingID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonShowPublishing, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(buttonCancel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonWildCard, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        GroupLayout fieldsPanelLayout = new GroupLayout(fieldsPanel);
        fieldsPanel.setLayout(fieldsPanelLayout);
        fieldsPanelLayout.setHorizontalGroup(fieldsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(fieldsPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        fieldsPanelLayout.setVerticalGroup(fieldsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(fieldsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fieldsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jPanel5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(fieldsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton buttonCancel;
    private JButton buttonClose;
    private JButton buttonDel;
    private JButton buttonEdit;
    private JButton buttonNew;
    private JButton buttonShowAuthor;
    private JButton buttonShowPublishing;
    private JButton buttonWildCard;
    private JFormattedTextField fieldAuthorID;
    private JFormattedTextField fieldBookID;
    private JFormattedTextField fieldBuy;
    private JFormattedTextField fieldCodeBar;
    private JFormattedTextField fieldFinishing;
    private JFormattedTextField fieldGender;
    private JFormattedTextField fieldNumberPages;
    private JFormattedTextField fieldPublishingID;
    private JFormattedTextField fieldSell;
    private JFormattedTextField fieldTitle;
    private JFormattedTextField fieldYear;
    private JPanel fieldsPanel;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel5;
    private JPanel jPanel6;
    private JPanel jPanel7;
    private JPanel jPanel8;
    private JScrollPane jScrollPane1;
    private JTable table;
    // End of variables declaration//GEN-END:variables
}