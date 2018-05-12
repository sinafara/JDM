import com.sun.javaws.util.JfxHelper;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("hello");
        FrameWork frame = new FrameWork();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(650 , 400));
        frame.setSize(1000 , 800);
        frame.setVisible(true);
    }
}


