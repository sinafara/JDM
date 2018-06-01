import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * the main program
 * that control the other classes
 * and access the all other access
 */
public class FrameWork extends JFrame {
    private JTable table;
    private TabelShowingDownload tabelShowingDownload;
    public static TabelShowingDownload tabelShowingDownload1;
    private JMenuBar menuBar;
    private JMenu eachMenu[];
    private JMenuItem item[];
    private JMenuItem aboutItem;
    private ArrayList<Download> result = new ArrayList<>();
    private JPanel panel;
    private JScrollPane scroll;
    private int NUMBER = 0;
    private JButton button[] = new JButton[6];

    public FrameWork() {
        super("this is JDM" );
        setLayout(new FlowLayout());
        /**
         *  this is menu bar
         *  */
        createChangeForToolbar();
        createChangeForMenubar();
        tabelShowingDownload = new TabelShowingDownload();
        table = new JTable(tabelShowingDownload);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getColumnModel().getColumn(0).setPreferredWidth(220);
        table.getColumnModel().getColumn(1).setPreferredWidth(30);
        table.getColumnModel().getColumn(2).setPreferredWidth(8);
        table.getColumnModel().getColumn(3).setPreferredWidth(40);
        table.getColumnModel().getColumn(4).setPreferredWidth(35);
        ProgressRenderer renderer = new ProgressRenderer(0, 100);
        renderer.setStringPainted(true);
        table.setDefaultRenderer(JProgressBar.class, renderer);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(1000, 400));
        TableRowSorter<TabelShowingDownload> sorter = new TableRowSorter(tabelShowingDownload);
        table.setRowSorter(sorter);
        sorter.setSortsOnUpdates(true);

        tabelShowingDownload.deserialaize();
        tabelShowingDownload.allAddDownload();

        tabelShowingDownload1 = tabelShowingDownload;
        JPanel panel = new JPanel(new BorderLayout());
        final JTextField filterText = new JTextField(40);
        panel.add(filterText, BorderLayout.CENTER);
        JButton button = new JButton("Filter" );
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = filterText.getText();
                if (text.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter(text));
                }
            }
        });
        panel.add(button, BorderLayout.WEST);
        add(scroll);
        add(panel);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getModifiers() == MouseEvent.BUTTON3_MASK) {
                    JFrame frame3;
                    JPanel panel2, panel3, panel1, panel4, panel5, panel6;
                    JTextField text1, text2, text3, text4, text5, text6;
                    frame3 = new JFrame("info for the selected downloaded file" );
                    frame3.setLayout(new FlowLayout());
                    frame3.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    JPanel container = new JPanel();
                    container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
                    panel1 = new JPanel(new FlowLayout());
                    panel2 = new JPanel(new FlowLayout());
                    panel3 = new JPanel(new FlowLayout());
                    panel4 = new JPanel(new FlowLayout());
                    panel5 = new JPanel(new FlowLayout());
                    panel6 = new JPanel(new FlowLayout());
                    if ((0 == tabelShowingDownload.getDownload(table.getSelectedRow()).getStatus()))
                        text1 = new JTextField("file Status :   The file is downloading" );
                    else if ((1 == tabelShowingDownload.getDownload(table.getSelectedRow()).getStatus()))
                        text1 = new JTextField("file Status :   The file is downloaded" );
                    else if (2 == tabelShowingDownload.getDownload(table.getSelectedRow()).getStatus())
                        text1 = new JTextField("file Status :   The file is paused" );
                    else if (tabelShowingDownload.getDownload(table.getSelectedRow()).getStatus() == 3)
                        text1 = new JTextField("file Status :   The file is canceled" );
                    else
                        text1 = new JTextField("file Status :\n   The file is downloading" );
                    text2 = new JTextField("the URL for the downloaded file is:\n" + tabelShowingDownload.getDownload(table.convertRowIndexToModel(table.getSelectedRow())).getURL());
                    text3 = new JTextField("size : " + tabelShowingDownload.getDownload(table.convertRowIndexToModel(table.getSelectedRow())).getSize());
                    text4 = new JTextField("how much Dwonloaded: " + tabelShowingDownload.getDownload(table.convertRowIndexToModel(table.getSelectedRow())).getHowMuchDownloaded());
                    text5 = new JTextField("release date : " + tabelShowingDownload.getDownload(table.convertRowIndexToModel(table.getSelectedRow())).getStarted());
                    text6 = new JTextField("file name : " + tabelShowingDownload.getDownload(table.convertRowIndexToModel(table.getSelectedRow())).getFileName());
                    JButton button = new JButton(createImageIconInfo("mes.png" ));
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
                    frame3.setSize(400, 300);
                    frame3.setMaximumSize(new Dimension(850, 700));
                    frame3.setVisible(true);

                }
                if (e.getClickCount() == 2 && !e.isConsumed()) {
                    e.consume();
                    File file = new File(tabelShowingDownload.getDownload(table.convertRowIndexToModel(table.getSelectedRow())).getPath());
                    try {
                        Desktop d = Desktop.getDesktop();
                        d.open(file);
                    } catch (Exception cc) {
                        cc.printStackTrace();
                    }
                    System.out.println("Double Click" );
                }
            }
        });
    }


    protected ImageIcon createImageIconMenuBar(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {

            ImageIcon imge = new ImageIcon(imgURL);
            int width = 30;
            int height = 20;
            Image scaled = scaleImage(imge.getImage(), width, height);
            ImageIcon scaledIcon = new ImageIcon(scaled);
            return scaledIcon;
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

    public class newDownload {
        public newDownload() {
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
            textField1 = new JTextField(" Enter your URl:   " );
            textField1.setEditable(false);
            textField2 = new JTextField("", 25);
            textField2.setToolTipText("ENTER your URL :  " );
            panel1.add(textField1, 0);
            panel1.add(textField2, 1);

            panel2 = new JPanel(new FlowLayout(0, 70, 20));
            textField3 = new JTextField("ENTER file name:  " );
            textField3.setEditable(false);
            textField4 = new JTextField("", 15);
            textField4.setToolTipText("ENTER Name of the file" );
            panel2.add(textField3, 0);
            panel2.add(textField4, 1);
            panel2.setBackground(Color.orange);
            panel1.setBackground(Color.orange);
            button1 = new JButton("OK" );
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if ((textField2.getText().equals("" ) || textField4.getText().equals("" )))
                        JOptionPane.showMessageDialog(frame1, "fill in the blanks please" );
                    else if ((checkForright(textField2.getText()) == null)) {
                        JOptionPane.showMessageDialog(frame1, "wrong url" );
                    } else {
                        settingNumber();
                        int k = 1;
                        int count = 0;
                        for (k = 1; k < tabelShowingDownload.getRowCount(); k++) {
                            if (tabelShowingDownload.getDownload(k).getStatus() == 0)
                                count++;

                        }
                        if ((count < NUMBER) || (NUMBER == 0)) {
                            tabelShowingDownload.addDownload(new Download(checkForright(textField2.getText()), textField4.getText(), new java.util.Date()));
                            tabelShowingDownload.serialaize();
                        } else {
                            JOptionPane.showMessageDialog(FrameWork.this, "u can't add more download" );
                            frame1.dispose();
                        }
                    }
                    frame1.dispose();
                }

            });

            button2 = new JButton("cancel" );
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

    public class filechooser {
        public filechooser() {
            JFrame frame;
            frame = new JFrame();
            frame.setLayout(new FlowLayout());
            JTextField textField1 = new JTextField("save path:  " );
            JTextField textField2 = new JTextField("limit of download: " );
            JTextField textField3 = new JTextField("", 10);
            JButton button10;
            button10 = new JButton("ok" );
            button10.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    NUMBER = Integer.parseInt(textField3.getText());
                    System.out.println(NUMBER);
                    try {
                        FileOutputStream file = new FileOutputStream("setting.jdm", false);
                        DataOutputStream out = new DataOutputStream(file);
                        out.writeInt(NUMBER);
                        file.close();
                    } catch (IOException m) {
                        m.printStackTrace();
                    }
                    frame.dispose();
                }
            });
            textField1.setEditable(false);
            textField2.setEditable(false);
            JPanel panel1;
            JPanel panel2;
            JPanel panel3;
            panel3 = new JPanel();
            panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
            panel2 = new JPanel();
            panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
            panel2.add(textField2);
            panel2.add(textField3);
            panel1 = new JPanel();
            panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            jfc.setDialogTitle("Choose a directory to save your file: " );
            jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            JButton btn = new JButton("click to save file directory" );
            ActionListener al;
            al = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    switch (jfc.showOpenDialog(FrameWork.this)) {
                        case JFileChooser.APPROVE_OPTION: {
                            try {
                                File files = new File("test1.txt" );
                                FileWriter fileWriter = new FileWriter(files);
                                fileWriter.write(jfc.getSelectedFile().toString());
                                fileWriter.flush();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            JOptionPane.showMessageDialog(FrameWork.this, "Selected: " +
                                            jfc.getSelectedFile(),
                                    "FCDemo",
                                    JOptionPane.OK_OPTION);
                        }
                        break;

                        case JFileChooser.CANCEL_OPTION:
                            JOptionPane.showMessageDialog(FrameWork.this, "Cancelled",
                                    "FCDemo",
                                    JOptionPane.CANCEL_OPTION);
                            break;

                        case JFileChooser.ERROR_OPTION:
                            JOptionPane.showMessageDialog(FrameWork.this, "Error",
                                    "FCDemo",
                                    JOptionPane.ERROR_MESSAGE);
                    }
                }
            };
            btn.addActionListener(al);
            panel3.add(textField1);
            panel3.add(btn);
            panel1.add(panel3);
            panel1.add(panel2);
            panel1.add(button10);
            frame.add(panel1);
            frame.setVisible(true);
            frame.setSize(600, 700);

        }

    }

    private void settingNumber() {
        try {
            File file = new File("setting.jdm" );
            DataInputStream outstream = new DataInputStream(new FileInputStream(file));
            NUMBER = outstream.readInt();
            outstream.close();
        } catch (IOException m) {
            m.printStackTrace();
        }
    }

    private void createChangeForToolbar() {
        String namesOfIcon[] = {"new download", "pause", "resume", "cancel", "remove", "setting", "explore", "        exit"};
        String imageOfIcon[] = {"n.png", "p.png", "res.png", "c.png", "rem.png", "s.png", "search.png"};
        menuBar = new JMenuBar();
        eachMenu = new JMenu[2];
        item = new JMenuItem[8];
        aboutItem = new JMenuItem("About" );
        eachMenu[0] = new JMenu("Download" );
        eachMenu[1] = new JMenu("Help" );
        eachMenu[1].add(aboutItem);
        aboutItem.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(null, "author:Sina Faramarzi release date:1397/02/11\n this is an Advanced internet download manager " );
                    }
                }
        );
        for (int i = 0; i <= 7; i++) {
            if (i == 7)
                item[i] = new JMenuItem(namesOfIcon[i]);
            else
                item[i] = new JMenuItem(namesOfIcon[i], createImageIconMenuBar(imageOfIcon[i]));
        }
        item[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
        item[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new newDownload();

            }
        });
        item[1].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.ALT_MASK));
        item[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tabelShowingDownload.getDownload(table.convertRowIndexToModel(table.getSelectedRow())).getStatus() != 1) {
                    tabelShowingDownload.getDownload(table.convertRowIndexToModel(table.getSelectedRow())).setStatus(2);
                    tabelShowingDownload.getDownload(table.convertRowIndexToModel(table.getSelectedRow())).stateChanged();
                    tabelShowingDownload.serialaize();
                }
            }
        });

        item[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tabelShowingDownload.getDownload(table.convertRowIndexToModel(table.getSelectedRow())).getStatus() != 1) {
                    tabelShowingDownload.getDownload(table.convertRowIndexToModel(table.getSelectedRow())).setStatus(0);
                    tabelShowingDownload.getDownload(table.convertRowIndexToModel(table.getSelectedRow())).actionOfDownload();
                    tabelShowingDownload.getDownload(table.convertRowIndexToModel(table.getSelectedRow())).stateChanged();
                    tabelShowingDownload.serialaize();
                }
            }
        });
        item[3].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int m = 0;
                if (tabelShowingDownload.getDownload(table.convertRowIndexToModel(table.getSelectedRow())).getStatus() != 1) {
                    tabelShowingDownload.getDownload(table.convertRowIndexToModel(table.getSelectedRow())).setStatus(3);
                   /* for (Download d : tabelShowingDownload.getDownloadList()) {
                        if (d == tabelShowingDownload.getDownloadList().get(m))
                            tabelShowingDownload.getDownloadList().get(m).setStatus(3);

                    }*/
                    tabelShowingDownload.getDownload(table.convertRowIndexToModel(table.getSelectedRow())).setHowMuchDownloaded(0);
                    tabelShowingDownload.getDownload(table.convertRowIndexToModel(table.getSelectedRow())).stateChanged();
                    tabelShowingDownload.serialaize();
                }
            }
        });
        item[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() != -1) {
                    try {
                        BufferedWriter x = new BufferedWriter(new FileWriter("removed.jdm", true));
                        x.write(tabelShowingDownload.getDownload(table.convertRowIndexToModel(table.getSelectedRow())).getURL() + "  " );
                        x.write(tabelShowingDownload.getDownload(table.convertRowIndexToModel(table.getSelectedRow())).getAnotherstring());
                        x.newLine();
                        x.close();
                    } catch (IOException k) {
                        k.printStackTrace();
                    }
                    tabelShowingDownload.removeDownload(table.convertRowIndexToModel(table.getSelectedRow()));
                }
            }
        });

        item[5].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new filechooser();
            }
        });
        item[6].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });
        item[7].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
        item[7].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        for (int i = 0; i <= 7; i++)
            eachMenu[0].add(item[i]);
        menuBar.add(eachMenu[0]);
        menuBar.add(eachMenu[1]);
        add(menuBar);
        /**
         *  end of the menu bar
         *  */
    }

    private void createChangeForMenubar() {
        String namesOfIcon[] = {"new download", "pause", "resume", "cancel", "remove", "setting", "        exit"};
        String imageOfIcon[] = {"n.png", "p.png", "res.png", "c.png", "rem.png", "s.png"};
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 60, 5));
        panel.setPreferredSize(new Dimension(2000, 70));
        panel.setBackground(Color.DARK_GRAY);
        for (int i = 0; i < 6; i++)

        {
            button[i] = new JButton(createImageIconToolBar(imageOfIcon[i]));
            button[i].setBackground(Color.orange);
            button[i].setOpaque(true);
            button[i].setBorderPainted(false);
            button[i].setMargin(new Insets(0, 0, 0, 0));
            button[i].setToolTipText(namesOfIcon[i]);
            button[i].setPreferredSize(new Dimension(60, 60));
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
                if (tabelShowingDownload.getDownload(table.convertRowIndexToModel(table.getSelectedRow())).getStatus() != 1) {
                    tabelShowingDownload.getDownload(table.convertRowIndexToModel(table.getSelectedRow())).setStatus(2);
                    tabelShowingDownload.getDownload(table.convertRowIndexToModel(table.getSelectedRow())).stateChanged();
                    tabelShowingDownload.serialaize();
                }
            }
        });
        button[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tabelShowingDownload.getDownload(table.convertRowIndexToModel(table.getSelectedRow())).getStatus() != 1) {
                    tabelShowingDownload.getDownload(table.convertRowIndexToModel(table.getSelectedRow())).setStatus(0);
                    tabelShowingDownload.getDownload(table.convertRowIndexToModel(table.getSelectedRow())).actionOfDownload();
                    tabelShowingDownload.getDownload(table.convertRowIndexToModel(table.getSelectedRow())).stateChanged();
                    tabelShowingDownload.serialaize();
                }

            }
        });
        button[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int m = 0;
                if (tabelShowingDownload.getDownload(table.convertRowIndexToModel(table.getSelectedRow())).getStatus() != 1) {
                    tabelShowingDownload.getDownload(table.convertRowIndexToModel(table.getSelectedRow())).setStatus(3);
                   /* for (Download d : tabelShowingDownload.getDownloadList()) {
                        if (d == tabelShowingDownload.getDownloadList().get(m))
                            tabelShowingDownload.getDownloadList().get(m).setStatus(3);

                    }*/
                    File file = new File(tabelShowingDownload.getDownload(table.convertRowIndexToModel(table.getSelectedRow())).files.getAbsolutePath());
                    file.delete();
                    tabelShowingDownload.getDownload(table.convertRowIndexToModel(table.getSelectedRow())).setHowMuchDownloaded(0);
                    tabelShowingDownload.getDownload(table.convertRowIndexToModel(table.getSelectedRow())).stateChanged();
                    tabelShowingDownload.serialaize();
                }
            }
        });


        button[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() != -1) {
                    try {
                        BufferedWriter x = new BufferedWriter(new FileWriter("removed.jdm", true));
                        x.write(tabelShowingDownload.getDownload(table.convertRowIndexToModel(table.getSelectedRow())).getURL() + "  " );
                        x.write(tabelShowingDownload.getDownload(table.convertRowIndexToModel(table.getSelectedRow())).getFileName());
                        x.newLine();
                        x.close();
                    } catch (IOException k) {
                        k.printStackTrace();
                    }
                    tabelShowingDownload.removeDownload(table.convertRowIndexToModel(table.getSelectedRow()));
                }
            }
        });
        button[5].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new filechooser();
            }
        });
        for (int i = 0; i < 6; i++)
            panel.add(button[i]);
        add(panel);
        /**
         *  end of toolbar
         *  */

    }

    private URL checkForright(String urlName) {
        if (!urlName.toLowerCase().startsWith("http://" ))
            return null;
        URL urlVerify = null;
        try {
            urlVerify = new URL(urlName);
        } catch (Exception e) {
            return null;
        }
        if (urlVerify.getFile().length() < 2)
            return null;
        return urlVerify;

    }

    private void zipFiles(ArrayList<File> fileList, String destZipFile) {
        try {

            FileOutputStream fos = new FileOutputStream(destZipFile);
            ZipOutputStream zos = new ZipOutputStream(fos);

            System.out.println("Creating Zip Archive : " + destZipFile);

            for (File file : fileList) {

                System.out.println("Added " + file);
                ZipEntry ze = new ZipEntry(file.getName());
                //This is required to make sure the archived files keep the last modified
                //time same as disk.
                ze.setTime(file.lastModified());
                zos.putNextEntry(ze);
            }
            zos.closeEntry();
            zos.close();
            System.out.println("Done" );
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
