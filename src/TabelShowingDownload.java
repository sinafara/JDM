
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class TabelShowingDownload extends AbstractTableModel implements Observer {
    private String columnNames[]={"URL","file name","Size","Progress","Status"};
    private Class classNames[]={String.class,String.class,Integer.class,JProgressBar.class,String.class};
    private ArrayList<Download> downloadList=new ArrayList();
    private ArrayList<Download> downloadList1=new ArrayList();
    public ArrayList<Download> getDownloadList() {
        return downloadList;
    }

    public void setDownloadList(ArrayList<Download> downloadList) {
        this.downloadList = downloadList;
    }

    public void update(Observable o,Object arg){
        int index=downloadList.indexOf(o);
        fireTableRowsUpdated(index,index);
        serialaize();
    }
    public void addDownload(Download download){
        download.addObserver(this);
        downloadList.add(download);
        fireTableDataChanged();
    }
    public void allAddDownload ()
    {
        fireTableDataChanged();

    }
    public void removeDownload(int row){
        downloadList.remove(row);
        serialaize();
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
            case 0:return obj.getUrlName();
            case 1:return obj.getFileName();
            case 2:int i=obj.getSize();
                return new Integer(i);
            case 3:
                return new Float (obj.getProgress());
            case 4:
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
    public void deserialaize()
    {
        try
        {
            FileInputStream fis = new FileInputStream("list.jdm");
            ObjectInputStream ois = new ObjectInputStream(fis);
            downloadList = (ArrayList) ois.readObject();
            fireTableDataChanged();
            ois.close();
            fis.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
            return;
        }catch(ClassNotFoundException c){
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }
        for(Object tmp: downloadList){
              System.out.println(tmp);
        }
    }
    public void serialaize()
    {
        try {

            // Saving of object in a file
            FileOutputStream file = new FileOutputStream("list.jdm");
            ObjectOutputStream out = new ObjectOutputStream(file);
            // Method for serialization of object
            out.writeObject(downloadList);
            for (Download x :downloadList)
            {
                x.addObserver(this);

            }
            out.flush();
            out.close();
            file.close();
        }

        catch (IOException ex) {
            System.out.println("IOException is caught");
        }
    }



}