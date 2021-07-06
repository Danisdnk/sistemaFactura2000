package models.documento;

import dal.Identificable;
import models.dtos.DDLItemDTO;
import models.dtos.DDLeable;
import models.proveedor.Item;

public class ItemFactura implements DDLeable, Identificable {

    private int ID;
    public Item item;
    public float precio;
    public float cantidad;
    private float total;

    public ItemFactura(Item item, float cantidad, float total) {
        this.item = item;
        this.cantidad = cantidad;
        this.total = total;
    }



    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Float getCantidad() {
        return cantidad;
    }

    public void setCantidad(Float cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void setID(int id) {

    }

    @Override
    public DDLItemDTO toDDL() {
        return null;
    }
}