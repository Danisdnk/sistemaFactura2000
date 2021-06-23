package controllers;

import dal.RepoFactory;
import dal.Repository;
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

    private int indiceComprobantes = 1;

    private Repository<Factura> repoFacturas;
    private Repository<Nota> repoNotas;

    private ControladorComprobantes() {
        var provCont = ControladorProveedor.getInstancia();

        this.repoFacturas = RepoFactory.getRepoFacturas();
        this.repoNotas = RepoFactory.getRepoNotas();

        var coto = provCont.getProveedorByNombre("Coto");
        var philips = provCont.getProveedorByNombre("Philips");

        this.agregarFactura(new Factura(coto, "0001-00002555", 5000));
        this.agregarFactura(new Factura(coto,"0001-00002556", 2500));
        this.agregarFactura(new Factura(coto,"0001-00002557", 1250));
        this.agregarFactura(new Factura(philips,"0001-00002558", 3000));
        this.agregarFactura(new Factura(philips,"0001-00002590", 4800));

        this.agregarNota(new Nota(TipoDeNota.DEBITO, coto, "0001-00003000", 1000));
    }

    public void agregarFactura(Factura fac) {
        this.repoFacturas.crear(fac);
    }

    public void agregarNota(Nota nota) {
        this.repoNotas.crear(nota);
    }

    public void modificarOP(OrdenPago op) {

    }

    public List<Comprobante> getComprobantesByProveedor(int provID) {
        return Stream.of(this.repoFacturas.getTodos(), this.repoNotas.getTodos())
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
        for (Factura f : this.repoFacturas.getTodos()) {
            if ( f.getProveedor().getID() == proveedorID ) {
                DDLItemDTO toDDL = f.toDDL();
                list.add(toDDL);
            }
        }
        return list;
    }

    // TODO agregar a diagrama clases
    public Factura getFacturaByID(int facID) {
        return this.repoFacturas.getTodos()
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
