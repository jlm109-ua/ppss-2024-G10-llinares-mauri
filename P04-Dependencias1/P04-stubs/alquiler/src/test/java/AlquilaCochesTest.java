import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AlquilaCochesTest {
    @Test
    public void C1(){
        // ARRANGE
        ServicioStub stub = new ServicioStub();
        stub.setTipo(TipoCoche.TURISMO);
        LocalDate fecha = LocalDate.of(2024, 5, 18);
        stub.setInicio(fecha);
        stub.setDias(10);
        AlquilaCoches sut = new AlquilaCoches();
        Ticket resultadoEsperado = new Ticket();
        resultadoEsperado.setPrecio_final(75);

        // ACT
        Ticket resultadoReal = assertDoesNotThrow(()->sut.calculaPrecio(stub));

        // ASSERT
        assertEquals(resultadoEsperado,resultadoReal);
    }

    @Test
    public void C2(){
        // ARRANGE
        ServicioStub stub = new ServicioStub();
        stub.setTipo(TipoCoche.CARAVANA);
        LocalDate fecha = LocalDate.of(2024, 6, 19);
        stub.setInicio(fecha);
        stub.setDias(7);
        AlquilaCoches sut = new AlquilaCoches();
        Ticket resultadoEsperado = new Ticket();
        resultadoEsperado.setPrecio_final((float) 62.5);

        // ACT
        Ticket resultadoReal = assertDoesNotThrow(()->sut.calculaPrecio(stub));

        // ASSERT
        assertEquals(resultadoEsperado,resultadoReal);
    }

    @Test
    public void C3(){
        // ARRANGE
        ServicioStub stub = new ServicioStub();
        stub.setTipo(TipoCoche.TURISMO);
        LocalDate fecha = LocalDate.of(2024, 4, 17);
        stub.setInicio(fecha);
        stub.setDias(8);
        AlquilaCoches sut = new AlquilaCoches();
        String resultadoEsperado = "Error en dia: 2024-04-18; Error en dia: 2024-04-21; Error en dia: 2024-04-22;";

        // ACT
        MensajeException excepcion = assertThrows(MensajeException.class,()->sut.calculaPrecio(stub));

        // ASSERT
        assertEquals(resultadoEsperado,excepcion.getMessage());
    }
}