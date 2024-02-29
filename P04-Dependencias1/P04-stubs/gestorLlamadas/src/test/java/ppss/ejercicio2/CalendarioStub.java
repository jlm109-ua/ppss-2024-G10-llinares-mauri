package ppss.ejercicio2;

public class CalendarioStub extends Calendario {
    int hora;

    public CalendarioStub(int hora) {
        this.hora = hora;
    };

    @Override
    public int getHoraActual() {
        return hora;
    }
}
