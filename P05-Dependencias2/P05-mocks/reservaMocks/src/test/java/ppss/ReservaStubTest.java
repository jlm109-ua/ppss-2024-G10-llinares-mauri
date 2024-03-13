package ppss;

import org.easymock.EasyMock;
import static org.junit.jupiter.api.Assertions.*;
import ppss.excepciones.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReservaStubTest {
    private Operacion stubOperacion;
    private FactoriaBOs stubFactoriaBOs;
    private Reserva reservaTestable;
    @BeforeEach
    public void setup() {
        stubOperacion = EasyMock.niceMock(Operacion.class);
        stubFactoriaBOs = EasyMock.niceMock(FactoriaBOs.class);
        reservaTestable = EasyMock.partialMockBuilder(Reserva.class).addMockedMethod("compruebaPermisos").mock();
        reservaTestable.setFd(stubFactoriaBOs);
    }

    @Test
    public void C1(){
        // ARRANGE
        String login = "xxxx";
        String password = "xxxx";
        String idSocio = "Pepe";
        String[] isbns = {"33333"};
        String resultadoEsperado = "ERROR de permisos; ";

        EasyMock.expect(reservaTestable.compruebaPermisos(login,password,Usuario.BIBLIOTECARIO)).andReturn(Boolean.FALSE);
        EasyMock.expect(stubFactoriaBOs.getOperacionBO()).andReturn(stubOperacion);

        // ACT
        EasyMock.replay(reservaTestable,stubFactoriaBOs,stubOperacion);

        ReservaException excepcion = assertThrows(ReservaException.class,()->reservaTestable.realizaReserva(login,password,idSocio,isbns));

        // ASSERT
        assertEquals(resultadoEsperado,excepcion.getMessage());
    }

    @Test
    public void C2(){
        // ARRANGE
        String login = "ppss";
        String password = "ppss";
        String idSocio = "Pepe";
        String[] isbns = {"22222","33333"};

        EasyMock.expect(reservaTestable.compruebaPermisos(login,password,Usuario.BIBLIOTECARIO)).andReturn(Boolean.TRUE);
        EasyMock.expect(stubFactoriaBOs.getOperacionBO()).andReturn(stubOperacion);
        assertDoesNotThrow(()->stubOperacion.operacionReserva(idSocio,isbns[0]));
        assertDoesNotThrow(()->stubOperacion.operacionReserva(idSocio,isbns[1]));

        // ACT
        EasyMock.replay(reservaTestable,stubFactoriaBOs,stubOperacion);

        // ASSERT
        assertDoesNotThrow(()->reservaTestable.realizaReserva(login,password,idSocio,isbns));
    }

    @Test
    public void C3(){
        // ARRANGE
        String login = "ppss";
        String password = "ppss";
        String idSocio = "Pepe";
        String[] isbns = {"11111","22222","55555"};
        String resultadoEsperado = "ISBN invalido:11111; ISBN invalido:55555; ";

        EasyMock.expect(reservaTestable.compruebaPermisos(login,password,Usuario.BIBLIOTECARIO)).andReturn(Boolean.TRUE);
        EasyMock.expect(stubFactoriaBOs.getOperacionBO()).andReturn(stubOperacion);
        assertDoesNotThrow(()->stubOperacion.operacionReserva(idSocio,isbns[0]));
        EasyMock.expectLastCall().andThrow(new IsbnInvalidoException());
        assertDoesNotThrow(()->stubOperacion.operacionReserva(idSocio,isbns[1]));
        EasyMock.expectLastCall().andVoid();
        assertDoesNotThrow(()->stubOperacion.operacionReserva(idSocio,isbns[2]));
        EasyMock.expectLastCall().andThrow(new IsbnInvalidoException());

        // ACT
        EasyMock.replay(reservaTestable,stubFactoriaBOs,stubOperacion);
        ReservaException excepcion = assertThrows(ReservaException.class,()->reservaTestable.realizaReserva(login,password,idSocio,isbns));

        // ASSERT
        assertEquals(resultadoEsperado,excepcion.getMessage());
    }

    @Test
    public void C4(){
        // ARRANGE
        String login = "ppss";
        String password = "ppss";
        String idSocio = "Luis";
        String[] isbns = {"22222"};
        String resultadoEsperado = "SOCIO invalido; ";

        EasyMock.expect(reservaTestable.compruebaPermisos(login,password,Usuario.BIBLIOTECARIO)).andReturn(Boolean.TRUE);
        EasyMock.expect(stubFactoriaBOs.getOperacionBO()).andReturn(stubOperacion);
        assertDoesNotThrow(()->stubOperacion.operacionReserva(login,isbns[0]));
        EasyMock.expectLastCall().andThrow(new SocioInvalidoException());

        // ACT
        EasyMock.replay(reservaTestable,stubFactoriaBOs,stubOperacion);
        ReservaException excepcion = assertThrows(ReservaException.class,()->reservaTestable.realizaReserva(login,password,idSocio,isbns));

        // ASSERT
        assertEquals(resultadoEsperado,excepcion.getMessage());
    }

    @Test
    public void C5(){
        // ARRANGE
        String login = "ppss";
        String password = "ppss";
        String idSocio = "Pepe";
        String[] isbns = {"11111","22222","33333"};
        String resultadoEsperado = "ISBN invalido:11111; CONEXION invalida; ";

        EasyMock.expect(reservaTestable.compruebaPermisos(login,password,Usuario.BIBLIOTECARIO)).andReturn(Boolean.TRUE);
        EasyMock.expect(stubFactoriaBOs.getOperacionBO()).andReturn(stubOperacion);
        assertDoesNotThrow(()->stubOperacion.operacionReserva(idSocio,isbns[0]));
        EasyMock.expectLastCall().andThrow(new IsbnInvalidoException());
        assertDoesNotThrow(()->stubOperacion.operacionReserva(idSocio,isbns[1]));
        EasyMock.expectLastCall().andVoid();
        assertDoesNotThrow(()->stubOperacion.operacionReserva(idSocio,isbns[2]));
        EasyMock.expectLastCall().andThrow(new JDBCException());

        // ACT
        EasyMock.replay(reservaTestable,stubFactoriaBOs,stubOperacion);
        ReservaException excepcion = assertThrows(ReservaException.class,()->reservaTestable.realizaReserva(login,password,idSocio,isbns));

        // ASSERT
        assertEquals(resultadoEsperado,excepcion.getMessage());
    }
}
