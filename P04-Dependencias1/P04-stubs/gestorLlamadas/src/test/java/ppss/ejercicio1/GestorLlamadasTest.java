package ppss.ejercicio1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GestorLlamadasTest {
    @Test
    public void C1_sut_should_208_when_hora_15_and_minutos_10(){
        // ARRANGE
        GestorLlamadasStub sut = new GestorLlamadasStub();
        sut.setHora(15);
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
        sut.setHora(22);
        double resultadoEsperado = 105;

        // ACT
        double resultadoReal = sut.calculaConsumo(22);

        // ASSERT
        assertEquals(resultadoReal,resultadoEsperado);
    }
}