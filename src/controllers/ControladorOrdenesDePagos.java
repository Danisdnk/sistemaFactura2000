package controllers;

import dal.RepoFactory;
import dal.Repository;
import models.documento.OrdenPago;
import models.dtos.DDLItemDTO;
import models.mediopago.TipoPago;

import java.util.List;

public class ControladorOrdenesDePagos {
    private static ControladorOrdenesDePagos instancia;

    private Repository<OrdenPago> repoOPs;
    private Repository<TipoPago> repoTiposDePago;

    private ControladorOrdenesDePagos() {
        this.repoOPs = RepoFactory.getRepoOrdenesPago();
        this.repoTiposDePago = RepoFactory.getRepoTiposDePago();

        if (this.repoTiposDePago.getTodos().size() == 0) {
            this.repoTiposDePago.insertar(new TipoPago("Cheque"));
            this.repoTiposDePago.insertar(new TipoPago("Efectivo"));
        }
    }

    public void agregarOP(OrdenPago op) {
        this.repoOPs.insertar(op);
    }

    public void modificarOP(OrdenPago op) {
        this.repoOPs.updatear(op);
    }

    public List<OrdenPago> getOPs() {
        return this.repoOPs.getTodos();
    }

    // TODO agregar a diagrama clases
    public List<DDLItemDTO> getOpcionesDDLFormasDePago() {
        return this.repoTiposDePago
                .getTodos()
                .stream()
                .map(TipoPago::toDDL)
                .toList();
    }

    // TODO agregar a diagrama clases
    public OrdenPago getOPByID(int opID) {
        return this.repoOPs
                .getTodos()
                .stream()
                .filter(op -> op.getID() == opID)
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
