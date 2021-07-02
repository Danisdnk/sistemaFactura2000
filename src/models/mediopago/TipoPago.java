package models.mediopago;

import dal.Identificable;
import models.dtos.DDLItemDTO;
import models.dtos.DDLeable;

// TODO agregar a diagrama clases
public class TipoPago implements DDLeable, Identificable {
    private int ID;

    private String type;

    public TipoPago(String type) {
        this.type = type;
    }

    public DDLItemDTO toDDL(){
        return new DDLItemDTO(){
            {
                id = ID;
                descripcion = type;
            }
        };
    }

    public String getType() {
        return this.type;
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void setID(int ID) {
        this.ID = ID;
    }
}
