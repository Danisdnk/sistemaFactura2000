package controllers;
import models.dtos.DDLItemDTO;
import models.mediopago.TipoPago;
import models.proveedor.Proveedor;

import java.util.ArrayList;
import java.util.List;
public class ControladorProveedor {
    private static ControladorProveedor instancia;

    private int indexer = 1;
    private List<Proveedor> proveedores;

    public ControladorProveedor() {
        this.proveedores = new ArrayList<Proveedor>();
        this.agregarProveedor(new Proveedor("Coto"));
        this.agregarProveedor(new Proveedor("YPF"));
        this.agregarProveedor(new Proveedor("Firulais"));
        this.agregarProveedor(new Proveedor("Philips"));
    }

    public void agregarProveedor(Proveedor p){
        p.setID(this.indexer);
        this.proveedores.add(p);
        this.indexer++;
    }

    public List<Proveedor> getProveedores() {
        return this.proveedores;
    }

    // TODO agregar a diagrama clases
    public Proveedor getProveedorByNombre(String nombre) {
        return this.proveedores
                .stream()
                .filter(p -> p.getNombre().equals(nombre))
                .findFirst()
                .get();
    }

    // TODO agregar a diagrama clases
    public Proveedor getProveedorByID(int id) {
        return this.proveedores
                .stream()
                .filter(p -> p.getID() == id)
                .findFirst()
                .get();
    }

    // TODO agregar a diagrama clases
    public List<DDLItemDTO> getOpcionesDDLProveedores() {
        return this.proveedores
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
