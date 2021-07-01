package controllers;

import dal.RepoFactory;
import dal.Repository;
import models.dtos.DDLItemDTO;
import models.proveedor.Proveedor;

import java.util.List;

public class ControladorUsuario {
    private static ControladorUsuario instancia;

    private Repository<Proveedor> RepoProveedores; //repousuarios

    public ControladorUsuario() {
        this.RepoProveedores =  RepoFactory.getRepoProveedores();
    }

    public void agregarProveedor(Proveedor p){
        this.RepoProveedores.insertar(p);
    }

    public List<Proveedor> getProveedores() {
        return this.RepoProveedores.getTodos();
    }

    // TODO para login comparar tmb con password
    public Proveedor getProveedorByNombre(String nombre) {
        return this.RepoProveedores.getTodos()
                .stream()
                .filter(p -> p.getNombre().equals(nombre) )
                .findFirst()
                .get();
    }


    // TODO agregar a diagrama clases
    public Proveedor getProveedorByID(int id) {
        return this.RepoProveedores.getByID(id);
    }

    // TODO agregar a diagrama clases
    public List<DDLItemDTO> getOpcionesDDLProveedores() {
        return this.RepoProveedores.getTodos()
                .stream()
                .map(Proveedor::toDDL)
                .toList();
    }

    public static ControladorUsuario getInstancia() {
        if (instancia == null) {
            instancia = new ControladorUsuario();
        }

        return instancia;
    }
}
