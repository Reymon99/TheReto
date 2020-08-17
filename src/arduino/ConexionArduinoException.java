package arduino;
public class ConexionArduinoException extends Exception {
    private String message;
    public ConexionArduinoException(String message) {
        this.message = message;
    }
    @Override
   public String getMessage() {
        return message;
    }//justificar el estado de la conexion
}