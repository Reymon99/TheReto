package gui;
import tools.Constrains;
import tools.Events;
import tools.Paneles;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
class Contenedor extends JPanel {
     private static HashMap<Paneles, Game> games;
    Contenedor(){
        setLayout(new CardLayout());
        games = new HashMap<>();
        games.put(Paneles.GAME_FACIL, new Game('F'));
        games.put(Paneles.GAME_INTERMEDIO, new Game('I'));
        games.put(Paneles.GAME_DIFICIL, new Game('D'));
        add(Paneles.WELCOME.toString(), welcome());
        add(Paneles.LEVES.toString(), levels());
        add(Paneles.GAME_FACIL.toString(), games.get(Paneles.GAME_FACIL));
        add(Paneles.GAME_INTERMEDIO.toString(), games.get(Paneles.GAME_INTERMEDIO));
        add(Paneles.GAME_DIFICIL.toString(), games.get(Paneles.GAME_DIFICIL));
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
        Constrains.addComp(theReto, panel, new Rectangle(0, 0, 1, 1), 0.2, 0,
                new Insets(20, 15, 0, 30), GridBagConstraints.NORTHWEST, GridBagConstraints.NONE);
        Constrains.addCompX(descripcion, panel, new Rectangle(0, 1, 1, 1), 0.1,
                new Insets(0, 30, 10, 8), GridBagConstraints.WEST, GridBagConstraints.BOTH);
        Constrains.addComp(press, panel, new Rectangle(0, 2, 1, 1), 0, 0,
                new Insets(25, 20, 1, 20), GridBagConstraints.SOUTH, GridBagConstraints.BOTH);
        return panel;
    }
    private Visor levels(){
        Visor visor = new Visor();
        Desplegable facil = new Desplegable('F', "Nivel Fácil", new Color(72, 181, 122));
        Desplegable intermedio = new Desplegable('I', "Nivel Intermedio", new Color(204, 155, 47));
        Desplegable dificil = new Desplegable('D', "Nivel Díficil", new Color(186, 29, 38));
        facil.setAcciones(() -> {
            Events.show(Paneles.GAME_FACIL);
            games.get(Paneles.GAME_FACIL).play().setVisible(true);
        });
        intermedio.setAcciones(() -> {
            Events.show(Paneles.GAME_INTERMEDIO);
            games.get(Paneles.GAME_INTERMEDIO).play().setVisible(true);
        });
        dificil.setAcciones(() -> {
            Events.show(Paneles.GAME_DIFICIL);
            games.get(Paneles.GAME_DIFICIL).play().setVisible(true);
        });
        Constrains.addComp(facil, visor.getContenido(), new Rectangle(0, 0, 1, 1), 1, 1,
                new Insets(10, 10, 10, 10), GridBagConstraints.CENTER, GridBagConstraints.NONE);
        Constrains.addComp(intermedio, visor.getContenido(), new Rectangle(1, 0, 1, 1), 1, 1,
                new Insets(10, 10, 10, 10),GridBagConstraints.CENTER, GridBagConstraints.NONE);
        Constrains.addComp(dificil, visor.getContenido(), new Rectangle(2, 0, 1, 1), 1, 1,
                new Insets(10, 10, 10, 10),GridBagConstraints.CENTER, GridBagConstraints.NONE);
        return visor;
    }
}