package main;

import com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme;
import views.MenuPrincipal;
import views.login.loginView;
import views.proveedores.provedorView;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        InicializadorDeDatos.iniciar();

        try {
          //  UIManager.setLookAndFeel(new FlatDarculaLaf()); //Windows Look and feel
            FlatArcDarkOrangeIJTheme.setup();
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame menuPrincipal = new MenuPrincipal("Factura 2000");//new loginView();
                menuPrincipal.setVisible(true);
            }
        });

    }
}
