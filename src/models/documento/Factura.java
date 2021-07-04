package models.documento;

import models.impuesto.Retencion;
import models.proveedor.Proveedor;

import java.time.LocalDate;
import java.util.*;


public class Factura extends Comprobante {
    private List<Retencion> retencion;
    private List<ItemFactura> itemFactura;



    public Factura(Proveedor prov, String nroFactura, float montoNeto, float iva, LocalDate fecha) {
        this.proveedor = prov;
        this.nro = nroFactura;
        this.montoNeto = montoNeto;
        this.iva = iva;
        this.fecha = fecha;
        this.tipo = "FAC";
        setMontoIva(iva, montoNeto);
        setMontoTotal(montoNeto, montoIva);
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

    public float getMontoIva() {
        return montoIva;
    }

    public double getIva() {
        return iva;
    }
}