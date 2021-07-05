package models.dtos;

/**
 * DTO que tiene un precio, alguna descripcion y un tipo (producto/servicio)
 * Usado para devolver un producto  con precio y proveedor
 */
public class DDlProveedorItemDTO extends DDLItemDTO {

        public int id;

        public Double precio;
        public String producto;
        public String proveedor;
        public String tipo;


        @Override
        public String toString() {
            return  this.producto;
         }

}
