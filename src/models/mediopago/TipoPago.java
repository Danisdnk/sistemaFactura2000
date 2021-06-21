package models.mediopago;

import models.dtos.DDLItemDTO;
import models.dtos.DDLeable;

// TODO agregar a diagrama clases
public class TipoPago implements DDLeable {
    private int ID;
    private String type;

    public TipoPago(String type) {
        this.type = type;
    }

    public DDLItemDTO toDDL(){
        var ddl = new DDLItemDTO(){ };
        ddl.setDescripcion(String.valueOf(this.type));
        ddl.setId(this.ID);
        return ddl;
    }
}
