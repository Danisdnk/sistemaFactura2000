package models.documento;

import models.impuesto.Retencion;
import models.proveedor.Proveedor;

import java.util.*;


public class Factura {

    /**
     * Default constructor
     */
    public Factura() {
    }


    private int facturaID;
    private Proveedor proveedor;
    private List<Retencion> retencion;
    private List<ItemFactura> itemFactura;
    private Date fecha;
    private int monto;
    // public Proveedor 1;


    /**
     * @param fecha
     */
    public void devolverFacturaDeUnaFecha(Date fecha) {
        // TODO implement here
    }

    public void devolverFacturaDeUnaFecha() {
        // TODO implement here
    }


    public void calcularDeuda() {
        // TODO implement here
    }

    public int getFacturaID() {
        return facturaID;
    }

    public void setFacturaID(int facturaID) {
        this.facturaID = facturaID;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }
}