package GUI;

import javax.swing.*;
import java.awt.*;

public class main
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());
        frame.setBackground(new Color(40, 40, 40));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Panel panel = new Panel();
        frame.add(panel);
        frame.setVisible(true);
    }
}
