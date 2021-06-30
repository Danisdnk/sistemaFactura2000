package models.proveedor;

import dal.Identificable;
import models.dtos.DDLItemDTO;
import models.dtos.DDLeable;
import models.impuesto.IVA;


public class Item implements DDLeable, Identificable {

    private int idItem;

    private String nombre;
    private TipoItem tipo;
    private IVA iva;
    private Unidad unidad;
    public Rubro pertenece;



    public Item(String nombre, Unidad unidad,TipoItem tipo, Rubro pertenece) {

        setNombre(nombre);
        setUnidad(unidad);
        setTipo(tipo);
        setPertenece(pertenece);

    }

    @Override
    public DDLItemDTO toDDL() {
        return new DDLItemDTO() {
            {
                id = idItem;
                descripcion = nombre;
            }
        };
    }

    /**
     * @param idItem
     * @return
     */
    public int getID() {
        return idItem;
    }

    public void setID(int idItem) {
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