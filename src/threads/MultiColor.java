package threads;
import gui.Game;
import java.util.Random;
public class MultiColor extends Thread {
    private Game game;
    private Random random;
    private int foreground;
    private int background;
    public MultiColor(Game game) {
        this.game = game;
        random = new Random();
        foreground = background = 0;
    }
    @Override
    public void run() {
        while (Temporizador.lineGame) {
            random();
            while (background == foreground) random();
            game.getColor().setBackground(Game.colors.get(background).getColor());
            game.getColor().setForeground(Game.colors.get(foreground).getColor());
            game.getColor().setText(Game.colors.get(background).getText());
            if (Temporizador.lineGame) {
                try {
                    Thread.sleep(88);
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