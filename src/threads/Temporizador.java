package threads;
import javax.swing.*;
public class Temporizador extends Thread {
    private int second;
    private int minute;
    private JLabel label;
    public Temporizador(String time, JLabel label){
        this.label = label;
        minute = Integer.parseInt(time.substring(0, time.indexOf(':')));
        second = Integer.parseInt(time.substring(time.indexOf(':') + 1));
    }
    @Override
    public void run() {
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
    }
    private String format(int number){
        return String.valueOf(String.valueOf(number).length() == 2 ? number : "0" + number);
    }
    private void timeUp(){
        label.setText(format(minute) + ':' + format(second));
        label.updateUI();
    }
}