import java.time.LocalDate;

public class ServicioStub extends Servicio implements IServiceStub{
    TipoCoche tipo;
    LocalDate inicio;
    int dias;

    public void setTipo(TipoCoche tipo) {
        this.tipo = tipo;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public TipoCoche tipo(){
        return tipo;
    }

    public LocalDate inicio(){
        return inicio;
    }

    public int ndias(){
        return dias;
    }

    public float consultaPrecio(TipoCoche tipo) {
        return 10;
    }
}
