import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.text.TableView;
import java.awt.*;
public class Table {
    private JTable showDownload;
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
                    "Pool", new Integer(10), "130 KB"}
    };
    public JScrollPane Table ()
    {
        TableColumn column = null;
        showDownload = new JTable(data, columnNames);
        showDownload.setDefaultEditor(Object.class, null);
        showDownload.setPreferredSize(new Dimension(1000 , 300));
        showDownload.setBackground(Color.orange);
        showDownload.setGridColor(Color.BLACK);
        showDownload.setForeground(Color.BLACK);
        showDownload.setFillsViewportHeight(true);
        for (int i=0;i<5;i++)
        {
            column = showDownload.getColumnModel().getColumn(i);
            if (i==0 || i==3)
                column.setPreferredWidth(100);
            else {
            column.setPreferredWidth(50);
        }
        }
        JScrollPane scrollPane = new JScrollPane(showDownload);
        return scrollPane;
    }
}
