package gui;
import threads.Temporizador;
import tools.Constrains;
import tools.Events;
import tools.Paneles;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
public class Game extends Visor {
    private char dificultad;
    private HashMap<Character, String> text;
    private JLabel time;
    private JLabel aciertos;
    private JLabel intentos;
    Game(char dificultad){
        super();
        this.dificultad = dificultad;
        text = new HashMap<>();
        text.put('F', "Nivel Fácil");
        text.put('I', "Nivel Intermedio");
        text.put('D', "Nivel Díficil");
        init();
    }
    private void init(){
        Constrains.addCompX(toolBar(), getContenido(), new Rectangle(0, 0, 1, 1), 1,
                new Insets(10, 0, 0, 0), GridBagConstraints.SOUTH, GridBagConstraints.BOTH);
    }
    JDialog play(){
        JDialog dialog = new JDialog();
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dialog.getContentPane().setLayout(new GridBagLayout());
        JButton play = new JButton("Jugar!");
        play.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        play.addActionListener((e) -> {
            dialog.dispose();
            new Temporizador("03:30", time).start();
        });
        JButton volver = new JButton("Volver");
        volver.setFont(play.getFont());
        volver.addActionListener((e) -> {
            dialog.dispose();
            Events.show(Paneles.LEVES);
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
    public JDialog continuePlay(){
        JDialog dialog = new JDialog();
        dialog.getContentPane().setLayout(new GridBagLayout());
        dialog.setUndecorated(false);
        JLabel theReto = new JLabel("The Reto!", SwingConstants.CENTER);
        theReto.setFont(new Font(Font.DIALOG, Font.BOLD + Font.ITALIC, 32));
        JButton volver = new JButton("Volver");
        volver.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        volver.addActionListener((e) -> {
            dialog.dispose();
            Events.show(Paneles.LEVES);
        });

        Constrains.addComp(theReto, dialog.getContentPane(), new Rectangle(0, 0, 2, 1), 1, 1,
                new Insets(5, 15, 5, 15), GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL);
        Constrains.addComp(volver, dialog.getContentPane(), new Rectangle(1, 1, 1, 1), 1, 1,
                new Insets(15, 10, 10, 30), GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        return dialog;
    }
    private JPanel toolBar(){
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        time = new JLabel("00:00");
        JLabel level = new JLabel(text.get(dificultad), SwingConstants.CENTER);
        intentos = new JLabel("Intentos: 0");
        aciertos = new JLabel("Aciertos: 0");
        Constrains.addCompX(intentos, panel, new Rectangle(0, 0, 1, 1), 1,
                new Insets(1, 1, 1, 1), GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
        Constrains.addCompX(aciertos, panel, new Rectangle(1, 0, 1, 1), 1,
                new Insets(1, 2, 1, 50), GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
        Constrains.addCompX(level, panel, new Rectangle(2, 0, 1, 1), 1,
                new Insets(1, 50, 1, 50), GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
        Constrains.addCompX(time, panel, new Rectangle(3, 0, 1, 1), 1,
                new Insets(1, 50, 1, 1), GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL);
        return panel;
    }
    public JLabel getTime() {
        return time;
    }
    public JLabel getAciertos() {
        return aciertos;
    }
    public JLabel getIntentos() {
        return intentos;
    }
}