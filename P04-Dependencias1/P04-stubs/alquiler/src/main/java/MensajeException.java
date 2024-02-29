public class MensajeException extends Exception{
    String mensaje;

    public MensajeException(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage() {
        return mensaje;
    }
}
