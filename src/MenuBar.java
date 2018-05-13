import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MenuBar {
    private JMenuBar menuBar;
    private JMenu eachMenu[] = new JMenu[2];
    private JMenuItem item[] =  new JMenuItem[7];
    private JMenuItem aboutItem = new JMenuItem();
    public JMenuBar MenuBar()
    {
        menuBar= new JMenuBar();
        String namesOfIcon[]={"new download","pause","resume","cancel","remove" , "setting" , "        exit"} ;
        String imageOfIcon[]={"n.png","p.png","res.png","c.png","rem.png","s.png"};
        eachMenu[0] = new JMenu("Download");
        eachMenu[0].setBackground(Color.darkGray);
        eachMenu[1] = new JMenu("Help");
        aboutItem = new JMenuItem("About");
        eachMenu[1].add(aboutItem);
        aboutItem.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(null ,"author:Sina Faramarzi release date:1397/02/11\n this is a simple internet download manager without using any network" );
                    }
                }
        );
        menuBar.add(eachMenu[0]);
        menuBar.add (eachMenu[1]);
        for (int i=0 ; i<=6 ;i++)
        {
            if (i!=6)
            item[i] = new JMenuItem(namesOfIcon[i], createImageIcon(imageOfIcon[i]));
            else
                item[i] = new JMenuItem(namesOfIcon[i]);
        }
        item[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S , ActionEvent.ALT_MASK));
        item[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewDownload download = new NewDownload();
            }
        });
        for (int i=0 ; i<=6 ;i++)
        eachMenu[0].add(item[i]);
        return menuBar;
    }
    protected ImageIcon createImageIcon(String path) {
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

}
