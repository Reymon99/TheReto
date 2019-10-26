package gui;
import tools.Constrains;
import javax.swing.*;
import java.awt.*;
public class Visor extends JPanel {
    private JPanel contenido;
    Visor(){
        setLayout(new BorderLayout(7, 7));
        contenido = new JPanel(new GridBagLayout());
        init();
    }
    private void init(){
        JPanel head = new JPanel(new GridBagLayout());
        JTextArea theReto = new JTextArea("The\nReto!");
        JLabel descripcion = new JLabel("Piensa un color y mecaniza otro", SwingConstants.RIGHT);
        theReto.setToolTipText("Desarrollado por Sergio Majé");
        theReto.setFont(new Font(Font.DIALOG, Font.BOLD + Font.ITALIC, 30));
        theReto.setOpaque(false);
        theReto.setEditable(false);
        descripcion.setFont(new Font(Font.DIALOG, Font.PLAIN + Font.ITALIC, 14));
        Constrains.addComp(theReto, head, new Rectangle(0, 0, 1, 1), 0, 0,
                new Insets(5, 5, 1, 5), GridBagConstraints.WEST, GridBagConstraints.NONE);
        Constrains.addCompX(descripcion, head, new Rectangle(0, 1, 1, 1), 0.1,
                new Insets(0, 30, 5, 100), GridBagConstraints.WEST, GridBagConstraints.NONE);
        add(head, BorderLayout.NORTH);
        add(contenido, BorderLayout.CENTER);
    }
    protected JPanel getContenido() {
        return contenido;
    }
}