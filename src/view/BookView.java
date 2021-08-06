package view;

import controll.BookController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Book;
import factories.JSONFactory;
import org.json.JSONObject;

/**
 * @author cassiano
 */
public class BookView extends JFrame{

    private BookController bookControll;
    private JSONFactory jsonFactory;
    private PubTableView pubTable;
    private AutTableView autTable;
    
    public BookView() {
        super("Livros");
        initComponents();

        this.bookControll = new BookController();
        this.jsonFactory  = new JSONFactory();
        this.pubTable     = new PubTableView(this);
        this.autTable     = new AutTableView(this);
        
        initListeners();
        setPanelState(false);
        loadTable(this.bookControll.getList());
        
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
        this.fieldBookID      .setEnabled(isEnabled);
        this.fieldPublishingID.setEnabled(isEnabled);
        this.fieldAuthorID    .setEnabled(isEnabled);
        
        this.fieldTitle       .setEnabled(isEnabled);
        this.fieldGender      .setEnabled(isEnabled);
        this.fieldFinishing   .setEnabled(isEnabled);
        this.fieldNumberPages .setEnabled(isEnabled);
        this.fieldYear        .setEnabled(isEnabled);
        
        this.fieldCodeBar     .setEnabled(isEnabled);
        this.fieldBuy         .setEnabled(isEnabled);
        this.fieldSell        .setEnabled(isEnabled);
        
        this.buttonWildCard   .setEnabled(isEnabled);
        this.buttonCancel     .setEnabled(isEnabled);
        
        this.buttonShowPublishing.setEnabled(isEnabled);
        this.buttonShowAuthor    .setEnabled(isEnabled);
        
    }
    
    public void setTextFields(String[] args){
        
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
    
    public String[] getTextFields(){
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
    
    public void templateMethod(boolean panelEnabled, boolean buttonsEnabled){
        setPanelState (panelEnabled);
        setButtonsCRUD(buttonsEnabled);
    }
    
    public boolean hasSelectedRow(){
        int row = this.table.getSelectedRow();
        return (row != -1);
            //return false;
        //return true;
    }
    
    public void editFields(){
        String[] args = new String[11];
        for (int i = 0; i < args.length; i++) {
            args[i] = table.getValueAt(this.table.getSelectedRow(), i).toString();
        }
        setTextFields(args);
    }
    
    public void clearFields(){
        String[] args = new String[11];
        for (String arg : args) {
            arg = "";
        }
        setTextFields(args);
    }
    
    public boolean fieldsOkay(){
        String[] args = getTextFields();
        if (args[0].isEmpty())
            args[0] = "0";
        
        if (args[1].isEmpty())
            args[1] = "1";
        
        if (args[2].isEmpty())
            args[2] = "1";
        
        for (String arg : args) {
            if ( arg.isEmpty() )
                return false;
        }
        this.jsonFactory.buildFrom(args);
        return true;
    }
    
    public JFormattedTextField getFieldPublishing(){
        return this.fieldPublishingID;
    }
    
    public JFormattedTextField getFieldAuthor(){
        return this.fieldAuthorID;
    }
    
    private void initListeners(){
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
                templateMethod(true, false);
            }
        });
        
        this.buttonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (hasSelectedRow()){
                    editFields();
                    buttonWildCard.setText("Atualizar");
                    templateMethod(true, false);
                }
            }
        });
        
        this.buttonDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                        bookControll.invoke(
                            "DelCommand",
                            new JSONObject().put("bookID", id)
                        );
                        loadTable(bookControll.getList());
                    }
                }
            }
        });
        
        this.buttonCancel.addActionListener(new ActionListener() {    
            @Override
            public void actionPerformed(ActionEvent event) {
                clearFields();
                templateMethod(false, true);
            }
        });

        this.buttonWildCard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (fieldsOkay()){
                    JSONObject json = jsonFactory.getJSON();
                
                    if (buttonWildCard.getText().equals("Atualizar"))
                        bookControll.invoke("EditCommand", json);
                    else
                        bookControll.invoke("NewCommand", json);
                    
                    loadTable(bookControll.getList());
                    clearFields();
                    templateMethod(false, true);
                }
            }
        });
        
        this.buttonShowPublishing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pubTable.setVisible(true);
            }
        });
        
        this.buttonShowAuthor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autTable.setVisible(true);
            }
        });
    }
    
    public void loadTable(List<Book> list){
        DefaultTableModel model = (DefaultTableModel) this.table.getModel();
        
        while (table.getRowCount() > 0) {
            model.removeRow(0);
        }
        for (Book book : list) {
            model.addRow(
                new Object[]{
                    book.getBookID(),
                    book.getPublishingID(),
                    book.getAuthorID(),
                    book.getTitle(),
                    book.getGender(),
                    book.getFinishing(),
                    book.getNumberPages(),
                    book.getReleaseYear(),
                    book.getCodeBar(),
                    book.getSellValue().toString(),
                    book.getBuyValue().toString()
                }
            );
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        buttonNew = new javax.swing.JButton();
        buttonEdit = new javax.swing.JButton();
        buttonDel = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        buttonClose = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        fieldTitle = new javax.swing.JFormattedTextField();
        fieldGender = new javax.swing.JFormattedTextField();
        fieldNumberPages = new javax.swing.JFormattedTextField();
        fieldFinishing = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        fieldYear = new javax.swing.JFormattedTextField();
        fieldCodeBar = new javax.swing.JFormattedTextField();
        fieldSell = new javax.swing.JFormattedTextField();
        fieldBuy = new javax.swing.JFormattedTextField();
        jPanel4 = new javax.swing.JPanel();
        fieldBookID = new javax.swing.JFormattedTextField();
        fieldPublishingID = new javax.swing.JFormattedTextField();
        fieldAuthorID = new javax.swing.JFormattedTextField();
        buttonShowAuthor = new javax.swing.JButton();
        buttonShowPublishing = new javax.swing.JButton();
        buttonWildCard = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(236, 235, 243));

        jScrollPane1.setBackground(new java.awt.Color(236, 235, 243));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(12, 18, 12), 3));

        table.setBackground(new java.awt.Color(236, 235, 243));
        table.setForeground(new java.awt.Color(12, 18, 12));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Livro", "ID Editora", "ID Autor", "Título", "Gênero", "Acabamento", "Número de Páginas", "Ano de Lançamento", "Código de Barras", "Valor de Venda", "Valor de Compra"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
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
        table.setShowGrid(true);
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setPreferredWidth(100);
            table.getColumnModel().getColumn(1).setPreferredWidth(100);
            table.getColumnModel().getColumn(2).setPreferredWidth(100);
            table.getColumnModel().getColumn(3).setPreferredWidth(300);
            table.getColumnModel().getColumn(4).setPreferredWidth(150);
            table.getColumnModel().getColumn(5).setPreferredWidth(150);
            table.getColumnModel().getColumn(6).setPreferredWidth(150);
            table.getColumnModel().getColumn(7).setPreferredWidth(150);
            table.getColumnModel().getColumn(8).setPreferredWidth(150);
            table.getColumnModel().getColumn(9).setPreferredWidth(120);
            table.getColumnModel().getColumn(10).setPreferredWidth(120);
        }

        jPanel5.setBackground(new java.awt.Color(236, 235, 243));

        buttonNew.setBackground(new java.awt.Color(236, 235, 243));
        buttonNew.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        buttonNew.setForeground(new java.awt.Color(12, 18, 12));
        buttonNew.setText("Novo");
        buttonNew.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(194, 1, 20), 3));
        buttonNew.setContentAreaFilled(false);
        buttonNew.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonNew.setFocusPainted(false);

        buttonEdit.setBackground(new java.awt.Color(236, 235, 243));
        buttonEdit.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        buttonEdit.setForeground(new java.awt.Color(12, 18, 12));
        buttonEdit.setText("Editar");
        buttonEdit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(194, 1, 20), 3));
        buttonEdit.setContentAreaFilled(false);
        buttonEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonEdit.setFocusPainted(false);

        buttonDel.setBackground(new java.awt.Color(236, 235, 243));
        buttonDel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        buttonDel.setForeground(new java.awt.Color(12, 18, 12));
        buttonDel.setText("Excluir");
        buttonDel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(194, 1, 20), 3));
        buttonDel.setContentAreaFilled(false);
        buttonDel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonDel.setFocusPainted(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(buttonNew, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonDel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonDel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonEdit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(194, 1, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/books.png"))); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/books.png"))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(20, 20, 20))
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

        jPanel9.setBackground(new java.awt.Color(236, 235, 243));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(12, 18, 12), 3));

        jPanel2.setBackground(new java.awt.Color(236, 235, 243));

        fieldTitle.setBackground(new java.awt.Color(236, 235, 243));
        fieldTitle.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Título", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(12, 18, 12))); // NOI18N
        fieldTitle.setForeground(new java.awt.Color(12, 18, 12));
        fieldTitle.setCaretColor(new java.awt.Color(194, 1, 20));
        fieldTitle.setSelectedTextColor(new java.awt.Color(236, 235, 243));
        fieldTitle.setSelectionColor(new java.awt.Color(152, 58, 69));

        fieldGender.setBackground(new java.awt.Color(236, 235, 243));
        fieldGender.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Gênero", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(12, 18, 12))); // NOI18N
        fieldGender.setForeground(new java.awt.Color(12, 18, 12));
        fieldGender.setCaretColor(new java.awt.Color(194, 1, 20));
        fieldGender.setSelectedTextColor(new java.awt.Color(236, 235, 243));
        fieldGender.setSelectionColor(new java.awt.Color(152, 58, 69));

        fieldNumberPages.setBackground(new java.awt.Color(236, 235, 243));
        fieldNumberPages.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Nº de Páginas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(12, 18, 12))); // NOI18N
        fieldNumberPages.setForeground(new java.awt.Color(12, 18, 12));
        fieldNumberPages.setCaretColor(new java.awt.Color(194, 1, 20));
        fieldNumberPages.setSelectedTextColor(new java.awt.Color(236, 235, 243));
        fieldNumberPages.setSelectionColor(new java.awt.Color(152, 58, 69));

        fieldFinishing.setBackground(new java.awt.Color(236, 235, 243));
        fieldFinishing.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Acabamento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(12, 18, 12))); // NOI18N
        fieldFinishing.setForeground(new java.awt.Color(12, 18, 12));
        fieldFinishing.setCaretColor(new java.awt.Color(194, 1, 20));
        fieldFinishing.setSelectedTextColor(new java.awt.Color(236, 235, 243));
        fieldFinishing.setSelectionColor(new java.awt.Color(152, 58, 69));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fieldGender, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                    .addComponent(fieldNumberPages)
                    .addComponent(fieldFinishing, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fieldTitle))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fieldTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldNumberPages, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldFinishing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(236, 235, 243));

        fieldYear.setBackground(new java.awt.Color(236, 235, 243));
        fieldYear.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Ano de Lançamento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(12, 18, 12))); // NOI18N
        fieldYear.setForeground(new java.awt.Color(12, 18, 12));
        try {
            fieldYear.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        fieldYear.setCaretColor(new java.awt.Color(194, 1, 20));
        fieldYear.setSelectedTextColor(new java.awt.Color(236, 235, 243));
        fieldYear.setSelectionColor(new java.awt.Color(152, 58, 69));

        fieldCodeBar.setBackground(new java.awt.Color(236, 235, 243));
        fieldCodeBar.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Código de Barras", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(12, 18, 12))); // NOI18N
        fieldCodeBar.setForeground(new java.awt.Color(12, 18, 12));
        fieldCodeBar.setCaretColor(new java.awt.Color(194, 1, 20));
        fieldCodeBar.setSelectedTextColor(new java.awt.Color(236, 235, 243));
        fieldCodeBar.setSelectionColor(new java.awt.Color(152, 58, 69));

        fieldSell.setBackground(new java.awt.Color(236, 235, 243));
        fieldSell.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Valor de Venda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(12, 18, 12))); // NOI18N
        fieldSell.setForeground(new java.awt.Color(12, 18, 12));
        fieldSell.setCaretColor(new java.awt.Color(194, 1, 20));
        fieldSell.setSelectedTextColor(new java.awt.Color(236, 235, 243));
        fieldSell.setSelectionColor(new java.awt.Color(152, 58, 69));

        fieldBuy.setBackground(new java.awt.Color(236, 235, 243));
        fieldBuy.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Valor de Compra", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(12, 18, 12))); // NOI18N
        fieldBuy.setForeground(new java.awt.Color(12, 18, 12));
        fieldBuy.setCaretColor(new java.awt.Color(194, 1, 20));
        fieldBuy.setSelectedTextColor(new java.awt.Color(236, 235, 243));
        fieldBuy.setSelectionColor(new java.awt.Color(152, 58, 69));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fieldYear)
                    .addComponent(fieldCodeBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                    .addComponent(fieldSell)
                    .addComponent(fieldBuy, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fieldYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldCodeBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldSell, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldBuy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(236, 235, 243));

        fieldBookID.setEditable(false);
        fieldBookID.setBackground(new java.awt.Color(236, 235, 243));
        fieldBookID.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "ID do Livro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(12, 18, 12))); // NOI18N
        fieldBookID.setForeground(new java.awt.Color(12, 18, 12));
        fieldBookID.setCaretColor(new java.awt.Color(194, 1, 20));
        fieldBookID.setSelectedTextColor(new java.awt.Color(236, 235, 243));
        fieldBookID.setSelectionColor(new java.awt.Color(152, 58, 69));

        fieldPublishingID.setEditable(false);
        fieldPublishingID.setBackground(new java.awt.Color(236, 235, 243));
        fieldPublishingID.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "ID da Editora", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(12, 18, 12))); // NOI18N
        fieldPublishingID.setForeground(new java.awt.Color(12, 18, 12));
        fieldPublishingID.setCaretColor(new java.awt.Color(194, 1, 20));
        fieldPublishingID.setSelectedTextColor(new java.awt.Color(236, 235, 243));
        fieldPublishingID.setSelectionColor(new java.awt.Color(152, 58, 69));

        fieldAuthorID.setEditable(false);
        fieldAuthorID.setBackground(new java.awt.Color(236, 235, 243));
        fieldAuthorID.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "ID do Autor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(12, 18, 12))); // NOI18N
        fieldAuthorID.setForeground(new java.awt.Color(12, 18, 12));
        fieldAuthorID.setCaretColor(new java.awt.Color(194, 1, 20));
        fieldAuthorID.setSelectedTextColor(new java.awt.Color(236, 235, 243));
        fieldAuthorID.setSelectionColor(new java.awt.Color(152, 58, 69));

        buttonShowAuthor.setBackground(new java.awt.Color(236, 235, 243));
        buttonShowAuthor.setForeground(new java.awt.Color(12, 18, 12));
        buttonShowAuthor.setText("Ver");
        buttonShowAuthor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        buttonShowAuthor.setContentAreaFilled(false);
        buttonShowAuthor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonShowAuthor.setFocusPainted(false);

        buttonShowPublishing.setBackground(new java.awt.Color(236, 235, 243));
        buttonShowPublishing.setForeground(new java.awt.Color(12, 18, 12));
        buttonShowPublishing.setText("Ver");
        buttonShowPublishing.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        buttonShowPublishing.setContentAreaFilled(false);
        buttonShowPublishing.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonShowPublishing.setFocusPainted(false);

        buttonWildCard.setBackground(new java.awt.Color(236, 235, 243));
        buttonWildCard.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        buttonWildCard.setForeground(new java.awt.Color(12, 18, 12));
        buttonWildCard.setText("Salvar");
        buttonWildCard.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(194, 1, 20), 3));
        buttonWildCard.setContentAreaFilled(false);
        buttonWildCard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonWildCard.setFocusPainted(false);

        buttonCancel.setBackground(new java.awt.Color(236, 235, 243));
        buttonCancel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        buttonCancel.setForeground(new java.awt.Color(12, 18, 12));
        buttonCancel.setText("Cancelar");
        buttonCancel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(194, 1, 20), 3));
        buttonCancel.setContentAreaFilled(false);
        buttonCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonCancel.setFocusPainted(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fieldBookID)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(fieldPublishingID, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fieldAuthorID, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonWildCard, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(buttonCancel, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(buttonShowAuthor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonShowPublishing, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fieldBookID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fieldAuthorID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonShowAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fieldPublishingID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonShowPublishing, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonWildCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonClose;
    private javax.swing.JButton buttonDel;
    private javax.swing.JButton buttonEdit;
    private javax.swing.JButton buttonNew;
    private javax.swing.JButton buttonShowAuthor;
    private javax.swing.JButton buttonShowPublishing;
    private javax.swing.JButton buttonWildCard;
    private javax.swing.JFormattedTextField fieldAuthorID;
    private javax.swing.JFormattedTextField fieldBookID;
    private javax.swing.JFormattedTextField fieldBuy;
    private javax.swing.JFormattedTextField fieldCodeBar;
    private javax.swing.JFormattedTextField fieldFinishing;
    private javax.swing.JFormattedTextField fieldGender;
    private javax.swing.JFormattedTextField fieldNumberPages;
    private javax.swing.JFormattedTextField fieldPublishingID;
    private javax.swing.JFormattedTextField fieldSell;
    private javax.swing.JFormattedTextField fieldTitle;
    private javax.swing.JFormattedTextField fieldYear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables

}
