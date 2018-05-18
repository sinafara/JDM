import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.Date;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Download extends Observable {
    public static final int DOWNLOAD = 0;
    public static final int COMPLETE = 1;
    public static final int PAUSE = 2;
    public static final int CANCEL = 3;
    private int status;
    private String fileName, URL;
    private int size, rate;
    private Date started;
    private int howMuchDownloaded;

    public int getStatus() {
        return status;
    }

    public void cancel() {
        status = CANCEL;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getHowMuchDownloaded() {
        return howMuchDownloaded;
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

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
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

    public Download(String url) {
        this.URL = url;
        status = DOWNLOAD;
       // actionOfDownload();
    }

    public void actionOfDownload() {
      howMuchDownloaded+=10;
      if (howMuchDownloaded==100)
          status=1;
    }

    public void stateChanged() {
        setChanged();
        notifyObservers();
    }
}
