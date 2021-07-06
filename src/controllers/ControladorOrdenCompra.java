package controllers;

import dal.RepoFactory;
import dal.Repository;
import models.documento.Factura;
import models.documento.OrdenCompra;
import models.dtos.DDLItemDTO;
import models.dtos.DDLOrdenDeCompraProveedor;
import models.dtos.DDlProveedorItemDTO;
import models.proveedor.Proveedor;
import models.proveedor.ProveedorItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    /*public List<OrdenCompra> getOCByProveedor(String cuit) {
        return Stream.of(this.repoOrdenCompra.getTodos())
                .flatMap(Collection::stream)
                .filter(c -> c.getProveedor().getCuit().equals(cuit) )
                .collect(Collectors.toList());
    }

     */

    public List<DDLOrdenDeCompraProveedor> getOCByProveedor(int proveedorID) {
        var list = new ArrayList<DDLOrdenDeCompraProveedor>();
        for (OrdenCompra p : this.repoOrdenCompra.getTodos()) {
            if ( p.getProveedor().getID() == proveedorID ) {
                DDLOrdenDeCompraProveedor toDDL = p.toDDL();
                list.add(toDDL);
            }
        }
        return list;
    }
}
