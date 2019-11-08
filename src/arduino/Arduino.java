package arduino;
import gui.Welcome;
public class Arduino {
    private String arduino;
    private Welcome welcome;
    private static Arduino instancia;
    private Arduino(Welcome welcome){
        this.welcome = welcome;
        this.arduino = "Conectando";
    }
    public static Arduino getConexion(Welcome welcome){
        if (instancia == null) instancia = new Arduino(welcome);
        return instancia;
    }
    public static Arduino getConexion(){
        return getConexion(new Welcome());
    }
}