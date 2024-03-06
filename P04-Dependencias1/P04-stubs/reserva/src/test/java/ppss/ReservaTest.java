package ppss;

import org.junit.jupiter.api.Test;
import ppss.excepciones.JDBCException;
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
        ReservaTestable sut = new ReservaTestable();
        Factoria factoria = new Factoria();
        OperacionStub operacionStub = new OperacionStub();
        factoria.setOperacion(operacionStub);
        sut.setFactoria(factoria);

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
        ReservaTestable sut = new ReservaTestable();
        Factoria factoria = new Factoria();
        OperacionStub operacionStub = new OperacionStub();
        factoria.setOperacion(operacionStub);
        sut.setFactoria(factoria);

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
        String resultadoEsperado = "ISBN invalido:33333; ISBN invalido:44444; ";
        ReservaTestable sut = new ReservaTestable();
        Factoria factoria = new Factoria();
        OperacionStub operacionStub = new OperacionStub();
        factoria.setOperacion(operacionStub);
        sut.setFactoria(factoria);

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
        String resultadoEsperado = "SOCIO invalido; ";
        ReservaTestable sut = new ReservaTestable();
        Factoria factoria = new Factoria();
        OperacionStub operacionStub = new OperacionStub();
        factoria.setOperacion(operacionStub);
        sut.setFactoria(factoria);

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
        String resultadoEsperado = "CONEXION invalida; ";
        ReservaTestable sut = new ReservaTestable();
        Factoria factoria = new Factoria();
        OperacionStub operacionStub = new OperacionStub();
        factoria.setOperacion(operacionStub);
        sut.setFactoria(factoria);

        // ACT
        JDBCException excepcion = assertThrows(JDBCException.class, ()->sut.realizaReserva(login,password,socio,isbns));

        // ASSERT
        assertEquals(resultadoEsperado,excepcion.getMessage());
    }
}