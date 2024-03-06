package ppss;

import ppss.excepciones.IsbnInvalidoException;
import ppss.excepciones.JDBCException;
import ppss.excepciones.SocioInvalidoException;

import java.util.List;

public class OperacionStub extends Operacion {
    String socioInvalido;
    List<String> isbnInvalidos;
    List<String> jdbcException; // Aquí guardaremos el isbn que lance una JDBCException

    public OperacionStub(String socioInvalido, List<String> isbnInvalidos, List<String> jdbcException) {
        this.socioInvalido = socioInvalido;
        this.isbnInvalidos = isbnInvalidos;
        this.jdbcException = jdbcException;
    }

    @Override
    public void operacionReserva(String socio, String isbn) throws IsbnInvalidoException, JDBCException, SocioInvalidoException {
        if (socioInvalido.equals(socio))
            throw new SocioInvalidoException();
        else if (isbnInvalidos.contains(isbn))
            throw new IsbnInvalidoException();
        else if (jdbcException.contains(isbn))
            throw new JDBCException();
    }
}
