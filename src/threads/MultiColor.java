package threads;
import arduino.Arduino;
import gui.Contenedor;
import gui.Game;
import java.util.Random;
public class MultiColor extends Thread {
    private Game game;
    private Random random;//colores aleatorios//
    public static int foreground;//color de la palabra//
    private int background;//fondo//
    private int lastForeground;
    static {
        foreground = 0; //yellow
    }
    public MultiColor(Game game) {
        this.game = game;
        random = new Random();
        background = 0;
        lastForeground = -1;//pra contar un estado anterior si este no existiese simplemente no repite el color anterior
    }
    @Override
    public void run() {
        while (Seleccion.lineIntento) {
            random();//si o si debe existir asi Majé diga que es ilogico
            while (background == foreground ||
                    lastForeground == foreground) random();//en caso que sea igual lo rompe hasta que sea diferente //
            game.getColor().setBackground(Contenedor.colors.get(background).getColor());
            game.getColor().setForeground(Contenedor.colors.get(foreground).getColor());
            game.getColor().setText(Contenedor.colors.get(background).getText());
            if (Seleccion.lineIntento) {
                try {
                    Thread.sleep(44);
                } catch (InterruptedException e) {//None
                }
            }
        }
        sendArduino(Contenedor.colors.get(foreground).getLedOpen());
        lastForeground = foreground;//tiene que correr una vez para comparar
    }
    private void sendArduino(String color) {
        Arduino arduino = Arduino.getConexion();
        arduino.sendDato(color);
    }//envia dato de color al arduino//
    private void random() {
        foreground = random.nextInt(5);
        background = random.nextInt(5);
    }
}