package main;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Color;
public class ConfirmDialogInFrame extends JFrame {

    /*
    just me messing around I didn't use this in anything
     */
    public ConfirmDialogInFrame() {
        getContentPane().setBackground(Color.DARK_GRAY);
        setTitle("Confirm Dialog in Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setSize(400, 300);
        getContentPane().setLayout(null);
    }
}