package gui;
import tools.Acciones;
import tools.Constrains;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
class Desplegable extends JPanel {
    private char text;
    private String descripcion;
    private Color color;
    private Acciones acciones;
    Desplegable(char text, String descripcion, Color color){
        setLayout(new GridBagLayout());
        setBackground(new Color(156, 118, 226, 95));
        this.text = text;
        this.descripcion = descripcion;
        this.color = color;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                acciones.action();
            }
        });
        init();
    }
    private void init(){
        JLabel letra = new JLabel(String.valueOf(text), JLabel.CENTER);
        letra.setFont(new Font(Font.DIALOG, Font.BOLD, 82));
        letra.addMouseListener(getMouseListeners()[0]);
        JLabel descrip = new JLabel(this.descripcion, SwingConstants.CENTER);
        descrip.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        descrip.addMouseListener(getMouseListeners()[0]);
        JProgressBar bar = new JProgressBar();
        bar.setValue(0);
        bar.setBackground(color);
        bar.setBorderPainted(false);
        bar.addMouseListener(getMouseListeners()[0]);
        Constrains.addComp(letra, this, new Rectangle(0, 0, 1, 1), 1, 1,
                new Insets(15, 5, 5, 5), GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        Constrains.addCompX(descrip, this, new Rectangle(0, 1, 1, 1), 0.5,
                new Insets(8, 5, 5, 5), GridBagConstraints.NORTH, GridBagConstraints.NONE);
        Constrains.addCompX(bar, this, new Rectangle(0, 2, 1, 1), 1,
                new Insets(10, 0, 0, 0), GridBagConstraints.SOUTH, GridBagConstraints.BOTH);
    }
    protected char getText(){
        return text;
    }
    protected void setAcciones(Acciones acciones){
        this.acciones = acciones;
    }
}