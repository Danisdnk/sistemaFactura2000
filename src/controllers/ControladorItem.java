package controllers;

import dal.RepoFactory;
import dal.Repository;
import models.dtos.DDLItemDTO;
import models.dtos.DDlProveedorItemDTO;
import models.proveedor.Item;
import models.proveedor.ProveedorItem;
import models.proveedor.Rubro;


import java.util.List;

public class ControladorItem {

    private static ControladorItem instancia;

    private Repository<Item> RepoItems;
    private Repository<Rubro> RepoRubros;
    private Repository<ProveedorItem> RepoProveedorItem;

    public ControladorItem() {
        this.RepoItems = RepoFactory.getRepoItems();
        this.RepoRubros = RepoFactory.getRepoRubros();
        this.RepoProveedorItem = RepoFactory.getProveedorItem();
    }

    public List<Item> getItems() {
        return this.RepoItems.getTodos();
    }

    // TODO agregar a diagrama clases
    public Item getItemByID(int id) {
        return this.RepoItems.getByID(id);
    }

    // TODO agregar a diagrama clases
    public Item getItemByNombre(String nombre) {            //metodo para buscar item por el nombre -> compulsaPrecos(Item)
        return this.RepoItems.getTodos()
                .stream()
                .filter(p -> p.getNombre().equals(nombre))
                .findFirst()
                .get();
    }

    public Rubro getRubroByNombre(String nombre) {         //metodo para buscar rubros por el nombre -> compulsaPrecos(Rubro)
        return this.RepoRubros.getTodos()
                .stream()
                .filter(p -> p.getNombre().equals(nombre))
                .findFirst()
                .get();
    }

    // TODO agregar a diagrama clases
    public List<DDLItemDTO> getOpcionesDDLItems() {
        return this.RepoItems.getTodos()
                .stream()
                .map(Item::toDDL)
                .toList();
    }

    public List<DDLItemDTO> getOpcionesDDLRubros() {
        return this.RepoRubros.getTodos()
                .stream()
                .map(Rubro::toDDL)
                .toList();

    }

    public List<DDLItemDTO> getOpcionesDDLRubros(String descripcion) {
        return this.RepoItems.getTodos()
                .stream()
                .filter(p -> p.getPertenece().getNombre().equals(descripcion))
                .map(Item::toDDL)
                .toList();

    }

    public List<DDlProveedorItemDTO> getProveedorItemByItem(String descripcion){
        return this.RepoProveedorItem.getTodos()
                .stream()
                .filter(p -> p.getItem().getNombre().equals(descripcion))
                .map(ProveedorItem::toDDL)
                .toList();
    }

    public static ControladorItem getInstancia() {
        if (instancia == null) {
            instancia = new ControladorItem();
        }

        return instancia;
    }


    public float consultarIva(int idItem) {
        // TODO implement here
        return 0.0f;
    }

}