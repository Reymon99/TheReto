package gui;
import arduino.Led;
import tools.Constrains;
import tools.Events;
import tools.Paneles;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
public class Contenedor extends JPanel {//heredamos de jpanel
    private static HashMap<Paneles, Game> games;
    public static final ArrayList<Led> colors;
    static {//se ejecuta antes que el constructor
        colors = new ArrayList<>(5);//indica el estado del led
        colors.add(new Led("Amarillo", Color.YELLOW, 1, 0));
        colors.add(new Led("Azul", Color.BLUE, 3, 2));
        colors.add(new Led("Rojo", Color.RED, 5, 4));
        colors.add(new Led("Verde", Color.GREEN, 7, 6));
        colors.add(new Led("Blanco", Color.WHITE, 9, 8));
        games = new HashMap<>();
        games.put(Paneles.GAME_FACIL, new Game('F'));//aqui esta el objeto
        games.put(Paneles.GAME_INTERMEDIO, new Game('I'));
        games.put(Paneles.GAME_DIFICIL, new Game('D'));
    }
    public Contenedor(){
        setLayout(new CardLayout());//card layout muestra que panel que se muestra
        add(Paneles.WELCOME.toString(), new Welcome());
        add(Paneles.LEVES.toString(), levels());
        add(Paneles.GAME_FACIL.toString(), games.get(Paneles.GAME_FACIL));//llama al objeto de la instancia game
        add(Paneles.GAME_INTERMEDIO.toString(), games.get(Paneles.GAME_INTERMEDIO));
        add(Paneles.GAME_DIFICIL.toString(), games.get(Paneles.GAME_DIFICIL));
        Events.setContenedor(this);//llama al panel con todos los paneles
    }
    private Visor levels(){
        Visor visor = new Visor();
        Desplegable facil = new Desplegable('F', "Nivel Fácil", new Color(72, 181, 122));
        Desplegable intermedio = new Desplegable('I', "Nivel Intermedio", new Color(204, 155, 47));
        Desplegable dificil = new Desplegable('D', "Nivel Díficil", new Color(186, 29, 38));
        facil.setAcciones(() -> movePanel(Paneles.GAME_FACIL));
        intermedio.setAcciones(() -> movePanel(Paneles.GAME_INTERMEDIO));
        dificil.setAcciones(() -> movePanel(Paneles.GAME_DIFICIL));
        Constrains.addComp(facil, visor.getContenido(), new Rectangle(0, 0, 1, 1), 1, 1,
                new Insets(10, 10, 10, 10), GridBagConstraints.CENTER, GridBagConstraints.NONE);
        Constrains.addComp(intermedio, visor.getContenido(), new Rectangle(1, 0, 1, 1), 1, 1,
                new Insets(10, 10, 10, 10),GridBagConstraints.CENTER, GridBagConstraints.NONE);
        Constrains.addComp(dificil, visor.getContenido(), new Rectangle(2, 0, 1, 1), 1, 1,
                new Insets(10, 10, 10, 10),GridBagConstraints.CENTER, GridBagConstraints.NONE);
        return visor;
    }

    private void movePanel(Paneles panel) {
        Events.show(panel);
        games.get(panel).play().setVisible(true);
    }
}