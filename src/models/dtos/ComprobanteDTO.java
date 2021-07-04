package models.dtos;

import models.proveedor.Proveedor;

import java.time.LocalDate;

public class ComprobanteDTO {

    private int ID;
    private Proveedor proveedor;
    private LocalDate fecha;
    private String nro;
    private String tipo;

    private  float montoNeto;
    private  double iva;
    private  float montoIva;
    private float montoTotal;

    public ComprobanteDTO(int id, String tipo, Proveedor prov, LocalDate fecha, String nro, float montoTotal) {
        this.ID = id;
        this.tipo = tipo;
        this.nro = nro;
        this.fecha = fecha;
        this.proveedor = prov;
        this.montoTotal = montoTotal;
    }

    public ComprobanteDTO(int id, String tipo, Proveedor prov, LocalDate fecha, String nro, float montoaNeto, double iva, float montoIva, float montoTotal) {
        this.ID = id;
        this.tipo = tipo;
        this.nro = nro;
        this.fecha = fecha;
        this.proveedor = prov;
        this.montoNeto = montoNeto;
        this.iva = iva;
        this.montoIva = montoIva;
        this.montoTotal = montoTotal;
    }


    public int getID() {
        return ID;
    }

    public String getTipo() {
        return tipo;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getNro() {
        return nro;
    }

    public float getMontoNeto() {
        return montoNeto;
    }

    public float getMontoIva() {
        return montoIva;
    }

    public float getMontoTotal() {
        return montoTotal;
    }

    public double getIva() {
        return iva;
    }

}
