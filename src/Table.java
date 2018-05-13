import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.text.TableView;
import java.awt.*;
public class Table {

    public JTable Table ()
    {
        JTable table = new JTable(new MyTableModel());
        return table;
    }

}
class MyTableModel extends AbstractTableModel {
    String[] columnNames = {"File name",
            "Last Name",
            "Sport",
            "Date ",
            "Size"};
    Object[][] data = {
            {"Kathy", "Smith",
                    "Snowboarding", new Integer(5), "2.3 MB"},
            {"John", "Doe",
                    "Rowing", new Integer(3), "302 KB"},
            {"Sue", "Black",
                    "Knitting", new Integer(2), "70 MB"},
            {"Jane", "White",
                    "Speed reading", new Integer(20), "193 MB"},
            {"Joe", "Brown",
                    "Pool", new Integer(10), "130 KB"}};

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }


}
