package ppss.ejercicio2;

public class GestorLlamadasStub extends GestorLlamadas {
    Calendario calendario;

    // Escogemos la opción 3: mediante setter podremos inyectar el doble.
    public void setCalendario(Calendario nuevoCalendario) {
        calendario = nuevoCalendario;
    }

    @Override
    public Calendario getCalendario() {
        return this.calendario;
    }
}
