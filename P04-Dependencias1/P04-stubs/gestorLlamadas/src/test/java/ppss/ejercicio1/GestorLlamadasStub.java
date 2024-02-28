package ppss.ejercicio1;

import static org.junit.jupiter.api.Assertions.*;

class GestorLlamadasStub extends GestorLlamadas {
    int hora;

    // Escogemos la opción 1 para realizar este ejercicio.
    public void setHora(int hora){
        this.hora = hora;
    }

    @Override
    public int getHoraActual(){
        return hora;
    }
}