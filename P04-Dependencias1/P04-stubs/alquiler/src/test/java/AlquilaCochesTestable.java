import java.time.LocalDate;

public class AlquilaCochesTestable extends AlquilaCoches {
    IService servicio;

    public AlquilaCochesTestable(IService servicio) {
        this.servicio = servicio;
    }

    @Override
    public IService getServicio() {
        return servicio;
    }

    public void setCalendario(Calendario calendario) {
        this.calendario = calendario;
    }
}