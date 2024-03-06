import java.time.LocalDate;
import java.util.List;

public class CalendarioStub extends Calendario {
    List<Integer> festivos; // Guardamos los días festivos para hacerlo lo más genérico posible.
    List<Integer> excepcion;

    public CalendarioStub(List<Integer> festivos, List<Integer> excepcion) {
        this.festivos = festivos;
        this.excepcion = excepcion;
    }

    public boolean es_festivo(LocalDate fecha) throws CalendarioException {
        int dia = fecha.getDayOfMonth();

        if (festivos.contains(dia))
            return true;
        else if (excepcion.contains(dia))
            throw new CalendarioException();
        else
            return false;
    }
}
