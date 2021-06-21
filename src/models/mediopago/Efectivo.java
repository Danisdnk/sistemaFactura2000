package models.mediopago;

public class Efectivo extends TipoPago {
    public void getMonto() {
        // TODO implement here
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public void setMonto(Float monto) {
        this.monto = monto;
    }
}