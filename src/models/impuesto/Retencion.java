package models.impuesto;

public class Retencion {

    public Retencion() {

    }


    private int idRetencion;


    private TipoImpuesto tipo;

    private double monto;
    private boolean exento;
    public void getTotalRetencion() {
        // TODO implement here
    }

    public int getIdRetencion() {
        return idRetencion;
    }

    public void setIdRetencion(int idRetencion) {
        this.idRetencion = idRetencion;
    }

    public TipoImpuesto getTipo() {
        return tipo;
    }

    public void setTipo(TipoImpuesto tipo) {
        this.tipo = tipo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public boolean isExento() {
        return exento;
    }

    public void setExento(boolean exento) {
        this.exento = exento;
    }
}