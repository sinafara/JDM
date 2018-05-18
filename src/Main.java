import com.sun.javaws.util.JfxHelper;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        FrameWork frame = new FrameWork();
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setMinimumSize(new Dimension(650 , 400));
        frame.setSize(1500, 1500);
        frame.setVisible(true);
    }
}


