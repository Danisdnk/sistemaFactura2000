package models.documento;

import models.proveedor.Item;

public class ItemFactura {

    public ItemFactura() {
    }

    public Item item;

    public Float precio;

    public Float cantidad;

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
}