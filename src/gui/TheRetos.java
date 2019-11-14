package gui;
import arduino.Arduino;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TheRetos extends JFrame {
    private TheRetos(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(new Contenedor());
        pack();//ajusta todo el contenido//
        setLocationRelativeTo(null);
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Arduino arduino = Arduino.getConexion();
                arduino.ledOffAll();
                arduino.closeOnConexion();
            }
            @Override
            public void windowClosed(WindowEvent e) {
                System.exit(0);
            }
        });
    }
    public static void main(String[] args) {
        new TheRetos().setVisible(true);
    }
}