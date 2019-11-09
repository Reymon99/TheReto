package threads;
import gui.Game;
public class Seleccion extends Temporizador {
    public static int intentos;
    public static boolean lineIntento;
    static {
        intentos = 0;
        lineIntento = false;
    }
    public Seleccion(String clock, Game game, char pausa) {
        super(clock, game, pausa);
    }
    @Override
    public void run() {
        if (Juego.lineGame) {
            updateIntentos();
            lineIntento = true;
            updateTime(getGame().getTimeGame());
            temporizador(getGame().getTimeGame());
            lineIntento = false;
        }
    }
    private void updateIntentos(){
        getGame().getIntentos().setText("Intentos: " + (++intentos));
        getGame().getIntentos().updateUI();
        getGame().updateUI();
    }
}