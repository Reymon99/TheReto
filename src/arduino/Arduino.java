package arduino;
import gui.Contenedor;
import gui.Welcome;
import panamahitek.Arduino.PanamaHitek_Arduino;//libreria disponible //
public class Arduino {
    private PanamaHitek_Arduino arduino;//libreria que maneja el arduino//
    private Welcome welcome;
    private boolean conexion;//el estado de conexion//
    private static Arduino instancia;
    private Arduino(Welcome welcome){
        this.welcome = welcome;
        this.arduino = new PanamaHitek_Arduino();
        this.conexion = false;
    }//constructor //
    public static Arduino getConexion(Welcome welcome) throws NullPointerException {
        if (instancia == null && welcome != null) instancia = new Arduino(welcome);
        if (instancia == null) throw new NullPointerException("instancia Arduino null");
        return instancia;
    }
    public static Arduino getConexion(){
        return getConexion(null);
    }
    public void conectarArduino(){//establece conexion por el puerto serie//
        if (arduino != null || conexion) {
            new Thread(() -> {//si existe un solo metodo lambda por que hay un solo proceso corriendo//
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
                } catch (ConexionArduinoException e) {//atrapa la excepcion
                    welcome.getConexion().setText(e.getMessage());
                    welcome.getPress().setText("Revise la conexión del arduino y reinicie el programa");
                    conexion = false;
                }
            }).start();//se ejecuta el hilo de la conexion mientras hace la conexion para que johan no lo bloquee
        }
    }
    private void conexion() throws ConexionArduinoException {//define el lanzamiento de la excepcion
        try {
            arduino.arduinoTX("COM3", 9600);//este es el puerto y la velocidad de la conexion a arduino//
            conexion = true;
        } catch (Exception e) {//captura el error
            conexion = false;
            throw new ConexionArduinoException("Error de Conexión.");//manda la excepcion
        }
    }
    public void sendDato(String data){//aqui se envia el dato al arduino//
        if (conexion && data != null && !data.isEmpty()) {
            try {
                arduino.sendData(data);
            } catch (Exception e) {//None
            }
        }
    }
    private void updateLabels(){
        welcome.getConexion().updateUI();
        welcome.getPress().updateUI();
        welcome.updateUI();
    }
    public boolean isConexion() {
        return conexion;
    }
    public void closeOnConexion(){//cierra la conexion entre el puerto y el arduino//
        ledOffAll();
        try {//mata la conexion //
            arduino.killArduinoConnection();
        } catch (Exception e) {//None
        }
    }//cierra la conexion//
    public void ledOnAll(){//enciende todos los leds//
        for (int i = 0; i < Contenedor.colors.size(); i++) {
            sendDato(Contenedor.colors.get(i).getLedOpen());
        }
    }
    public void ledOffAll(){//apaga todos los led del arduino//
        for (int i = 0; i < Contenedor.colors.size(); i++) {
            sendDato(Contenedor.colors.get(i).getLedClosed());
        }
    }
}