package gui;
import tools.Constrains;
import javax.swing.*;
import java.awt.*;
public class Visor extends JPanel {//clase padre
    private JPanel contenido;//es lo que se va mostrar en el panel que se muestra en visor
    Visor(){
        setLayout(new BorderLayout(7, 7));//aqui hay otro layout para definir el tamaño
        contenido = new JPanel(new GridBagLayout());//como se muestra el baglayout
        init();
    }
    private void init(){
        JPanel head = new JPanel(new GridBagLayout());
        JTextArea theReto = new JTextArea("The\nReto!");
        JLabel descripcion = new JLabel("Lee un color y mecaniza otro", SwingConstants.RIGHT);
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