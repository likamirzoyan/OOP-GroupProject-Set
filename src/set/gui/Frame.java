package set.gui;

import java.awt.Color;
import javax.swing.*;

public class Frame {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("SET");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,400);

        ImageIcon image = new ImageIcon("set_logo.jpg");
        frame.setIconImage(image.getImage());
        frame.getContentPane().setBackground(new Color(255,250,205));

        frame.setVisible(true);
    }
}
