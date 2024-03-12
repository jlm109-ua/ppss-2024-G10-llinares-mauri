package ppss;

public class GestorLlamadasTestable extends  GestorLlamadas{
    protected Calendario calendario = new Calendario();

    @Override
    public Calendario getCalendario() {
        return calendario;
    }
}
