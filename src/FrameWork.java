import com.sun.javaws.util.JfxHelper;
import sun.tools.jps.Jps;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
import javax.swing.text.TableView;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class FrameWork extends JFrame
{
    private JTable table;
    private TabelShowingDownload tabelShowingDownload;
    private ArrayList<Download> downloads;
    private JMenuBar menuBar;
    private JMenu eachMenu[];
    private JMenuItem item[];
    private JMenuItem aboutItem;
    private JPanel panel;
    private JScrollPane scroll;
    private JButton button[] = new JButton[6];
    public ArrayList<Download> getDownloads() {
        return downloads;
    }

    public void setDownloads(ArrayList<Download> downloads) {
        this.downloads = downloads;
    }

    public FrameWork()
    {
        super("this is JDM");
        String namesOfIcon[]={"new download","pause","resume","cancel","remove" , "setting" , "        exit"} ;
        String imageOfIcon[]={"n.png","p.png","res.png","c.png","rem.png","s.png"};
        downloads = new ArrayList<Download>();
        setLayout(new FlowLayout());
        /**
         *  this is menu bar
         *  */
        menuBar = new JMenuBar();
        eachMenu = new JMenu[2];
        item =  new JMenuItem[7];
        aboutItem = new JMenuItem("About");
        eachMenu[0] = new JMenu("Download");
        eachMenu[1] = new JMenu("Help");
        eachMenu[1].add(aboutItem);
        aboutItem.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed (ActionEvent e) {
                        JOptionPane.showMessageDialog(null ,"author:Sina Faramarzi release date:1397/02/11\n this is a simple internet download manager without using any network" );
                    }
                }
        );
        for (int i=0 ; i<=6 ;i++)
        {
            if (i!=6)
                item[i] = new JMenuItem(namesOfIcon[i], createImageIconMenuBar(imageOfIcon[i]));
            else
                item[i] = new JMenuItem(namesOfIcon[i]);
        }
        item[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S , ActionEvent.ALT_MASK));
        item[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new newDownload();

            }
        });
        item[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabelShowingDownload.getDownload(table.getSelectedRow()).setStatus(2);
                tabelShowingDownload.getDownload(table.getSelectedRow()).stateChanged();
                tabelShowingDownload.fireTableRowsUpdated(0 , tabelShowingDownload.getRowCount()+1);

            }
        });
        item[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabelShowingDownload.getDownload(table.getSelectedRow()).setStatus(0);
                tabelShowingDownload.getDownload(table.getSelectedRow()).stateChanged();
                tabelShowingDownload.fireTableRowsUpdated(0 , tabelShowingDownload.getRowCount()+1);

            }
        });
        item[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabelShowingDownload.getDownload(table.getSelectedRow()).setStatus(3);
                tabelShowingDownload.getDownload(table.getSelectedRow()).setHowMuchDownloaded(0);
                tabelShowingDownload.getDownload(table.getSelectedRow()).stateChanged();
                tabelShowingDownload.fireTableRowsUpdated(0 , tabelShowingDownload.getRowCount()+1);

            }
        });

        item[6].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A , ActionEvent.ALT_MASK));
        item[6].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        for (int i=0 ; i<=6 ;i++)
            eachMenu[0].add(item[i]);
        menuBar.add(eachMenu[0]);
        menuBar.add (eachMenu[1]);
        add(menuBar);
        /**
         *  end of the menu bar
         *  */
    ////////////////////////////////////////////////////////////////////////////////////////
        /**
         *  this is toolbar
         *  */
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 60 , 5));
        panel.setPreferredSize(new Dimension(2000 , 70));
        panel.setBackground(Color.DARK_GRAY);
        for (int i=0 ; i<6 ;i++)

        {
            button[i] = new JButton( createImageIconToolBar(imageOfIcon[i]));
            button[i].setBackground(Color.orange);
            button[i].setOpaque(true);
            button[i].setBorderPainted(false);
            button[i].setMargin(new Insets(0, 0, 0, 0));
            button[i].setToolTipText(namesOfIcon[i]);
            button[i].setPreferredSize(new Dimension(60 , 60));
        }
        button[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new newDownload();

            }
        });
        button[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabelShowingDownload.getDownload(table.getSelectedRow()).setStatus(2);
                tabelShowingDownload.getDownload(table.getSelectedRow()).stateChanged();
                tabelShowingDownload.fireTableRowsUpdated(0 , tabelShowingDownload.getRowCount()+1);
            }
        });
        button[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabelShowingDownload.getDownload(table.getSelectedRow()).setStatus(0);
                tabelShowingDownload.getDownload(table.getSelectedRow()).stateChanged();
                tabelShowingDownload.fireTableRowsUpdated(0 , tabelShowingDownload.getRowCount()+1);
            }
        });
        button[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabelShowingDownload.getDownload(table.getSelectedRow()).setStatus(3);
                tabelShowingDownload.getDownload(table.getSelectedRow()).setHowMuchDownloaded(0);
                tabelShowingDownload.getDownload(table.getSelectedRow()).stateChanged();
                tabelShowingDownload.fireTableRowsUpdated(0 , tabelShowingDownload.getRowCount()+1);
            }
        });


        button[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow()!=-1)
                    tabelShowingDownload.removeDownload(table.getSelectedRow());
            }
        });
        for (int i=0 ; i<6 ;i++)
            panel.add(button[i]);
        add(panel);
        /**
         *  end of toolbar
         *  */


        tabelShowingDownload = new TabelShowingDownload();
        table=new JTable(tabelShowingDownload);
        table.setPreferredSize(new Dimension(900 , 600));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        ProgressRenderer renderer=new ProgressRenderer(0,100);
        renderer.setStringPainted(true);
        table.setDefaultRenderer(JProgressBar.class , renderer);

        scroll = new JScrollPane(table);
        add(scroll);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                JFrame frame3;
                JPanel panel2 ,panel3 , panel1 , panel4 , panel5 , panel6;
                JTextField text1 , text2 , text3 , text4 , text5 , text6;
                frame3 = new JFrame("info for the selected downloaded file");
                frame3.setLayout(new FlowLayout());
                frame3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                JPanel container = new JPanel();
                container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
                panel1 = new JPanel(new FlowLayout());
                panel2 = new JPanel(new FlowLayout());
                panel3 = new JPanel(new FlowLayout());
                panel4 = new JPanel(new FlowLayout());
                panel5 = new JPanel(new FlowLayout());
                panel6 = new JPanel(new FlowLayout());
                if ((0==tabelShowingDownload.getDownload(table.getSelectedRow()).getStatus()) )
                    text1 = new JTextField("file Status :   The file is downloading");
                else if ((1==tabelShowingDownload.getDownload(table.getSelectedRow()).getStatus()) )
                    text1 = new JTextField("file Status :   The file is downloaded");
                else if (2==tabelShowingDownload.getDownload(table.getSelectedRow()).getStatus())
                    text1 = new JTextField("file Status :   The file is paused");
                else if (tabelShowingDownload.getDownload(table.getSelectedRow()).getStatus()==3)
                    text1 = new JTextField("file Status :   The file is canceled");
                else
                    text1 = new JTextField("file Status :\n   The file is downloading");
                text2 = new JTextField("the URL for the downloaded file is:\n"+tabelShowingDownload.getDownload(table.getSelectedRow()).getURL());
                text3 = new JTextField("size : "+ tabelShowingDownload.getDownload(table.getSelectedRow()).getSize() + "MB");
                text4 = new JTextField("how much Dwonloaded: "+tabelShowingDownload.getDownload(table.getSelectedRow()).getSize()/100*tabelShowingDownload.getDownload(table.getSelectedRow()).getHowMuchDownloaded()+ "MB");
                text5 = new JTextField("release date : "+tabelShowingDownload.getDownload(table.getSelectedRow()).getStarted());
                text6 = new JTextField("file name : "+tabelShowingDownload.getDownload(table.getSelectedRow()).getFileName());
                JButton button = new JButton(createImageIconInfo("mes.png"));
                frame3.add(button);
                text1.setEditable(false);
                text2.setEditable(false);
                text3.setEditable(false);
                text4.setEditable(false);
                text5.setEditable(false);
                text6.setEditable(false);
                panel1.add(text2);
                panel2.add(text1);
                panel3.add(text3);
                panel4.add(text4);
                panel5.add(text5);
                panel6.add(text6);
                container.add(panel1);
                container.add(panel6);
                container.add(panel2);
                container.add(panel3);
                container.add(panel4);
                container.add(panel5);
                frame3.add(container);
                frame3.setSize(700,600);
                frame3.setMaximumSize(new Dimension(850 , 700));
                frame3.setVisible(true);


                container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));


            }
        });
    }

    protected ImageIcon createImageIconMenuBar(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {

            ImageIcon imge = new ImageIcon(imgURL);
            int width = 30;
            int height = 20;
            Image scaled = scaleImage(imge.getImage() , width , height);
            ImageIcon scaledIcon = new ImageIcon(scaled);
            return  scaledIcon;
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    private Image scaleImage(Image image, int w, int h) {

        Image scaled = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);

        return scaled;
    }
    protected ImageIcon createImageIconToolBar(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            ImageIcon imge = new ImageIcon(imgURL);
            int width = 50;
            int height = 50;
            Image scaled = scaleImage(imge.getImage(), width, height);
            ImageIcon scaledIcon = new ImageIcon(scaled);
            return scaledIcon;
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    protected ImageIcon createImageIconInfo(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            ImageIcon imge = new ImageIcon(imgURL);
            int width = 80;
            int height = 80;
            Image scaled = scaleImage(imge.getImage(), width, height);
            ImageIcon scaledIcon = new ImageIcon(scaled);
            return scaledIcon;
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    private void addToTable(Download d){
        tabelShowingDownload.addDownload(d);

    }
    private void deleteFromTable (Download d)
    {
        tabelShowingDownload.removeDownload(table.getSelectedRow());
    }
     public class  newDownload {
         public newDownload()
         {
             JFrame frame1;
             JPanel panel1;
             JPanel panel2, panel3, panel4, panel5;
             JButton button1;
             JButton button2;
             JTextField textField1;
             JTextField textField2;
            // JFileChooser fileChooser;
             JPanel container, container1;
             JTextField textField3;
             JTextField textField4;
             frame1 = new JFrame();
             frame1.setSize(700, 400);
             frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             frame1.setMinimumSize(new Dimension(700, 400));
             frame1.setMaximumSize(new Dimension(800, 500));
             frame1.setVisible(true);
             frame1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
             container = new JPanel();
             container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
             container1 = new JPanel();
             container1.setPreferredSize(new Dimension(600, 300));
             container1.setLayout(new BoxLayout(container1, BoxLayout.Y_AXIS));

             panel1 = new JPanel(new FlowLayout(0, 70, 20));
             textField1 = new JTextField(" Enter your URl:   ");
             textField1.setEditable(false);
             textField2 = new JTextField("", 25);
             textField2.setToolTipText("ENTER your URL :  ");
             panel1.add(textField1, 0);
             panel1.add(textField2, 1);

             panel2 = new JPanel(new FlowLayout(0, 70, 20));
             textField3 = new JTextField("ENTER file name:  ");
             textField3.setEditable(false);
             textField4 = new JTextField("", 15);
             textField4.setToolTipText("ENTER Name of the file");
             panel2.add(textField3, 0);
             panel2.add(textField4, 1);
             panel2.setBackground(Color.orange);
             panel1.setBackground(Color.orange);
             button1 = new JButton("OK");
             button1.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     int i=0;
                     if (textField2.getText().equals("") || textField4.getText().equals(""))
                         JOptionPane.showMessageDialog( null, "fill in the blanks please");
                     else {
                         Download download = new Download(textField2.getText());
                         download.setStarted(new java.util.Date());
                         String fileName = textField2.getText();
                         download.setFileName(fileName.substring(fileName.lastIndexOf('/') + 1));
                         download.setStatus(0);
                         download.setSize(new Random().nextInt(800));
                         addToTable(download);
                         java.util.Timer timer;
                         timer = new Timer();
                             timer.scheduleAtFixedRate(new TimerTask() {
                                 @Override
                                 public void run() {
                                     if (download.getStatus()==0) {
                                         download.actionOfDownload();
                                     }
                                         tabelShowingDownload.fireTableRowsUpdated(0, tabelShowingDownload.getRowCount() + 1);

                                 }
                             }, 2 * 1000, 2 * 1000);
                     }
                         frame1.dispose();
                     }

             });
             button2 = new JButton("cancel");
             button2.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     frame1.dispose();
                 }
             });
             panel3 = new JPanel(new GridLayout(1, 2, 20, 200));
             panel3.add(button1);
             panel3.add(button2);
             panel4 = new JPanel(new FlowLayout());
             panel4.add(panel3);
             //fileChooser = new JFileChooser();
             panel5 = new JPanel();
             //panel5.add(fileChooser);
             container.add(panel1);
             container.add(panel2);
             container1.add(container);
             container1.add(panel4);
             container1.add(panel5);
             frame1.add(container1);
         }
     }
}










