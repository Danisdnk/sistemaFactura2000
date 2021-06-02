package models.proveedor;
import models.impuesto.CertificadoExento;
import models.impuesto.ResponsableIva;

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

    private List<CertificadoExento> certificados;

    private List<models.documento.Factura> Factura;

    private Float credito;

   // public ControladorSistema ; //metodo para devolver algo al controlador???












    /**
     * @param cuit
     */
    public void getCuit(String cuit) {
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

}