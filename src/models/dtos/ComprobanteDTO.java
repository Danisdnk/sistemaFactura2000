package models.dtos;

import models.proveedor.Proveedor;

import java.time.LocalDate;

public class ComprobanteDTO {
    public int ID;
    public Proveedor proveedor;
    public LocalDate fecha;
    public String nro;
    public float total;
}
