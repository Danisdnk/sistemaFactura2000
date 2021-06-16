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



// PROVEEDORES CONTROLER - DAR DE ALTA , MODIFICAR, LISTAR Prov del sistema, BORRAR

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroIIBB() {
        return numeroIIBB;
    }

    public void setNumeroIIBB(String numeroIIBB) {
        this.numeroIIBB = numeroIIBB;
    }

    public LocalDate getInicioActividades() {
        return inicioActividades;
    }

    public void setInicioActividades(LocalDate inicioActividades) {
        this.inicioActividades = inicioActividades;
    }

    public ResponsableIva getResponsableIva() {
        return responsableIva;
    }

    public void setResponsableIva(ResponsableIva responsableIva) {
        this.responsableIva = responsableIva;
    }

    public Float getCredito() {
        return credito;
    }

    public void setCredito(Float credito) {
        this.credito = credito;
    }

    /**
     * @param cuit
     */


    /**
     * @param cuit
     */


    /**
     * @param idItem
     * @return
     */


    /**
     * @param cuit
     */
    public void calcularDeudaDelProveedor(String cuit) {
        // TODO implement here
    }





}