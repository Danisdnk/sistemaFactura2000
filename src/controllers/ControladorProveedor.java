package controllers;
import models.proveedor.Proveedor;

import java.util.ArrayList;
import java.util.List;
public class ControladorProveedor {

    private static ControladorProveedor instancia;
    private int indexer = 1;
    private List<Proveedor> proveedor;

    public ControladorProveedor() {
        this.proveedor = new ArrayList<Proveedor>();
        this.agregarP(new Proveedor());
    }

    public void agregarP (Proveedor p){
        /*p.setOpID(this.indexer);*/
        this.proveedor.add(p);
        this.indexer++;
    }

    public List<Proveedor> getOPs() {
        return this.proveedor;
    }

    public static ControladorProveedor getInstancia() {
        if (instancia == null) {
            instancia = new ControladorProveedor();
        }

        return instancia;
    }
}
