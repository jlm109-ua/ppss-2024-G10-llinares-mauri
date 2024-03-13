package ppss;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ppss.excepciones.IsbnInvalidoException;
import ppss.excepciones.JDBCException;
import ppss.excepciones.ReservaException;
import ppss.excepciones.SocioInvalidoException;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

class ReservaMockTest {
    private IMocksControl ctrl;
    private Operacion mockOperacion;
    private FactoriaBOs mockFactoria;
    private Reserva reservaTestable;

    @BeforeEach
    public void setup(){
        ctrl = EasyMock.createStrictControl();
        mockFactoria = ctrl.createMock(FactoriaBOs.class);
        mockOperacion = ctrl.createMock(Operacion.class);
        reservaTestable = EasyMock.partialMockBuilder(Reserva.class).addMockedMethod("compruebaPermisos").mock(ctrl);
        reservaTestable.setFd(mockFactoria); // Inyectamos el mock
    }

    @Test
    void C1(){
        // ARRANGE
        String login = "xxxx";
        String password = "xxxx";
        String idSocio = "Pepe";
        String[] isbns = {"33333"};
        String resultadoEsperado = "ERROR de permisos; ";

        EasyMock.expect(reservaTestable.compruebaPermisos(login,password,Usuario.BIBLIOTECARIO)).andReturn(Boolean.FALSE);

        // ACT
        ctrl.replay();

        // ASSERT
        ReservaException excepcion = assertThrows(ReservaException.class,()->reservaTestable.realizaReserva(login,password,idSocio,isbns));
        assertEquals(resultadoEsperado,excepcion.getMessage());
        ctrl.verify();
    }

    @Test
    void C2(){
        // ARRANGE
        String login = "ppss";
        String password = "ppss";
        String idSocio = "Pepe";
        String[] isbns = {"22222","33333"};

        assertDoesNotThrow(()->EasyMock.expect(reservaTestable.compruebaPermisos(login,password,Usuario.BIBLIOTECARIO)).andReturn(Boolean.TRUE));
        assertDoesNotThrow(()->EasyMock.expect(mockFactoria.getOperacionBO()).andReturn(mockOperacion));
        assertDoesNotThrow(()->mockOperacion.operacionReserva(idSocio,isbns[0]));
        EasyMock.expectLastCall().andVoid();
        assertDoesNotThrow(()->mockOperacion.operacionReserva(idSocio,isbns[1]));

        // LA SIGUIENTE MANERA DE IMPLEMENTARLO DA ERROR
        // assertDoesNotThrow(()->EasyMock.expect(mockOperacion.operacionReserva(idSocio,isbns[0])));
        // assertDoesNotThrow(()->EasyMock.expectLastCall(mockOperacion.operacionReserva(idSocio,isbns[1])));

        // ACT
        ctrl.replay();

        // ASSERT
        assertDoesNotThrow(()->reservaTestable.realizaReserva(login,password,idSocio,isbns));
        ctrl.verify();
    }

    @Test
    void C3(){
        // ARRANGE
        String login = "ppss";
        String password = "ppss";
        String idSocio = "Pepe";
        String[] isbns = {"11111","22222","55555"};
        String resultadoEsperado = "ISBN invalido:11111; ISBN invalido:55555; ";

        EasyMock.expect(reservaTestable.compruebaPermisos(login,password,Usuario.BIBLIOTECARIO)).andReturn(Boolean.TRUE);
        assertDoesNotThrow(()->EasyMock.expect(mockFactoria.getOperacionBO()).andReturn(mockOperacion));
        assertDoesNotThrow(()->mockOperacion.operacionReserva(idSocio,isbns[0]));
        EasyMock.expectLastCall().andThrow(new IsbnInvalidoException());
        assertDoesNotThrow(()->mockOperacion.operacionReserva(idSocio,isbns[1]));
        EasyMock.expectLastCall().andVoid();
        assertDoesNotThrow(()->mockOperacion.operacionReserva(idSocio,isbns[2]));
        EasyMock.expectLastCall().andThrow(new IsbnInvalidoException());

        // ACT
        ctrl.replay();
        ReservaException excepcion = assertThrows(ReservaException.class,()->reservaTestable.realizaReserva(login,password,idSocio,isbns));

        // ASSERT
        assertEquals(resultadoEsperado,excepcion.getMessage());
        ctrl.verify();
    }

    @Test
    void C4(){
        // ARRANGE
        String login = "ppss";
        String password = "ppss";
        String idSocio = "Luis";
        String[] isbns = {"22222"};
        String resultadoEsperado = "SOCIO invalido; ";

        EasyMock.expect(reservaTestable.compruebaPermisos(login,password,Usuario.BIBLIOTECARIO)).andReturn(Boolean.TRUE);
        assertDoesNotThrow(()->EasyMock.expect(mockFactoria.getOperacionBO()).andReturn(mockOperacion));
        assertDoesNotThrow(()->mockOperacion.operacionReserva(idSocio,isbns[0]));
        EasyMock.expectLastCall().andThrow(new SocioInvalidoException());

        // ACT
        ctrl.replay();
        ReservaException excepcion = assertThrows(ReservaException.class,()->reservaTestable.realizaReserva(login,password,idSocio,isbns));

        // ASSERT
        assertEquals(resultadoEsperado,excepcion.getMessage());
        ctrl.verify();
    }

    @Test
    void C5(){
        // ARRANGE
        String login = "ppss";
        String password = "ppss";
        String idSocio = "Pepe";
        String[] isbns = {"11111","22222","33333"};
        String resultadoEsperado = "ISBN invalido:11111; CONEXION invalida; ";

        EasyMock.expect(reservaTestable.compruebaPermisos(login,password,Usuario.BIBLIOTECARIO)).andReturn(Boolean.TRUE);
        assertDoesNotThrow(()->EasyMock.expect(mockFactoria.getOperacionBO()).andReturn(mockOperacion));
        assertDoesNotThrow(()->mockOperacion.operacionReserva(idSocio,isbns[0]));
        EasyMock.expectLastCall().andThrow(new IsbnInvalidoException());
        assertDoesNotThrow(()->mockOperacion.operacionReserva(idSocio,isbns[1]));
        EasyMock.expectLastCall().andVoid();
        assertDoesNotThrow(()->mockOperacion.operacionReserva(idSocio,isbns[2]));
        EasyMock.expectLastCall().andThrow(new JDBCException());

        // ACT
        ctrl.replay();
        ReservaException excepcion = assertThrows(ReservaException.class,()->reservaTestable.realizaReserva(login,password,idSocio,isbns));

        // ASSERT
        assertEquals(resultadoEsperado,excepcion.getMessage());
        ctrl.verify();
    }
}