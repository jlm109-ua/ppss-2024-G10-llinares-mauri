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

        // ACT
        EasyMock.expect(reservaTestable.compruebaPermisos(login,password,Usuario.BIBLIOTECARIO)).andReturn(Boolean.TRUE);
        EasyMock.replay();

        ReservaException excepcion = assertThrows(ReservaException.class,()->reservaTestable.realizaReserva(login,password,idSocio,isbns));

        // ASSERT
        assertEquals(resultadoEsperado,excepcion.getMessage());
    }

    
}
