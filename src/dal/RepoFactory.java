package dal;

import models.documento.Factura;
import models.documento.Nota;
import models.documento.OrdenPago;
import models.mediopago.TipoPago;
import models.proveedor.Proveedor;

public class RepoFactory {
    public static Repository<OrdenPago> getRepoOrdenesPago(){  return new Repository<>(OrdenPago.class);  }

    public static Repository<TipoPago> getRepoTiposDePago(){  return new Repository<>(TipoPago.class);  }

    public static Repository<Factura> getRepoFacturas(){  return new Repository<>(Factura.class); }
    public static Repository<Nota> getRepoNotas(){  return new Repository<>(Nota.class);  }
    public static Repository<Proveedor> getRepoProveedores(){  return new Repository<>(Proveedor.class); }
}
