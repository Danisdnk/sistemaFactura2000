package models.proveedor;

import dal.Identificable;
import models.dtos.DDLeable;
import models.dtos.DDlProveedorItemDTO;


public class ProveedorItem implements DDLeable, Identificable {

    private int IDProveedorItem;

    public ProveedorItem(Double precioUnitario, Item item, Proveedor proveedor) {
        this.setPrecioUnitario(precioUnitario);
        this.setItem(item);
        this.setProveedor(proveedor);
    }
    private Proveedor proveedor;

    private Item item;
    private Double precioUnitario;



    //@return
    public float getItemProveedorPrecio() {
        //TODO implement here
        return 0.0f;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    @Override
    public int getID() {
        return IDProveedorItem;
    }

    @Override
    public void setID(int ID) {
        this.IDProveedorItem = ID;
    }

    @Override
    public DDlProveedorItemDTO toDDL() {
        return new DDlProveedorItemDTO(){
            {
                id = item.getID();
                precio = precioUnitario;
                producto = item.getNombre();
                tipo = item.getTipo().toString();
                proveedor = getProveedor().getNombre();
                iva = item.getIva();
            }
        };
    }

    public Item getItem() {return item;}

    public void setItem(Item item){ this.item = item;}

    public Proveedor getProveedor() {return proveedor;}

    public void setProveedor(Proveedor proveedor){this.proveedor = proveedor;}

}