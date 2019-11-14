package threads;
import gui.Game;
public class Seleccion extends Temporizador {
    public static int intentos;
    public static boolean lineIntento;
    static {
        intentos = 0;
        lineIntento = false;
    }
    public Seleccion(String clock, Game game) {//constructor de los hilos
        super(clock, game);
    }
    @Override
    public void run() {//aqui esta el hilo de esta vaina
        if (Juego.lineGame) {
            updateIntentos();
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
    @Override
    public void action() {
        if (Juego.lineGame) super.action();
    }
}