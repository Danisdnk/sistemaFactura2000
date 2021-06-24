package dal;

import models.documento.Factura;
import models.documento.Nota;
import models.documento.OrdenPago;
import models.mediopago.TipoPago;
import models.proveedor.Proveedor;

public class RepoFactory {
    private static Repository<OrdenPago> ordenPagoRepository;
    private static Repository<TipoPago> tipoPagoRepository;
    private static Repository<Factura> facturaRepository;
    private static Repository<Nota> notaRepository;
    private static Repository<Proveedor> proveedorRepository;

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
}
