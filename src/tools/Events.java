package tools;
import javax.swing.*;
import java.awt.*;
public class Events {
    private static JPanel contenedor;
    public static void setContenedor(JPanel contenedor){
        Events.contenedor = contenedor;
    }
    public static void show(Paneles paneles){
        ((CardLayout)Events.contenedor.getLayout()).show(Events.contenedor, paneles.toString());
    }
}