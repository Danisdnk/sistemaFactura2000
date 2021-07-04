package models.documento;

import models.proveedor.Proveedor;

import java.time.LocalDate;

public interface IComprobante {
    String tipo();

   int getID();

   void setID(int ID);

   Proveedor getProveedor();

   void setProveedor(Proveedor proveedor);

   LocalDate getFecha();

   void setFecha(LocalDate fecha);

   String getNro();

   void setNro(String nro);

   float getMontoTotal();

    void setMontoTotal(float total);

    @Override
    String toString();
}
