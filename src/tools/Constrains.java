package tools;
import java.awt.*;
import java.awt.geom.Point2D;
public abstract class Constrains {
    /**
     * Añade y distribuye el componente dentro del eje X y Y de la interfaz gráfica
     * @param component {@link Component} a añadir dentro del {@link Container}
     * @param container {@link Container} al cual se añadirá el {@link Component}
     * @param rectangle posicionamiento del componente
     * @param weightx   dispersión al eje X
     * @param weighty   dispersión al eje Y
     * @param insets    margen del componente
     * @param place     ubicación dentro del espacio del grid
     * @param stretch   llenado dentro del espacio del grid
     */
    public static void addComp(Component component, Container container, Rectangle rectangle, double weightx, double weighty, Insets insets, int place, int stretch) {
        GridBagConstraints grid = grid(rectangle, insets, place, stretch);
        grid.weightx = weightx;
        grid.weighty = weighty;
        container.add(component, grid);
    }
    /**
     * Añade y distribuye el componente dentro del eje X de la interfaz gráfica
     * @param component {@link Component} a añadir dentro del {@link Container}
     * @param container {@link Container} al cual se añadirá el {@link Component}
     * @param rectangle posicionamiento del componente
     * @param weightx   dispersión al eje X
     * @param insets    margen del componente
     * @param place     ubicación dentro del espacio del grid
     * @param stretch   llenado dentro del espacio del grid
     */
    public static void addCompX(Component component, Container container, Rectangle rectangle, double weightx, Insets insets, int place, int stretch) {
        GridBagConstraints grid = grid(rectangle, insets, place, stretch);
        grid.weightx = weightx;
        container.add(component, grid);
    }
    /**
     * Distribución por defecto del grid
     * @param r       posicionamiento del componente
     * @param insets  margen dentro del espacio del grid
     * @param place   ubicación dentro del espacio del grid
     * @param stretch llenado dentro del espacio del grid
     * @return grid configurado por defecto
     */
    private static GridBagConstraints grid(Rectangle r, Insets insets, int place, int stretch) {
        GridBagConstraints grid = new GridBagConstraints();
        grid.gridx = r.x;
        grid.gridy = r.y;
        grid.gridwidth = r.width;
        grid.gridheight = r.height;
        grid.insets = insets;
        grid.anchor = place;
        grid.fill = stretch;
        return grid;
    }
}