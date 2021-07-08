package controllers;
import dal.RepoFactory;
import dal.Repository;
import models.dtos.DDLItemDTO;
import models.impuesto.ResponsableIva;
import models.proveedor.Proveedor;

import java.util.List;

public class ControladorProveedor {
    private static ControladorProveedor instancia;
    private Repository<Proveedor> RepoProveedores;
    private  Repository<ResponsableIva> RepoResponsableIva;
    private ControladorProveedor() {
        this.RepoProveedores =  RepoFactory.getRepoProveedores();
    }

    public void agregarProveedor(Proveedor p){
        this.RepoProveedores.insertar(p);
    }

    public boolean eliminarProveedor(Proveedor p){ return this.RepoProveedores.borrar(p); }
    public void actualizarProveedor(Proveedor p){ this.RepoProveedores.updatear(p); }
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
        return this.RepoProveedores.getByID(id);
    }

    // TODO agregar a diagrama clases
    public List<DDLItemDTO> getOpcionesDDLProveedores() {
        return this.RepoProveedores.getTodos()
                .stream()
                .map(Proveedor::toDDL)
                .toList();
    }

    public List<DDLItemDTO> getOpcionesDDLResponsableIva() {
        this.RepoResponsableIva =  RepoFactory.getResponsableIva();
        return this.RepoResponsableIva.getTodos()
                .stream()
                .map(ResponsableIva::toDDL)
                .toList();
    }

    public DDLItemDTO getOpcionesDDLResponsableIvaByProv(int p) {
        this.RepoResponsableIva =  RepoFactory.getResponsableIva();
        return this.RepoResponsableIva.getTodos()
                .stream()
                .map(ResponsableIva::toDDL)
                .filter(a->a.descripcion.equals(getProveedorByID(p).getResponsableIva()))
                .findFirst()
                .get();
    }

    public DDLItemDTO getOpcionesDDLRubrosByProv(int p) {
        var controladorItem= ControladorItem.getInstancia();
        return controladorItem.getOpcionesDDLRubros()
                .stream()
                .filter(a->a.descripcion.equals(getProveedorByID(p).getRubros()))
                .findFirst()
                .get();
    }

    /**
     * Metodo que verfica si existe un Proveedor segun un cuit
     * @param cuit
     * @return boolean
     */
    public boolean existsProveedorCuit(String cuit) {
        return this.RepoProveedores.getTodos()
                .stream()
                .anyMatch(p -> p.getCuit().equals(cuit));
    }

    public boolean existsProveedorNombre(String nombre) {
        return this.RepoProveedores.getTodos()
                .stream()
                .anyMatch(p -> p.getNombre().equals(nombre));
    }

    public static ControladorProveedor getInstancia() {
        if (instancia == null) {
            instancia = new ControladorProveedor();
        }

        return instancia;
    }
}
