package models.documento;

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

    public double getMontoTotal() {
        return getComprobantesAsociados().stream().mapToDouble(Comprobante::getMontoTotal).sum();
    }
    public double getMontoIva() {
        return getComprobantesAsociados().stream().mapToDouble(Comprobante::getMontoIva).sum();
    }
    public double getMontoNeto() {
        return getComprobantesAsociados().stream().mapToDouble(Comprobante::getMontoNeto).sum();
    }

    public double getMontoRetencionIVA(){

        float retencioniva = 0;

        for (Comprobante c : getComprobantesAsociados().stream().toList()) {
            if (c.iva >= 21){                   //se aplica un 50% retencion sobre el montoIVA si el iva es mayor igual a 21
                if(0<c.iva && c.iva<21){          //se aplica un 80% retencion sobre el montoIVA si el iva es mayor que 0 y menor q 21
                    retencioniva = (float) (retencioniva + (c.getMontoIva()*0.8));
                }
            }else{
                retencioniva = (float) (retencioniva + (c.getMontoIva()*0.5));
            }
        }
     return retencioniva;
    }

    public double getMontoRetencionIIBB() {     //se aplica un 2% de retencion sobre el monto total
        return ((getComprobantesAsociados().stream().mapToDouble(Comprobante::getMontoTotal).sum())/100)*2;
    }
}
