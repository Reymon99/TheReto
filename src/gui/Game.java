package gui;
import threads.Temporizador;
import tools.Constrains;
import tools.Events;
import tools.Paneles;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
public class Game extends Visor {
    private int intentos;
    private char dificultad;
    private HashMap<Character, String> text;
    private JLabel time;
    Game(char dificultad){
        super();
        this.dificultad = dificultad;
        text = new HashMap<>();
        text.put('F', "Nivel Fácil");
        text.put('I', "Nivel Intermedio");
        text.put('D', "Nivel Díficil");
        intentos = 0;
        init();
    }
    private void init(){
        Constrains.addCompX(toolBar(), getContenido(), new Rectangle(0, 0, 1, 1), 1,
                new Insets(10, 0, 0, 0), GridBagConstraints.SOUTH, GridBagConstraints.BOTH);
    }
    JDialog play(){
        JDialog dialog = new JDialog();
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
        Constrains.addComp(play, dialog.getContentPane(), new Rectangle(0, 0, 1, 1), 1, 1,
                new Insets(30, 30, 10, 10), GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        Constrains.addComp(volver, dialog.getContentPane(), new Rectangle(1, 0, 1, 1), 1, 1,
                new Insets(30, 10, 10, 30), GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        Constrains.addComp(level, dialog.getContentPane(), new Rectangle(0, 1, 2, 1), 1, 1,
                new Insets(7, 15, 20, 15), GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        return dialog;
    }
    private JDialog continuePlay(){
        JDialog dialog = new JDialog();
        dialog.getContentPane().setLayout(new GridBagLayout());
        dialog.setUndecorated(false);

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        return dialog;
    }
    private JPanel toolBar(){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        time = new JLabel("00:00");
        panel.add(time);
        return panel;
    }
}