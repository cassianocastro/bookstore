package model.utils;

import javax.swing.JTable;

/**
 *
 */
public class TableChecker
{

    public boolean hasSelectedRow(JTable table)
    {
        int row = table.getSelectedRow();

        return row != -1;
    }
}