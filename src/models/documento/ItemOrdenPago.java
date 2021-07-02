package models.documento;

import models.documento.Comprobante;
import models.dtos.DDLItemDTO;
import models.mediopago.TipoPago;

import java.util.List;

public class ItemOrdenPago {
    private TipoPago tipoDePago;

    private List<Comprobante> comprobantesAsociados;

    public ItemOrdenPago(TipoPago tipoDePago, List<Comprobante> comprobantesAsociados) {
        this.tipoDePago = tipoDePago;
        this.comprobantesAsociados = comprobantesAsociados;
    }

    public TipoPago getTipoDePago() {
        return tipoDePago;
    }

    public List<Comprobante> getComprobantesAsociados() {
        return comprobantesAsociados;
    }

    public double getTotal() {
        return getComprobantesAsociados().stream().mapToDouble(Comprobante::getTotal).sum();
    }
}
