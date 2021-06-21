package models.documento;

import java.util.*;

public class OrdenPago {

    public OrdenPago() {
    }

    // TODO agregar a diagrama clases
    private int opID;

    private List<Factura> factura;

    private List<Nota> nota;

    private float totalACancelar;

    private int idFormaPago;

    private float totalRetenciones;

    private int monto;

    // TODO agregar a diagrama clases
    public void setOpID(int opID) {
        this.opID = opID;
    }

    // TODO agregar a diagrama clases
    public int getOpID() {
        return opID;
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

    public int getIdFormaPago() {
        return idFormaPago;
    }

    public void setIdFormaPago(int idFormaPago) {
        this.idFormaPago = idFormaPago;
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