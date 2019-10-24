package gui;
import javax.swing.*;
public class TheRetos extends JFrame {
    private TheRetos(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(new Contenedor());
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }
    public static void main(String[] args) {
        new TheRetos().setVisible(true);
    }
}