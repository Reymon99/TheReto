package gui;
import javax.swing.*;
import java.awt.*;
public class TheRetos extends JFrame {
    private TheRetos(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setContentPane(new Contenedor());
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }
    public static void main(String[] args) {
        new TheRetos().setVisible(true);
    }
}