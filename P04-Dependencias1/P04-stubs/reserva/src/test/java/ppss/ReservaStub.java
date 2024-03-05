package ppss;

import ppss.excepciones.ReservaException;

public class ReservaStub extends Reserva {
    @Override
    public boolean compruebaPermisos(String login, String password, Usuario tipoUsu) {
        if (login.equals("ppss") && password.equals("ppss"))
            return true;
        return false;
    }

    @Override
    public void realizaReserva(String login, String password, String socio, String[] isbns) throws ReservaException {
        super.realizaReserva(login, password, socio, isbns);
    }
}
