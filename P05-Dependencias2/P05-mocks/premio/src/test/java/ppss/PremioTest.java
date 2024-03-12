package ppss;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;

import static org.junit.jupiter.api.Assertions.*;

class PremioTest {
    private IMocksControl ctrl;
    private Premio premioTestable;
    private ClienteWebService mock;

    @BeforeEach
    public void setup() {
        ctrl = EasyMock.createStrictControl();
        mock = ctrl.mock(ClienteWebService.class);
        premioTestable = EasyMock.partialMockBuilder(Premio.class).addMockedMethod("generaNumero").mock(ctrl);
        premioTestable.cliente = mock; // Inyectamos el doble
    }

    @Test
    public void C1_A() {
        // ARRANGE
        float numero = (float) 0.07;
        String premio = "entrada final Champions";
        String resultadoEsperado = "Premiado con entrada final Champions";

        // ES PARTE DE ARRANGE O DE ACT ???
        EasyMock.expect(premioTestable.generaNumero()).andReturn(numero);
        assertDoesNotThrow(()->EasyMock.expect(mock.obtenerPremio()).andReturn(premio));
        ctrl.replay();

        // ACT
        String resultadoReal = assertDoesNotThrow(()->premioTestable.compruebaPremio());

        // ASSERT
        assertEquals(resultadoEsperado, resultadoReal);
        ctrl.verify();
    }

    @Test
    public void C2_B(){
        // ARRANGE
        float numero = (float) 0.03;
        String resultadoEsperado = "No se ha podido obtener el premio";

        // ACT
        EasyMock.expect(premioTestable.generaNumero()).andReturn(numero);
        assertDoesNotThrow(()->EasyMock.expect(mock.obtenerPremio()).andThrow(new ClienteWebServiceException()));
        ctrl.replay();
        String resultadoReal = premioTestable.compruebaPremio();

        // ASSERT
        assertEquals(resultadoEsperado,resultadoReal);
        ctrl.verify();
    }

    @Test
    public void C3_C(){
        // ARRANGE
        float numero = 0.3f;
        String resultadoEsperado = "Sin premio";

        // ACT
        EasyMock.expect(premioTestable.generaNumero()).andReturn(numero);
        ctrl.replay();
        String resultadoReal = premioTestable.compruebaPremio();

        // ASSERT
        assertEquals(resultadoEsperado,resultadoReal);
        ctrl.verify();
    }
}