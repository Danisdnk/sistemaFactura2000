package models.documento;

import dal.Identificable;
import models.dtos.ComprobanteDTO;
import models.dtos.DDLItemDTO;
import models.dtos.DDLeable;
import models.proveedor.Proveedor;

import java.time.LocalDate;

// TODO agregar a diagrama clases nroFactura
public class Comprobante implements IComprobante, DDLeable, Identificable {
    protected int ID;

    protected Proveedor proveedor;

    protected LocalDate fecha;

    protected String nro;

    protected float total;

    protected String tipo;

    public ComprobanteDTO toCompDTO() {
        return new ComprobanteDTO(
                ID,
                this.tipo(),
                proveedor,
                fecha,
                nro,
                total
        );
    }

    public DDLItemDTO toDDL() {
        var desc = this.toString();
        return new DDLItemDTO() {
            {
                id = ID;
                descripcion = desc;
            }
        };
    }

    public String tipo() {
        return this.tipo;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getNro() {
        return nro;
    }

    public void setNro(String nro) {
        this.nro = nro;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return tipo() + " " + nro;
    }
}
