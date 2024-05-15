package ejercicio1;

import ejercicio1.MultipathExample;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultipathExampleTest {
    int a, b, c;
    MultipathExample mp;

    @BeforeEach
    public void setup(){
        a = 0;
        b = 0;
        c = 0;

        mp = new MultipathExample();
    }

    @Test
    public void test1(){
        a = 1;
        b = 1;
        c = 0;

        assertEquals(0,mp.multiPath1(a,b,c));
    }

    @Test
    public void test2(){
        a = 6;
        b = 6;
        c = 0;

        assertEquals(12,mp.multiPath1(a,b,c));
    }

    @Test
    public void test3(){
        a=3;
        b=6;
        c=2;

        assertEquals(8,mp.multiPath1(a,b,c));
    }

    @ParameterizedTest()
    @Tag("d)")
    @MethodSource("d")
    public void testD(int a, int b, int c, int resultadoEsperado){
        assertEquals(resultadoEsperado,mp.multiPath2(a,b,c));
    }

    private static Stream<Arguments> d(){
        return Stream.of(
            Arguments.of(5, 5, 5, 5),  // No ifs
            Arguments.of(6, 4, 4, 4), // 2 ifs
            Arguments.of(6, 5, 4, 4)
        );
    }
}
