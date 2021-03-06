package gui;
import arduino.Arduino;
import threads.Juego;
import threads.MultiColor;
import threads.Seleccion;
import tools.Constrains;
import tools.Events;
import tools.Paneles;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
public class Game extends Visor implements ActionListener {
    private char dificultad;
    private JLabel time;//tiempo de juego
    private JLabel aciertos;
    private JLabel intentos;
    private JLabel color;//rectangulo de color mostrado
    private JLabel acertos;//informa si acerto el color o se equivoco
    private JLabel timeGame;//tiempo de ciclo
    private int acierto;//numero de aciertos
    private JDialog continuePlay; //mensaje de continuar
    private final ArrayList<String> acertosText;
    private final HashMap<Character, String> text;
    private final HashMap<Character, String> timeDificultad;
    {
        acertosText = new ArrayList<>(3);
        acertosText.add("Intentalo nuevamente");
        acertosText.add("Felicitaciones acertaste!!!");
        acertosText.add("");
        timeDificultad = new HashMap<>();
        timeDificultad.put('F', "00:15");//define el tiempo de los hilos
        timeDificultad.put('I', "00:10");
        timeDificultad.put('D', "00:08");
        text = new HashMap<>();
        text.put('F', "Nivel Fácil");
        text.put('I', "Nivel Intermedio");
        text.put('D', "Nivel Díficil");
    }
    Game(char dificultad){
        super();//este e s el constructor del padre
        this.dificultad = dificultad;
        acierto = 0;
        init();
    }
    private void init(){
        acertos = new JLabel(acertosText.get(2), SwingConstants.CENTER);
        color = new JLabel("Color", SwingConstants.CENTER);
        JButton yellow = new JButton(); //son los botones de los colores
        JButton blue = new JButton();
        JButton green = new JButton();
        JButton red = new JButton();
        JButton white = new JButton();
        color.setFont(new Font(Font.DIALOG, Font.BOLD, 80));
        acertos.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 22));
        yellow.setBackground(Contenedor.colors.get(0).getColor());
        blue.setBackground(Contenedor.colors.get(1).getColor());
        green.setBackground(Contenedor.colors.get(2).getColor());
        red.setBackground(Contenedor.colors.get(3).getColor());
        white.setBackground(Contenedor.colors.get(4).getColor());
        yellow.addActionListener(this);
        blue.addActionListener(this);
        green.addActionListener(this);
        red.addActionListener(this);
        white.addActionListener(this);
        yellow.setPreferredSize(new Dimension(80, 30));//este ajusta el tamaño y se aplica a todos
        blue.setPreferredSize(yellow.getPreferredSize());
        green.setPreferredSize(yellow.getPreferredSize());
        red.setPreferredSize(yellow.getPreferredSize());
        white.setPreferredSize(yellow.getPreferredSize());
        yellow.setMinimumSize(yellow.getPreferredSize());
        blue.setMinimumSize(yellow.getPreferredSize());
        green.setMinimumSize(yellow.getPreferredSize());
        red.setMinimumSize(yellow.getPreferredSize());
        white.setMinimumSize(yellow.getPreferredSize());
        yellow.setActionCommand("0");
        blue.setActionCommand("1");
        green.setActionCommand("2");
        red.setActionCommand("3");
        white.setActionCommand("4");
        color.setOpaque(true);//establece que aparezca el fondo
        color.setBackground(Color.YELLOW);//establece el color de fondo
        color.setForeground(Color.BLUE);//este es el color de la letra
        color.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));//se establece el borde que tiene
        Constrains.addComp(color, getContenido(), new Rectangle(0, 0, 5, 1), 1, 1,
                new Insets(50, 60, 50, 60), GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        Constrains.addComp(yellow, getContenido(), new Rectangle(0, 1, 1, 1), 1, 1,
                new Insets(10, 20, 5, 10), GridBagConstraints.CENTER, GridBagConstraints.NONE);
        Constrains.addComp(blue, getContenido(), new Rectangle(1, 1, 1, 1), 1, 1,
                new Insets(10, 10, 5, 10), GridBagConstraints.CENTER, GridBagConstraints.NONE);
        Constrains.addComp(green, getContenido(), new Rectangle(2, 1, 1, 1), 1, 1,
                new Insets(10, 10, 5, 10), GridBagConstraints.CENTER, GridBagConstraints.NONE);
        Constrains.addComp(red, getContenido(), new Rectangle(3, 1, 1, 1), 1, 1,
                new Insets(10, 10, 5, 10), GridBagConstraints.CENTER, GridBagConstraints.NONE);
        Constrains.addComp(white, getContenido(), new Rectangle(4, 1, 1, 1), 1, 1,
                new Insets(10, 10, 5, 20), GridBagConstraints.CENTER, GridBagConstraints.NONE);
        Constrains.addComp(acertos, getContenido(), new Rectangle(0, 2, 5, 1), 1, 1,
                new Insets(30, 10, 40, 10), GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
        Constrains.addCompX(toolBar(), getContenido(), new Rectangle(0, 3, 5, 1), 1,
                new Insets(10, 0, 0, 0), GridBagConstraints.SOUTH, GridBagConstraints.BOTH);
    }
    protected JDialog play(){
        JDialog dialog = new JDialog();
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dialog.getContentPane().setLayout(new GridBagLayout());
        JButton play = new JButton("Jugar!");
        play.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        play.addActionListener((e) -> {
            dialog.dispose();
            new Juego("03:30", this).start();//primer hilo que se ejecuta
            intento();
        });
        JButton volver = new JButton("Volver");
        volver.setFont(play.getFont());
        volver.addActionListener((e) -> {
            dialog.dispose();
            volver();
        });
        JLabel level = new JLabel(text.get(dificultad), SwingConstants.CENTER);
        level.setFont(new Font(Font.MONOSPACED, Font.BOLD, 28));
        JLabel theReto = new JLabel("The Reto!", SwingConstants.CENTER);
        theReto.setFont(new Font(Font.DIALOG, Font.BOLD + Font.ITALIC, 32));
        Constrains.addComp(theReto, dialog.getContentPane(), new Rectangle(0, 0, 2, 1), 1, 1,
                new Insets(5, 15, 5, 15), GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL);
        Constrains.addComp(play, dialog.getContentPane(), new Rectangle(0, 1, 1, 1), 1, 1,
                new Insets(15, 30, 10, 10), GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        Constrains.addComp(volver, dialog.getContentPane(), new Rectangle(1, 1, 1, 1), 1, 1,
                new Insets(15, 10, 10, 30), GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        Constrains.addComp(level, dialog.getContentPane(), new Rectangle(0, 2, 2, 1), 1, 1,
                new Insets(7, 15, 20, 15), GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        return dialog;
    }
    private JDialog continuePlay(){
        continuePlay = new JDialog();
        continuePlay.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);//por mas que lo intente no cierra la ventana
        continuePlay.getContentPane().setLayout(new GridBagLayout());
        JLabel theReto = new JLabel("The Reto!", SwingConstants.CENTER);
        theReto.setFont(new Font(Font.DIALOG, Font.BOLD + Font.ITALIC, 32));
        JButton volver = new JButton("Volver");
        volver.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        volver.addActionListener((e) -> {
            continuePlay.dispose();
            volver();
        });
        JButton continuar = new JButton("Continuar");
        continuar.setFont(volver.getFont());
        continuar.addActionListener((e) -> {
            continuePlay.dispose();
            intento();
        });
        Constrains.addComp(theReto, continuePlay.getContentPane(), new Rectangle(0, 0, 2, 1), 1, 1,
                new Insets(5, 15, 5, 15), GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL);
        Constrains.addComp(volver, continuePlay.getContentPane(), new Rectangle(1, 1, 1, 1), 1, 1,
                new Insets(15, 5, 10, 30), GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        Constrains.addComp(continuar, continuePlay.getContentPane(), new Rectangle(0, 1, 1, 1), 1, 1,
                new Insets(15, 30, 10, 5), GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        continuePlay.pack();
        continuePlay.setLocationRelativeTo(this);//lanza la ventana en la mitad panel
        return continuePlay;
    }
    public JDialog estadisticas(){
        JDialog dialog = new JDialog();
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dialog.getContentPane().setLayout(new GridBagLayout());
        acierto = acierto == 0 ? 0 : acierto - 1;// exactitud de las estadisticas
        JLabel sta = new JLabel("Estadísticas", SwingConstants.CENTER);
        sta.setFont(new Font(Font.DIALOG, Font.BOLD, 32));
        JTextArea stadis = new JTextArea();
        stadis.setTabSize(4);
        stadis.setOpaque(false);
        stadis.append("\tIntentos: " + Seleccion.intentos + '\n');
        stadis.append("\tAciertos: " + acierto + '\n');
        stadis.append("\tDesaciertos: " + (Seleccion.intentos - acierto) + '\n');
        stadis.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        stadis.setEditable(false);
        JButton volver = new JButton("Volver");
        volver.setFont(new Font(Font.DIALOG, Font.BOLD, 13));
        volver.addActionListener((e) -> {
            dialog.dispose();
            volver();
        });
        Constrains.addComp(sta, dialog.getContentPane(), new Rectangle(0, 0, 1, 1), 1, 1,
                new Insets(8, 10, 5, 10), GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
        Constrains.addComp(stadis, dialog.getContentPane(), new Rectangle(0, 1, 1, 1), 1, 1,
                new Insets(5, 2, 5, 8), GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        Constrains.addCompX(volver, dialog.getContentPane(), new Rectangle(0, 2, 1, 1), 1,
                new Insets(5, 7, 5, 7), GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        return dialog;
    }
    private JPanel toolBar(){
        JPanel panel = new JPanel(new GridBagLayout());
        time = new JLabel("00:00");
        time.setToolTipText("Tiempo de Juego");
        timeGame = new JLabel(time.getText());
        timeGame.setToolTipText("Tiempo de Ciclo");
        JLabel level = new JLabel(text.get(dificultad), SwingConstants.CENTER);//nivel en que nos encontramos
        intentos = new JLabel("Intentos: 0");
        aciertos = new JLabel(aciertos());
        Constrains.addCompX(intentos, panel, new Rectangle(0, 0, 1, 1), 1,
                new Insets(1, 10, 1, 1), GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
        Constrains.addCompX(aciertos, panel, new Rectangle(1, 0, 1, 1), 1,
                new Insets(1, 2, 1, 10), GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
        Constrains.addCompX(level, panel, new Rectangle(2, 0, 1, 1), 1,
                new Insets(1, 30, 1, 30), GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
        Constrains.addCompX(timeGame, panel, new Rectangle(3, 0, 1, 1), 1,
                new Insets(1, 30, 1, 10), GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
        Constrains.addCompX(time, panel, new Rectangle(4, 0, 1, 1), 1,
                new Insets(1, 30, 1, 10), GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL);
        return panel;
    }
    public JLabel getTime() {
        return time;
    }
    public JLabel getIntentos() {
        return intentos;
    }
    public JLabel getColor(){
        return color;
    }
    public JLabel getTimeGame(){
        return timeGame;
    }
    public char getDificultad(){
        return dificultad;
    }
    @Override
    public void actionPerformed(ActionEvent e) {// aqui se manejan todos los botones
        if (Juego.lineGame && !Seleccion.lineIntento) {
            if (Integer.parseInt(e.getActionCommand()) == MultiColor.foreground) {//para saber el color que esta seleccionado
                aciertos.setText(aciertos());//aqui se actualiza los aciertos
                acertos.setText(acertosText.get(1));
                aciertos.updateUI();//actualiza la interfaz
            } else acertos.setText(acertosText.get(0));
            acertos.updateUI();
            updateUI();
            ledOff();
            continuePlay().setVisible(true);
        }
    }
    private String aciertos(){//este es el metodo de los aciertos
        return aciertos(acierto++);
    }
    private String aciertos(int acer){
        return "Aciertos: " + acer;
    }
    public JDialog getContinuePlay(){//este retorna a la ventanita del juego
        return continuePlay;
    }
    private void volver(){
        Events.show(Paneles.LEVES);
        aciertos.setText(aciertos(acierto=0));
        intentos.setText("Intentos: " + (Seleccion.intentos=0));//los intentos se controlan desde un hilo
        acertos.setText(acertosText.get(2));
        updateUI();
        Juego.lineGame = false;
        Juego.stop = true;
    }
    private void intento(){
        acertos.setText(acertosText.get(2));
        Seleccion.lineIntento = true;//indica que vuelve a iniciar el ciclo
        new Seleccion(timeDificultad.get(dificultad), this).start();//contabiliza el ciclo
        new MultiColor(this).start();//inicia el ciclo de los colores
    }
    private void ledOff(){
        Arduino arduino = Arduino.getConexion();
        arduino.sendDato(Contenedor.colors.get(MultiColor.foreground).getLedClosed());
    }
}