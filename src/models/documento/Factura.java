package models.documento;

import models.impuesto.Retencion;
import models.proveedor.Proveedor;

import java.time.LocalDate;
import java.util.*;


public class Factura extends Comprobante {
    private List<Retencion> retencion;
    private List<ItemFactura> itemFactura;

    public Factura(Proveedor prov, String nroFactura, float monto, LocalDate fecha) {
        this.proveedor = prov;
        this.nro = nroFactura;
        this.total = monto;
        this.fecha = fecha;
        this.tipo = "FAC";
    }

    public void devolverFacturaDeUnaFecha(Date fecha) {
        // TODO implement here
    }

    public void devolverFacturaDeUnaFecha() {
        // TODO implement here
    }

    public void calcularDeuda() {
        // TODO implement here
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}