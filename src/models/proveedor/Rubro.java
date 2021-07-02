package models.proveedor;

import dal.Identificable;
import models.dtos.DDLItemDTO;
import models.dtos.DDLeable;

public class Rubro implements DDLeable, Identificable {

    private int IDrubro;
    private String nombre;

    public Rubro(String nombre) {

        setNombre(nombre);
    }

    @Override
    public DDLItemDTO toDDL() {
        return new DDLItemDTO() {
            {
                id = IDrubro;
                descripcion = nombre;
            }
        };
    }


    public int getID() {
        return IDrubro;
    }

    public void setID(int ID) {
        this.IDrubro = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}