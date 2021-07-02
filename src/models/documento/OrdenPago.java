package models.documento;

import models.mediopago.TipoPago;
import models.proveedor.Proveedor;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OrdenPago extends Comprobante {
    private List<ItemOrdenPago> items = new ArrayList<>();

    private float totalRetenciones;

    public List<ItemOrdenPago> getItems() {
        return items;
    }

    public void setItems(List<ItemOrdenPago> items) {
        this.items = items;
        this.total = (float)this.items.stream().mapToDouble(ItemOrdenPago::getTotal).sum();
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

    public void setTotalRetenciones(float totalRetenciones) {
        this.totalRetenciones = totalRetenciones;
    }
}