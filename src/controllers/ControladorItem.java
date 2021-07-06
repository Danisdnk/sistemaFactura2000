package controllers;

import dal.RepoFactory;
import dal.Repository;
import models.dtos.DDLItemDTO;
import models.dtos.DDlProveedorItemDTO;
import models.proveedor.Item;
import models.proveedor.ProveedorItem;
import models.proveedor.Rubro;


import java.util.List;

/**
 * Este Contolador se opcupa de los Items (Productos y servicios), Rubros y ProveedorItem (Item con precio de un proveedor )
 */
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

    public Item getItemByID(int id) {
        return this.RepoItems.getByID(id);
    }

    /**
     * metodo para traer item  por nombre -> Usado en inicializacion de datos
     * @param nombre
     * @return Item
     */
    public Item getItemByNombre(String nombre) {
        return this.RepoItems.getTodos()
                .stream()
                .filter(p -> p.getNombre().equals(nombre))
                .findFirst()
                .get();
    }

    /**
     * metodo para buscar rubro por el nombre -> Usado en inicializacion de datos
     * @param  nombre
     * @return Rubro
     */
    public Rubro getRubroByNombre(String nombre) {
        return this.RepoRubros.getTodos()
                .stream()
                .filter(p -> p.getNombre().equals(nombre))
                .findFirst()
                .get();
    }

    /**
     * metodo que devuelve una DropDownList de Items, convertida a DDLItemDTO
     * Usado en CompulsaPrecios y SolapaCompras
     * @return List<DDLItemDTO>
     */
    public List<DDLItemDTO> getOpcionesDDLItems() {
        return this.RepoItems.getTodos()
                .stream()
                .map(Item::toDDL)
                .toList();
    }

    /**
     * metodo que devuelve una DropDownList de Items segun un Rubro, convertida a DDLItemDTO
     * Usado en CompulsaPrecios y SolapaCompras
     * @param rubroID
     * @return List<DDLItemDTO>
     */
    public List<DDLItemDTO> getOpcionesDDLItems(int rubroID) {
        return this.RepoItems.getTodos()
                .stream()
                .filter(p -> p.getPertenece().getID() ==  rubroID)
                .map(Item::toDDL)
                .toList();
    }

    /**
     * metodo que devuelve una DropDownList de item de un proveedor segun un cuit
     * @param cuit
     * @return List<DDLItemDTO>
     */
    public List<DDlProveedorItemDTO> getOpcionesDDLItemsByProveedor(String cuit) {
        return this.RepoProveedorItem.getTodos()
                .stream()
                .filter(p -> p.getProveedor().getCuit().equals(cuit))
                .map(ProveedorItem::toDDL)
                .toList();
    }



    /**
     * metodo que devuelve una DropDownList de Rubros, convertida a DDLItemDTO
     * Usado en CompulsaPrecios
     * @return List<DDLItemDTO>
     */

    public List<DDLItemDTO> getOpcionesDDLRubros() {
        return this.RepoRubros.getTodos()
                .stream()
                .map(Rubro::toDDL)
                .toList();

    }

    /**
     * metodo que devuelve una lista de ProveedorItems segun un ItemID (sirve para obtener el precio de un item de todos los proveedores)
     * Usada en CompulsaPrecios
     * @param itemID
     * @return List<DDlProveedorItemDTO>
     */
    public List<DDlProveedorItemDTO> getProveedorItemsByItem(int itemID){
        return this.RepoProveedorItem.getTodos()
                .stream()
                .filter(p -> p.getItem().getID() == itemID)
                .map(ProveedorItem::toDDL)
                .toList();
    }

    /**
     * metodo que devuelve un ProveedorItem segun un Proveedor (sirve para obtener el precio de un item de un proveedor)
     * @param cuit
     * @return DDlProveedorItemDTO
     */
    public DDlProveedorItemDTO getProveedorItemByProveedor(String cuit, int itemID){
        return this.RepoProveedorItem.getTodos()
                .stream()
                .filter(p -> p.getProveedor().getCuit().equals(cuit) && p.getItem().getID() == itemID)
                .map(ProveedorItem:: toDDL)
                .findFirst()
                .get();

    }

    public static ControladorItem getInstancia() {
        if (instancia == null) {
            instancia = new ControladorItem();
        }

        return instancia;
    }



}