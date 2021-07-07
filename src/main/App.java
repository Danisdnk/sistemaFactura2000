package main;

import com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme;
import views.login.loginView;

public class App {
    public static void main(String[] args) {
        InicializadorDeDatos.iniciar();

        try {
          //  UIManager.setLookAndFeel(new FlatDarculaLaf()); //Windows Look and feel
            FlatArcDarkOrangeIJTheme.setup();
            //FlatLightFlatIJTheme.setup();
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                //JFrame menuPrincipal = new MenuPrincipal("Factura 2000");//new loginView();
                loginView menuPrincipal = new loginView();
                menuPrincipal.setVisible(true);
            }
        });

    }
}