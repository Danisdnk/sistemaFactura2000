package models.dtos;

public class DDLItemDTO {
    public int id;

    public String descripcion;

    @Override
    public String toString() {
        return this.descripcion;
    }
}
