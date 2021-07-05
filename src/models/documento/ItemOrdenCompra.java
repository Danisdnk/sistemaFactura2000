package models.documento;

import dal.Identificable;
import models.dtos.DDLItemDTO;
import models.dtos.DDLeable;
import models.proveedor.Item;

public class ItemOrdenCompra implements DDLeable, Identificable {

    private int ID;

    private Item item;

    private Double precio;

    private int cantidad;

    public ItemOrdenCompra(Item item, int cant, Double precio) {
        this.item = item;
        this.cantidad = cant;
        this.precio = precio;
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void setID(int ID) {
        this.ID = ID;
    }


//public OrdenCompra 1; //TODO orden de compra controller


    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public DDLItemDTO toDDL() {
        return null;
    }
}