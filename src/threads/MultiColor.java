package threads;
import gui.Game;
import java.util.Random;
public class MultiColor extends Thread {
    private Game game;
    public MultiColor(Game game) {
        this.game = game;
    }
    @Override
    public void run() {
        while (Temporizador.lineGame) {
            Random random = new Random();
            int background = random.nextInt(5);
            int foreground = random.nextInt(5);
            game.getColor().setBackground(Game.colors.get(background).getColor());
            game.getColor().setForeground(Game.colors.get(foreground).getColor());
            game.getColor().setText(Game.colors.get(background).getText());
            if (Temporizador.lineGame) {
                try {
                    Thread.sleep(28);
                } catch (InterruptedException e) {//None
                }
            } else sendArduino(Game.colors.get(background).getText());
        }
    }
    private void sendArduino(String color) {
        //Wait Oscar
    }
}