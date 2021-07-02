package controllers;

import dal.RepoFactory;
import dal.Repository;
import models.documento.*;
import models.dtos.ComprobanteDTO;
import models.dtos.DDLItemDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ControladorComprobantes {
    private static ControladorComprobantes instancia;

    private Repository<Factura> repoFacturas;
    private Repository<Nota> repoNotas;

    private ControladorComprobantes() {
        this.repoFacturas = RepoFactory.getRepoFacturas();
        this.repoNotas = RepoFactory.getRepoNotas();
    }

    public List<Comprobante> getComprobantesByProveedor(int provID) {
        return Stream.of(this.repoFacturas.getTodos(), this.repoNotas.getTodos())
                .flatMap(Collection::stream)
                .filter(c -> c.getProveedor().getID() == provID)
                .collect(Collectors.toList());
    }

    public List<Comprobante> getComprobantesByProveedorYNro(int provID, String nro) {
        return Stream.of(this.repoFacturas.getTodos(), this.repoNotas.getTodos())
                .flatMap(Collection::stream)
                .filter(c -> c.getProveedor().getID() == provID && c.getNro() == nro)
                .collect(Collectors.toList());
    }

    public Comprobante getComprobanteByProveedorYNro(int provID, String nro) {
        return Stream.of(this.repoFacturas.getTodos(), this.repoNotas.getTodos())
                .flatMap(Collection::stream)
                .filter(c -> c.getProveedor().getID() == provID && c.getNro().equals(nro))
                .findFirst()
                .get();
    }

    public List<Factura> getFacturasByProveedor(String cuit) { //metodo para traer facturas por ID -> facturas recibidas(cuit)
        return Stream.of(this.repoFacturas.getTodos())
                .flatMap(Collection::stream)
                .filter(c -> c.getProveedor().getCuit().equals(cuit) )
                .collect(Collectors.toList());
    }

    public List<Factura> getFacturasByFecha(LocalDate fecha) {  //metodo para traer facturas por fecha -> facturas recibidas(fecha)
        return Stream.of(this.repoFacturas.getTodos())
                .flatMap(Collection::stream)
                .filter(c -> c.getFecha().equals(fecha))
                .collect(Collectors.toList());
    }

    public List<Factura> getFacturasByFechaYProveedor(String cuit, LocalDate fecha) {  //metodo para traer facturas por fecha y proveedor -> facturas recibidas(cuit, fecha)
        return Stream.of(this.repoFacturas.getTodos())
                .flatMap(Collection::stream)
                .filter(c -> c.getProveedor().getCuit().equals(cuit) && c.getFecha().equals(fecha) )
                .collect(Collectors.toList());
    }

    public List<ComprobanteDTO> getComprobanteDTOsByProveedor(int provID) {
        return getComprobantesByProveedor(provID)
                .stream()
                .map(Comprobante::toCompDTO)
                .toList();
    }

    public List<ComprobanteDTO> getFacturasDTOsByProveedor(String cuit) {
        return getFacturasByProveedor(cuit)
                .stream()
                .map(Comprobante::toCompDTO)
                .toList();
    }

    public List<ComprobanteDTO> getFacturasDTOsByFecha(LocalDate fecha) {
        return getFacturasByFecha(fecha)
                .stream()
                .map(Comprobante::toCompDTO)
                .toList();
    }

    public List<ComprobanteDTO> getFacturasDTOByFechaYProveedor(String cuit, LocalDate fecha) {
        return getFacturasByFechaYProveedor(cuit,fecha)
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
        return this.repoFacturas.getByID(facID);
    }

    public static ControladorComprobantes getInstancia() {
        if (instancia == null) {
            instancia = new ControladorComprobantes();
        }

        return instancia;
    }
}
