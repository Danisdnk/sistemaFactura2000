package models.documento;

import dal.Identificable;
import models.dtos.DDLItemDTO;
import models.dtos.DDLeable;
import models.proveedor.Proveedor;

import java.time.LocalDate;
import java.util.*;


public class OrdenCompra implements DDLeable, Identificable {

    private int ID;

    private LocalDate fecha;

    private Proveedor proveedor;

    private List<ItemOrdenCompra> items;

    private Float total;

    public OrdenCompra(LocalDate fecha, Proveedor proveedor,List<ItemOrdenCompra> items, Float total) {
        this.fecha = fecha;
        this.proveedor = proveedor;
        setItems(items);
        this.total = total;
    }

    public OrdenCompra(LocalDate fecha, Proveedor proveedor, Float total) {
        this.fecha = fecha;
        this.proveedor = proveedor;
        this.total = total;
    }



   // public Proveedor 1; //TODO orden de pago puede devolver algo al controlador de proveedor


    @Override
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public List<ItemOrdenCompra> getItems() {
        return items;
    }

    public void setItems(List<ItemOrdenCompra> items) {
        this.items = items;
    }

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


    @Override
    public DDLItemDTO toDDL() {
        return null;
    }
}