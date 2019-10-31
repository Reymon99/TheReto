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
            int background = random.nextInt(4) + 1;
            int foreground = random.nextInt(4) + 1;
            game.getColor().setForeground(Game.colors.get(foreground));
            game.getColor().setBackground(Game.colors.get(background));

            if (Temporizador.lineGame) {
                try {
                    Thread.sleep(28);
                } catch (InterruptedException e) {//None
                }
            }
        }
    }
}