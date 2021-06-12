package controllers;

import models.documento.Factura;
import models.documento.Nota;

import java.util.*;

/**
 * 
 */
public class ControladorSistema {

    /**
     * Default constructor
     */
    public ControladorSistema() {
    }
   /**
     * 
     */
    public Set<Nota> conoce;

    /**
     * 
     */
    //public Set<Usuarios> conoce;

    /**
     * @param fecha 
     * @param cuit 
     * @return
     */
    public List<Factura> TotalFacturasPorDiaYProveedor(Date fecha, String cuit) {
        // TODO implement here
        return null;
    }

    /**
     * @param fecha 
     * @return
     */
    public List<Factura> TotalFacturasPorDia(Date fecha) {
        // TODO implement here
        return null;
    }

    /**
     * @param cuit 
     * @return
     */
    public List<Factura> TotalFacturasPorProveedor(String cuit) {
        // TODO implement here
        return null;
    }

    /**
     * @param idItem
     */
    public void compulsaDePrecios(int idItem) {
        // TODO implement here
    }

    /**
     * 
     */
    public void listarOrdenesDePagoEmitidas() {
        // TODO implement here
    }

    /**
     * 
     */
    public void listarTotalImpuestosRetenidos() {
        // TODO implement here
    }

    /**
     * @param cuit
     */
    public void calcularTotalDeudaPorProveedor(String cuit) {
        // TODO implement here
    }

    /**
     * 
     */
    public void calcularCuentaCorrienteDeProveedores() {
        // TODO implement here
    }

    /**
     * 
     */
    public void ConsultaDeLibroIVA() {
        // TODO implement here
        // TODO implement here
        //todo la clase deberia llamarse en singular
        // los package en java siempre se escriben en minuscula
        // camelCase snake_case
        //organizar mas en package para que quede mas ordenados ej para tipo de documentos
        //medios de pago
        //main package aparte de los controladores
        //excluir .idea en el git ignore , tampoco carpeta auth , no subir archivos .iml
    }

}