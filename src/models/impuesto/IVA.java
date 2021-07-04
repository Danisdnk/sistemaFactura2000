package models.impuesto;

import dal.Identificable;
import models.dtos.DDLItemDTO;
import models.dtos.DDLeable;

public class IVA implements DDLeable, Identificable {

    private Float importeIva;

    public IVA(Float importeIva) {
        this.importeIva = importeIva;
    }

    public void calcularImporteIva(float importeIva) {

    }

    public Float getImporteIva() {
        return importeIva;
    }

    public void setImporteIva(Float importeIva) {
        this.importeIva = importeIva;
    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void setID(int id) {

    }

    @Override
    public DDLItemDTO toDDL() {
        return null;
    }
}
