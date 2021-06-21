package models.mediopago;

import models.dtos.DDLItemDTO;
import models.dtos.DDLeable;

// TODO agregar a diagrama clases
public abstract class TipoPago implements DDLeable {
    protected int idPago;
    protected Float monto;

    public DDLItemDTO toDDL(){
        var ddl = new DDLItemDTO(){ };
        ddl.setDescripcion(String.valueOf(this.monto));
        ddl.setId(this.idPago);
        return ddl;
    }
}
