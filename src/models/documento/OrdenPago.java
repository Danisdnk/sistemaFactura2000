package models.documento;

import models.mediopago.TipoPago;
import models.proveedor.Proveedor;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OrdenPago extends Comprobante {
    // TODO renombrar a diagrama clases
    private List<Factura> facturas;

    private List<Nota> notas;

    // TODO cambio de id a TipoPago a diagrama clases
    private TipoPago formaPago;

    private float totalRetenciones;

    // TODO agregar a diagrama clases
    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Factura getFactura() {
        return this.facturas.stream().findFirst().get();
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }

    // TODO agregar a diagrama clases
    public void setComprobantesAsociados(List<Comprobante> comprobantes) {

    }

    public List<Comprobante> getComprobantesAsociados() {
        return Stream.of(this.facturas, this.notas)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public void comprobarMedioPago() {
        // TODO implement here
    }

    public void getMontoyVerificarPago() {
        // TODO implement here
    }

    // TODO agregar a diagrama clases
    public TipoPago getFormaPago() {
        return this.formaPago;
    }

    // TODO agregar a diagrama clases
    public void setFormaPago(TipoPago pago) {
        this.formaPago = pago;
    }

    public float getTotalRetenciones() {
        return totalRetenciones;
    }

    public void setTotalRetenciones(float totalRetenciones) {
        this.totalRetenciones = totalRetenciones;
    }
}