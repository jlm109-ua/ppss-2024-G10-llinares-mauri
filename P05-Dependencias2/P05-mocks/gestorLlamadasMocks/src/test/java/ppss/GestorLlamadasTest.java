package ppss;

import static org.junit.jupiter.api.Assertions.*;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GestorLlamadasTest {
    private IMocksControl ctrl;
    private Calendario mock;
    private GestorLlamadas gestorLlamadasTestable;

    @BeforeEach
    public void setup() {
        ctrl = EasyMock.createStrictControl(); // StrictMock para controlar todos los aspectos de la SUT.
        mock = ctrl.mock(Calendario.class); // Creamos el mock de la dependencia externa.
        gestorLlamadasTestable = EasyMock.partialMockBuilder(GestorLlamadas.class).addMockedMethod("getCalendario").mock(ctrl); // Creamos el mock parcial para la dependencia de nuestra misma clase y lo añadimos al mock de control.
    }

    @Test
    public void C1_should_457_6_when_minutos_22_and_hora_10(){
        // ARRANGE
        int minutos = 22;
        int hora = 10;
        double resultadoEsperado = 457.6;
        EasyMock.expect(gestorLlamadasTestable.getCalendario()).andReturn(mock); // Tenemos que inyectar el doble en "getCalendario()". Comprobamos que esta dependencia devuelve el doble.
        EasyMock.expect(mock.getHoraActual()).andReturn(hora); // Necesitamos que cuando se llame a "getHoraActual()" se devuelva la hora del test.
        ctrl.replay(); // Ya está listo para pasar el test.

        // ACT
        double resultadoReal = assertDoesNotThrow(()->gestorLlamadasTestable.calculaConsumo(minutos));

        // ASSERT
        assertEquals(resultadoEsperado,resultadoReal);
    }

    @Test
    public void C2_should_136_5_when_minutos_13_and_hora_21(){
        // ARRANGE
        int minutos = 13;
        int hora = 21;
        double resultadoEsperado = 136.5;
        EasyMock.expect(gestorLlamadasTestable.getCalendario()).andReturn(mock);
        EasyMock.expect(mock.getHoraActual()).andReturn(hora);
        ctrl.replay();

        // ACT
        double resultadoReal = assertDoesNotThrow(()->gestorLlamadasTestable.calculaConsumo(minutos));

        // ASSERT
        assertEquals(resultadoEsperado,resultadoReal);
    }
}