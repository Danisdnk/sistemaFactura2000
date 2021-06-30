package models.dtos;

public class DDlProveedorItemDTO extends DDLItemDTO {

        public int id;

        public  Float precio;
        public String descripcion;


        @Override
        public String toString() {
            return  ("El Proveedor: ")+this.descripcion+(" lo vende a $")+this.precio;
         }

}
