import ejercicio1.MultipathExample;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}
