package main;

import controllers.ControladorProveedor;
import dal.RepoFactory;
import models.documento.Factura;
import models.documento.Nota;
import models.documento.TipoDeNota;
import models.mediopago.TipoPago;
import models.proveedor.Proveedor;

public class InicializadorDeDatos {
    public static void iniciar() {
        var repoProveedores = RepoFactory.getRepoProveedores();
        var repoTiposDePago = RepoFactory.getRepoTiposDePago();
        var repoFacturas = RepoFactory.getRepoFacturas();
        var repoNotas = RepoFactory.getRepoNotas();

        //Tipos de pago
        if (repoTiposDePago.getTodos().size() == 0) {
            repoTiposDePago.insertar(new TipoPago("Cheque"));
            repoTiposDePago.insertar(new TipoPago("Efectivo"));
        }

        //Proveedores
        if (repoProveedores.getTodos().size() == 0) {
            repoProveedores.insertar(new Proveedor("Coto"));
            repoProveedores.insertar(new Proveedor("Philips"));
        }

        //Facturas
        if(repoFacturas.getTodos().size() == 0) {
            var coto = ControladorProveedor.getInstancia().getProveedorByNombre("Coto");
            var philips = ControladorProveedor.getInstancia().getProveedorByNombre("Philips");

            repoFacturas.insertar(new Factura(coto, "0001-00002555", 5000));
            repoFacturas.insertar(new Factura(coto,"0001-00002556", 2500));
            repoFacturas.insertar(new Factura(coto,"0001-00002557", 1250));
            repoFacturas.insertar(new Factura(philips,"0001-00002558", 3000));
            repoFacturas.insertar(new Factura(philips,"0001-00002590", 4800));
        }

        //Notas
        if(repoNotas.getTodos().size() == 0) {
            var coto = ControladorProveedor.getInstancia().getProveedorByNombre("Coto");
            //Debito
            repoNotas.insertar(new Nota(TipoDeNota.DEBITO, coto, "0001-00003000", 1000));

            //Credito
        }
    }
}
