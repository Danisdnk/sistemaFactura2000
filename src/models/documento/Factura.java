package models.documento;

import models.impuesto.Retencion;
import models.proveedor.Proveedor;

import java.util.*;

/**
 * 
 */
public class Factura {

    /**
     * Default constructor
     */
    public Factura() {
    }

    /**
     * 
     */
    private int facturaID;

    /**
     * 
     */
    private Proveedor proveedor;

    /**
     * 
     */
    private List<Retencion> retencion;

    /**
     * 
     */
    private List<ItemFactura> itemFactura;

    /**
     * 
     */
    private Date fecha;

    /**
     * 
     */
    private int monto;

    /**
     * 
     */
    // public Proveedor 1;






    /**
     * @param fecha
     */
    public void devolverFacturaDeUnaFecha(Date fecha) {
        // TODO implement here
    }

    /**
     * 
     */
    public void devolverFacturaDeUnaFecha() {
        // TODO implement here
    }

    /**
     * 
     */
    public void getTotalImpuestosPorFactura() {
        // TODO implement here
    }

    /**
     * 
     */
    public void calcularDeuda() {
        // TODO implement here
    }

    /**
     * 
     */
    public void getSaldoNota() {
        // TODO implement here
    }

    /**
     * 
     */
    public void getfecha() {
        // TODO implement here
    }

    /**
     * 
     */
    public void getImpuestoIVAporItem() {
        // TODO implement here
    }

}