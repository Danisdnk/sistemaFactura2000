package main;

import views.MenuPrincipal;
import views.login.loginView;
import views.proveedores.provedorView;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        InicializadorDeDatos.iniciar();

        JFrame menuPrincipal = new MenuPrincipal("Factura 2000");//new loginView();
        menuPrincipal.setVisible(true);
    }
}
