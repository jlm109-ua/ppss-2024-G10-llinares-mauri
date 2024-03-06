package ppss;

public class ReservaTestable extends Reserva {
    public void setFactoria(Factoria factoria) {
        this.factoria = factoria;
    }

    @Override
    public boolean compruebaPermisos(String login, String password, Usuario tipoUsu) {
        return login.equals("ppss") && password.equals("ppss");
    }
}
