package models.proveedor;

import dal.Identificable;
import models.dtos.DDLeable;
import models.dtos.DDlProveedorItemDTO;


public class ProveedorItem implements DDLeable, Identificable {

    private int IDProveedorItem;

    public ProveedorItem(Float precioUnitario, Item item, Proveedor proveedor) {
        this.setPrecioUnitario(precioUnitario);
        this.setItem(item);
        this.setProveedor(proveedor);
    }
    private Proveedor proveedor;

    private Item item;
    private Float precioUnitario;


    //@return
    public float getItemProveedorPrecio() {
        //TODO implement here
        return 0.0f;
    }

    public Float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Float precioUnitario) {
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
                id =IDProveedorItem;
                precio = precioUnitario;
                descripcion = proveedor.getNombre();
            }
        };
    }

    public Item getItem() {return item;}

    public void setItem(Item item){ this.item = item;}

    public Proveedor getProveedor() {return proveedor;}

    public void setProveedor(Proveedor proveedor){this.proveedor = proveedor;}

}