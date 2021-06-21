package models.documento;

import models.dtos.DDLItemDTO;
import models.dtos.DDLeable;
import models.impuesto.Retencion;
import models.proveedor.Proveedor;

import java.util.*;


public class Factura implements DDLeable {
    // TODO agregar a diagrama clases, rename de factura ID a ID
    private int ID;

    // TODO agregar a diagrama clases nroFactura
    private String nroFactura;

    private Proveedor proveedor;
    private List<Retencion> retencion;
    private List<ItemFactura> itemFactura;
    private Date fecha;
    private int monto;

    public Factura(String nroFactura) {
        this.nroFactura = nroFactura;
    }

    public void devolverFacturaDeUnaFecha(Date fecha) {
        // TODO implement here
    }

    public void devolverFacturaDeUnaFecha() {
        // TODO implement here
    }

    public void calcularDeuda() {
        // TODO implement here
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    @Override
    public DDLItemDTO toDDL() {
        var ddl = new DDLItemDTO();
        ddl.setDescripcion(this.nroFactura);
        ddl.setId(this.ID);
        return ddl;
    }
}