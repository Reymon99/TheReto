package gui;
import tools.Constrains;
import tools.Events;
import tools.Paneles;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
class Contenedor extends JPanel {
    Contenedor(){
        setLayout(new CardLayout());
        add(Paneles.WELCOME.toString(), welcome());
        add(Paneles.LEVES.toString(), new Leves());
        add(Paneles.GAME.toString(), new Game());
        Events.setContenedor(this);
    }
    private JPanel welcome(){
        JPanel panel = new JPanel(new GridBagLayout());
        JTextArea theReto = new JTextArea("The\nReto!");
        theReto.setFont(new Font(Font.MONOSPACED, Font.BOLD + Font.ITALIC, 120));
        theReto.setOpaque(false);
        theReto.setEditable(false);
        theReto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                Events.show(Paneles.LEVES);
            }
        });
        JLabel press = new JLabel("Presione cualquier tecla para continuar", JLabel.CENTER);
        press.setFont(new Font(Font.DIALOG, Font.PLAIN, 10));
        JLabel descripcion = new JLabel("Piensa un color y mecaniza otro", JLabel.RIGHT);
        descripcion.setFont(new Font(Font.DIALOG, Font.PLAIN + Font.ITALIC, 20));
        Constrains.addComp(theReto, panel, new Rectangle(0, 0, 1, 1), 0.2, 0, new Insets(20, 15, 0, 30), GridBagConstraints.NORTHEAST, GridBagConstraints.NONE);
        Constrains.addCompX(descripcion, panel, new Rectangle(0, 1, 1, 1), 0.1, new Insets(0, 30, 10, 8), GridBagConstraints.WEST, GridBagConstraints.BOTH);
        Constrains.addComp(press, panel, new Rectangle(0, 2, 1, 1), 0, 0, new Insets(25, 20, 1, 20), GridBagConstraints.SOUTH, GridBagConstraints.BOTH);
        return panel;
    }
}