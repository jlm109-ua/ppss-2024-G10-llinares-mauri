import java.time.LocalDate;

public class AlquilaCoches {
    protected Calendario calendario = new Calendario();

    // FACTORÍA LOCAL !!
    public IService getServicio() {
        return new Servicio();
    }

    public Ticket calculaPrecio(TipoCoche tipo, LocalDate inicio, int ndias) throws MensajeException {
        Ticket ticket = new Ticket();
        float precioDia,precioTotal =0.0f;
        float porcentaje = 0.25f;

        String observaciones = "";
        precioDia = getServicio().consultaPrecio(tipo);
        for (int i=0; i<ndias;i++) {
            LocalDate otroDia = inicio.plusDays((long)i);
            try {
                if (calendario.es_festivo(otroDia)) {
                    precioTotal += (1+ porcentaje)*precioDia;
                } else {
                    precioTotal += (1- porcentaje)*precioDia;
                }
            } catch (CalendarioException ex) {
                observaciones += "Error en dia: "+otroDia+"; ";
            }
        }

        if (observaciones.length()>0) {
            throw new MensajeException(observaciones);
        }

        ticket.setPrecio_final(precioTotal);
        return ticket;
    }

    /** MAL: El enunciado dice de NO añadir parámetros ni CAMBIAR la manera en la que otras unidades llaman a esta.
    public Ticket calculaPrecio(Servicio servicio1) throws MensajeException {
        Ticket ticket = new Ticket();
        float precioDia,precioTotal =0.0f;
        float porcentaje = 0.25f;

        String observaciones = "";
        IService servicio = new Servicio();
                precioDia = servicio.consultaPrecio(servicio1.tipo());
        for (int i = 0; i< servicio1.ndias(); i++) {
            LocalDate otroDia = servicio1.inicio().plusDays((long)i);
            try {
                if (calendario.es_festivo(otroDia)) {
                    precioTotal += (1+ porcentaje)*precioDia;
                } else {
                    precioTotal += (1- porcentaje)*precioDia;
                }
            } catch (CalendarioException ex) {
                observaciones += "Error en dia: "+otroDia+"; ";
            }
        }

        if (observaciones.length()>0) {
            throw new MensajeException(observaciones);
        }

        ticket.setPrecio_final(precioTotal);
        return ticket;
    }
    */

    /** Método antes de refactorizar:
    public Ticket calculaPrecio(TipoCoche tipo, LocalDate inicio, int ndias) throws MensajeException {
        Ticket ticket = new Ticket();
        float precioDia,precioTotal =0.0f;
        float porcentaje = 0.25f;

        String observaciones = "";
        IService servicio = new Servicio(); <-- Necesitamos poder inyectar servicios desde fuera.
        precioDia = servicio.consultaPrecio(tipo);
        for (int i=0; i<ndias;i++) {
            LocalDate otroDia = inicio.plusDays((long)i);
            try {
                if (calendario.es_festivo(otroDia)) { <-- Con calenario podemos crear un setter
                    precioTotal += (1+ porcentaje)*precioDia;
                } else {
                    precioTotal += (1- porcentaje)*precioDia;
                }
            } catch (CalendarioException ex) {
                observaciones += "Error en dia: "+otroDia+"; ";
            }
        }

        if (observaciones.length()>0) {
            throw new MensajeException(observaciones);
        }

        ticket.setPrecio_final(precioTotal);
        return ticket;
    }
     */
}