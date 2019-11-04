package threads;
import gui.Game;
import tools.Time;
import javax.swing.*;
import java.util.HashMap;
public class Temporizador extends Thread {
    private int second;
    private int minute;
    private Game game;
    public static boolean lineGame;
    private boolean control;
    private char pausa;
    private Time time;
    private HashMap<Character, Long> pausaTime;
    private int intentos;
    static {
        lineGame = false;
    }
    public Temporizador(String clock, Game game, boolean control, char pausa, Time time){
        this.game = game;
        this.control = control;
        this.pausa = pausa;
        this.time = time;
        minute = Integer.parseInt(clock.substring(0, clock.indexOf(':')));
        second = Integer.parseInt(clock.substring(clock.indexOf(':') + 1));
        intentos = 0;
        loadPause();
    }
    @Override
    public void run() {
        if (time.equals(Time.TIME)) gameCompleto();
        else gameIntento();
    }
    private String format(int number){
        return String.valueOf(String.valueOf(number).length() == 2 ? number : "0" + number);
    }
    private void timeUp(JLabel clock){
        clock.setText(format(minute) + ':' + format(second));
        clock.updateUI();
    }
    private void loadPause(){
        pausaTime = new HashMap<>();
        pausaTime.put('F', 1000L);
        pausaTime.put('I', 800L);
        pausaTime.put('D', 600L);
    }
    private void gameCompleto(){
        setLineGame(true);
        intentosUp();
        timeUp(game.getTime());
        temporizador(game.getTime());
        game.continuePlay().setVisible(true);
        setLineGame(false);
    }
    private void gameIntento(){
        intentosUp();
        temporizador(game.getTimeGame());
    }
    private void temporizador(JLabel clock){
        //Calcular el parado con control y con lineGame
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
    private void intentosUp(){
        game.getIntentos().setText("Intentos: " + (++intentos));
    }
    public boolean isLineGame() {
        return lineGame;
    }
    public void setLineGame(boolean lineGame) {
        this.lineGame = lineGame;
    }
}