import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Time;
import java.util.Date;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * this is download object
 *
 */
public class Download extends Observable implements Serializable , Runnable {
    public static final int DOWNLOAD = 0;
    public static final int COMPLETE = 1;
    public static final int PAUSE = 2;
    public static final int CANCEL = 3;
    private int status;
    private String fileName;
    private int size, rate;
    private String anotherString , path;
    private Date started;
    public File files;
    private int howMuchDownloaded;
    private URL url;
    public int getStatus() {
        return status;
    }

    public String getPath() {
        return path;
    }

    public void cancel() {
        status = CANCEL;
    }

    public String getAnotherstring() {
        return anotherString;
    }

    public void setAnotherstring(String anotherstring) {
        this.anotherString = anotherstring;
    }

    public File getFile() {
        return files;
    }

    public void setFile(File file) {
        this.files = file;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getHowMuchDownloaded() {
        return howMuchDownloaded;
    }
    public float getProgress(){
        return ((float)howMuchDownloaded/size)*100;
    }

    public void setHowMuchDownloaded(int howMuchDownloaded) {
        this.howMuchDownloaded = howMuchDownloaded;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public URL getURL() {
        return url;
    }

    public void setURL(URL URL) {
        this.url = URL;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }


    public Date getStarted() {
        return started;
    }

    public void setStarted(Date started) {
        this.started = started;
    }

    public Download(URL url , String fileName , Date started) {
        this.url = url;
        this.fileName = fileName;
        status = DOWNLOAD;
        size=1;
        this.started = started;
        howMuchDownloaded=0;
        actionOfDownload();
    }

    public void actionOfDownload() {
        Thread newThread = new Thread(this);
        newThread.start();

    }
    public String getUrlName(){
        return url.toString();
    }
    public void run ()
    {
        RandomAccessFile file=null;
        InputStream stream=null;
        try{
            HttpURLConnection connection=(HttpURLConnection)(getURL().openConnection());
            connection.setRequestProperty("Range", "bytes="+howMuchDownloaded+"-");
            connection.connect();
            if(connection.getResponseCode()==200){
                setStatus(3);
                setChanged();
            }
            int contentLength=connection.getContentLength();
            if(contentLength<1){
                setStatus(3);
                setChanged();
            }
            if(size==1){
                size=contentLength;
                setChanged();
            }
            anotherString = fileName + getUrlName().substring(getUrlName().length()-4,getUrlName().length());
            File files1 = new File("test1.txt");
            FileReader fileReader = new FileReader(files1);
            StringBuffer stringBuffer = new StringBuffer();
            int numCharsRead;
            char[] charArray = new char[1024];
            while ((numCharsRead = fileReader.read(charArray)) > 0) {
                stringBuffer.append(charArray, 0, numCharsRead);
            }
            path = stringBuffer.toString()+"/"+anotherString;
            fileReader.close();
            files = new File(path);
            file=new RandomAccessFile(files ,"rw");
            file.seek(howMuchDownloaded);
            stream=connection.getInputStream();
            while(status==DOWNLOAD){
                byte buffer[];
                if((size-howMuchDownloaded)>=2048){
                    buffer=new byte[2048];
                }
                else buffer=new byte[size-howMuchDownloaded];
                int read=stream.read(buffer);
                if(read==-1)
                    break;
                file.write(buffer, 0, read);;
                howMuchDownloaded+=read;
                stateChanged();
            }
            if(status==DOWNLOAD){
                status=COMPLETE;
                stateChanged();
            }
        }catch(Exception e){
            e.printStackTrace();
            setStatus(3);
            stateChanged();
        }finally{
            if(file!=null){
                try{
                    file.close();
                }catch(Exception e){}
            }
            if(stream!=null){
                try{
                    stream.close();
                }catch(Exception e){}
            }
        }
    }

    public void stateChanged() {
        setChanged();
        notifyObservers();
    }
}
