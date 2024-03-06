import java.time.LocalDate;

public class ServicioStub extends Servicio implements IServiceStub{
    @Override
    public float consultaPrecio(TipoCoche tipo) {
        return 10;
    }
}
