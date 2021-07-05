package controllers;

import dal.RepoFactory;
import dal.Repository;
import models.documento.Factura;
import models.documento.OrdenCompra;
import models.proveedor.Proveedor;

public class ControladorOrdenCompra {

    private static ControladorOrdenCompra instancia;
    private Repository<OrdenCompra> repoOrdenCompra;

    public void agregarOrdenCompra(OrdenCompra oc){
        this.repoOrdenCompra.insertar(oc);
    }

    private ControladorOrdenCompra() {
        this.repoOrdenCompra = RepoFactory.getRepoOrdenCompra();
    }

    public static ControladorOrdenCompra getInstancia() {
        if (instancia == null) {
            instancia = new ControladorOrdenCompra();
        }

        return instancia;
    }
}
