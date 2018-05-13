import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.text.TableView;
import java.awt.*;
public class Table extends JTable {
    public static DefaultTableModel MyTableModel;
    public static ScrollPane sc;

    public JTable Table ()
    {
        String[] columnNames = {"File name", "Size" , "Size type"};
        Object[][] data ={
                {"Kathy", "2.3 " , "MB"}};
          MyTableModel= new DefaultTableModel(columnNames,0);
          MyTableModel.addRow(data);
        JTable table = new JTable(MyTableModel);
       // ScrollPane sc= new ScrollPane(this);
       // this.setFillsViewportHeight(true);
        return table;


    }











}
