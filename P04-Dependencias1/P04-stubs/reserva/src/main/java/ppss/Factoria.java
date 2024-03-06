package ppss;

import java.util.Objects;

public class Factoria {
    protected IOperacionBO operacion = null;

    public IOperacionBO createOp() {
        return Objects.requireNonNullElseGet(this.operacion, Operacion::new);
    }

    public void setOperacion(Operacion op) {
        this.operacion = op;
    }
}
