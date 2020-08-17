package gui;
import arduino.Arduino;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class TheRetos extends JFrame {
    private TheRetos(){
        setContentPane(new Contenedor());
        pack();//ajusta todo el contenido al frame//
        setLocationRelativeTo(null);
        setResizable(false);//no le cambia el tamaño al panel
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Arduino arduino = Arduino.getConexion();
                arduino.closeOnConexion();
                dispose();
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