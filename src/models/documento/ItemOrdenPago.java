package models.documento;

import models.documento.Comprobante;
import models.dtos.DDLItemDTO;
import models.mediopago.TipoPago;

import java.util.ArrayList;
import java.util.List;

public class ItemOrdenPago {
    private TipoPago tipoDePago;

    private List<Comprobante> comprobantesAsociados;

    public ItemOrdenPago(TipoPago tipoDePago) {
        this.tipoDePago = tipoDePago;
        this.comprobantesAsociados = new ArrayList<>();
    }

    public ItemOrdenPago(TipoPago tipoDePago, List<Comprobante> comprobantesAsociados) {
        this.tipoDePago = tipoDePago;
        this.comprobantesAsociados = comprobantesAsociados;
    }

    public void addComprobante(Comprobante comp) {
        this.comprobantesAsociados.add(comp);
    }

    public TipoPago getTipoDePago() {
        return tipoDePago;
    }

    public List<Comprobante> getComprobantesAsociados() {
        return comprobantesAsociados;
    }

    public double getTotal() {
        return getComprobantesAsociados().stream().mapToDouble(Comprobante::getMontoTotal).sum();
    }
}
