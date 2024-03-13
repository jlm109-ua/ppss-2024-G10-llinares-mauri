package ppss;

import java.util.ArrayList;
import ppss.excepciones.*;

public class Reserva {
    private FactoriaBOs fd;

    public void setFd(FactoriaBOs fd) {
        this.fd = fd;
    }

    public FactoriaBOs getFactoriaBO(){
        return this.fd;
    }

    public boolean compruebaPermisos(String login, String password, Usuario tipoUsu) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void realizaREserva(String login, String password, String socio, String [] isbns) throws ReservaException {
        ArrayList<String> errores = new ArrayList<String>();
        if(!compruebaPermisos(login, password, Usuario.BIBLIOTECARIO)) { // Dependencia externa -> partial mock
            errores.add("ERROR de permisos");
        } else {
            //FactoriaBOs fd = new FactoriaBOs(); // Refactorizamos con set y get
            fd = getFactoriaBO();
            IOperacionBO io = fd.getOperacionBO(); // Dependencia externa -> mockFactoria
            try {
                for(String isbn: isbns) {
                    try {
                        io.operacionReserva(socio,isbn); // Dependencia externa -> mockOperacion
                    } catch (IsbnInvalidoException iie) {
                        errores.add("ISBN invalido" + ":" + isbn);
                    }
                }
            } catch (SocioInvalidoException sie) {
                errores.add("SOCIO invalido");
            } catch (JDBCException je) {
                errores.add("CONEXION invalida");
            }
            if (!errores.isEmpty()) {
                String mensajeError = "";
                for(String error: errores) {
                    mensajeError += error + "; ";
                }
                throw new ReservaException(mensajeError);
            }
        }
    }
}