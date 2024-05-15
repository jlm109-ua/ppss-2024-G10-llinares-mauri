import ejercicio1.MultipathExample;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MultipathExampleTest {
    int a, b, c;
    MultipathExample mp;

    @BeforeEach
    public void setup(){
        a = 0;
        b = 0;
        c = 0;
    }

    @Test
    public void test1(){
        a = 1;
        b = 1;

        mp.multiPath1(a,b,c);
    }

    @Test
    public void test2(){
        a = 5;
        b = 5;

        mp.multiPath1(a,b,c);
    }
}
