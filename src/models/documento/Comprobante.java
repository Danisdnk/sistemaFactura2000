package models.documento;

import models.dtos.ComprobanteDTO;
import models.dtos.DDLItemDTO;
import models.dtos.DDLeable;
import models.proveedor.Proveedor;

import java.time.LocalDate;

// TODO agregar a diagrama clases nroFactura
public class Comprobante implements DDLeable {
    protected int ID;

    protected Proveedor proveedor;

    protected LocalDate fecha;

    protected String nro;

    protected float total;

    public ComprobanteDTO toCompDTO() {
        return new ComprobanteDTO(){
            {
                ID = this.ID;
                total = this.total;
                proveedor = this.proveedor;
                nro = this.nro;
                fecha = this.fecha;
            }
        };
    }

    public DDLItemDTO toDDL() {
        return new DDLItemDTO() {
            {
                id = ID;
                descripcion = nro;
            }
        };
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
}
