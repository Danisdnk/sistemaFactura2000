package models.documento;
import models.proveedor.Proveedor;

public class Nota extends Comprobante {
    private TipoDeNota tipo;

    public Nota(
            TipoDeNota tipo,
            Proveedor p,
            String nro,
            float monto) {
        this.tipo = tipo;
        this.proveedor = p;
        this.nro = nro;
        this.total = monto;
    }

    public TipoDeNota getTipo() {
        return tipo;
    }

    public void setTipo(TipoDeNota tipo) {
        this.tipo = tipo;
    }
}