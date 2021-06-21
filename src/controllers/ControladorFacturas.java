package controllers;

import models.documento.Factura;
import models.documento.OrdenPago;
import models.dtos.DDLItemDTO;

import java.util.ArrayList;
import java.util.List;

public class ControladorFacturas {
    private static ControladorFacturas instancia;
    private int indexer = 1;
    private List<Factura> facturas;

    private ControladorFacturas() {
        this.facturas = new ArrayList<Factura>();

        this.agregarFactura(new Factura("0001-00002555"));
        this.agregarFactura(new Factura("0001-00002556"));
        this.agregarFactura(new Factura("0001-00002557"));
        this.agregarFactura(new Factura("0001-00002558"));
        this.agregarFactura(new Factura("0001-00002590"));
    }

    public void agregarFactura(Factura fac) {
        fac.setID(this.indexer);
        this.facturas.add(fac);
        this.indexer++;
    }

    public void modificarOP(OrdenPago op) {

    }

    public List<Factura> getFacturas() {
        return this.facturas;
    }

    public List<DDLItemDTO> getOpcionesDDLFacturaByProveedor(int proveedorID) {
        return this.facturas
                .stream()
                .filter(f -> f.getProveedor().getID() == proveedorID)
                .map(Factura::toDDL)
                .toList();
    }

    // TODO agregar a diagrama clases
    public Factura getFacturaByID(int facID) {
        return this.facturas
                .stream()
                .filter(fac -> fac.getID() == facID)
                .findFirst()
                .get();
    }

    public static ControladorFacturas getInstancia() {
        if (instancia == null) {
            instancia = new ControladorFacturas();
        }

        return instancia;
    }
}
