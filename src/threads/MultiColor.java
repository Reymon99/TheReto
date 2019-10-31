package threads;

import gui.Game;

public class MultiColor extends Thread {
    private Game game;
    public MultiColor(Game game) {
        this.game = game;
    }
    @Override
    public void run() {
        Game.colors.get(3);
    }
}
