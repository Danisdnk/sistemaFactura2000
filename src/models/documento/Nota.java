package models.documento;
import models.proveedor.Proveedor;

public class Nota extends Comprobante {
    private TipoDeNota nota;

    public Nota(
            TipoDeNota tipo,
            Proveedor p,
            String nro,
            float monto) {
        this.nota = tipo;
        this.proveedor = p;
        this.nro = nro;
        this.total = monto;
        this.tipo = "NOTA " + this.nota.name();
    }

    @Override
    public String tipo() {
        return this.tipo;
    }

    @Override
    public float getTotal() {
        //debito suma, credito resta
        return this.nota == TipoDeNota.DEBITO ? this.total : this.total*-1;
    }
}