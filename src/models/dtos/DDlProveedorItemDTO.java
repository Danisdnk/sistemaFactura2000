package models.dtos;

public class DDlProveedorItemDTO extends DDLItemDTO {

        public int id;

        public  Float precio;
        public String descripcion;


        @Override
        public String toString() {
            return  this.descripcion+(" ")+this.precio;
         }

}
