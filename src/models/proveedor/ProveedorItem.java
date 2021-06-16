package models.proveedor;

import java.util.*;

/**
 * 
 */
public class ProveedorItem {

    public ProveedorItem() {
    }

    private List  <Proveedor> proveedor;
    private List <Item> item;
    private Float precioUnitario;


    //@return
    public float getItemProveedorPrecio() {
        //TODO implement here
        return 0.0f;
    }

    public List<Proveedor> getProveedor() {
        return proveedor;
    }

    public void setProveedor(List<Proveedor> proveedor) {
        this.proveedor = proveedor;
    }

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }

    public Float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}