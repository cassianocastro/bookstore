package view;

import java.awt.*;
import java.text.ParseException;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import controller.BooksController;
import view.listeners.BookListener;

/**
 *
 */
public class BookView extends JFrame
{

    public BookView(BooksController controller)
    {
        super("Livros");

        this.initComponents();
        
        new BookListener(this, controller);

        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setLocationRelativeTo(null);
        super.setVisible(true);
    }

    public JFormattedTextField getIdField()
    {
        return this.id;
    }

    public JFormattedTextField getFinishingField()
    {
        return this.finishing;
    }
    
    public JFormattedTextField getTitleField()
    {
        return this.title;
    }

    public JFormattedTextField getGenderField()
    {
        return this.gender;
    }

    public JFormattedTextField getPagesField()
    {
        return this.pages;
    }

    public JFormattedTextField getReleaseField()
    {
        return this.release;
    }
    
    public JFormattedTextField getCodeField()
    {
        return this.code;
    }

    public JFormattedTextField getSellField()
    {
        return this.sell;
    }
    
    public JFormattedTextField getBuyField()
    {
        return this.buy;
    }
    
    public JFormattedTextField getPublishingField()
    {
        return this.publishing;
    }

    public JFormattedTextField getAuthorField()
    {
        return this.author;
    }
    
    public JButton getAddButton()
    {
        return this.add;
    }
    
    public JButton getUpdateButton()
    {
        return this.update;
    }
    
    public JButton getDeleteButton()
    {
        return this.delete;
    }

    public JButton getSubmitButton()
    {
        return this.submit;
    }
    
    public JButton getCancelButton()
    {
        return this.cancel;
    }

    public JButton getCloseButton()
    {
        return this.closeButton;
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
        add = new JButton();
        update = new JButton();
        delete = new JButton();
        jPanel6 = new JPanel();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jPanel7 = new JPanel();
        jPanel8 = new JPanel();
        closeButton = new JButton();
        fieldsPanel = new JPanel();
        jPanel2 = new JPanel();
        title = new JFormattedTextField();
        gender = new JFormattedTextField();
        pages = new JFormattedTextField();
        finishing = new JFormattedTextField();
        jPanel3 = new JPanel();
        release = new JFormattedTextField();
        code = new JFormattedTextField();
        sell = new JFormattedTextField();
        buy = new JFormattedTextField();
        jPanel4 = new JPanel();
        id = new JFormattedTextField();
        publishing = new JFormattedTextField();
        author = new JFormattedTextField();
        submit = new JButton();
        cancel = new JButton();

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

        add.setBackground(new Color(236, 235, 243));
        add.setFont(new Font("Dialog", 1, 14)); // NOI18N
        add.setForeground(new Color(12, 18, 12));
        add.setText("Novo");
        add.setBorder(BorderFactory.createLineBorder(new Color(194, 1, 20), 3));
        add.setContentAreaFilled(false);
        add.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        add.setFocusPainted(false);

        update.setBackground(new Color(236, 235, 243));
        update.setFont(new Font("Dialog", 1, 14)); // NOI18N
        update.setForeground(new Color(12, 18, 12));
        update.setText("Editar");
        update.setBorder(BorderFactory.createLineBorder(new Color(194, 1, 20), 3));
        update.setContentAreaFilled(false);
        update.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        update.setFocusPainted(false);

        delete.setBackground(new Color(236, 235, 243));
        delete.setFont(new Font("Dialog", 1, 14)); // NOI18N
        delete.setForeground(new Color(12, 18, 12));
        delete.setText("Excluir");
        delete.setBorder(BorderFactory.createLineBorder(new Color(194, 1, 20), 3));
        delete.setContentAreaFilled(false);
        delete.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        delete.setFocusPainted(false);

        GroupLayout jPanel5Layout = new GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(add, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(update, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(delete, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(delete, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(update, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                    .addComponent(add, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel6.setBackground(new Color(194, 1, 20));

        jLabel1.setIcon(new ImageIcon("/var/www/miscellaneous/java/BookStore/lib/img/books.png")); // NOI18N

        jLabel2.setIcon(new ImageIcon("/var/www/miscellaneous/java/BookStore/lib/img/books.png")); // NOI18N

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

        closeButton.setFont(new Font("Dialog", 1, 14)); // NOI18N
        closeButton.setForeground(new Color(236, 235, 243));
        closeButton.setText("Close");
        closeButton.setBorder(BorderFactory.createLineBorder(new Color(194, 1, 20), 3));
        closeButton.setContentAreaFilled(false);
        closeButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        closeButton.setFocusPainted(false);

        GroupLayout jPanel8Layout = new GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(closeButton, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
        );
        jPanel8Layout.setVerticalGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(closeButton, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        fieldsPanel.setBackground(new Color(236, 235, 243));
        fieldsPanel.setBorder(BorderFactory.createLineBorder(new Color(12, 18, 12), 3));

        jPanel2.setBackground(new Color(236, 235, 243));

        title.setBackground(new Color(236, 235, 243));
        title.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2), "Título", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Liberation Sans", 0, 15), new Color(12, 18, 12))); // NOI18N
        title.setForeground(new Color(12, 18, 12));
        title.setCaretColor(new Color(194, 1, 20));
        title.setSelectedTextColor(new Color(236, 235, 243));
        title.setSelectionColor(new Color(152, 58, 69));

        gender.setBackground(new Color(236, 235, 243));
        gender.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2), "Gênero", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Liberation Sans", 0, 15), new Color(12, 18, 12))); // NOI18N
        gender.setForeground(new Color(12, 18, 12));
        gender.setCaretColor(new Color(194, 1, 20));
        gender.setSelectedTextColor(new Color(236, 235, 243));
        gender.setSelectionColor(new Color(152, 58, 69));

        pages.setBackground(new Color(236, 235, 243));
        pages.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2), "Nº de Páginas", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Liberation Sans", 0, 15), new Color(12, 18, 12))); // NOI18N
        pages.setForeground(new Color(12, 18, 12));
        pages.setCaretColor(new Color(194, 1, 20));
        pages.setSelectedTextColor(new Color(236, 235, 243));
        pages.setSelectionColor(new Color(152, 58, 69));

        finishing.setBackground(new Color(236, 235, 243));
        finishing.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2), "Acabamento", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Liberation Sans", 0, 15), new Color(12, 18, 12))); // NOI18N
        finishing.setForeground(new Color(12, 18, 12));
        finishing.setCaretColor(new Color(194, 1, 20));
        finishing.setSelectedTextColor(new Color(236, 235, 243));
        finishing.setSelectionColor(new Color(152, 58, 69));

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(gender, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                    .addComponent(pages)
                    .addComponent(finishing, GroupLayout.Alignment.TRAILING)
                    .addComponent(title))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gender, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pages, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(finishing, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new Color(236, 235, 243));

        release.setBackground(new Color(236, 235, 243));
        release.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2), "Ano de Lançamento", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Liberation Sans", 0, 15), new Color(12, 18, 12))); // NOI18N
        release.setForeground(new Color(12, 18, 12));
        try
        {
            release.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("####")));
        } catch (ParseException ex)
        {
            ex.printStackTrace();
        }
        release.setCaretColor(new Color(194, 1, 20));
        release.setSelectedTextColor(new Color(236, 235, 243));
        release.setSelectionColor(new Color(152, 58, 69));

        code.setBackground(new Color(236, 235, 243));
        code.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2), "Código de Barras", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Liberation Sans", 0, 15), new Color(12, 18, 12))); // NOI18N
        code.setForeground(new Color(12, 18, 12));
        code.setCaretColor(new Color(194, 1, 20));
        code.setSelectedTextColor(new Color(236, 235, 243));
        code.setSelectionColor(new Color(152, 58, 69));

        sell.setBackground(new Color(236, 235, 243));
        sell.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2), "Valor de Venda", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Liberation Sans", 0, 15), new Color(12, 18, 12))); // NOI18N
        sell.setForeground(new Color(12, 18, 12));
        sell.setCaretColor(new Color(194, 1, 20));
        sell.setSelectedTextColor(new Color(236, 235, 243));
        sell.setSelectionColor(new Color(152, 58, 69));

        buy.setBackground(new Color(236, 235, 243));
        buy.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2), "Valor de Compra", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Liberation Sans", 0, 15), new Color(12, 18, 12))); // NOI18N
        buy.setForeground(new Color(12, 18, 12));
        buy.setCaretColor(new Color(194, 1, 20));
        buy.setSelectedTextColor(new Color(236, 235, 243));
        buy.setSelectionColor(new Color(152, 58, 69));

        GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(release)
                    .addComponent(code, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                    .addComponent(sell)
                    .addComponent(buy, GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(release, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(code, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sell, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buy, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new Color(236, 235, 243));

        id.setEditable(false);
        id.setBackground(new Color(236, 235, 243));
        id.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2), "ID do Livro", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Liberation Sans", 0, 15), new Color(12, 18, 12))); // NOI18N
        id.setForeground(new Color(12, 18, 12));
        id.setCaretColor(new Color(194, 1, 20));
        id.setSelectedTextColor(new Color(236, 235, 243));
        id.setSelectionColor(new Color(152, 58, 69));

        publishing.setEditable(false);
        publishing.setBackground(new Color(236, 235, 243));
        publishing.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2), "Editora", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Liberation Sans", 0, 15), new Color(12, 18, 12))); // NOI18N
        publishing.setForeground(new Color(12, 18, 12));
        publishing.setCaretColor(new Color(194, 1, 20));
        publishing.setSelectedTextColor(new Color(236, 235, 243));
        publishing.setSelectionColor(new Color(152, 58, 69));

        author.setEditable(false);
        author.setBackground(new Color(236, 235, 243));
        author.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2), "Autor", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Liberation Sans", 0, 15), new Color(12, 18, 12))); // NOI18N
        author.setForeground(new Color(12, 18, 12));
        author.setCaretColor(new Color(194, 1, 20));
        author.setSelectedTextColor(new Color(236, 235, 243));
        author.setSelectionColor(new Color(152, 58, 69));

        submit.setBackground(new Color(236, 235, 243));
        submit.setFont(new Font("Dialog", 1, 14)); // NOI18N
        submit.setForeground(new Color(12, 18, 12));
        submit.setText("Submit");
        submit.setBorder(BorderFactory.createLineBorder(new Color(194, 1, 20), 3));
        submit.setContentAreaFilled(false);
        submit.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        submit.setFocusPainted(false);

        cancel.setBackground(new Color(236, 235, 243));
        cancel.setFont(new Font("Dialog", 1, 14)); // NOI18N
        cancel.setForeground(new Color(12, 18, 12));
        cancel.setText("Cancel");
        cancel.setBorder(BorderFactory.createLineBorder(new Color(194, 1, 20), 3));
        cancel.setContentAreaFilled(false);
        cancel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        cancel.setFocusPainted(false);

        GroupLayout jPanel4Layout = new GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(id)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(submit, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancel, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
                    .addComponent(author)
                    .addComponent(publishing))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(author, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(publishing, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(cancel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(submit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
    private JButton add;
    private JFormattedTextField author;
    private JFormattedTextField buy;
    private JButton cancel;
    private JButton closeButton;
    private JFormattedTextField code;
    private JButton delete;
    private JPanel fieldsPanel;
    private JFormattedTextField finishing;
    private JFormattedTextField gender;
    private JFormattedTextField id;
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
    private JFormattedTextField pages;
    private JFormattedTextField publishing;
    private JFormattedTextField release;
    private JFormattedTextField sell;
    private JButton submit;
    private JTable table;
    private JFormattedTextField title;
    private JButton update;
    // End of variables declaration//GEN-END:variables
}