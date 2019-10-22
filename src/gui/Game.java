package gui;
import tools.Constrains;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
public class Game extends Visor {
    private int intentos;
    private char dificultad;
    private HashMap<Character, String> text;
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

    }
    protected JDialog play(){
        JDialog dialog = new JDialog();
        dialog.getContentPane().setLayout(new GridBagLayout());
        JButton play = new JButton("Jugar!");
        play.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        play.addActionListener((e) -> dialog.dispose());
        JLabel level = new JLabel(text.get(dificultad), SwingConstants.CENTER);
        level.setFont(new Font(Font.MONOSPACED, Font.BOLD, 28));
        Constrains.addComp(play, dialog.getContentPane(), new Rectangle(0, 0, 1, 1), 1, 1,
                new Insets(30, 30, 10, 30), GridBagConstraints.CENTER, GridBagConstraints.NONE);
        Constrains.addComp(level, dialog.getContentPane(), new Rectangle(0, 1, 1, 1), 1, 1,
                new Insets(7, 15, 20, 15), GridBagConstraints.CENTER, GridBagConstraints.BOTH);
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
}