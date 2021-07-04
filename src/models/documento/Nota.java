package models.documento;
import models.proveedor.Proveedor;

import java.time.LocalDate;

public class Nota extends Comprobante {
    private TipoDeNota nota;

    public Nota(
            TipoDeNota tipo,
            Proveedor p,
            String nro,
            float total,
            LocalDate fecha) {
        this.nota = tipo;
        this.proveedor = p;
        this.nro = nro;
        this.montoTotal = total;
        this.tipo = "NOTA " + this.nota.name();
        this.fecha = fecha;
    }
    /**Este deberia ser el constructor por default pero falta implementar
     * en la vista que se pueda ingresar las notas de credito/debito con estos datos
     * */
    public Nota(
            TipoDeNota tipo,
            Proveedor p,
            String nro,
            float montoNeto,
            float iva,
            LocalDate fecha) {
        this.nota = tipo;
        this.proveedor = p;
        this.nro = nro;
        this.montoNeto = montoNeto;
        this.iva = iva;
        this.tipo = "NOTA " + this.nota.name();
        this.fecha = fecha;
        setMontoIva(iva, montoNeto);
        setMontoTotal(montoNeto, montoIva);
    }

    @Override
    public String tipo() {
        return this.tipo;
    }

    @Override
    public float getMontoTotal() {
        //debito suma, credito resta
        return this.nota == TipoDeNota.DEBITO ? this.montoTotal : this.montoTotal*-1;
    }
}