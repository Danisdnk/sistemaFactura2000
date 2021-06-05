package main;

import views.MenuPrincipal;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        JFrame menuPrincipal = new MenuPrincipal("Factura 2000");
        menuPrincipal.setVisible(true);

        System.out.println("I'm alive");
    }
}
