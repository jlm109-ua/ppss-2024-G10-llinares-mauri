package ppss.ejercicio2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GestorLlamadasTest {
    @Test
    public void C1_sut_should_208_when_hora_15_and_minutos_10(){
        // ARRANGE
        GestorLlamadasStub sut = new GestorLlamadasStub();
        CalendarioStub doble = new CalendarioStub(15);
        sut.setCalendario(doble);
        double resultadoEsperado = 208;

        // ACT
        double resultadoReal = sut.calculaConsumo(10);

        // ASSERT
        assertEquals(resultadoReal,resultadoEsperado);
    }

    @Test
    public void C2_sut_should_105_when_hora_22_and_minutos_10(){
        // ARRANGE
        GestorLlamadasStub sut = new GestorLlamadasStub();
        CalendarioStub doble = new CalendarioStub(22);
        sut.setCalendario(doble);
        double resultadoEsperado = 105;

        // ACT
        double resultadoReal = sut.calculaConsumo(10);

        // ASSERT
        assertEquals(resultadoEsperado,resultadoReal);
    }
}