package tools;
import java.awt.*;
public class Colour {
    private String text;
    private Color color;
    private String ledOpen;
    private String ledClosed;
    public Colour(String text, Color color, int ledOpen, int ledClosed) {
        this.text = text;
        this.color = color;
        this.ledOpen = String.valueOf(ledOpen);
        this.ledClosed = String.valueOf(ledClosed);
    }
    public String getText() {
        return text;
    }
    public Color getColor() {
        return color;
    }
    public String getLedOpen() {
        return ledOpen;
    }
    public String getLedClosed() {
        return ledClosed;
    }
    @Override
    public String toString() {
        return text;
    }
}