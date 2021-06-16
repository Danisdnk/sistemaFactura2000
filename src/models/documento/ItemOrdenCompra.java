package models.documento;

import models.proveedor.Item;

public class ItemOrdenCompra {


    public ItemOrdenCompra() {
    }


    private Item item;

    private Float precio;

    private Float cantidad;



   //public OrdenCompra 1; //TODO orden de compra controller


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