package controllers;
import dal.RepoFactory;
import dal.Repository;
import models.dtos.DDLItemDTO;
import models.impuesto.EnumResponsableIva;
import models.impuesto.ResponsableIva;
import models.proveedor.Proveedor;
import java.util.List;

public class ControladorProveedor {
    private static ControladorProveedor instancia;
    private Repository<Proveedor> RepoProveedores;
    private  Repository<ResponsableIva> RepoResponsableIva;
    public ControladorProveedor() {
        this.RepoProveedores =  RepoFactory.getRepoProveedores();
    }

    public void verificarIndice(Proveedor p){
        this.RepoProveedores.restarIndice(p);
    }

    public void agregarProveedor(Proveedor p){
        this.RepoProveedores.insertar(p);
    }

    public void eliminarProveedor(Proveedor p){
        this.RepoProveedores.borrar(p);
    }
    public void actualizarProveedor(Proveedor p){
        this.RepoProveedores.updatear(p);
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

    /**
     * Metodo que verfica si existe un Proveedor segun un cuit
     * @param cuit
     * @return boolean
     */
    public boolean existsProveedor(String cuit) {
        return this.RepoProveedores.getTodos()
                .stream()
                .anyMatch(p -> p.getCuit().equals(cuit));
    }

    public static ControladorProveedor getInstancia() {
        if (instancia == null) {
            instancia = new ControladorProveedor();
        }

        return instancia;
    }
}
