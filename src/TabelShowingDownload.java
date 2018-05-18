import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class TabelShowingDownload extends AbstractTableModel implements Observer{
    private String columnNames[]={"URL","Size","Progress","Status"};
    private Class classNames[]={String.class,String.class,JProgressBar.class,String.class};
    private ArrayList<Download> downloadList=new ArrayList();

    public ArrayList<Download> getDownloadList() {
        return downloadList;
    }

    public void setDownloadList(ArrayList<Download> downloadList) {
        this.downloadList = downloadList;
    }

    public TabelShowingDownload()
    {
    }
    public void addDownload(Download download){
        downloadList.add(download);
        fireTableRowsInserted(getRowCount(),getRowCount());
    }
    public void removeDownload(int row){
        downloadList.remove(row);
        fireTableRowsDeleted(row,row);
    }
    public String getColumnName(int col){
        return columnNames[col];
    }
    public Class<?> getColumnClass(int col){
        return classNames[col];
    }
    public Download getDownload(int row){
        return downloadList.get(row);
    }
    public int getRowCount(){
        return downloadList.size();
    }
    public int getColumnCount(){
        return columnNames.length;
    }
    public Object getValueAt(int row,int col){
        Download obj=downloadList.get(row);
        switch(col){
            case 0:return obj.getURL();
            case 1:int i=obj.getSize();
                return (i==-1)?"":Integer.toString(i);
            case 2:
                return new Float(obj.getHowMuchDownloaded());
            case 3:
                if (obj.getStatus()==0)
                    return "Downloading";
                if (obj.getStatus()==1)
                    return "Completed";
                if (obj.getStatus()==2)
                    return "Pause";
                if (obj.getStatus()==3)
                    return "Cancel";
        }
        return "";
    }
    public void update(Observable o,Object arg){
        int index=downloadList.indexOf(o);
        fireTableRowsUpdated(index,index);
    }
}