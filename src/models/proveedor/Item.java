package models.proveedor;
import models.impuesto.IVA;
public class Item {


    public Item() {
    }


    private int idItem;

    private String nombre;

    private TipoItem tipo;
    private IVA iva;

    private Unidad unidad;

    public Rubro pertenece;









    /**
     * @param idItem 
     * @return
     */
    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoItem getTipo() {
        return tipo;
    }

    public void setTipo(TipoItem tipo) {
        this.tipo = tipo;
    }

    public IVA getIva() {
        return iva;
    }

    public void setIva(IVA iva) {
        this.iva = iva;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

    public Rubro getPertenece() {
        return pertenece;
    }

    public void setPertenece(Rubro pertenece) {
        this.pertenece = pertenece;
    }
}