package controllers;
import dal.RepoFactory;
import dal.Repository;
import models.dtos.DDLItemDTO;
import models.mediopago.TipoPago;
import models.proveedor.Proveedor;

import java.util.ArrayList;
import java.util.List;
public class ControladorProveedor {
    private static ControladorProveedor instancia;

    private Repository<Proveedor> RepoProveedores;

    public ControladorProveedor() {
        this.RepoProveedores =  RepoFactory.getRepoProveedores();

    }

    public void agregarProveedor(Proveedor p){
        this.RepoProveedores.crear(p);
    }

    public List<Proveedor> getProveedores() {
        return this.RepoProveedores.getTodos();
    }

    // TODO agregar a diagrama clases
    public Proveedor getProveedorByNombre(String nombre) {
        return this.RepoProveedores.getTodos()
                .stream()
                .filter(p -> p.getNombre().equals(nombre))
                .findFirst()
                .get();
    }

    // TODO agregar a diagrama clases
    public Proveedor getProveedorByID(int id) {
        return this.RepoProveedores.getTodos()
                .stream()
                .filter(p -> p.getID() == id)
                .findFirst()
                .get();
    }

    // TODO agregar a diagrama clases
    public List<DDLItemDTO> getOpcionesDDLProveedores() {
        return this.RepoProveedores.getTodos()
                .stream()
                .map(Proveedor::toDDL)
                .toList();
    }

    public static ControladorProveedor getInstancia() {
        if (instancia == null) {
            instancia = new ControladorProveedor();
        }

        return instancia;
    }
}
