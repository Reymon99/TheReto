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
    static {
        foreground = 0;
    }
    public MultiColor(Game game) {
        this.game = game;
        random = new Random();
        background = 0;
    }
    @Override
    public void run() {
        while (Seleccion.lineIntento) {
            random();//si o si debe existir asi Majé diga que es ilogico
            while (background == foreground) random();//en caso que sea igual lo rompe hasta que sea diferente //
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
    }
    private void sendArduino(String color) {
        //Wait Oscar
        Arduino arduino = Arduino.getConexion();
        arduino.sendDato(color);
    }//envia dato de color al arduino//
    private void random() {
        foreground = random.nextInt(5);
        background = random.nextInt(5);
    }
}