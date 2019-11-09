package gui;
import tools.Constrains;
import tools.Events;
import tools.Paneles;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
class Contenedor extends JPanel {
    private static HashMap<Paneles, Game> games;
    static {
        games = new HashMap<>();
        games.put(Paneles.GAME_FACIL, new Game('F'));
        games.put(Paneles.GAME_INTERMEDIO, new Game('I'));
        games.put(Paneles.GAME_DIFICIL, new Game('D'));
    }
    Contenedor(){
        setLayout(new CardLayout());
        add(Paneles.WELCOME.toString(), new Welcome());
        add(Paneles.LEVES.toString(), levels());
        add(Paneles.GAME_FACIL.toString(), games.get(Paneles.GAME_FACIL));
        add(Paneles.GAME_INTERMEDIO.toString(), games.get(Paneles.GAME_INTERMEDIO));
        add(Paneles.GAME_DIFICIL.toString(), games.get(Paneles.GAME_DIFICIL));
        Events.setContenedor(this);
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