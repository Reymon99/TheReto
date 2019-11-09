package threads;
import gui.Game;
import tools.Acciones;
import javax.swing.*;
import java.util.HashMap;
public abstract class Temporizador extends Thread implements Acciones {
    private int second;
    private int minute;
    private Game game;
    private HashMap<Character, Long> pausaTime;
    public Temporizador(String clock, Game game){
        this.game = game;
        this.minute = Integer.parseInt(clock.substring(0, clock.indexOf(':')));
        this.second = Integer.parseInt(clock.substring(clock.indexOf(':') + 1));
        loadPause();
    }
    private String format(int number){
        return String.valueOf(String.valueOf(number).length() == 2 ? number : "0" + number);
    }
    protected void updateTime(JLabel clock){
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
    protected void temporizador(JLabel clock){
        while (minute != 0 || second != 0) {
            if (second == 0) {
                second = 59;
                minute--;
            }
            else second--;
            action();
            updateTime(clock);
        }
    }
    protected Game getGame() {
        return game;
    }
    @Override
    public void action() {
        try {
            Thread.sleep(pausaTime.get(game.getDificultad()));
        } catch (InterruptedException e) {//None
        }
    }
    protected int getSecond() {
        return second;
    }
    protected int getMinute() {
        return minute;
    }
    protected void setSecond(int second) {
        this.second = second;
    }
    protected void setMinute(int minute) {
        this.minute = minute;
    }
}