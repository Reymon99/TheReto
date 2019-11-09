package threads;
import gui.Game;
import java.util.Random;
public class MultiColor extends Thread {
    private Game game;
    private Random random;
    public static int foreground;
    private int background;
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
            random();
            while (background == foreground) random();
            game.getColor().setBackground(Game.colors.get(background).getColor());
            game.getColor().setForeground(Game.colors.get(foreground).getColor());
            game.getColor().setText(Game.colors.get(background).getText());
            if (Seleccion.lineIntento) {
                try {
                    Thread.sleep(60);
                } catch (InterruptedException e) {//None
                }
            } else sendArduino(Game.colors.get(background).getText());
        }
    }
    private void sendArduino(String color) {
        //Wait Oscar
    }
    private void random() {
        foreground = random.nextInt(5);
        background = random.nextInt(5);
    }
}