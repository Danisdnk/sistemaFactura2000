package models.documento;

import models.impuesto.Retencion;
import models.proveedor.Proveedor;

import java.time.LocalDate;
import java.util.*;


public class Factura extends Comprobante {
    private List<Retencion> retencion;
    private List<ItemFactura> itemFactura;



    public Factura(Proveedor prov, String nroFactura, float montoTotal, float montoIva, double iva, LocalDate fecha) {
        this.proveedor = prov;
        this.nro = nroFactura;
        this.montoTotal = montoTotal;
        this.montoIva = montoIva;
        this.iva = iva;
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

    public float getMontoIva() {
        return montoIva;
    }

    public void setMontoIva(float montoIva) {
        this.montoIva = montoIva;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }
}