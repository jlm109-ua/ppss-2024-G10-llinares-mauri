import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import ppss.ButacasException;
import ppss.Cine;

import static org.junit.jupiter.api.Assertions.*;

class CineTest {
    public Cine cine;

    @BeforeEach
    void setup(){
        this.cine = new Cine();
    }

    @Test
    void C1_reservaButacas_should_return_Exception_when_fila_empty_and_want_3(){
        // ARRANGE
        boolean[] asientos = {}; // Array vacío para el C1
        int solicitados = 3;

        // ACT (No hay en este caso)

        // ASSERT
        ButacasException exception = assertThrows(ButacasException.class,() -> cine.reservaButacas(asientos,solicitados));
        assertEquals("No se puede procesar la solicitud",exception.getMessage());
    }

    @Test
    void C2_reservaButacas_should_return_false_when_fila_empty_and_want_zero(){
        // ARRANGE
        boolean[] asientos = {};
        int solicitados = 0;
        boolean resultadoEsperado = false;
        boolean[] arrayVacio = {};

        // ACT
        boolean resultadoReal = cine.reservaButacas(asientos,solicitados);

        // ASSERT
        assertAll(
                () -> assertEquals(resultadoEsperado,resultadoReal),
                () -> assertArrayEquals(asientos,arrayVacio)
        );
    }

    @Test
    void C3_reservaButacas_should_return_true_when_fila_has_3_seats_free_and_want_2(){
        // ARRANGE
        boolean[] asientos = {false, false, false, true, true};
        int solicitados = 2;
        boolean resultadoEsperado = true;
        boolean[] asientosEsperados = {true, true, false, true, true};

        // ACT
        boolean resultadoReal = cine.reservaButacas(asientos,solicitados);

        // ASSERT
        assertAll(
                () -> assertEquals(resultadoEsperado,resultadoReal),
                () -> assertArrayEquals(asientosEsperados,asientos)
        );
    }

    @Test
    void C4_reservaButacas_should_return_false_when_no_free_seats_and_want_1(){
        // ARRANGE
        boolean[] asientos = {true, true, true};
        int solicitados = 1;
        boolean resultadoEsperado = false;
        boolean[] asientosEsperados = {true, true, true};

        // ACT
        boolean resultadoReal = cine.reservaButacas(asientos,solicitados);

        // ASSERT
        assertAll(
                () -> assertEquals(resultadoEsperado,resultadoReal),
                () -> assertArrayEquals(asientosEsperados,asientos)
        );
    }
}