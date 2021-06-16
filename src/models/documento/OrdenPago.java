package models.documento;

import java.util.*;

public class OrdenPago {

    public OrdenPago() {
    }
    private List<Factura> factura;


    private List<Nota> nota;


    private float totalACancelar;


    private int idFormaPago;


    private float totalRetenciones;


    private int monto;


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