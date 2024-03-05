package ppss;

import org.junit.jupiter.api.Test;
import ppss.excepciones.ReservaException;

import static org.junit.jupiter.api.Assertions.*;

class ReservaTest {
    @Test
    public void C1(){
        // ARRANGE
        String login = "xxxx";
        String password = "xxxx";
        String socio = "Luis";
        String[] isbns = {"11111"};
        String resultadoEsperado = "ERROR de permisos; ";
        ReservaStub sut = new ReservaStub();

        // ACT
        ReservaException excepcion = assertThrows(ReservaException.class, ()->sut.realizaReserva(login,password,socio,isbns));

        // ASSERT
        assertEquals(resultadoEsperado,excepcion.getMessage());
    }

    @Test
    public void C2(){
        // ARRANGE
        String login = "ppss";
        String password = "ppss";
        String socio = "Luis";
        String[] isbns = {"11111","22222"};
        ReservaStub sut = new ReservaStub();

        // ACT & ASSERT
        assertDoesNotThrow(()->sut.realizaReserva(login,password,socio,isbns));
    }

    @Test
    public void C3(){
        // ARRANGE
        String login = "ppss";
        String password = "ppss";
        String socio = "Luis";
        String[] isbns = {"11111","33333","44444"};
        String resultadoEsperado = "ISBN invalido:33333; ISBN invalido:44444;";
        ReservaStub sut = new ReservaStub();

        // ACT
        ReservaException excepcion = assertThrows(ReservaException.class, ()->sut.realizaReserva(login,password,socio,isbns));

        // ASSERT
        assertEquals(resultadoEsperado,excepcion.getMessage());
    }

    @Test
    public void C4(){
        // ARRANGE
        String login = "ppss";
        String password = "ppss";
        String socio = "Pepe";
        String[] isbns = {"11111"};
        String resultadoEsperado = "SOCIO invalido;";
        ReservaStub sut = new ReservaStub();

        // ACT
        ReservaException excepcion = assertThrows(ReservaException.class, ()->sut.realizaReserva(login,password,socio,isbns));

        // ASSERT
        assertEquals(resultadoEsperado,excepcion.getMessage());
    }

    @Test
    public void C5(){
        // ARRANGE
        String login = "ppss";
        String password = "ppss";
        String socio = "Luis";
        String[] isbns = {"11111"};
        String resultadoEsperado = "CONEXION invalida;";
        ReservaStub sut = new ReservaStub();

        // ACT
        ReservaException excepcion = assertThrows(ReservaException.class, ()->sut.realizaReserva(login,password,socio,isbns));

        // ASSERT
        assertEquals(resultadoEsperado,excepcion.getMessage());
    }
}