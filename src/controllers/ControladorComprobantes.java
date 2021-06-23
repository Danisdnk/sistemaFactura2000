package controllers;

import models.documento.*;
import models.dtos.ComprobanteDTO;
import models.dtos.DDLItemDTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ControladorComprobantes {
    private static ControladorComprobantes instancia;

    private int indiceFacturas = 1;
    private int indiceNotas = 1;

    private List<Factura> facturas;
    private List<Nota> notas;

    private ControladorComprobantes() {
        var provCont = ControladorProveedor.getInstancia();

        this.facturas = new ArrayList<Factura>();

        var coto = provCont.getProveedorByNombre("Coto");
        var philips = provCont.getProveedorByNombre("Philips");

        this.agregarFactura(new Factura(coto, "0001-00002555", 5000));
        this.agregarFactura(new Factura(coto,"0001-00002556", 2500));
        this.agregarFactura(new Factura(coto,"0001-00002557", 1250));
        this.agregarFactura(new Factura(philips,"0001-00002558", 3000));
        this.agregarFactura(new Factura(philips,"0001-00002590", 4800));

        this.notas = new ArrayList<>();
        this.agregarNota(new Nota(TipoDeNota.DEBITO, coto, "0001-00003000", 1000));
    }

    public void agregarFactura(Factura fac) {
        fac.setID(this.indiceFacturas);
        this.facturas.add(fac);
        this.indiceFacturas++;
    }

    public void agregarNota(Nota nota) {
        nota.setID(this.indiceNotas);
        this.notas.add(nota);
        this.indiceNotas++;
    }

    public void modificarOP(OrdenPago op) {

    }

    public List<Factura> getFacturas() {
        return this.facturas;
    }

    public List<Comprobante> getComprobantesByProveedor(int provID) {
        return Stream.of(this.facturas, this.notas)
                .flatMap(Collection::stream)
                .filter(c -> c.getProveedor().getID() == provID)
                .collect(Collectors.toList());
    }

    public List<ComprobanteDTO> getComprobanteDTOsByProveedor(int provID) {
        return getComprobantesByProveedor(provID)
                .stream()
                .map(Comprobante::toCompDTO)
                .toList();
    }

    public List<DDLItemDTO> getOpcionesDDLFacturaByProveedor(int proveedorID) {
        var list = new ArrayList<DDLItemDTO>();
        for (Factura f : this.facturas) {
            if ( f.getProveedor().getID() == proveedorID ) {
                DDLItemDTO toDDL = f.toDDL();
                list.add(toDDL);
            }
        }
        return list;
    }

    // TODO agregar a diagrama clases
    public Factura getFacturaByID(int facID) {
        return this.facturas
                .stream()
                .filter(fac -> fac.getID() == facID)
                .findFirst()
                .get();
    }

    public static ControladorComprobantes getInstancia() {
        if (instancia == null) {
            instancia = new ControladorComprobantes();
        }

        return instancia;
    }
}
