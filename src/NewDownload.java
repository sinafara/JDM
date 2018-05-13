import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NewDownload extends JFrame{
    private JFrame frame1;
    private JPanel panel1;
    private JPanel panel2 , panel3 , panel4;
    private JButton button1;
    private JButton button2;
    private JTextField textField1;
    private JTextField textField2;
    private JPanel container , container1;
    private JTextField textField3;
    private JTextField textField4;
    public NewDownload()
    {
        super("new Download");
        frame1 = new JFrame();
        frame1.setSize( 700 , 400);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setMinimumSize(new Dimension(700 , 400));
        frame1.setMaximumSize(new Dimension(800 , 500));
        frame1.setVisible(true);
        frame1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container1 = new JPanel();
        container1.setPreferredSize(new Dimension(600 , 300));
        container1.setLayout(new BoxLayout( container1, BoxLayout.Y_AXIS));

        panel1 = new JPanel(new FlowLayout(0 , 70 , 20));
        textField1 = new JTextField(" Enter your URl:   " );
        textField1.setEditable(false);
        textField2 = new JTextField("",10);
        textField2.setToolTipText("ENTER your URL :  " );
        panel1.add(textField1 , 0);
        panel1.add(textField2, 1);

        panel2 = new JPanel(new FlowLayout(0 , 70 , 20));
        textField3 = new JTextField("ENTER file name:  " );
        textField3.setEditable(false);
        textField4 = new JTextField("",10);
        textField4.setToolTipText("ENTER Name of the file");
        panel2.add(textField3 , 0);
        panel2.add(textField4, 1);

        button1 = new JButton("OK");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField2.getText().equals("")|| textField4.getText().equals(""))
                    JOptionPane.showMessageDialog(frame1 , "fill in the blanks please");
                else
                {
                    Object data[]={textField4.getText() , new Random().nextInt( 700) , (((new Random().nextInt(2))== 0)?"MB":"KB")};
                    FrameWork.MyTableModel.addRow(data);
                    frame1.dispose();
                }
            }
        });
        button2 = new JButton("cancel");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame1.dispose();
            }
        });
        panel3 = new JPanel(new GridLayout(1 , 2 ,20,200));
        panel3.add(button1);
        panel3.add(button2);
        panel4 = new JPanel(new FlowLayout());
        panel4.add(panel3);

        container.add(panel1);
        container.add(panel2);
        container1.add(container);
        container1.add(panel4);

        frame1.add(container1);
        frame1.setVisible(true);


    }
}
