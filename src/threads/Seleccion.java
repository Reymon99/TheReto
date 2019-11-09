package threads;
import gui.Game;
public class Seleccion extends Temporizador {
    private static int intentos;
    static {
        intentos = 0;
    }
    public Seleccion(String clock, Game game, char pausa) {
        super(clock, game, pausa);
    }
    @Override
    public void run() {

        intentos = 0;
    }
    private void intentosUp(){
        getGame().getIntentos().setText("Intentos: " + (++intentos));
    }
}