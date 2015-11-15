package demo;

import javax.swing.*;

/**
 * Created by Abris on 2015. 11. 14..
 */
public class Main {
    public static void main(String[] args) {
        Demo demo = new Demo();
        JPanel jp = demo.getGraphPanel();
        JFrame jf = new JFrame();
        jf.getContentPane().add(jp);
        jf.setDefaultCloseOperation(3);
        jf.pack();
        jf.setVisible(true);
    }
}
