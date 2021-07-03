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

    public List<Comprobante> getComprobantesNoPagosByProveedor(int provID) {
        //comprobantes pagos son aquellos asociados a ordenes de pago
        var ordenesDePago = ControladorOrdenesDePagos.getInstancia().getOrdenesDePagoByProveedor(provID);
        var comprobantesPagos = ordenesDePago
                .stream()
                .flatMap(x -> x.getItems().stream())
                .flatMap(x -> x.getComprobantesAsociados().stream())
                .toList();

        return Stream.of(this.repoFacturas.getTodos(), this.repoNotas.getTodos())
                .flatMap(Collection::stream)
                .filter(c ->
                        provID == c.getProveedor().getID() &&
                        !comprobantesPagos.stream().anyMatch(x -> x.getID() == c.getID()))
                .collect(Collectors.toList());
    }

    public Comprobante getComprobanteByProveedorYNro(int provID, String nro) {
        return Stream.of(this.repoFacturas.getTodos(), this.repoNotas.getTodos())
                .flatMap(Collection::stream)
                .filter(c -> c.getProveedor().getID() == provID && c.getNro().equals(nro))
                .findFirst()
                .get();
    }


    /**
     * metodo para traer facturas por CUIT -> Usado en CG facturas recibidas(cuit)
     * @param cuit
     * @return List<Factura>
     */
    public List<Factura> getFacturasByProveedor(String cuit) {
        return Stream.of(this.repoFacturas.getTodos())
                .flatMap(Collection::stream)
                .filter(c -> c.getProveedor().getCuit().equals(cuit) )
                .collect(Collectors.toList());
    }

    /**
     * //metodo para traer facturas por fecha -> Usado en CG facturas recibidas(fecha)
     * @param fecha
     * @return List<Factura>
     */
    public List<Factura> getFacturasByFecha(LocalDate fecha) {
        return Stream.of(this.repoFacturas.getTodos())
                .flatMap(Collection::stream)
                .filter(c -> c.getFecha().equals(fecha))
                .collect(Collectors.toList());
    }

    /**
     * metodo para traer facturas por fecha y proveedor -> CG facturas recibidas(cuit, fecha)
     * @param cuit
     * @param fecha
     * @return List<Factura>
     */
    public List<Factura> getFacturasByFechaYProveedor(String cuit, LocalDate fecha) {
        return Stream.of(this.repoFacturas.getTodos())
                .flatMap(Collection::stream)
                .filter(c -> c.getProveedor().getCuit().equals(cuit) && c.getFecha().equals(fecha) )
                .collect(Collectors.toList());
    }

    /**
     * metodo para traer(facturas y notas) Comprobantes por CUIT -> CG calculo deuda?
     * @param cuit
     * @return List<Factura>
     */
    public List<Comprobante> getComprobantesByCuit(String cuit) {
        return Stream.of(this.repoFacturas.getTodos(), this.repoNotas.getTodos())
                .flatMap(Collection::stream)
                .filter(c -> c.getProveedor().getCuit().equals(cuit) )
                .collect(Collectors.toList());
    }

    public List<ComprobanteDTO> getComprobanteDTOsByProveedor(int provID) {
        return getComprobantesByProveedor(provID)
                .stream()
                .map(Comprobante::toCompDTO)
                .toList();
    }


    /**
     * metodo que devuelve Facturas(cuit) convertidas a ComprobanteDTO
     * @param cuit
     * @return List<ComprobanteDTO>
     */
    public List<ComprobanteDTO> getFacturasDTOsByProveedor(String cuit) {
        return getFacturasByProveedor(cuit)
                .stream()
                .map(Comprobante::toCompDTO)
                .toList();
    }

    /**
     * metodo que devuelve Facturas(Fecha) convertidas a ComprobanteDTO
     * @param fecha
     * @return List<ComprobanteDTO>
     */
    public List<ComprobanteDTO> getFacturasDTOsByFecha(LocalDate fecha) {
        return getFacturasByFecha(fecha)
                .stream()
                .map(Comprobante::toCompDTO)
                .toList();
    }

    /**
     * metodo que devuelve Facturas(CUITyFecha) convertidas a ComprobanteDTO
     * @param cuit
     * @param fecha
     * @return List<ComprobanteDTO>
     */
    public List<ComprobanteDTO> getFacturasDTOByFechaYProveedor(String cuit, LocalDate fecha) {
        return getFacturasByFechaYProveedor(cuit,fecha)
                .stream()
                .map(Comprobante::toCompDTO)
                .toList();
    }

    /**
     * metodo para que devuelve (facturas y notas) Comprobantes  convertidas en ComprobanteDTO -> CG calculo deuda?
     * @param cuit
     * @return List<ComprobanteDTO>
     */
    public List<ComprobanteDTO> getComprobantesByCuitDTO(String cuit) {

        return getComprobantesByCuit(cuit)
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


    /**
     * metodo que devuelve todos los comprobantes convertidos en ComprobantesDTO  (facturas y notas de credito y debito)
     * @return List<ComprobanteDTO>
     */
    public List<ComprobanteDTO> getAllComprobantesDTO() {
        return  getAllComprobantes()
                .stream()
                .map(Comprobante::toCompDTO)
                .toList();
    }

    /**
     * metodo que devuelve todos los comprobantes (facturas y notas de credito y debito)
     * @return
     */
    public List<Comprobante> getAllComprobantes() {
        return Stream.of(this.repoFacturas.getTodos(), this.repoNotas.getTodos())
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public static ControladorComprobantes getInstancia() {
        if (instancia == null) {
            instancia = new ControladorComprobantes();
        }

        return instancia;
    }
}
