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
        return this.repoOPs.getByID(opID);
    }

    public static ControladorOrdenesDePagos getInstancia() {
        if (instancia == null) {
            instancia = new ControladorOrdenesDePagos();
        }

        return instancia;
    }
}
