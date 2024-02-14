package ppss;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;

import static org.junit.jupiter.api.Assertions.*;

class DataArrayTest {
    public DataArray da;

    @BeforeEach
    void setup() {
        this.da = new DataArray();
    }

    @Test
    void C1_delete_should_delete_5_and_return_3_when_deleting_5() {
        // ARRANGE
        int[] coleccion = {1,3,5,7};
        int[] coleccionEsperada = {1,3,7};
        int numElemEsperado = 3;

        // ACT
        try {
            this.da = new DataArray(coleccion);
            da.delete(5);
        } catch(DataException exception) {
            System.out.println("ERROR: " + exception.getMessage());
            Assertions.fail();
        }

        // ASSERT
        assertAll(
                () -> assertArrayEquals(coleccionEsperada,da.getColeccion()),
                () -> assertEquals(da.getColeccion().length,numElemEsperado)
        );
    }

    @Test
    void C2_delete_should_delete_3_and_return_4_when_deleting_3() {
        // ARRANGE
        int[] coleccion = {1,3,3,5,7};
        int[] coleccionEsperada = {1,3,5,7};
        int numElemEsperado = 4;

        // ACT
        try {
            this.da = new DataArray(coleccion);
            da.delete(3);
        } catch(DataException exception) {
            System.out.println("ERROR: " + exception.getMessage());
            Assertions.fail();
        }

        // ASSERT
        assertAll(
                () -> assertArrayEquals(coleccionEsperada,da.getColeccion()),
                () -> assertEquals(da.getColeccion().length,numElemEsperado)
        );
    }

    @Test
    void C3_delete_should_delete_4_and_return_9_when_deleting_4() {
        // ARRANGE
        int[] coleccion = {1,2,3,4,5,6,7,8,9,10};
        int[] coleccionEsperada = {1,2,3,5,6,7,8,9,10};
        int numElemEsperado = 9;

        // ACT
        try {
            this.da = new DataArray(coleccion);
            da.delete(4);
        } catch(DataException exception) {
            System.out.println("ERROR: " + exception.getMessage());
            Assertions.fail();
        }

        // ASSERT
        assertAll(
                () -> assertArrayEquals(coleccionEsperada,da.getColeccion()),
                () -> assertEquals(da.getColeccion().length,numElemEsperado)
        );
    }

    @Test
    void C4_delete_should_throw_dataexception_when_colection_is_empty() {
        // ARRANGE
        int[] coleccion = {};

        // ACT
        this.da = new DataArray(coleccion);
        DataException exception = assertThrows(DataException.class, () -> da.delete(8));

        // ASSERT
        assertEquals("No hay elementos en la colección",exception.getMessage());
    }

    @Test
    void C5_delete_should_throw_dataexception_when_element_is_less_than_zero() {
        // ARRANGE
        int[] coleccion = {1,3,5,7};

        // ACT
        this.da = new DataArray(coleccion);
        DataException exception = assertThrows(DataException.class, () -> da.delete(-5));

        // ASSERT
        assertEquals("El valor a borrar debe ser > 0",exception.getMessage());
    }

    @Test
    void C6_delete_should_throw_dataexception_when_colection_is_empty_and_element_is_equal_to_zero() {
        // ARRANGE
        int[] coleccion = {};

        // ACT
        this.da = new DataArray(coleccion);
        DataException exception = assertThrows(DataException.class, () -> da.delete(0));

        // ASSERT
        assertEquals("Colección vacía. Y el valor a borrar debe ser > 0",exception.getMessage());
    }

    @Test
    void C7_delete_should_throw_dataexception_when_element_is_not_in_collection() {
        // ARRANGE
        int[] coleccion = {1,3,5,7};

        // ACT
        this.da = new DataArray(coleccion);
        DataException exception = assertThrows(DataException.class, () -> da.delete(4));

        // ASSERT
        assertEquals("Elemento no encontrado",exception.getMessage());
    }
}