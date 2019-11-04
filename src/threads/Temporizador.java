package threads;
import gui.Game;
public class Temporizador extends Thread {
    private int second;
    private int minute;
    private Game game;
    public static boolean lineGame;
    private boolean control;
    public Temporizador(String time, Game game, boolean control){
        this.game = game;
        this.control = control;
        minute = Integer.parseInt(time.substring(0, time.indexOf(':')));
        second = Integer.parseInt(time.substring(time.indexOf(':') + 1));
        lineGame = false;
    }
    @Override
    public void run() {
        //Calcular el parado con control y con lineGame
        setLineGame(true);
        String[] intentos = game.getIntentos().getText().split( ": ");
        game.getIntentos().setText(intentos[0] + ": " + (Integer.parseInt(intentos[1]) + 1));
        timeUp();
        while (minute != 0 || second != 0) {
            if (second == 0) {
                second = 59;
                minute--;
            }
            else second--;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {//None
            }
            timeUp();
        }
        game.continuePlay().setVisible(true);
        setLineGame(false);
    }
    private String format(int number){
        return String.valueOf(String.valueOf(number).length() == 2 ? number : "0" + number);
    }
    private void timeUp(){
        game.getTime().setText(format(minute) + ':' + format(second));
        game.getTime().updateUI();
    }
    public boolean isLineGame() {
        return lineGame;
    }
    public void setLineGame(boolean lineGame) {
        this.lineGame = lineGame;
    }
}