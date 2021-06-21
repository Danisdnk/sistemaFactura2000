package main;

import views.MenuPrincipal;
import views.login.loginView;
import views.proveedores.provedorView;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        JFrame menuPrincipal = new loginView();
        menuPrincipal.setVisible(true);

        System.out.println("I'm alive");
    }
}
