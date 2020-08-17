package threads;
import arduino.Arduino;
import gui.Game;

import javax.swing.*;

public class Juego extends Temporizador {//temporizador es la clase padre
    public static boolean lineGame;//linea del juego
    public static boolean stop;//linea que detiene el tiempo
    static {
        lineGame = false;
        stop = false;
    }
    public Juego(String clock, Game game) {
        super(clock, game);
    }
    @Override
    public void run() {//este es el metodo que arranca el hilo
        lineGame = true;
        stop = false;
        JLabel time = getGame().getTime();
        updateTime(time);
        temporizador(time);//si el tiempo no llega cero no se ejecuta
        lineGame = false;
        try {
            getGame().getContinuePlay().dispose();//se encarga de matar la ventana de continuación del juego
        } catch (Exception e){//en el caso de que la ventana no exista
        }
        if (!stop){
            Arduino arduino = Arduino.getConexion();
            arduino.ledOffAll();
            getGame().estadisticas().setVisible(true);
        }
    }
    @Override
    public void action() {
        if (lineGame) super.action();
        else {
            setMinute(0);
            setSecond(0);
            updateTime(getGame().getTime());
        }
    }
}