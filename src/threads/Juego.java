package threads;
import gui.Game;
public class Juego extends Temporizador {
    protected static boolean lineGame;
    static {
        lineGame = false;
    }
    public Juego(String clock, Game game, char pausa) {
        super(clock, game, pausa);
    }
    @Override
    public void run() {
        lineGame = true;
        updateTime(getGame().getTime());
        temporizador(getGame().getTime());
        lineGame = false;
    }
}