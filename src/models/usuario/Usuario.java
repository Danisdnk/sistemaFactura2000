package models.usuario;

import dal.Identificable;
import models.dtos.*;

public class Usuario implements DDLeable, Identificable {

    public Usuario(String nombre, String pass) {

        this.nombreUsuario = nombre;
        this.password = pass;

    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        return new DDLItemDTO() {
            {

                nombreUsuario = nombreUsuario;
            }
        };
    }
    }
