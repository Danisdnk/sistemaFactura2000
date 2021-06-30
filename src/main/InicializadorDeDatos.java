package main;

import controllers.ControladorItem;
import controllers.ControladorProveedor;
import dal.RepoFactory;
import models.documento.Factura;
import models.documento.Nota;
import models.documento.TipoDeNota;
import models.mediopago.TipoPago;
import models.proveedor.*;

public class InicializadorDeDatos {
    public static void iniciar() {
        var repoProveedores = RepoFactory.getRepoProveedores();
        var repoTiposDePago = RepoFactory.getRepoTiposDePago();
        var repoFacturas = RepoFactory.getRepoFacturas();
        var repoNotas = RepoFactory.getRepoNotas();
        var repoItems = RepoFactory.getRepoItems();
        var repoRubros = RepoFactory.getRepoRubros();
        var repoProveedorItem = RepoFactory.getProveedorItem();


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

        //Rubros
        if(repoRubros.getTodos().size() == 0){

            repoRubros.insertar(new Rubro("Materias Prima"));
            repoRubros.insertar(new Rubro("Comida"));
        }

        //Items
        if(repoItems.getTodos().size() == 0){
            var materiasPrimas = ControladorItem.getInstancia().getRubroByNombre("Materias Prima");



            repoItems.insertar(new Item("Madera", Unidad.PESO, TipoItem.PRODUCTO,materiasPrimas));
            repoItems.insertar(new Item("Acero", Unidad.PESO,TipoItem.PRODUCTO,materiasPrimas));
        }

        //ProvedoorItems
        if(repoProveedorItem.getTodos().size() == 0){

            var item = ControladorItem.getInstancia().getItemByNombre("Madera");
            var proveedor1 = ControladorProveedor.getInstancia().getProveedorByNombre("Coto");
            var proveedor2 = ControladorProveedor.getInstancia().getProveedorByNombre("Philips");


            repoProveedorItem.insertar(new ProveedorItem(10F, item, proveedor1) );
            repoProveedorItem.insertar(new ProveedorItem(20F,item,proveedor2));

        }








    }
}
