import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.text.TableView;
import java.awt.*;

public class FrameWork extends JFrame
{
    private MenuBar menubar ;
    private Tulbar toolbar;
    public static DefaultTableModel MyTableModel;
    public  static Table tablebar;
    public FrameWork()
    {
        super("this is JDM");

        String[] columnNames = {"File name",
                "Size" , "Size Type"};
        String[] data ={"Kathy movie","27", "Mb"};
        MyTableModel= new DefaultTableModel(columnNames,0);
        MyTableModel.addRow(data);
        JTable table = new JTable(MyTableModel);
        JScrollPane sc= new JScrollPane(table);
        table.setFillsViewportHeight(true);
        setLayout(new FlowLayout());
        menubar = new MenuBar();
        toolbar = new Tulbar();
        tablebar = new Table();
        setJMenuBar(menubar.MenuBar());
        add(toolbar.Tulbar());
        add(sc);
    }


}