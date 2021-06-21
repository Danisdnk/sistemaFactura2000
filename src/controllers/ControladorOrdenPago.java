package controllers;

import models.documento.OrdenPago;

import java.util.ArrayList;
import java.util.List;

public class ControladorOrdenPago {
    private static ControladorOrdenPago instancia;
    private int indexer = 1;
    private List<OrdenPago> ordenesDePago;

    private ControladorOrdenPago() {
        this.ordenesDePago = new ArrayList<OrdenPago>();

        this.agregarOP(new OrdenPago());
        this.agregarOP(new OrdenPago());
        this.agregarOP(new OrdenPago());
        this.agregarOP(new OrdenPago());
        this.agregarOP(new OrdenPago());
        this.agregarOP(new OrdenPago());
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
    public OrdenPago getOPByID(int opID) {
        return this.ordenesDePago
                .stream()
                .filter(op -> op.getOpID() == opID)
                .findFirst()
                .get();
    }

    public static ControladorOrdenPago getInstancia() {
        if (instancia == null) {
            instancia = new ControladorOrdenPago();
        }

        return instancia;
    }
}
