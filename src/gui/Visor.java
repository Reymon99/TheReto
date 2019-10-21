package gui;
import tools.Constrains;
import javax.swing.*;
import java.awt.*;
public class Visor extends JPanel {
    private JPanel contenido;
    Visor(){
        setLayout(new BorderLayout(5, 5));
        contenido = new JPanel(new GridBagLayout());
        init();
    }
    private void init(){
        JPanel head = new JPanel(new GridBagLayout());
        JTextArea theReto = new JTextArea("The\nReto!");
        JLabel descripcion = new JLabel("Piensa un color y mecaniza otro", SwingConstants.RIGHT);
        theReto.setFont(new Font(Font.DIALOG, Font.BOLD + Font.ITALIC, 23));
        theReto.setOpaque(false);
        theReto.setEditable(false);
        descripcion.setFont(new Font(Font.DIALOG, Font.PLAIN + Font.ITALIC, 9));
        Constrains.addComp(theReto, head, new Rectangle(0, 0, 1, 1), 0.2, 0, new Insets(2, 2, 1, 5), GridBagConstraints.NORTHEAST, GridBagConstraints.BOTH);
        Constrains.addCompX(descripcion, head, new Rectangle(0, 1, 1, 1), 0.1, new Insets(0, 30, 5, 5), GridBagConstraints.EAST, GridBagConstraints.NONE);
        add(head, BorderLayout.NORTH);
        add(contenido, BorderLayout.CENTER);
    }
    public JPanel getContenido() {
        return contenido;
    }
}