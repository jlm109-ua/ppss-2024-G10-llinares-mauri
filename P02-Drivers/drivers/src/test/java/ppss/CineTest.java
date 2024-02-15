package ppss;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import ppss.ButacasException;
import ppss.Cine;

import java.util.stream.Stream;

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

        // ACT
        ButacasException exception = assertThrows(ButacasException.class,() -> cine.reservaButacas(asientos,solicitados));

        // ASSERT
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

    @ParameterizedTest(name = "reservaButacas_[{index}]: should be {0} when we want {1} and {2}")
    @MethodSource("casosDePrueba")
    @Tag("parametrizado")
    @DisplayName("reservaButacas_")
    void reservaButacasC5(boolean reserved, int solicitados, String info,boolean[] asientos){
        // ASSERT (Lo recibimos por los parámetros)

        // ACT
        boolean resultadoReal = cine.reservaButacas(asientos,solicitados);

        // ARRANGE
        assertEquals(reserved, resultadoReal);
    }

    private static Stream<Arguments> casosDePrueba() {
        return Stream.of(
                Arguments.of(false, 0, "fila has no seats",new boolean[]{}),
                Arguments.of(true, 2, "there are 2 free seats",new boolean[]{false,false,false,true,true}),
                Arguments.of(false, 1, "all seats are already reserved",new boolean[]{true,true,true})
        );
    }
}