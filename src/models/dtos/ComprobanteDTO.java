package models.dtos;

import models.proveedor.Proveedor;

import java.time.LocalDate;

public class ComprobanteDTO {
    private int ID;
    private Proveedor proveedor;
    private LocalDate fecha;
    private String nro;
    private float total;
    private String tipo;
    private  float montoIva;
    private  double iva;

    public ComprobanteDTO(int id, String tipo, Proveedor prov, LocalDate fecha, String nro, float total) {
        this.ID = id;
        this.tipo = tipo;
        this.nro = nro;
        this.fecha = fecha;
        this.proveedor = prov;
        this.total = total;
    }

    public ComprobanteDTO(int id, String tipo, Proveedor prov, LocalDate fecha, String nro, float total, float montoaIva, double iva) {
        this.ID = id;
        this.tipo = tipo;
        this.nro = nro;
        this.fecha = fecha;
        this.proveedor = prov;
        this.total = total;
        this.montoIva = montoaIva;
        this.iva = iva;
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

    public float getTotal() {
        return total;
    }

    public float getMontoaIva() {
        return montoIva;
    }

    public double getIva() {
        return iva;
    }

}
