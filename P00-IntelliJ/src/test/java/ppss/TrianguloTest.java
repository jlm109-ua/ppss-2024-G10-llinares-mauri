package ppss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrianguloTest {
    Triangulo tri;

    @BeforeEach
    public void setup() {
        tri= new Triangulo();
    }

    @Test
    public void C1_tipo_triangulo_should_be_Equilatero_when_three_sides_are_equals() {
        //Preparamos los datos (Arrange)
        int a = 1;
        int b = 1;
        int c = 1;
        String resultadoEsperado = "Equilatero";
        //Ejecutamos (Act)
        String resultadoReal = tri.tipo_triangulo(a,b,c);
        //Verificamos el resultado (Assert)
        /* Aclaración: El método Assert.assertEquals devuelve cierto si el
           resultadoEsperado = resultadoReal */
        assertEquals(resultadoEsperado, resultadoReal);
    }

    @Test
    public void C2_tipo_triangulo_should_be_No_triangulo_when_sum_of_a_and_b_is_lower_than_c() {
        //Preparamos los datos (Arrange)
        int a = 1;
        int b = 1;
        int c = 11;
        String resultadoEsperado = "No es un triangulo";
        //Ejecutamos (Act)
        String resultadoReal = tri.tipo_triangulo(a,b,c);
        //Verificamos el resultado (Assert)
        assertEquals(resultadoEsperado, resultadoReal);
    }

    @Test
    public void C3_tipo_triangulo_should_be_c_Fuera_rango_when_c_0() {
        //Preparamos los datos (Arrange)
        int a = 1;
        int b = 2;
        int c = 0;
        String resultadoEsperado = "Valor c fuera del rango permitido";
        //Ejecutamos (Act)
        String resultadoReal = tri.tipo_triangulo(a,b,c);
        //Verificamos el resultado (Assert)
        assertEquals(resultadoEsperado, resultadoReal);
    }

    @Test
    public void C4_tipo_triangulo_should_be_Isosceles_when_last_two_sides_are_equals() {
        //Preparamos los datos (Arrange)
        int a = 14;
        int b = 10;
        int c = 10;
        String resultadoEsperado = "Isosceles";
        //Ejecutamos (Act)
        String resultadoReal = tri.tipo_triangulo(a,b,c);
        //Verificamos el resultado (Assert)
        assertEquals(resultadoEsperado, resultadoReal);
    }

    /**
     * Este nuevo test C5 carece de información válida para la prueba del SUT, pues, aunque cambien los valores
     * con los que se prueba el SUT, el camino independiente que se sigue en el grafo es el mismo y no aporta
     * valor nuevo al testeo de la unidad.
    @Test
    public void C5_tipo_triangulo_should_be_Equilatero_when_three_sides_are_equals() {
        // ARRANGE
        int a = 7, b = 7, c = 7;
        String resultadoEsperado = "Equilatero";
        // ACT
        String resultadoReal = tri.tipo_triangulo(a,b,c);
        // ASSERT
        assertEquals(resultadoEsperado,resultadoReal);
    }
    */

    /**
     * Con este nuevo test recorremos un nuevo camino independiente en la unidad. Este camino nuevo es
     * el que resulta de entrar al if que contiene la cadena "Valor a fuera del rango permitido".
     */
    @Test
    public void C6_tipo_triangulo_should_be_a_Fuera_rango_when_a_0() {
        // ARRANGE
        int a = -4;
        int b = 8;
        int c = 5;
        String resultadoEsperado = "Valor a fuera del rango permitido";
        // ACT
        String resultadoReal = tri.tipo_triangulo(a,b,c);
        // ASSERT
        assertEquals(resultadoEsperado,resultadoReal);
    }

    /**
     * Con este nuevo test recorremos un nuevo camino independiente en la unidad. Este camino nuevo es
     * el que resulta de entrar al if que contiene la cadena "Valor b fuera del rango permitido".
     */
    @Test
    public void C7_tipo_triangulo_should_be_b_Fuera_rango_when_b_0() {
        // ARRANGE
        int a = 9;
        int b = 0;
        int c = 5;
        String resultadoEsperado = "Valor b fuera del rango permitido";
        // ACT
        String resultadoReal = tri.tipo_triangulo(a,b,c);
        // ASSERT
        assertEquals(resultadoEsperado,resultadoReal);
    }
}
