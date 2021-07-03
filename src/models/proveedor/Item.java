package models.proveedor;

import dal.Identificable;
import models.dtos.DDLItemDTO;
import models.dtos.DDLeable;
import models.impuesto.IVA;



public class Item implements DDLeable, Identificable {
    public Item(Integer idItem,String nombre, Integer cantidad, Double precio) {
        this.idItem = idItem;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }


    private int idItem;

    private String nombre;
    private Integer cantidad;
    private Double precio;
    private TipoItem tipo;
    private Double iva;
    private Unidad unidad;
    public Rubro pertenece;



    public Item(String nombre, Unidad unidad,TipoItem tipo,  Double iva, Rubro pertenece) {

        setNombre(nombre);
        setUnidad(unidad);
        setTipo(tipo);
        setIva(iva);
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

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
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
    public Integer getCantidad() {        return cantidad;
    }

    public void setCantidad(Integer cantidad) {        this.cantidad = cantidad;
    }

    public Double getPrecio() {        return precio;
    }

    public void setPrecio(Double precio) {        this.precio = precio;
    }
}