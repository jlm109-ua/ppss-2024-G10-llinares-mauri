package ppss;

import ppss.excepciones.IsbnInvalidoException;
import ppss.excepciones.JDBCException;
import ppss.excepciones.SocioInvalidoException;

public class OperacionStub extends Operacion {
    @Override
    public void operacionReserva(String socio, String isbn) throws IsbnInvalidoException, JDBCException, SocioInvalidoException {
        if (socio.equals("Pepe")) {
            throw new SocioInvalidoException();
        } else if (isbn.equals("33333") || isbn.equals("44444")) {
            throw new IsbnInvalidoException();
        } /** else if (socio.equals("Luis") && isbn.equals("22222")) {
            throw new JDBCException(); // ¿CÓMO?
        }*/
    }
}
