package threads;
import gui.Game;
public class Juego extends Temporizador {
    public static boolean lineGame;
    static {
        lineGame = false;
    }
    public Juego(String clock, Game game) {
        super(clock, game);
    }
    @Override
    public void run() {
        lineGame = true;
        updateTime(getGame().getTime());
        temporizador(getGame().getTime());
        lineGame = false;
        try {
            getGame().getContinuePlay().dispose();
        } catch (Exception e){//None
        }
        getGame().estadisticas().setVisible(true);
    }
}