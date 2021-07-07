package models.proveedor;

import dal.Identificable;
import models.dtos.*;
import models.impuesto.EnumResponsableIva;
import models.impuesto.ResponsableIva;

import java.time.LocalDate;

public class Proveedor implements DDLeable, Identificable {
    // TODO agregar a diagrama clases
    private int ID;

    private String cuit;

    private String razonSocial;

    private String nombre;

    private String direccion;
    private String telefono;

    private String email; //TODO puede ser nuleable ? verificar

    private String numeroIIBB;

    private LocalDate inicioActividades;

    private String rubros;

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    private String responsableIva;

    //private List<Factura> facturas;

    //private List<CertificadoExento> certificados;

    //private List<Factura> Factura;

    //private Float credito;

    public Proveedor(String nombre) {
        this.nombre = nombre;
    }

    public Proveedor(String nombre, String cuit) {
        this.nombre = nombre;
        this.cuit = cuit;
    }

    public Proveedor( String nombre, String direccion, String email, String telefono,
                      String numeroIIBB, LocalDate inicioActividades, String rubros,
                      String razonSocial, String cuit, String responsableIva, int idProv)
            //  Float credito,)
    {
        setNombre(nombre); setDireccion(direccion); setEmail(email); setTelefono(telefono);
        setID(idProv);setInicioActividades(inicioActividades);setNumeroIIBB(numeroIIBB);
        setRubros(rubros);setRazonSocial(razonSocial);setCuit(cuit);setResponsableIva(responsableIva);
        //setCredito(credito);
    }

    public Proveedor( String nombre, String direccion, String email, String telefono,
                      String numeroIIBB, LocalDate inicioActividades, String rubros,
                      String razonSocial, String cuit, String responsableIva)
    {
        setNombre(nombre); setDireccion(direccion); setEmail(email); setTelefono(telefono);
        setID(ID);setInicioActividades(inicioActividades);setNumeroIIBB(numeroIIBB);
        setRubros(rubros);setRazonSocial(razonSocial);setCuit(cuit);setResponsableIva(responsableIva);
        //setCredito(credito);
    }
    public int getID() {
        return ID;
    }

    public String getRubros() {
        return rubros;
    }

    public void setRubros(String rubros) {
        this.rubros = rubros;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

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

    public String getResponsableIva() {
        return responsableIva;
    }

    public void setResponsableIva(String responsableIva) {
        this.responsableIva = responsableIva;
    }

    // public Float getCredito() {
    //    return credito;
    //}

    //   public void setCredito(Float credito) {
    //    this.credito = credito;
    //   }


    @Override
    public String toString() {
        return nombre + " - " + cuit;
    }

    @Override
    public DDLItemDTO toDDL() {
        var desc = toString();
        return new DDLItemDTO() {
            {
                id = ID;
                descripcion = desc;
            }
        };
    }
}