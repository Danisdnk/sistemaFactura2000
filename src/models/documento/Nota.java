package models.documento;
import java.time.LocalDate;
public class Nota {

    /**
     * Default constructor
     */
    public Nota() {
    }


    private TipoDeNota tipoN;


    private int notaID;


    private int facturaID;


    private String cuit;


    private String cuitPropio;


    private LocalDate fecha;


    private float saldo;


    public TipoDeNota getTipoN() {
        return tipoN;
    }

    public void setTipoN(TipoDeNota tipoN) {
        this.tipoN = tipoN;
    }

    public int getNotaID() {
        return notaID;
    }

    public void setNotaID(int notaID) {
        this.notaID = notaID;
    }

    public int getFacturaID() {
        return facturaID;
    }

    public void setFacturaID(int facturaID) {
        this.facturaID = facturaID;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getCuitPropio() {
        return cuitPropio;
    }

    public void setCuitPropio(String cuitPropio) {
        this.cuitPropio = cuitPropio;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
}