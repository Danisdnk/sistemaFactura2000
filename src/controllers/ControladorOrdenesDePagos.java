package controllers;

import models.documento.Factura;
import models.documento.OrdenPago;
import models.dtos.DDLItemDTO;
import models.mediopago.Cheque;
import models.mediopago.Efectivo;
import models.mediopago.TipoPago;

import java.util.ArrayList;
import java.util.List;

public class ControladorOrdenesDePagos {
    private static ControladorOrdenesDePagos instancia;
    private int indexer = 1;
    private List<OrdenPago> ordenesDePago;
    private List<TipoPago> formasDePago;

    private ControladorOrdenesDePagos() {
        this.ordenesDePago = new ArrayList<OrdenPago>();

        this.agregarOP(new OrdenPago());
        this.agregarOP(new OrdenPago());
        this.agregarOP(new OrdenPago());
        this.agregarOP(new OrdenPago());
        this.agregarOP(new OrdenPago());
        this.agregarOP(new OrdenPago());

        this.formasDePago = new ArrayList<TipoPago>();
        this.formasDePago.add(new TipoPago("Cheque"));
        this.formasDePago.add(new TipoPago("Efectivo"));
    }

    public void agregarOP(OrdenPago op) {
        op.setOpID(this.indexer);
        this.ordenesDePago.add(op);
        this.indexer++;
    }

    public void modificarOP(OrdenPago op) {

    }

    public List<OrdenPago> getOPs() {
        return this.ordenesDePago;
    }

    // TODO agregar a diagrama clases
    public List<DDLItemDTO> getOpcionesDDLFormasDePago() {
        return this.formasDePago
                .stream()
                .map(TipoPago::toDDL)
                .toList();
    }

    // TODO agregar a diagrama clases
    public OrdenPago getOPByID(int opID) {
        return this.ordenesDePago
                .stream()
                .filter(op -> op.getOpID() == opID)
                .findFirst()
                .get();
    }

    public static ControladorOrdenesDePagos getInstancia() {
        if (instancia == null) {
            instancia = new ControladorOrdenesDePagos();
        }

        return instancia;
    }
}
