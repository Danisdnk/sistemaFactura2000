package controllers;

import dal.RepoFactory;
import dal.Repository;
import models.documento.*;
import models.dtos.ComprobanteDTO;
import models.dtos.DDLItemDTO;
import models.impuesto.IVA;

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
    private Repository<IVA> repoIVA;

    private ControladorComprobantes() {
        this.repoFacturas = RepoFactory.getRepoFacturas();
        this.repoNotas = RepoFactory.getRepoNotas();
    }
    public void agregarFactura(Factura f) {
        this.repoFacturas.insertar(f);
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
     * metodo para traer(facturas y notas) Comprobantes por CUIT -> CG calculo deuda
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

    /**
     * Funcion que calcula la deuda de un proveedor agarrando todas los Comprobantes de un proveedor
     * por cuit (facturas/notas credito y debito)  y tambien agarra todas las ordenes de pago por cuit
     * y lace las sumatorias de ambos grupos y las resta entre si
     * @param cuit
     * @return
     */
    public float calcularDeudaDeProveedorByCuit(String cuit) {
        var ordenesDePago = ControladorOrdenesDePagos.getInstancia().getOPsByCuit(cuit);
        var comprobantes = getComprobantesByCuit(cuit);

        float sumaCompro = 0;
        float sumaOPs = 0;
        for (Comprobante comprobante : comprobantes)
            sumaCompro = sumaCompro  + comprobante.getMontoTotal();

        for (OrdenPago op : ordenesDePago)
            sumaOPs = sumaOPs  + op.getMontoTotal();

        return  sumaCompro - sumaOPs;

    }

    /**
     * Metodo para traer notas de credito por cuit
     * @param cuit
     * @return List<Nota>
     */
    public List<Nota> getNCreditoByCuit(String cuit){
        return this.repoNotas.getTodos()
                .stream()
                .filter(n -> n.tipo().equals("NOTA CREDITO") && n.getProveedor().getCuit().equals(cuit))
                .toList();
    }

    /**
     * Metodo para traer notas de credito por cuit
     * @param cuit
     * @return List<Nota>
     */
    public List<Nota> getNDebitoByCuit(String cuit){
        return this.repoNotas.getTodos()
                .stream()
                .filter(n -> n.tipo().equals("NOTA DEBITO") && n.getProveedor().getCuit().equals(cuit))
                .toList();
    }



    public static ControladorComprobantes getInstancia() {
        if (instancia == null) {
            instancia = new ControladorComprobantes();
        }

        return instancia;
    }
}
