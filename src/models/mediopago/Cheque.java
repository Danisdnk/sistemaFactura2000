package models.mediopago;

import java.util.*;

/**
 * 
 */
public class Cheque {


    public Cheque() {
    }


    private int idPago;


    private String tipoCheque;

    private Date fechaEmision;

    private Date fechaVencimiento;


    private String idFirmante;

    private Float monto;

    public void getMonto() {
        // TODO implement here
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public String getTipoCheque() {
        return tipoCheque;
    }

    public void setTipoCheque(String tipoCheque) {
        this.tipoCheque = tipoCheque;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getIdFirmante() {
        return idFirmante;
    }

    public void setIdFirmante(String idFirmante) {
        this.idFirmante = idFirmante;
    }

    public void setMonto(Float monto) {
        this.monto = monto;
    }
}