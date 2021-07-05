package controllers;

import dal.RepoFactory;
import dal.Repository;
import models.dtos.DDLItemDTO;
import models.dtos.DDLUsuarioDTO;
import models.proveedor.Proveedor;
import models.usuario.Usuario;

import java.util.List;

public class ControladorUsuario {
    private static ControladorUsuario instancia;

    private Repository<Usuario> RepoUsuarios; //repousuarios

    public ControladorUsuario() {
        this.RepoUsuarios =  RepoFactory.getUsuarioRepository();
    }

    public void agregarUsuario(Usuario u){
        this.RepoUsuarios.insertar(u);
    }

    public List<Usuario> getProveedores() {
        return this.RepoUsuarios.getTodos();
    }

    // TODO para login comparar tmb con password
    public boolean getUsuarioByNombreUsuarioPass(String nombreUsuario, String pass) {
        return this.RepoUsuarios.getTodos()
                .stream()
                .anyMatch(p -> p.getNombreUsuario().equals(nombreUsuario) && p.getPassword().equals(pass) );
    }



    // TODO para login comparar tmb con password
    public boolean getUsuarioByCredenciales(String nombreUsuario) {
        return this.RepoUsuarios.getTodos()
                .stream()
                .anyMatch(p -> p.getNombreUsuario().equals(nombreUsuario) );
    }



    public static ControladorUsuario getInstancia() {
        if (instancia == null) {
            instancia = new ControladorUsuario();
        }

        return instancia;
    }
}
