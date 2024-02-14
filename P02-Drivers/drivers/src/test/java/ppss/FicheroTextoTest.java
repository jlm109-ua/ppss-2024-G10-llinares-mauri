package ppss;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FicheroTextoTest {
    public FicheroTexto ft;

    @BeforeEach
    void setup() {
        this.ft = new FicheroTexto();
    }

    @Test
    void C1_contarCaracteres_should_return_Exception_when_file_does_not_exist() {
        // ARRANGE
        String nombreFichero = "ficheroC1.txt";

        // ACT
        FicheroException exception = assertThrows(FicheroException.class, () -> ft.contarCaracteres(nombreFichero));

        // ASSERT
        assertEquals("ficheroC1.txt (No existe el archivo o el directorio)",exception.getMessage());
    }

    @Test
    void C2_contarCaracteres_should_return_3_when_file_has_3_chars() {
        // ARRANGE
        String nombreFichero = "ficheroCorrecto.txt";
        int resultadoEsperado = 3;
        int resultadoReal = 0;

        // ACT
        try {
            resultadoReal = ft.contarCaracteres(nombreFichero);
        } catch(FicheroException exception) {
            System.out.println("ERROR: " + exception.getMessage());
            Assertions.fail(); // Si lanza una excepción es que ha fallado algo.
        }

        // ASSERT
        assertEquals(resultadoEsperado,resultadoReal);
    }

    @Test
    @Tag("excluido")
    void C3_contarCaracteres_should_return_Exception_when_file_cannot_be_read() {
        // ARRANGE
        String nombreFichero = "ficheroC3.txt";

        // ACT

        // ASSERT
        Assertions.fail();
    }

    @Test
    @Tag("excluido")
    void C4_contarCaracteres_should_return_Exception_when_file_cannot_be_closed() {
        // ARRANGE
        String nombreFichero = "ficheroC4.txt";

        // ACT

        // ASSERT
        Assertions.fail();
    }
}