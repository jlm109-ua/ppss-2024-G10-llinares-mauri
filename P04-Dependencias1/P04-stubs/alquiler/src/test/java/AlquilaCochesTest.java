import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AlquilaCochesTest {
    @Test
    public void C1(){
        // ARRANGE
        ServicioStub servicioStub = new ServicioStub();
        CalendarioStub calendarioStub = new CalendarioStub();
        LocalDate fecha = LocalDate.of(2024, 5, 18);
        AlquilaCochesTestable sut = new AlquilaCochesTestable(servicioStub);
        sut.setCalendario(calendarioStub);
        Ticket resultadoEsperado = new Ticket();
        resultadoEsperado.setPrecio_final(75);

        // ACT
        Ticket resultadoReal = assertDoesNotThrow(()->sut.calculaPrecio(TipoCoche.TURISMO,fecha,10));

        // ASSERT
        assertEquals(resultadoEsperado.getPrecio_final()
                ,resultadoReal.getPrecio_final());
    }

    @Test
    public void C2(){
        // ARRANGE
        ServicioStub servicioStub = new ServicioStub();
        CalendarioStub calendarioStub = new CalendarioStub();
        LocalDate fecha = LocalDate.of(2024, 6, 19);
        AlquilaCochesTestable sut = new AlquilaCochesTestable(servicioStub);
        sut.setCalendario(calendarioStub);
        Ticket resultadoEsperado = new Ticket();
        resultadoEsperado.setPrecio_final((float) 62.5);

        // ACT
        Ticket resultadoReal = assertDoesNotThrow(()->sut.calculaPrecio(TipoCoche.CARAVANA,fecha,7));

        // ASSERT
        assertEquals(resultadoEsperado.getPrecio_final(),resultadoReal.getPrecio_final());
    }

    @Test
    public void C3(){
        // ARRANGE
        ServicioStub servicioStub = new ServicioStub();
        CalendarioStub calendarioStub = new CalendarioStub();
        LocalDate fecha = LocalDate.of(2024, 4, 17);
        AlquilaCochesTestable sut = new AlquilaCochesTestable(servicioStub);
        sut.setCalendario(calendarioStub);
        String resultadoEsperado = "Error en dia: 2024-04-18; Error en dia: 2024-04-21; Error en dia: 2024-04-22; ";

        // ACT
        MensajeException excepcion = assertThrows(MensajeException.class,()->sut.calculaPrecio(TipoCoche.TURISMO,fecha,8));

        // ASSERT
        assertEquals(resultadoEsperado,excepcion.getMessage());
    }
}