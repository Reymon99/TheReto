package threads;
import gui.Game;
import tools.Acciones;
import javax.swing.*;
import java.util.HashMap;
public abstract class Temporizador extends Thread implements Acciones {
    private int second;
    private int minute;
    private Game game;//apuntador a la clase game
    private HashMap<Character, Long> pausaTime;
    public Temporizador(String clock, Game game){//constructor del hilo padre
        this.game = game;
        String[] time = clock.split(":");
        this.minute = Integer.parseInt(time[0]);
        this.second = Integer.parseInt(time[1]);
        loadPause();
    }
    private String format(int number){//metodo que retorna el formato de dos digitos para numeros
        return number > 10 ? String.valueOf(number) : "0" + number;
    }
    protected void updateTime(JLabel clock){
        clock.setText(format(minute) + ':' + format(second));
        clock.updateUI();
        game.updateUI();
    }
    private void loadPause(){// el tiempo que pone a dormir para hacer los cambios
        pausaTime = new HashMap<>();
        pausaTime.put('F', 1000L);
        pausaTime.put('I', 800L);
        pausaTime.put('D', 600L);
    }
    protected void temporizador(JLabel clock){//es el metodo contador
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
            Thread.sleep(pausaTime.get(game.getDificultad()));//duerme según la dificultad
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