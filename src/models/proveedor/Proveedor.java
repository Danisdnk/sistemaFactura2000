package models.proveedor;
import models.documento.Factura;
import models.impuesto.ResponsableIva;
import models.impuesto.CertificadoExento;
import java.time.LocalDate;
import java.util.*;

public class Proveedor {

    public Proveedor() {

    }
    private String cuit;

    private String razonSocial;

    private String nombre;

    private String direccion;

    private String email; //TODO puede ser nuleable ? verificar

    private String numeroIIBB;

    private LocalDate inicioActividades;

    private List<Rubro> rubros;

    private ResponsableIva responsableIva;

    private List<Factura> facturas;

    private List<CertificadoExento> certificados;

    private List<Factura> Factura;

    private Float credito;

   // public ControladorSistema ; //metodo para devolver algo al controlador???












    /**
     * @param cuit
     */
    public void getFacturas(String cuit, Date fecha) {
        // TODO implement here
    }

    /**
     * @param cuit
     */
    public void getFacturas(String cuit) {
        // TODO implement here
    }

    /**
     *
     */
    public void getFactura() {
        // TODO implement here
    }

    /**
     * @param idItem
     * @return
     */
    public float getItemProveedor(int idItem) {
        // TODO implement here
        return 0.0f;
    }

    /**
     * @param cuit
     */
    public void calcularDeudaDelProveedor(String cuit) {
        // TODO implement here
    }

    /**
     *
     */
    public void getDocumentosImpagos() {
        // TODO implement here
    }

    /**
     *
     */
    public void getNotasProveedor() {
        // TODO implement here
    }

    /**
     *
     */
    public void getPagosRealizados() {
        // TODO implement here
    }

    /**
     *
     */
    public void getNombreProveedor() {
        // TODO implement here
    }

    /**
     *
     */
    public void getCuit() {
        // TODO implement here
    }


}