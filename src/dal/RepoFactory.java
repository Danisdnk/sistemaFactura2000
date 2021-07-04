package dal;

import models.documento.Factura;
import models.documento.Nota;
import models.documento.OrdenPago;
import models.impuesto.ResponsableIva;
import models.impuesto.IVA;
import models.mediopago.TipoPago;
import models.proveedor.Item;
import models.proveedor.Proveedor;
import models.proveedor.ProveedorItem;
import models.proveedor.Rubro;

public class RepoFactory {
    private static Repository<OrdenPago> ordenPagoRepository;
    private static Repository<TipoPago> tipoPagoRepository;
    private static Repository<Factura> facturaRepository;
    private static Repository<Nota> notaRepository;
    private static Repository<Proveedor> proveedorRepository;
    private static Repository<Item> itemRepository;
    private static Repository<Rubro> rubroRepository;
    private static Repository<ProveedorItem> ProveedorItemRepository;
    private static Repository<IVA> IVARepository;
    private static Repository<ResponsableIva> ResponsableIvaRepository;


    public static Repository<OrdenPago> getRepoOrdenesPago(){
        if (ordenPagoRepository == null) {
            ordenPagoRepository = new Repository<>(OrdenPago.class);
        }

        return  ordenPagoRepository;
    }

    public static Repository<TipoPago> getRepoTiposDePago(){
        if (tipoPagoRepository == null) {
            tipoPagoRepository = new Repository<>(TipoPago.class);
        }

        return tipoPagoRepository;
    }


    public static Repository<Factura> getRepoFacturas(){  return new Repository<>(Factura.class); }

    public static Repository<Nota> getRepoNotas(){  return new Repository<>(Nota.class);  }

    public static Repository<Proveedor> getRepoProveedores(){  return new Repository<>(Proveedor.class); }

    public static Repository<Item> getRepoItems(){  return new Repository<>(Item.class); }

    public static Repository<Rubro> getRepoRubros(){  return new Repository<>(Rubro.class); }

    public static Repository<ProveedorItem> getProveedorItem(){  return new Repository<>(ProveedorItem.class); }
    public static Repository<ResponsableIva> getResponsableIva(){  return new Repository<>(ResponsableIva.class); }

    public static Repository<IVA> getIVA(){  return new Repository<>(IVA.class); }
}
