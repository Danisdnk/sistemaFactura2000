package models.documento;

import models.proveedor.Proveedor;

import java.time.LocalDate;
import java.util.*;


public class OrdenCompra {


    public OrdenCompra() {
    }

    private LocalDate fecha;

    private Proveedor proveedor;

    private List<ItemOrdenCompra> items;


   // public Proveedor 1; //TODO orden de pago puede devolver algo al controlador de proveedor


    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}