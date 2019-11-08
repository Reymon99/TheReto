package arduino;
import gui.Welcome;
public class Arduino {
    private String arduino;
    private Welcome welcome;
    private boolean conexion;
    private static Arduino instancia;
    private Arduino(Welcome welcome){
        this.welcome = welcome;
        this.arduino = null;
        this.conexion = false;
    }
    public static Arduino getConexion(Welcome welcome) throws NullPointerException {
        if (instancia == null && welcome != null) instancia = new Arduino(welcome);
        if (instancia == null) throw new NullPointerException("instancia Arduino null");
        return instancia;
    }
    public static Arduino getConexion(){
        return getConexion(null);
    }
    public String conectarArduino(){
        if (arduino == null || conexion) {
            new Thread(() -> {
                try {
                    String press = welcome.getPress().getText();
                    welcome.getPress().setText("");
                    welcome.getConexion().setText("Conectando Arduino...");
                    updateLabels();
                    conexion();
                    welcome.getConexion().setText("Conexión Exitosa.");
                    welcome.getPress().setText(press);
                    updateLabels();
                    conexion = true;
                } catch (ConexionArduinoException e) {
                    welcome.getConexion().setText(e.getMessage());
                    welcome.getPress().setText("Revise la conexión del arduino y reinicie el programa");
                    conexion = false;
                }
            }).start();
        }
        return arduino;
    }
    private void conexion() throws ConexionArduinoException {
        arduino = "Conectando";
        if (arduino.isEmpty()) throw new ConexionArduinoException("Error de Conexión.");
    }
    private void updateLabels(){
        welcome.getConexion().updateUI();
        welcome.getPress().updateUI();
        welcome.updateUI();
    }
    public boolean isConexion() {
        return conexion;
    }
}