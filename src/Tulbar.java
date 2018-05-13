import javax.swing.*;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
public class Tulbar {
    private JPanel panel;
    private JToolBar toolbar ;
    private JFrame frame;
    private NewDownload dl;
    private JButton button[] = new JButton[6];
    public JPanel Tulbar()
    {
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 60 , 5));
        panel.setPreferredSize(new Dimension(2000 , 70));
        panel.setBackground(Color.DARK_GRAY);
        toolbar= new JToolBar();
        String namesOfIcon[]={"new download","pause","resume","cancel","remove" , "setting"} ;
        String imageOfIcon[]={"n.png","p.png","res.png","c.png","rem.png","s.png"};
        for (int i=0 ; i<6 ;i++)

        {
            button[i] = new JButton( createImageIcon(imageOfIcon[i]));
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
                dl =  new NewDownload();
            }
        });
        button[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent  arg0) {
                FrameWork.MyTableModel.removeRow(0);
                FrameWork.MyTableModel.fireTableDataChanged();

            }
        });
        for (int i=0 ; i<6 ;i++)
            panel.add(button[i]);
        return panel;

    }
    protected ImageIcon createImageIcon(String path) {
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
    private Image scaleImage(Image image, int w, int h) {

        Image scaled = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);

        return scaled;
    }

}
