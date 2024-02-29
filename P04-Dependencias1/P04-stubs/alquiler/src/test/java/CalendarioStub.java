import java.time.LocalDate;

public class CalendarioStub extends Calendario {
    public boolean es_festivo(LocalDate fecha) throws CalendarioException {
        int dia = fecha.getDayOfMonth();
        int mes = fecha.getMonthValue();

        if (mes == 6 && (dia == 20 || dia == 24)) {
            return true;
        } else if (mes == 4 && (dia == 18 || dia == 21 || dia == 22)) {
            throw new CalendarioException();
        } else {
            return false;
        }
    }
}
