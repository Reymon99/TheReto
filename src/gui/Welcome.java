package gui;
import arduino.Arduino;
import tools.Constrains;
import tools.Events;
import tools.Paneles;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class Welcome extends JPanel {
    private JLabel press; // contiene el mensaje de presionar botón, el que Oscar y Majé pensaron que era clic
    private JLabel conexion; // contiene el mensaje del estado de la conexion
    private Arduino arduino; //contiene la instancia del arduino
    public Welcome(){
        setLayout(new GridBagLayout());//no olvidar aqui esta la grilla
        init();
        this.arduino = Arduino.getConexion(this);//apuntamos a la instancia de arduino
        this.arduino.conectarArduino();
    }
    private void init(){
        JTextArea theReto = new JTextArea("The\nReto!");
        theReto.setFont(new Font(Font.MONOSPACED, Font.BOLD + Font.ITALIC, 120));//fuente del texto
        theReto.setOpaque(false); //para quitar el fondo blanco
        theReto.setEditable(false);// para que nadie lo edite
        theReto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {//libera la tecla despues de liberarla
                if (arduino.isConexion()){// comprueba la conexión con el arduino
                    arduino.ledOnAll();
                    Events.show(Paneles.LEVES);
                }
                // To Diana
                //Events.show(Paneles.LEVES);
            }
        });
        press = new JLabel("Presione cualquier tecla para continuar", SwingConstants.CENTER); // no clic,tecla!!!
        press.setFont(new Font(Font.DIALOG, Font.PLAIN, 10));
        JLabel descripcion = new JLabel("Lee un color y mecaniza otro", SwingConstants.RIGHT);
        descripcion.setFont(new Font(Font.DIALOG, Font.PLAIN + Font.ITALIC, 20));
        conexion = new JLabel("", SwingConstants.LEFT);//aqui mostramos el estado de la conexion
        conexion.setFont(new Font(Font.DIALOG, Font.PLAIN, 12));
        Constrains.addComp(theReto, this, new Rectangle(0, 0, 1, 1), 0.2, 0,
                new Insets(20, 15, 0, 30), GridBagConstraints.NORTHWEST, GridBagConstraints.NONE);
        Constrains.addCompX(descripcion, this, new Rectangle(0, 1, 1, 1), 0.1,
                new Insets(0, 30, 10, 8), GridBagConstraints.WEST, GridBagConstraints.BOTH);
        Constrains.addComp(conexion, this, new Rectangle(0, 2, 1, 1), 1, 1,
                new Insets(75, 20, 1, 50), GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL);
        Constrains.addComp(press, this, new Rectangle(0, 3, 1, 1), 0, 1,
                new Insets(15, 20, 3, 20), GridBagConstraints.NORTH, GridBagConstraints.BOTH);
    }
    public JLabel getPress() {
        return press;
    }
    public JLabel getConexion() {
        return conexion;
    }
}