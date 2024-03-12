package ppss;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FicheroTextoTest {
    private IMocksControl ctrl;
    private FicheroTexto ficheroTextoTestable;
    private FileReader mock;

    @BeforeEach
    public void setup() {
        ctrl = EasyMock.createStrictControl();
        ficheroTextoTestable = EasyMock.partialMockBuilder(FicheroTexto.class).addMockedMethod("getFichero").mock(ctrl);
        mock = ctrl.mock(FileReader.class);
    }

    @Test
    public void C1(){
        // ARRANGE
        String fichero = "src/test/resources/ficheroC1.txt";
        String resultadoEsperado = fichero + " (Error al leer el archivo)";

        // ACT
        assertDoesNotThrow(()->EasyMock.expect(ficheroTextoTestable.getFichero(fichero)).andReturn(mock));
        assertDoesNotThrow(()->EasyMock.expect(mock.read()).andReturn((int) 'a').andReturn((int) 'b').andThrow(new IOException()));
        ctrl.replay();
        FicheroException excepcion = assertThrows(FicheroException.class,()->ficheroTextoTestable.contarCaracteres(fichero));

        // ASSERT
        assertEquals(resultadoEsperado,excepcion.getMessage());
        ctrl.verify();
    }

    @Test
    public void C2(){
        // ARRANGE
        String fichero = "src/test/resources/ficheroC2.txt";
        String resultadoEsperado = fichero + " (Error al cerrar el archivo)";

        // ACT
        assertDoesNotThrow(()->EasyMock.expect(ficheroTextoTestable.getFichero(fichero)).andReturn(mock));
        assertDoesNotThrow(()->EasyMock.expect(mock.read()).andReturn((int) 'a').andReturn((int) 'b').andReturn((int) 'c').andReturn(-1));
        assertDoesNotThrow(()->mock.close());
        EasyMock.expectLastCall().andThrow(new IOException()); // En la última llamada al mock hacemos que lance una IOException
        ctrl.replay();
        FicheroException excepcion = assertThrows(FicheroException.class,()->ficheroTextoTestable.contarCaracteres(fichero));

        // ASSERT
        assertEquals(resultadoEsperado,excepcion.getMessage());
        ctrl.verify();
    }
}