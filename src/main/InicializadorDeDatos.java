package main;

import controllers.ControladorComprobantes;
import controllers.ControladorItem;
import controllers.ControladorProveedor;
import dal.RepoFactory;
import models.documento.*;
import models.impuesto.EnumResponsableIva;
import models.impuesto.ResponsableIva;
import models.impuesto.IVA;
import models.mediopago.TipoPago;
import models.proveedor.*;
import java.time.LocalDate;


public class InicializadorDeDatos {
    public static void iniciar() {
        var repoProveedores = RepoFactory.getRepoProveedores();
        var repoTiposDePago = RepoFactory.getRepoTiposDePago();
        var repoOrdenesDePago = RepoFactory.getRepoOrdenesPago();
        var repoFacturas = RepoFactory.getRepoFacturas();
        var repoNotas = RepoFactory.getRepoNotas();
        var repoItems = RepoFactory.getRepoItems();
        var repoRubros = RepoFactory.getRepoRubros();
        var repoProveedorItem = RepoFactory.getProveedorItem();
        var repoResponsableIva=RepoFactory.getResponsableIva();
        var repoIVA = RepoFactory.getIVA();
        var repoOrdenCompra = RepoFactory.getRepoOrdenCompra();


        //Tipos de pago
        if (repoTiposDePago.getTodos().size() == 0) {
            repoTiposDePago.insertar(new TipoPago("Cheque"));
            repoTiposDePago.insertar(new TipoPago("Efectivo"));
        }

        //
        if(repoIVA.getTodos().size() == 0){
            repoIVA.insertar(new IVA(2.5F));
            repoIVA.insertar(new IVA(5F));
            repoIVA.insertar(new IVA(10.5F));
            repoIVA.insertar(new IVA(21F));
            repoIVA.insertar(new IVA(27F));

        }

        //Proveedores
        if (repoProveedores.getTodos().size() == 0) {
            repoProveedores.insertar(new Proveedor("Coto", "20"));
            repoProveedores.insertar(new Proveedor("Philips", "21"));
        }

        if (repoResponsableIva.getTodos().size() == 0) {
            var philips = ControladorProveedor.getInstancia();

            repoResponsableIva.insertar(new ResponsableIva(EnumResponsableIva.MONOTRIBUTO.toString()));
            repoResponsableIva.insertar(new ResponsableIva(EnumResponsableIva.RESPONSABLE_INSCRIPTO.toString()));

        }


        // ---- NO MODIFICAR LOS NROS DE IDENTIFICACION DE LOS COMPROBANTES ----
        //Facturas
        if(repoFacturas.getTodos().size() == 0) {
            var coto = ControladorProveedor.getInstancia().getProveedorByNombre("Coto");
            var philips = ControladorProveedor.getInstancia().getProveedorByNombre("Philips");

            repoFacturas.insertar(new Factura(coto, "0001-00002555", 5000, 21F, LocalDate.parse("2020-01-01")));
            repoFacturas.insertar(new Factura(coto,"0001-00002556", 2500F, 10.5F ,LocalDate.parse("2020-01-01")));
            repoFacturas.insertar(new Factura(coto,"0001-00002557", 1250F ,0F ,LocalDate.parse("2020-01-02")));

            repoFacturas.insertar(new Factura(philips,"0001-00002558", 3000F,21F ,LocalDate.parse("2020-01-03")));
            repoFacturas.insertar(new Factura(philips,"0001-00002590", 4800F, 27F,LocalDate.parse("2020-01-04")));
        }

        //Notas
        if(repoNotas.getTodos().size() == 0) {
            var coto = ControladorProveedor.getInstancia().getProveedorByNombre("Coto");
            var philips = ControladorProveedor.getInstancia().getProveedorByNombre("Philips");

            //Debito
            repoNotas.insertar(new Nota(TipoDeNota.DEBITO, coto, "0001-00003000", 1000F, 21F,LocalDate.parse("2020-01-02")));
            repoNotas.insertar(new Nota(TipoDeNota.DEBITO, coto, "0001-00003010", 4500F, 10.5F, LocalDate.parse("2020-01-02")));
            repoNotas.insertar(new Nota(TipoDeNota.DEBITO, coto, "0001-00003030", 2000F, 0F ,LocalDate.parse("2020-01-02")));
            repoNotas.insertar(new Nota(TipoDeNota.DEBITO, coto, "0001-00003099", 1350F, 5F,LocalDate.parse("2020-01-02")));

            repoNotas.insertar(new Nota(TipoDeNota.DEBITO, philips, "0001-00008888", 2000F, 10.5F,LocalDate.parse("2020-01-04")));
            repoNotas.insertar(new Nota(TipoDeNota.DEBITO, philips, "0001-00008750", 3000F, 27F,LocalDate.parse("2020-01-04")));
            repoNotas.insertar(new Nota(TipoDeNota.DEBITO, philips, "0001-00008890", 2000F, 2.5F,LocalDate.parse("2020-01-04")));
            repoNotas.insertar(new Nota(TipoDeNota.DEBITO, philips, "0001-00009940", 4000F, 21F ,LocalDate.parse("2020-01-04")));

            //Credito
            repoNotas.insertar(new Nota(TipoDeNota.CREDITO, coto, "0001-00003664", 2000, 0F,LocalDate.parse("2020-01-02")));
            repoNotas.insertar(new Nota(TipoDeNota.CREDITO, coto, "0001-00003555", 1350, 27F,LocalDate.parse("2020-01-02")));

            repoNotas.insertar(new Nota(TipoDeNota.CREDITO, philips, "0001-00008354", 2000, 21F ,LocalDate.parse("2020-01-02")));
            repoNotas.insertar(new Nota(TipoDeNota.CREDITO, philips, "0001-00009159", 1350, 10.5F ,LocalDate.parse("2020-01-02")));
        }

        //Ordenes de Pago
        if(repoOrdenesDePago.getTodos().size() == 0) {
            var cheque = repoTiposDePago.getByID(1);
            var efectivo = repoTiposDePago.getByID(2);

            var contComp = ControladorComprobantes.getInstancia();

            // ---- COTO ----
            var coto = ControladorProveedor.getInstancia().getProveedorByNombre("Coto");

            // orden de pago coto.
            var op1 = new OrdenPago(coto);
            var op1Cheque = new ItemOrdenPago(cheque);
            var op1Efectivo = new ItemOrdenPago(efectivo);

            // FAC + ND
            var comprobantesChequeOp1 = new String[]{ "0001-00002555", "0001-00003000" };

            // ND + NC
            var comprobantesEfectivoOp1 = new String[]{ "0001-00003010", "0001-00003555" };

            for (var comp:comprobantesChequeOp1) {
                var comprobante = contComp.getComprobanteByProveedorYNro(coto.getID(), comp);
                op1Cheque.addComprobante(comprobante);
            }

            for (var comp:comprobantesEfectivoOp1) {
                var comprobante = contComp.getComprobanteByProveedorYNro(coto.getID(), comp);
                op1Efectivo.addComprobante(comprobante);
            }

            op1.setFecha(LocalDate.now().plusDays(15));
            op1.addItem(op1Cheque);
            op1.addItem(op1Efectivo);

            repoOrdenesDePago.insertar(op1);
        }

        //Rubros
        if(repoRubros.getTodos().size() == 0){

            repoRubros.insertar(new Rubro("Alimentos"));
            repoRubros.insertar(new Rubro("Computacion"));
            repoRubros.insertar(new Rubro("Electrodomesticos"));
            repoRubros.insertar(new Rubro("Materias Primas"));
        }

        //Items
        if(repoItems.getTodos().size() == 0){
            var alimentos = ControladorItem.getInstancia().getRubroByNombre("Alimentos");
            var computacion = ControladorItem.getInstancia().getRubroByNombre("Computacion");
            var electrodomesticos = ControladorItem.getInstancia().getRubroByNombre("Electrodomesticos");
            var materiasPrimas = ControladorItem.getInstancia().getRubroByNombre("Materias Primas");



            repoItems.insertar(new Item("Acero", Unidad.PESO,TipoItem.PRODUCTO, 10.5D, materiasPrimas));
            repoItems.insertar(new Item("Cobre", Unidad.PESO,TipoItem.PRODUCTO, 10.5D, materiasPrimas));
            repoItems.insertar(new Item("Hierro", Unidad.PESO,TipoItem.PRODUCTO, 10.5D, materiasPrimas));
            repoItems.insertar(new Item("Madera", Unidad.PESO, TipoItem.PRODUCTO,  10.5D,materiasPrimas));
            repoItems.insertar(new Item("Oro", Unidad.PESO,TipoItem.PRODUCTO, 10.5D, materiasPrimas));

            repoItems.insertar(new Item("Carne", Unidad.PESO,TipoItem.PRODUCTO, 10.5D, alimentos));
            repoItems.insertar(new Item("Pollo", Unidad.PESO,TipoItem.PRODUCTO, 10.5D, alimentos));
            repoItems.insertar(new Item("Arroz", Unidad.PESO,TipoItem.PRODUCTO, 10.5D, alimentos));
            repoItems.insertar(new Item("Asado", Unidad.PESO,TipoItem.PRODUCTO, 10.5D, alimentos));

            repoItems.insertar(new Item("Computadora", Unidad.UNIDAD,TipoItem.PRODUCTO, 21D, computacion));
            repoItems.insertar(new Item("Laptop", Unidad.UNIDAD,TipoItem.PRODUCTO, 21D, computacion));
            repoItems.insertar(new Item("Monitor", Unidad.UNIDAD,TipoItem.PRODUCTO, 21D, computacion));
            repoItems.insertar(new Item("Router", Unidad.UNIDAD,TipoItem.PRODUCTO, 21D, computacion));

            repoItems.insertar(new Item("Aire Acondicionado", Unidad.UNIDAD,TipoItem.PRODUCTO, 27D, electrodomesticos));
            repoItems.insertar(new Item("Heladera", Unidad.UNIDAD,TipoItem.PRODUCTO, 27D, electrodomesticos));
            repoItems.insertar(new Item("Lavaropa", Unidad.UNIDAD,TipoItem.PRODUCTO, 27D, electrodomesticos));
            repoItems.insertar(new Item("Microondas", Unidad.UNIDAD,TipoItem.PRODUCTO, 27D, electrodomesticos));








        }

        //ProvedoorItems
        if(repoProveedorItem.getTodos().size() == 0){

            var acero = ControladorItem.getInstancia().getItemByNombre("Acero");
            var cobre = ControladorItem.getInstancia().getItemByNombre("Cobre");
            var hierro = ControladorItem.getInstancia().getItemByNombre("Hierro");
            var madera = ControladorItem.getInstancia().getItemByNombre("Madera");
            var oro = ControladorItem.getInstancia().getItemByNombre("Oro");

            var coto = ControladorProveedor.getInstancia().getProveedorByNombre("Coto");
            var philips = ControladorProveedor.getInstancia().getProveedorByNombre("Philips");


            repoProveedorItem.insertar(new ProveedorItem(2000D, acero, coto));
            repoProveedorItem.insertar(new ProveedorItem(700D, cobre, coto));
            repoProveedorItem.insertar(new ProveedorItem(1000D, hierro, coto));
            repoProveedorItem.insertar(new ProveedorItem(500D, madera, coto));
            repoProveedorItem.insertar(new ProveedorItem(50000D, oro, coto));

            repoProveedorItem.insertar(new ProveedorItem(2200D,acero,philips));
            repoProveedorItem.insertar(new ProveedorItem(900D,cobre,philips));
            repoProveedorItem.insertar(new ProveedorItem(1300D,hierro,philips));
            repoProveedorItem.insertar(new ProveedorItem(700D,madera,philips));
            repoProveedorItem.insertar(new ProveedorItem(40000D,oro,philips));

        }
        if(repoOrdenCompra.getTodos().size() == 0){

        }








    }
}
