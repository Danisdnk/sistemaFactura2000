package models.documento;

import dal.Identificable;
import models.dtos.ComprobanteDTO;
import models.dtos.DDLItemDTO;
import models.dtos.DDLeable;
import models.proveedor.Proveedor;

import java.time.LocalDate;

// TODO agregar a diagrama clases nroFactura
public class Comprobante implements IComprobante, DDLeable, Identificable {
    protected int ID;

    protected Proveedor proveedor;

    protected LocalDate fecha;

    protected String nro;

    protected String tipo;

    protected float montoNeto;
    protected double iva;
    protected float montoIva;
    protected float montoTotal;

    public ComprobanteDTO toCompDTO() {

        return new ComprobanteDTO(
                ID,
                this.tipo(),
                proveedor,
                fecha,
                nro,
                montoNeto,
                iva,
                montoIva,
                montoTotal


        );
    }

    public DDLItemDTO toDDL() {
        var desc = this.toString();
        return new DDLItemDTO() {
            {
                id = ID;
                descripcion = desc;
            }
        };
    }

    public String tipo() {
        return this.tipo;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;

        if (this.nro == null) {
            var stringID = String.valueOf(ID);
            this.nro = "0001-" + "0".repeat(getDigitosNro()-stringID.length()) + stringID;
        }
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getNro() {
        return nro;
    }

    public void setNro(String nro) {
        this.nro = nro;
    }

    public float getMontoNeto() {
        return montoNeto;
    }

    public void setMontoNeto(float montoNeto) {
        this.montoNeto = montoNeto;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public float getMontoIva() {
        return montoIva;
    }

    public void setMontoIva(float montoIva) {
        this.montoIva = montoIva;
    }

    public float getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(float montoTotal) {
        this.montoTotal = montoTotal;
    }

    @Override
    public String toString() {
        return tipo() + " " + nro;
    }

    private int getDigitosNro() {
        //no usamos una constante dado que es serializada por JSON.
        return 8;
    }
}
