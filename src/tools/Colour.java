package tools;
import java.awt.*;
public class Colour {
    private String text;
    private Color color;
    public Colour(String text, Color color) {
        this.text = text;
        this.color = color;
    }
    public String getText() {
        return text;
    }
    public Color getColor() {
        return color;
    }
    @Override
    public String toString() {
        return text;
    }
}