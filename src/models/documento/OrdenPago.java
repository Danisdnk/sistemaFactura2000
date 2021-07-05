package models.documento;

import models.proveedor.Proveedor;

import java.util.ArrayList;
import java.util.List;

public class OrdenPago extends Comprobante {
    private List<ItemOrdenPago> items = new ArrayList<>();

    private float totalRetenciones;
    private float retencionIVA;
    private float retencionIIBB;

    public OrdenPago() {
        this.tipo = "OP";
    }

    public OrdenPago(Proveedor prov) {
        this();
        this.proveedor = prov;
    }

    public List<ItemOrdenPago> getItems() {
        return items;
    }

    public void setItems(List<ItemOrdenPago> items) {
        this.items = items;
        calcularTotal();
    }

    public void addItem(ItemOrdenPago item) {
        this.items.add(item);
        calcularTotal();
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public void comprobarMedioPago() {
        // TODO implement here
    }

    public void getMontoyVerificarPago() {
        // TODO implement here
    }

    public float getTotalRetenciones() {
        return totalRetenciones;
    }

    public void setTotalRetenciones(float retencionIVA, float retencionIIBB) {
        this.totalRetenciones = totalRetenciones + retencionIVA + retencionIIBB;
    }

    private void calcularTotal() {
        this.montoTotal = (float) this.items.stream().mapToDouble(ItemOrdenPago::getMontoTotal).sum();
        this.montoNeto = (float) this.items.stream().mapToDouble(ItemOrdenPago::getMontoNeto).sum();
        this.montoIva = (float) this.items.stream().mapToDouble(ItemOrdenPago::getMontoIva).sum();
        this.retencionIVA = (float) this.items.stream().mapToDouble(ItemOrdenPago::getMontoRetencionIVA).sum();
        this.retencionIIBB = (float) this.items.stream().mapToDouble(ItemOrdenPago::getMontoRetencionIIBB).sum();
        setTotalRetenciones(retencionIVA,retencionIIBB);
    }
    public float getRetencionIIBB() {
        return retencionIIBB;
    }

    public float getRetencionIVA() {
        return retencionIVA;
    }
}