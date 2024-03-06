import java.time.LocalDate;

public class ServicioStub extends Servicio implements IService{
    @Override
    public float consultaPrecio(TipoCoche tipo) {
        return 10;
    }
}
