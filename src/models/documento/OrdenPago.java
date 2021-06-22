package models.documento;

import models.mediopago.TipoPago;
import models.proveedor.Proveedor;

import java.time.LocalDate;
import java.util.*;

public class OrdenPago {
    // TODO agregar a diagrama clases
    private int opID;

    // TODO agregar a diagrama clases
    private Proveedor proveedor;

    // TODO renombrar a diagrama clases
    private List<Factura> facturas;

    private List<Nota> nota;

    private float totalACancelar;

    // TODO cambio de id a TipoPago a diagrama clases
    private TipoPago formaPago;

    private float totalRetenciones;

    private int monto;

    private LocalDate fechaPago;

    // TODO agregar a diagrama clases
    public void setOpID(int opID) {
        this.opID = opID;
    }

    // TODO agregar a diagrama clases
    public int getOpID() {
        return opID;
    }

    // TODO agregar a diagrama clases
    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Factura getFactura() {
        return this.facturas.stream().findFirst().get();
    }

    public void comprobarMedioPago() {
        // TODO implement here
    }

    public void getMontoyVerificarPago() {
        // TODO implement here
    }

    public float getTotalACancelar() {
        return totalACancelar;
    }

    public void setTotalACancelar(float totalACancelar) {
        this.totalACancelar = totalACancelar;
    }

    // TODO agregar a diagrama clases
    public TipoPago getFormaPago() {
        return this.formaPago;
    }

    // TODO agregar a diagrama clases
    public void setFormaPago(TipoPago pago) {
        this.formaPago = pago;
    }

    public float getTotalRetenciones() {
        return totalRetenciones;
    }

    public void setTotalRetenciones(float totalRetenciones) {
        this.totalRetenciones = totalRetenciones;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }
}