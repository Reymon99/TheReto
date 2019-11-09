package threads;
import gui.Game;
import javax.swing.*;
import java.util.HashMap;
public abstract class Temporizador extends Thread {
    private int second;
    private int minute;
    private Game game;
    private char pausa;
    private HashMap<Character, Long> pausaTime;
    public Temporizador(String clock, Game game, char pausa){
        this.game = game;
        this.pausa = pausa;
        this.minute = Integer.parseInt(clock.substring(0, clock.indexOf(':')));
        this.second = Integer.parseInt(clock.substring(clock.indexOf(':') + 1));
        loadPause();
    }
    private String format(int number){
        return String.valueOf(String.valueOf(number).length() == 2 ? number : "0" + number);
    }
    protected void timeUp(JLabel clock){
        clock.setText(format(minute) + ':' + format(second));
        clock.updateUI();
        game.updateUI();
    }
    private void loadPause(){
        pausaTime = new HashMap<>();
        pausaTime.put('F', 1000L);
        pausaTime.put('I', 800L);
        pausaTime.put('D', 600L);
    }
    private void gameIntento(){
        intentosUp();
        temporizador(game.getTimeGame());
    }
    protected void temporizador(JLabel clock){
        while (minute != 0 || second != 0) {
            if (second == 0) {
                second = 59;
                minute--;
            }
            else second--;
            try {
                Thread.sleep(pausaTime.get(pausa));
            } catch (InterruptedException e) {//None
            }
            timeUp(clock);
        }
    }

    public boolean isLineGame() {
        return lineGame;
    }
    public void setLineGame(boolean lineGame) {
        this.lineGame = lineGame;
    }
    protected Game getGame() {
        return game;
    }
}