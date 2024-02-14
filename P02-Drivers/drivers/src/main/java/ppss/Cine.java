package ppss;

public class Cine {
    public boolean reservaButacas(boolean[] asientos, int solicitados) /*throws ButacasException*/ {
        boolean reserva = false;
        int j=0;
        int sitiosLibres =0;
        int primerLibre = 0;

        while ((j < asientos.length) && (sitiosLibres < solicitados)) {
            if (!asientos[j]) {
                sitiosLibres++;
            } else {
                sitiosLibres = 0;
            }
            j++;
        }
        if (sitiosLibres == solicitados && solicitados != 0) {
            reserva = true;
            for (int k=primerLibre; k<(primerLibre+solicitados); k++) {
                asientos[k] = true;
            }
        }

        return reserva;
    }
}
