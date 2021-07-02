package models;

import dal.Identificable;
import models.dtos.DDLItemDTO;
import models.dtos.DDLeable;

public class Usuario implements DDLeable, Identificable {

    public Usuario() {

    }

    private String nombreUsuario;

    private String password;


    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void setID(int id) {

    }

    @Override
    public DDLItemDTO toDDL() {
        return null;
    }
}